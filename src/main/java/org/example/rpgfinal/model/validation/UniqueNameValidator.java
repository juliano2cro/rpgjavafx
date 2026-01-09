package org.example.rpgfinal.model.validation;

import org.example.rpgfinal.model.character.Character;

import java.util.Set;

public class UniqueNameValidator extends AbstractCharacterValidator {

    private final Set<String> existingNames;

    public UniqueNameValidator(Set<String> existingNames) {
        this.existingNames = existingNames;
    }

    @Override
    protected void check(Character character) {
        if (existingNames.contains(character.getName())) {
            throw new IllegalArgumentException(
                    "Le nom '" + character.getName() + "' est déjà utilisé"
            );
        }
        System.out.println("Le nom n'est pas encore utilsé");
    }
}
