package org.example.rpgfinal.model.ability;

public class TelepathyAbility extends AbilityDecorator {

    public TelepathyAbility(CharacterComponent base) {
        super(base);
    }

    @Override
    public int getPower() {
        return super.getPower() + 10;
    }

    @Override
    public int getHp() {
        // délégation au composant de base
        return super.getHp();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Télépathie";
    }
}