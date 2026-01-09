package org.example.rpgfinal.model.combat;

import org.example.rpgfinal.model.ability.BaseCharacterComponent;
import org.example.rpgfinal.model.ability.CharacterComponent;
import org.example.rpgfinal.model.history.CombatHistory;
import java.util.ArrayList;
import java.util.List;

public class CombatService {

    private static final int MAX_TURNS = 5;
    private final List<CombatListener> listeners = new ArrayList<>();

    // Permet d'ajouter un observateur pour le journal du combat
    public void addListener(CombatListener listener) {
        listeners.add(listener);
    }

    private void notify(String event) {
        for (CombatListener listener : listeners) {
            listener.onCombatEvent(event);
        }
    }

    public CombatResult fight(CharacterComponent player,
                              CharacterComponent bot) {

        CombatContext context = new CombatContext(player, bot);
        CombatHistory history = new CombatHistory();

        notify("\n---- DÉBUT DU COMBAT ----");

        for (int turn = 1; turn <= MAX_TURNS; turn++) {
            notify("\n---- TOUR " + turn + " ----");

            CombatAction playerAction = PlayerInput.askPlayerAction();
            CombatAction botAction = AiCombatStrategy.chooseAction();

            notify("Joueur : " + playerAction);
            notify("Bot    : " + botAction);

            if (playerAction == botAction) {
                notify("Égalité, aucun dégât");
                continue;
            }

            boolean playerWins = playerAction.beats(botAction);

            CombatAction chosenAction = playerWins ? playerAction : botAction;
            CombatActor actor = playerWins ? CombatActor.PLAYER : CombatActor.BOT;

            CombatCommand command = CombatCommandFactory.fromAction(chosenAction, actor);

            // Ajout a l'historique
            history.add(command);

            // Exécution de la commande et mise à jour des PV
            command.execute(context);

            // Notifier les changements de PV automatiquement
            notify("Notification des vie de " + context.getPlayerName() + " HP : " + Math.max(context.getPlayerHp(), 0));
            notify("Notification des vie de " + context.getBotName() + " HP : " + Math.max(context.getBotHp(), 0));

            // Stop si quelqu'un est mort
            if (context.getPlayerHp() <= 0 || context.getBotHp() <= 0) break;
        }

        CharacterComponent winner =
                context.getPlayerHp() > context.getBotHp() ? player :
                        context.getBotHp() > context.getPlayerHp() ? bot : null;

        notify("\n🏆 Combat terminé ! Gagnant : " +
                (winner != null ? winner.getDescription() : "Égalité"));

        // Replay automatique
        //history.replay(new CombatContext(player, bot));

        return new CombatResult(player, bot, winner);
    }

    // Interface pour les observateurs du combat
    public interface CombatListener {
        void onCombatEvent(String event);
    }
}