package org.example.rpgfinal.model.character;

public class Character {

    private final String name;
    private final String characterClass;
    private final int hp;
    private final int strength;
    private final int intelligence;
    private final int agility;

    // Constructeur protégé pour le Builder
    protected Character(CharacterBuilder builder) {
        this.name = builder.name;
        this.characterClass = builder.characterClass;
        this.hp = builder.hp;
        this.strength = builder.strength;
        this.intelligence = builder.intelligence;
        this.agility = builder.agility;
    }

    // les Getters
    public String getName() {
        return name;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public int getHp() {
        return hp;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAgility() {
        return agility;
    }

    @Override
    public String toString() {
        return name + " (" + characterClass + ")"
                + " [HP=" + hp
                + ", STR=" + strength
                + ", INT=" + intelligence
                + ", AGI=" + agility + "]";
    }
}