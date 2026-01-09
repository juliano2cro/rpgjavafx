package org.example.rpgfinal.model.combat;

public class CombatCommandFactory {

    public static CombatCommand fromAction(
            CombatAction action,
            CombatActor actor
    ) {
        switch (action) {
            case ATTACK:
                return new AttackCombatCommand(actor);
            case POWER:
                return new PowerCombatCommand(actor);
            case BLOCK:
                return new BlockCombatCommand(actor);
            default:
                throw new IllegalArgumentException("Action de combat inconnue : " + action);
        }
    }
}
