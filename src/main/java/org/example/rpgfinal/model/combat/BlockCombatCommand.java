package org.example.rpgfinal.model.combat;

import java.util.Random;

public class BlockCombatCommand implements CombatCommand {

    private static final Random RANDOM = new Random();
    private final CombatActor actor;

    public BlockCombatCommand(CombatActor actor) {
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
                    + " ce met en possition de défense et fais un contre "
                    + context.getBotName()
                    + " subit "
                    + dmg + " dégâts");
        } else {
            context.damagePlayer(dmg);
            System.out.println(
                    context.getBotName()
                    + " ce met en possition de défense et fais un contre  "
                    + context.getPlayerName()
                    + " subit "
                    + dmg + " dégâts");
        }
    }

    @Override
    public CombatActor getActor() {
        return actor;
    }

    @Override
    public String getDescription() {
        return "Bloquer (" + actor + ")";
    }
}
