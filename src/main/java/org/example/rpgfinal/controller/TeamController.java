package org.example.rpgfinal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.example.rpgfinal.dao.CharacterDao;
import org.example.rpgfinal.model.character.Character;
import org.example.rpgfinal.model.ability.BaseCharacterComponent;
import org.example.rpgfinal.model.group.*;

public class TeamController {

    @FXML private TextField teamNameField;
    @FXML private TextField armyNameField;

    @FXML private ListView<Character> characterList;
    @FXML private ListView<GroupComponent> teamMembersList;
    @FXML private ListView<GroupComposite> teamsList;

    @FXML private TextArea logArea;

    private final CharacterDao dao = new CharacterDao();

    private GroupComposite currentTeam;
    private GroupComposite army;

    private final ObservableList<GroupComposite> createdTeams =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        characterList.setItems(
                FXCollections.observableArrayList(dao.findAll())
        );

        teamsList.setItems(createdTeams);

        characterList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Character c, boolean empty) {
                super.updateItem(c, empty);
                setText(empty || c == null ? null : c.getName());
            }
        });

        teamMembersList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(GroupComponent g, boolean empty) {
                super.updateItem(g, empty);
                setText(empty || g == null ? null : g.getName());
            }
        });

        teamsList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(GroupComposite g, boolean empty) {
                super.updateItem(g, empty);
                setText(empty || g == null ? null : g.getName());
            }
        });
    }

    // equipe

    @FXML
    private void createTeam() {
        if (teamNameField.getText().isEmpty()) return;

        currentTeam = new GroupComposite(teamNameField.getText());
        currentTeam.addListener(this::log);

        createdTeams.add(currentTeam);
        teamMembersList.getItems().clear();

        log("Équipe créée : " + currentTeam.getName());
    }

    @FXML
    private void addToTeam() {
        if (currentTeam == null) return;

        Character selected = characterList.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        CharacterLeaf leaf = new CharacterLeaf(
                new BaseCharacterComponent(selected)
        );

        currentTeam.add(leaf);
        teamMembersList.getItems().add(leaf);
    }

    @FXML
    private void removeFromTeam() {
        GroupComponent selected =
                teamMembersList.getSelectionModel().getSelectedItem();

        if (selected != null && currentTeam != null) {
            currentTeam.remove(selected);
            teamMembersList.getItems().remove(selected);
        }
    }

    //armee

    @FXML
    private void createArmy() {
        if (armyNameField.getText().isEmpty()) return;

        army = new GroupComposite(armyNameField.getText());
        army.addListener(this::log);

        log("Armée créée : " + army.getName());
    }

    @FXML
    private void addTeamToArmy() {
        if (army == null) return;

        GroupComposite selected =
                teamsList.getSelectionModel().getSelectedItem();

        if (selected != null) {
            army.add(selected);
        }
    }

    //log
    private void log(String message) {
        logArea.appendText(message + "\n");
    }
}
