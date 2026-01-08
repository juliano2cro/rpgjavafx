package org.example.rpgfinal;

import javafx.application.Application;
import org.example.rpgfinal.model.character.Character;
import org.example.rpgfinal.model.character.CharacterBuilder;
import org.example.rpgfinal.model.rules.GameSettings;

public class Launcher {
    public static void main(String[] args) {

        //test
        Character hero = new CharacterBuilder()
                .name("Juliano")
                .characterClass("Guerrier")
                .hp(120)
                .strength(40)
                .intelligence(20)
                .agility(30)
                .build();

        System.out.println(hero);

        GameSettings settings = GameSettings.getInstance();
        System.out.println("Max stats: " + settings.getMaxStatPoints());
        System.out.println("Max pers par groupe: " + settings.getMaxCharactersPerGroup());

        GameSettings settings2 = GameSettings.getInstance();
        System.out.println(settings == settings2);

        Application.launch(MainApp.class, args);
    }
}
