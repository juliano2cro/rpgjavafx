package org.example.rpgfinal.model.combat;

import java.util.Random;

public class AttackCombatCommand implements CombatCommand {

    private static final Random RANDOM = new Random();
    private final CombatActor actor;

    public AttackCombatCommand(CombatActor actor) {
        this.actor = actor;
    }

    @Override
    public void execute(CombatContext context) {

        boolean isPlayer = actor == CombatActor.PLAYER;

        int basePower = isPlayer
                ? context.getPlayer().getPower()
                : context.getBot().getPower();

        int dmg = 5 + RANDOM.nextInt(Math.max(1, basePower / 2));

        if (isPlayer) {
            context.damageBot(dmg);
            System.out.println(
                    context.getPlayerName()
                    + " attaque "
                    + context.getBotName()
                    + " et inflige "
                    + dmg + " dégâts");
        } else {
            context.damagePlayer(dmg);
            System.out.println(
                    context.getBotName()
                    + " attaque "
                    + context.getPlayerName()
                    + " et inflige "
                    + dmg + " dégâts");
        }
    }

    @Override
    public CombatActor getActor() {
        return actor;
    }

    @Override
    public String getDescription() {
        return "Attaque (" + actor + ")";
    }
}
