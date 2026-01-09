package org.example.rpgfinal.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.example.rpgfinal.dao.CharacterDao;
import org.example.rpgfinal.model.ability.BaseCharacterComponent;
import org.example.rpgfinal.model.character.Character;

public class CharacterListController {

    @FXML private TableView<Character> table;

    @FXML private TableColumn<Character, String> nameCol;
    @FXML private TableColumn<Character, String> classCol;
    @FXML private TableColumn<Character, Integer> powerCol;
    @FXML private TableColumn<Character, Integer> strCol;
    @FXML private TableColumn<Character, Integer> intCol;
    @FXML private TableColumn<Character, Integer> agiCol;
    @FXML private TableColumn<Character, String> abilitiesCol;

    private final CharacterDao dao = new CharacterDao();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        classCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCharacterClass()));

        strCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getStrength()).asObject());
        intCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getIntelligence()).asObject());
        agiCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getAgility()).asObject());

        powerCol.setCellValueFactory(c -> new SimpleIntegerProperty(
                c.getValue().getStrength() + c.getValue().getIntelligence() + c.getValue().getAgility()
        ).asObject());

        abilitiesCol.setCellValueFactory(c -> {
            Character character = c.getValue();

            // Créer un CharacterComponent de base
            BaseCharacterComponent component = new BaseCharacterComponent(character);

            // Ici, si tu appliques des décorateurs, tu devrais les appliquer avant
            // Par exemple : component = new InvisibilityAbility(component);

            // Pour juste afficher les capacités existantes, on prend la description
            String desc = component.getDescription();

            // Extraire seulement les capacités, si la description contient "Nom (Classe) [Capacités]"
            // Ici on suppose que le décorateur ajoute ses noms à la fin
            String[] parts = desc.split(" : ");
            String abilities = parts.length > 1 ? parts[1] : "";

            return new SimpleStringProperty(abilities);
        });


        refresh();
    }

    @FXML
    private void delete() {
        Character selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            dao.delete(selected);
            refresh();
        }
    }

    private void refresh() {
        table.getItems().setAll(dao.findAll());
    }
}
