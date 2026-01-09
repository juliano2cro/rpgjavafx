package org.example.rpgfinal.model.combat;

import org.example.rpgfinal.model.ability.BaseCharacterComponent;
import org.example.rpgfinal.model.ability.CharacterComponent;
import org.example.rpgfinal.model.history.CombatHistory;
import org.example.rpgfinal.model.observable.ObservableManager;

import java.util.ArrayList;
import java.util.List;

public class CombatService {

    private static final int MAX_TURNS = 5;

    private final ObservableManager<CombatService> observable =
            new ObservableManager<>();

    public ObservableManager<CombatService> getObservable() {
        return observable;
    }

    @Override
    public String toString() {
        return "Action";
    }


    public CombatResult fight(CharacterComponent player,
                              CharacterComponent bot) {

        CombatContext context = new CombatContext(player, bot);
        CombatHistory history = new CombatHistory();

        observable.notify(this.toString(), "\n---- DÉBUT DU COMBAT ----");

        for (int turn = 1; turn <= MAX_TURNS; turn++) {
            observable.notify(this.toString(), "\n---- TOUR " + turn + " ----");

            CombatAction playerAction = PlayerInput.askPlayerAction();
            CombatAction botAction = AiCombatStrategy.chooseAction();

            observable.notify(this.toString(), context.getPlayerName() + " " + playerAction);
            observable.notify(this.toString(), context.getBotName() + " " + botAction);

            if (playerAction == botAction) {
                observable.notify(this.toString(), "Égalité, aucun dégât");
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
            observable.notify(this.toString(), "Notif Pv : " + context.getPlayerName() + " HP : " + Math.max(context.getPlayerHp(), 0));
            observable.notify(this.toString(), "Notif Pv : " + context.getBotName() + " HP : " + Math.max(context.getBotHp(), 0));

            // Stop si quelqu'un est mort
            if (context.getPlayerHp() <= 0 || context.getBotHp() <= 0) break;
        }

        CharacterComponent winner =
                context.getPlayerHp() > context.getBotHp() ? player :
                        context.getBotHp() > context.getPlayerHp() ? bot : null;

        observable.notify(this.toString(), "\n Combat terminé ! Gagnant : " +
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