package org.example.rpgfinal.controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.rpgfinal.dao.CharacterDao;
import org.example.rpgfinal.model.character.CharacterBuilder;
import org.example.rpgfinal.model.character.Character;

import javafx.scene.control.*;

public class CreateCharacterController {

    @FXML private TextField nameField;
    @FXML private ComboBox<String> classBox;
    @FXML private Spinner<Integer> strSpinner, intSpinner, agiSpinner;
    @FXML private CheckBox invisibilityBox, telepathyBox;

    private final CharacterDao dao = new CharacterDao();

    @FXML
    public void initialize() {
        classBox.getItems().addAll("Mage", "Guerrier", "Archer");
        classBox.setValue("Mage");

        strSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        intSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        agiSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
    }

    @FXML
    private void createCharacter() {

        int total = strSpinner.getValue()
                + intSpinner.getValue()
                + agiSpinner.getValue();

        if (total > 100) {
            alert("Erreur", "Maximum 100 points de statistiques");
            return;
        }

        Character character = new CharacterBuilder()
                .name(nameField.getText())
                .characterClass(classBox.getValue())
                .hp(100)
                .strength(strSpinner.getValue())
                .intelligence(intSpinner.getValue())
                .agility(agiSpinner.getValue())
                .build();

        dao.save(character);
        alert("Succès", "Personnage : " + nameField.getText() + " ajouté !");
    }

    private void alert(String title, String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}
