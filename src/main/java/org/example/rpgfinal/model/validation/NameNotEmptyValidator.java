package org.example.rpgfinal.model.validation;

import org.example.rpgfinal.model.character.Character;

public class NameNotEmptyValidator extends AbstractCharacterValidator {

    @Override
    protected void check(Character character) {
        if (character.getName() == null || character.getName().isBlank()) {
            throw new IllegalArgumentException("Le nom du personnage est obligatoire");
        }
    }
}
