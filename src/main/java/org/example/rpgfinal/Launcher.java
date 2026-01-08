package org.example.rpgfinal;

import javafx.application.Application;
import org.example.rpgfinal.model.character.Character;
import org.example.rpgfinal.model.character.CharacterBuilder;

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

        Application.launch(MainApp.class, args);
    }
}
