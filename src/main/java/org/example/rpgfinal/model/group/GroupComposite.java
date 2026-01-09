package org.example.rpgfinal.model.group;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GroupComposite implements GroupComponent {

    private final String name;
    private final List<GroupComponent> members = new ArrayList<>();
    private final List<Consumer<String>> listeners = new ArrayList<>();

    public GroupComposite(String name) {
        this.name = name;
    }

    public void addListener(Consumer<String> listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String message) {
        for (Consumer<String> listener : listeners) {
            listener.accept(message);
        }
    }

    public void add(GroupComponent component) {
        members.add(component);
        notifyListeners("Ajout de " + component.getName() + " à " + name);
    }

    public void remove(GroupComponent component) {
        members.remove(component);
        notifyListeners("Suppression de " + component.getName() + " de " + name);
    }

    public List<GroupComponent> getMembers() {
        return members;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTotalPower() {
        return members.stream().mapToInt(GroupComponent::getTotalPower).sum();
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "+ " + name + " (Puissance totale: " + getTotalPower() + ")");
        for (GroupComponent member : members) {
            member.display(indent + "  ");
        }
    }
}