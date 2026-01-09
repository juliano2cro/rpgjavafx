package org.example.rpgfinal.model.ability;

public class InvisibilityAbility extends AbilityDecorator {

    public InvisibilityAbility(CharacterComponent base) {
        super(base);
    }

    @Override
    public int getPower() {
        return super.getPower() + 15;
    }

    @Override
    public int getHp() {
        // délégation au composant de base
        return super.getHp();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Invisibilité";
    }
}
