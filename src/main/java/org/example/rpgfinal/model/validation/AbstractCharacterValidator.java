package org.example.rpgfinal.model.validation;

import org.example.rpgfinal.model.character.Character;

public abstract class AbstractCharacterValidator implements CharacterValidator {

    protected CharacterValidator next;

    @Override
    public void setNext(CharacterValidator next) {
        this.next = next;
    }

    @Override
    public void validate(Character character) {
        check(character);
        if (next != null) {
            next.validate(character);
        }
    }

    protected abstract void check(Character character);
}
