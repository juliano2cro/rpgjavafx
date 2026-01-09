package org.example.rpgfinal.model.observable;

import java.util.ArrayList;
import java.util.List;

public class ObservableManager<T> {

    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notify(String source, String message) {
        for (Observer obs : observers) {
            obs.update("[" + source + "] " + message);
        }
    }

}