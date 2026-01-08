package org.example.rpgfinal.model.character;

public class CharacterBuilder {

    String name;
    String characterClass;
    int hp;
    int strength;
    int intelligence;
    int agility;

    public CharacterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder characterClass(String characterClass) {
        this.characterClass = characterClass;
        return this;
    }

    public CharacterBuilder hp(int hp) {
        this.hp = hp;
        return this;
    }

    public CharacterBuilder strength(int strength) {
        this.strength = strength;
        return this;
    }

    public CharacterBuilder intelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public CharacterBuilder agility(int agility) {
        this.agility = agility;
        return this;
    }

    public Character build() {
        return new Character(this);
    }
}