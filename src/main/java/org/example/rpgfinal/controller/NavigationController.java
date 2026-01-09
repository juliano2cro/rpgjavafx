package org.example.rpgfinal.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class NavigationController {

    @FXML
    private BorderPane contentPane;

    @FXML
    public void initialize() {
        // Optionnel : vue par défaut
        load("create_character.fxml");
    }

    private void load(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/" + fxml)
            );
            Parent view = loader.load();
            contentPane.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void goCreate() { load("create_character.fxml"); }
    @FXML void goList()   { load("character_list.fxml"); }
    @FXML void goTeam()   { load("team.fxml"); }
    @FXML void goCombat() { load("combat.fxml"); }
}
