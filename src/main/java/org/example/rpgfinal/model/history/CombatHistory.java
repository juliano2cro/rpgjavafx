package org.example.rpgfinal.model.history;

import org.example.rpgfinal.model.combat.CombatCommand;
import org.example.rpgfinal.model.combat.CombatContext;

import java.util.ArrayList;
import java.util.List;

public class CombatHistory {

    private final List<CombatCommand> commands = new ArrayList<>();

    // Ajouter une commande à l'historique
    public void add(CombatCommand command) {
        commands.add(command);
    }

    // Rejouer le combat à partir d'un contexte
    public void replay(CombatContext context) {
        System.out.println("---- Relecture du combat ----");

        for (CombatCommand command : commands) {
            System.out.println("actions : " + command.getDescription());
            command.execute(context);
        }

        System.out.println("---- Fin de la relecture ----");
    }

    // Journal texte
    public List<String> getDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (CombatCommand command : commands) {
            descriptions.add(command.getDescription());
        }
        return descriptions;
    }

    public void clear() {
        commands.clear();
    }
}
