package org.example.rpgfinal.model.rules;

public class GameSettings {

    private static GameSettings instance;

    private int maxStatPoints;
    private int maxCharactersPerGroup;

    private GameSettings() {
        this.maxStatPoints = 100;
        this.maxCharactersPerGroup = 5;
    }

    public static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        return instance;
    }

    // Getters
    public int getMaxStatPoints() {
        return maxStatPoints;
    }

    public int getMaxCharactersPerGroup() {
        return maxCharactersPerGroup;
    }
}
