package org.example.rpgfinal.model.ability;

import org.example.rpgfinal.model.character.Character;
import org.example.rpgfinal.model.observable.HpChangeListener;

import java.util.ArrayList;
import java.util.List;

public class BaseCharacterComponent implements CharacterComponent {

    private final Character character;
    private int currentHp;
    private final List<HpChangeListener> hpListeners = new ArrayList<>();

    public BaseCharacterComponent(Character character) {
        this.character = character;
        this.currentHp = character.getHp(); // PV dynamiques
    }

    @Override
    public String getDescription() {
        return character.getName() + " (" + character.getCharacterClass() + ")";
    }

    @Override
    public int getPower() {
        return character.getStrength() + character.getIntelligence() + character.getAgility();
    }

    public int getHp() {
        return currentHp;
    }

    public void takeDamage(int damage) {
        currentHp -= damage;
        if (currentHp < 0) currentHp = 0;
        notifyHpChange();
    }

    public void heal(int amount) {
        currentHp += amount;
        if (currentHp > character.getHp()) currentHp = character.getHp();
        notifyHpChange();
    }

    public void addHpListener(HpChangeListener listener) {
        hpListeners.add(listener);
    }

    private void notifyHpChange() {
        for (HpChangeListener listener : hpListeners) {
            listener.onHpChanged(character.getName(), currentHp);
        }
    }
}