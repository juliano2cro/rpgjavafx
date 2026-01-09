package org.example.rpgfinal.model.combat;

import java.util.Random;

public class AiCombatStrategy {

    private static final Random RANDOM = new Random();

    public static CombatAction chooseAction() {
        CombatAction[] actions = CombatAction.values();
        return actions[RANDOM.nextInt(actions.length)];
    }
}