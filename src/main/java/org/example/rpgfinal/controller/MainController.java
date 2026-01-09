package org.example.rpgfinal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label welcomeText;

    //test interface graphique
    @FXML
    private void onHelloButtonClick() {
        welcomeText.setText("Bienvenue dans le RPG !");
    }
}
