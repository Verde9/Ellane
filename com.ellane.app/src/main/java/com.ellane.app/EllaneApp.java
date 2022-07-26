package com.ellane.app;

import com.ellane.model.ActionCommands;
import com.ellane.model.Characters;
import com.ellane.model.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class EllaneApp {
    private String firstWord;
    private String secondWord;
    private String playerCurrentRoom;
    private int roundCount = 50;
    Boolean gameOver = false;
    private com.ellane.model.ActionCommands ActionCommands;
    private com.ellane.model.Characters Characters;


    Scanner scan = new Scanner(System.in);
    Player player = new Player("LB", com.ellane.model.Characters.MALE_SOLDIER);


    //change player locations to GSON Json soon.
    com.ellane.model.PlayerLocationsAndItems bedroom = new com.ellane.model.PlayerLocationsAndItems("'BEDROOM'","OPEN AREA", "sword",
            "inside of display case. It is Unlocked",
            "gun", "its a MF gun, but it doesnt do anything without bullets",
            "20", "This room is dope");
    com.ellane.model.PlayerLocationsAndItems open_area = new com.ellane.model.PlayerLocationsAndItems("'BEDROOM'","OPEN AREA", "sword",
            "inside of display case. It is Unlocked",
            "gun", "its a MF gun, but it doesnt do anything without bullets",
            "20", "This room is dope");

    public void initialize() throws InterruptedException {
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

    private void promptToStartGame() throws InterruptedException {
        System.out.println("Do you wish to play? type 'yes' or type 'quit game'");
        String userInput = scan.nextLine().toLowerCase();
        System.out.println("user input: " + userInput);

        if (userInput.equals("quit game")) {
            System.out.println("...Thanks for playing! Goodbye!");
        }

        if (userInput.equals("yes")) {
            System.out.println("Let's Play!");
            startGame();
        } else {
            System.out.println("INVALID INPUT");
        }
    }

    private void startGame() throws InterruptedException {
        displayGameInfo();

        while (!gameOver) {
            displayGameInfo();
            displayGameLevelOneInfo();
        }
    }

    private void displayGameLevelOneInfo() {
        System.out.println();
        //String playerDecision = player.makeDecision();
        //verifyDecision(playerDecision);
    }

    private void verifyFirstWord(String firstWord) throws InterruptedException {
        switch (firstWord) {
            case "look":
               // System.out.println("this is in your inventory " + getInventory());
                System.out.println("You are in  " + bedroom.getCurrentRoom() + " " + " I can see a " +
                        bedroom.getItem() + " and I can sea " + bedroom.getItem2() +
                        " " + bedroom.getItem_status());
                player.makeDecision();
                break;
            case "controls":
                showGameControls();
                break;
            case "go":
                verifyRoomMovement(secondWord);
                break;
            default:
                System.out.println("incorrect command. Check game control for options");
                player.makeDecision();
                break;
            case "inventory":
               // System.out.println(getInventory());
                player.makeDecision();
                break;
            case "get":
                if (secondWord.equals(bedroom.getItem()) || secondWord.equals(bedroom.getItem2()))
                   // inventory.add(secondWord);
                System.out.println("You grabbed this item, press enter ");
                player.makeDecision();
                break;
            case "q":
                System.out.println("Thank you for Playing!");
                TimeUnit.SECONDS.sleep(1);


        }
    }

    private void verifyRoomMovement (String secondWord){
            switch (secondWord) {
                case "east":
                    System.out.println();
                    //verify there is an east room to move to
                    //update currentroom property
                    //display currentRoom description
                    //display currentRoom items by looping over them all
                    //call makeDecision()
                    //If not valid, throw ERROR MESSAGE
                    //makeDecision();
                    break;
                case "west":
                    System.out.println();
                    break;
            }
        }

    private void lookInCurrentRoom () {
            System.out.println("current Room Description");
            System.out.println("Items in room are going to be provided");// replace code later
            //Call Room details here from temporary room
            //makeDecision();
        }

    private void displayGameInfo () throws InterruptedException {
            System.out.println("The chaos spreads & the bombs keep exploding around the city");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("The fire is spreading from building to building & most signs of life as gone!");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("you get stuck inside of a building, but it can collapse at any minute & fire is spreading");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Luckily there id a helicopter on the roof evacuating the survivors that made it to the roof");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Unfortunately, you have been wounded & are losing blood as more time passes");
            TimeUnit.SECONDS.sleep(2);
            System.out.println();
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Time is ticking & you don't have much time!");
            TimeUnit.SECONDS.sleep(2);
            System.out.println();
            System.out.println("A dying woman tells you her sick daughter Ellane is stuck somewhere inside & asked you to save & escape together");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("The objectives you have are simple:");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("- collect the 3 items needed for a cure to save Ellane");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("- Find Ellane");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("- Safely make it to the roof to escape by helicopter before 50 turns pass");
            TimeUnit.SECONDS.sleep(2);
            System.out.println();

            player.makeDecision();

        }

    private void showGameControls() throws InterruptedException {
        System.out.println("Actions:\n" +
                "    GO [north, south, east, west, up, down]\n" +
                "    GET [item, spell]\n" +
                "    USE [item, spell]\n" +
                "    LOOK\n" +
                "    INV/INVENTORY\n" +
                "\n" +
                "Type 'help' at any time! Type 'q' to quit!");
        //System.out.println("The game commands are as follows: ");
        //System.out.println("valid action commands: LOOK, USE, GO, JUMP, DROP, PICKUP, CONTROLS");
        // System.out.println("eg. 'LOOK UP', 'PICKUP SWORD', JUMP DOWN, MOVE, ");
        String answer = player.makeDecision();
        verifyDecision(answer);
    }

    private void verifyDecision(String decision) throws InterruptedException {
        String stringArr[] = decision.split(" ", 2);
        System.out.println(stringArr);
        firstWord = stringArr[0].toLowerCase();
        secondWord = stringArr[1].toLowerCase();
        System.out.println("first Word: " + firstWord);
        System.out.println("second Word: " + secondWord);

        verifyFirstWord(firstWord);
    }
}




