package org.example.rpgfinal.model.combat;

public interface CombatCommand {

    void execute(CombatContext context);

    CombatActor getActor();

    String getDescription();
}
