package org.example.rpgfinal.model.combat;

import org.example.rpgfinal.model.ability.CharacterComponent;

public class CombatContext {

    private int playerHp;
    private int botHp;

    private final CharacterComponent player;
    private final CharacterComponent bot;

    public CombatContext(CharacterComponent player, CharacterComponent bot) {
        this.player = player;
        this.bot = bot;
        this.playerHp = player.getHp();
        this.botHp = bot.getHp();
    }

    public int getPlayerHp() { return playerHp; }
    public int getBotHp() { return botHp; }

    public void damagePlayer(int dmg) {
        playerHp -= dmg;
    }

    public void damageBot(int dmg) {
        botHp -= dmg;
    }

    public CharacterComponent getPlayer() { return player; }
    public CharacterComponent getBot() { return bot; }

    public String getPlayerName() {
        return player.getDescription();
    }

    public String getBotName() {
        return bot.getDescription();
    }
}
