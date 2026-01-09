package org.example.rpgfinal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.rpgfinal.dao.CharacterDao;
import org.example.rpgfinal.model.character.Character;
import org.example.rpgfinal.model.character.CharacterBuilder;
import org.example.rpgfinal.model.validation.*;

import java.util.Set;
import java.util.stream.Collectors;

public class CreateCharacterController {

    @FXML private TextField nameField;
    @FXML private ComboBox<String> classBox;
    @FXML private Spinner<Integer> strSpinner;
    @FXML private Spinner<Integer> intSpinner;
    @FXML private Spinner<Integer> agiSpinner;
    @FXML private CheckBox invisibilityBox;
    @FXML private CheckBox telepathyBox;

    private final CharacterDao dao = new CharacterDao();

    @FXML
    public void initialize() {
        classBox.getItems().addAll("Mage", "Guerrier", "Archer");
        classBox.setValue("Mage");

        strSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        intSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        agiSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
    }

    @FXML
    private void createCharacter() {

        int totalStats = strSpinner.getValue()
                + intSpinner.getValue()
                + agiSpinner.getValue();

        if (totalStats > 100) {
            alert("Erreur", "Maximum 100 points de statistiques");
            return;
        }

        try {
            Character character = new CharacterBuilder()
                    .name(nameField.getText())
                    .characterClass(classBox.getValue())
                    .hp(100)
                    .strength(strSpinner.getValue())
                    .intelligence(intSpinner.getValue())
                    .agility(agiSpinner.getValue())
                    .build();

            // validations
            Set<String> existingNames = dao.findAll()
                    .stream()
                    .map(Character::getName)
                    .collect(Collectors.toSet());

            CharacterValidator[] validators = {
                    new NameNotEmptyValidator(),
                    new UniqueNameValidator(existingNames),
                    new StatPointsValidator()
            };

            for (CharacterValidator validator : validators) {
                validator.validate(character);
            }

            dao.save(character);
            alert("Succès", "Personnage \"" + character.getName() + "\" ajouté !");

        } catch (IllegalArgumentException e) {
            alert("Erreur de validation", e.getMessage());
        }
    }

    private void alert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}