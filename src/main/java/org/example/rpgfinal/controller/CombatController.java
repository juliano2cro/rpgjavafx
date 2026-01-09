package org.example.rpgfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.rpgfinal.dao.CharacterDao;
import org.example.rpgfinal.model.ability.BaseCharacterComponent;
import org.example.rpgfinal.model.ability.CharacterComponent;
import org.example.rpgfinal.model.character.Character;
import org.example.rpgfinal.model.combat.*;

import java.util.Random;

public class CombatController {

    @FXML private ComboBox<Character> player1Box;
    @FXML private ComboBox<Character> player2Box;

    @FXML private Button attackBtn;
    @FXML private Button powerBtn;
    @FXML private Button blockBtn;

    @FXML private TextArea combatLog;

    private final ObservableList<Character> characters = FXCollections.observableArrayList();

    private BaseCharacterComponent p1;
    private BaseCharacterComponent p2;
    private CombatContext context;
    private CombatService service;
    private int currentTurn = 1;
    private final int maxTurns = 5;

    private final Random random = new Random();

    @FXML
    public void initialize() {
        // Charger les personnages depuis DAO
        CharacterDao dao = new CharacterDao();
        characters.addAll(dao.findAll());

        player1Box.setItems(characters);
        player2Box.setItems(characters);

        setupComboBox(player1Box);
        setupComboBox(player2Box);

        attackBtn.setOnAction(e -> playTurn(CombatAction.ATTACK));
        powerBtn.setOnAction(e -> playTurn(CombatAction.POWER));
        blockBtn.setOnAction(e -> playTurn(CombatAction.BLOCK));
    }

    private void setupComboBox(ComboBox<Character> comboBox) {
        comboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Character item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName() + " (" + item.getCharacterClass() + ")");
            }
        });
        comboBox.setButtonCell(comboBox.getCellFactory().call(null));
    }

    private void startCombat() {
        Character c1 = player1Box.getValue();
        Character c2 = player2Box.getValue();

        if (c1 == null || c2 == null || c1 == c2) {
            combatLog.appendText("Sélectionnez deux personnages différents pour commencer le combat.\n");
            return;
        }

        p1 = new BaseCharacterComponent(c1);
        p2 = new BaseCharacterComponent(c2);

        context = new CombatContext(p1, p2);
        service = new CombatService();

        // Observer pour afficher les logs dans le TextArea
        service.getObservable().addObserver(msg -> combatLog.appendText(msg + "\n"));

        currentTurn = 1;
        combatLog.appendText("\n---- DÉBUT DU COMBAT ----\n");
        combatLog.appendText(p1.getDescription() + " VS " + p2.getDescription() + "\n");
        combatLog.appendText("Tour " + currentTurn + " : Choisissez votre action.\n");
    }

    private void playTurn(CombatAction playerAction) {
        if (p1 == null || p2 == null) {
            startCombat();
            return;
        }

        if (currentTurn > maxTurns || context.getPlayerHp() <= 0 || context.getBotHp() <= 0) {
            combatLog.appendText("Le combat est terminé.\n");
            return;
        }

        CombatAction botAction = AiCombatStrategy.chooseAction();

        combatLog.appendText("\n--- TOUR " + currentTurn + " ---\n");
        combatLog.appendText(p1.getDescription() + " choisit : " + actionToString(playerAction) + "\n");
        combatLog.appendText(p2.getDescription() + " choisit : " + actionToString(botAction) + "\n");

        // Gestion égalité
        if (playerAction == botAction) {
            combatLog.appendText("Égalité, aucun dégât.\n");
        } else {
            // Déterminer gagnant
            boolean playerWins = playerAction.beats(botAction);
            CombatAction chosenAction = playerWins ? playerAction : botAction;
            CombatActor actor = playerWins ? CombatActor.PLAYER : CombatActor.BOT;

            CombatCommand command = CombatCommandFactory.fromAction(chosenAction, actor);
            command.execute(context);

            // Affichage des PV
            combatLog.appendText(p1.getDescription() + " HP : " + Math.max(context.getPlayerHp(), 0)
                    + " | " + p2.getDescription() + " HP : " + Math.max(context.getBotHp(), 0) + "\n");
        }

        currentTurn++;

        // Fin du combat
        if (context.getPlayerHp() <= 0 || context.getBotHp() <= 0 || currentTurn > maxTurns) {
            CharacterComponent winner = context.getPlayerHp() > context.getBotHp() ? p1 :
                    context.getBotHp() > context.getPlayerHp() ? p2 : null;

            combatLog.appendText("\n---- FIN DU COMBAT ----\nGagnant : " +
                    (winner != null ? winner.getDescription() : "Égalité") + "\n");
        } else {
            combatLog.appendText("Tour " + currentTurn + " : Choisissez votre action.\n");
        }
    }

    private String actionToString(CombatAction action) {
        return switch (action) {
            case ATTACK -> "Attaque";
            case POWER -> "Pouvoir";
            case BLOCK -> "Bloc";
        };
    }
}
