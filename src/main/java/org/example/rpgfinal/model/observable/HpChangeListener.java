package org.example.rpgfinal.model.observable;

@FunctionalInterface
public interface HpChangeListener {
    void onHpChanged(String name, int newHp);
}
