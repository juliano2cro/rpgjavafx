package org.example.rpgfinal.model.validation;

import org.example.rpgfinal.model.character.Character;
import org.example.rpgfinal.model.rules.GameSettings;

public class StatPointsValidator extends AbstractCharacterValidator {

    @Override
    protected void check(Character character) {
        int totalStats =
                character.getStrength()
                        + character.getIntelligence()
                        + character.getAgility();

        int max = GameSettings.getInstance().getMaxStatPoints();

        if (totalStats > max) {
            throw new IllegalArgumentException(
                    "Total des stats (" + totalStats + ") supérieur à la limite (" + max + ")"
            );
        }
    }
}