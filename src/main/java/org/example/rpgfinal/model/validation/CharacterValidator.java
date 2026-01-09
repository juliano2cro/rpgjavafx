package org.example.rpgfinal.model.validation;

import org.example.rpgfinal.model.character.Character;

public interface CharacterValidator {

    void setNext(CharacterValidator next);

    void validate(Character character);
}
