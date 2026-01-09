package org.example.rpgfinal.model.combat;

import org.example.rpgfinal.model.ability.CharacterComponent;

public class CombatResult {

    private final CharacterComponent fighter1;
    private final CharacterComponent fighter2;
    private final CharacterComponent winner;

    public CombatResult(CharacterComponent fighter1,
                        CharacterComponent fighter2,
                        CharacterComponent winner) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.winner = winner;
    }

    public CharacterComponent getWinner() {
        return winner;
    }

    public String getSummary() {
        if (winner == null) {
            return "Combat nul entre "
                    + fighter1.getDescription()
                    + " et "
                    + fighter2.getDescription();
        }

        return "Vainqueur : "
                + winner.getDescription();
    }
}