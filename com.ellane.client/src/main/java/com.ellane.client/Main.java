package com.ellane.client;


import com.ellane.app.EllaneApp;
import com.ellane.model.Characters;
import com.ellane.model.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        EllaneApp app = new EllaneApp();
        app.initialize();
        //app.run();
        //app.verifyLocation();

        //TODO: move logic to SRC

        //TODO: need to test all items...//main shouldn't throw exceptions

        boolean valid = false;  // added changes
        while (!valid) {
            System.out.println("Select character to play as:");

            for (Characters character : Characters.values()) {
                System.out.println(character.getCharacterType());
            }

            System.out.println();
            Scanner scan = null;  // added changes
            String characterChoice = scan.nextLine().toLowerCase();

            for (Characters character : Characters.values()) {
                if (characterChoice.equals(character.getCharacterType())) {
                    System.out.println();
                    String playerName = null; // added changes
                    Player player1 = new Player(playerName, character);
                    System.out.println("New Player Created!");
                    app.startGame();  // added changes. initialized and made public
                    valid = true;
                    break;
                }
            }

            if (!valid) {
                System.out.println();
                System.err.println("INVALID CHARACTER SELECTED");
            }
        }
    }
}