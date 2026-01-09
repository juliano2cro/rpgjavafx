package org.example.rpgfinal.model.group;

import org.example.rpgfinal.model.ability.CharacterComponent;

public class CharacterLeaf implements GroupComponent {

    private final CharacterComponent character;

    public CharacterLeaf(CharacterComponent character) {
        this.character = character;
    }

    @Override
    public String getName() {
        return character.getDescription();
    }

    @Override
    public int getTotalPower() {
        return character.getPower();
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "- " + getName()
                + " (Puissance : " + getTotalPower() + ")");
    }
}