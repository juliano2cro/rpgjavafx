package org.example.rpgfinal.model.ability;

public abstract class AbilityDecorator implements CharacterComponent {

    protected final CharacterComponent base;

    protected AbilityDecorator(CharacterComponent base) {
        this.base = base;
    }

    @Override
    public int getPower() {
        return base.getPower();
    }

    @Override
    public int getHp() {
        return base.getHp();
    }

    @Override
    public String getDescription() {
        return base.getDescription();
    }
}
