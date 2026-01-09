package org.example.rpgfinal.model.combat;

import java.util.Scanner;

public class PlayerInput {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static CombatAction askPlayerAction() {
        System.out.println("\nChoisissez votre action :");
        System.out.println("1 - Attaquer");
        System.out.println("2 - Pouvoir");
        System.out.println("3 - Bloquer");

        while (true) {
            String input = SCANNER.nextLine();
            switch (input) {
                case "1": return CombatAction.ATTACK;
                case "2": return CombatAction.POWER;
                case "3": return CombatAction.BLOCK;
                default:
                    System.out.println("Choix invalide, recommence.");
            }
        }
    }
}
