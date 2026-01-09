package org.example.rpgfinal.model.combat;

public enum CombatAction {

    ATTACK,
    POWER,
    BLOCK;

    public boolean beats(CombatAction other) {
        switch (this) {
            case ATTACK:
                return other == POWER;
            case POWER:
                return other == BLOCK;
            case BLOCK:
                return other == ATTACK;
            default:
                return false; // sécurité pour compiler
        }
    }
}
