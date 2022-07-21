package com.ellane.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

/*
 *This is a the Controller.
 * It orchestrates the overall flow of the application.
 *It prompts the user for input, and then passes that input into the system ("Model").
 * ALL PROMPTS SHOULD COME FROM HERE
 */
public class EllaneApp {
    private int roundCount = 50;
    Boolean gameEnds = false;

    Scanner scan = new Scanner(System.in);

    public void initialize() {
        gameWelcomeMessage();
        promptToStartGame();
    }

    private void gameWelcomeMessage() {

        String banner = null;
        if (Files.exists(Path.of("com.ellane.app/resources/gameArt.txt"))) {
            try {
                banner = Files.readString(Path.of("com.ellane.app/resources/gameArt.txt"));
                System.out.println(banner);
                System.out.println("\n");
                System.out.println("Ellane Needs your Help!");
                System.out.println("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("file wasn't found");
        }
    }

    private void promptToStartGame() {
        System.out.println("Do you wish to play? type 'yes' or typ 'quit game'");
        String userInput = scan.nextLine().toLowerCase();
        System.out.println("user input: " + userInput);

        if(userInput.equals("quit game")) {
            System.out.println("...Thanks for playing! Goodbye!");
        }

        if(userInput.equals("yes")) {
            System.out.println("Let's Play!");
            startGame();
        } else {
            System.out.println("INVALID INPUT");
        }
    }

    private void startGame() {

    }
}


