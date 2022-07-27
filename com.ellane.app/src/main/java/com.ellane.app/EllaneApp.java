package com.ellane.app;

//import com.ellane.model.*;
//import com.google.gson.Gson;
import com.ellane.model.Player;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class EllaneApp {
    private String firstWord;
    private String secondWord;
    private String playerCurrentRoom;
    private int roundCount = 50;
    Boolean gameOver = false;
    ArrayList<String> inventory = new ArrayList<>();
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


    //this will run the app in the main class
    public void initialize() throws InterruptedException {
        gameWelcomeMessage();
        promptToStartGame();

    }


    //after initialize is called, the "ellane" picture will show
    //the image is pretty big now.... should we make it smaller?
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

    //this would be one of the first things asked... Needs to make a introGame function again to call before
    //the sout line to play music
    private void promptToStartGame() throws InterruptedException {
        introMusic("Music/For Work 2_1.wav");
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
        while (!gameOver){
            String answer = player.makeDecision();
            verifyDecision(answer);
        }

    }


    private void displayGameLevelOneInfo() throws InterruptedException {
        verifyDecision(player.makeDecision());
    }


    //Chris is working on this method, but we may not need it... depending on the Gson to Json
    //but have him code this still, just in case

    private void verifyRoomMovement (String secondWord){
            switch (secondWord) {
                case "south":
                    System.out.println("I did it");
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

                case "north":
                    System.out.println("My name is Shon");
                    break;
            }
        }


        //TODO: (delete this comment later),this is making a String Array of our words so like ["john", "doe"]
    private void verifyDecision(String decision) throws InterruptedException {
        String[] stringArr = decision.split(" ");
        firstWord = stringArr[0].toLowerCase();
        if (stringArr.length <= 1){
            verifyFirstWord(firstWord);
        }
        else {

            secondWord = stringArr[1].toLowerCase();
            verifyFirstWord(firstWord);
        }

    }

    //TODO: (Delete this comment later)--- this is the main game info screen
        private void displayGameInfo () throws InterruptedException {
            System.out.println("The chaos spreads & the bombs keep exploding around the city");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("The fire is spreading from building to building & most signs of life as gone!");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("you get stuck inside of a building, but it can collapse at any minute & fire is spreading");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Luckily there id a helicopter on the roof evacuating the survivors that made it to the roof");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Unfortunately, you have been wounded & are losing blood as more time passes");
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Time is ticking & you don't have much time!");
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
            System.out.println("A dying woman tells you her sick daughter Ellane is stuck somewhere inside & asked you to save & escape together");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("The objectives you have are simple:");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("- collect the 3 items needed for a cure to save Ellane");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("- Find Ellane");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("- Safely make it to the roof to escape by helicopter before 50 turns pass");
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
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

    }

    //TODO: get the Gson working... this code needs modifying
//    public void GsonParsing() {
//        String bedroom2 = "{\n" +
//                "  \"currentRoom\": \"BEDROOM\",\n" +
//                "  \"south\": \"OPEN AREA\",\n" +
//                "  \"item\": \"sword\",\n" +
//                "  \"item_status\": \" inside of a display case. It is unlocked\",\n" +
//                "  \"item2\": \"gun\",\n" +
//                "  \"item_status2\": \"its a MF gun..but it doesnt do anything with out bullets\",\n" +
//                "  \"randenc\": \"20\",\n" +
//                "  \"desc\": \"You are in a bedroom. There is nothing of use in this room. It stinks and everything looks crappy, but you see, to the SOUTH; an ugly OPEN AREA\"\n" +
//                "\n" +
//                "}";
//        Gson json = new Gson();
//        PlayerLocationsAndItems bedroom3 = json.fromJson(bedroom2, PlayerLocationsAndItems.class);
//        System.out.println(bedroom3.getItem2());
//    }



  /*  public void makeDecision() throws InterruptedException {
        System.out.println();
        System.out.println("What do you want to do: ");
        System.out.println("Enter CONTROLS to get game controls");
        TimeUnit.SECONDS.sleep(1);
        String decision = in.nextLine();
        System.out.println(decision);
        verifyDecision(decision);
    }*/




    //TODO: MAIN GAME LOGIC
    private void verifyFirstWord(String firstWord) throws InterruptedException {
        switch (firstWord) {
            case "look":
                System.out.println("this is in your inventory " + getInventory());
                System.out.println("You are in  " +bedroom.getCurrentRoom() + " " + " I can see a "+
                        bedroom.getItem() + " and I can sea " + bedroom.getItem2() +
                        " "+ bedroom.getItem_status());
                String answer = player.makeDecision();
                verifyDecision(answer);
                break;
            case "controls":
                showGameControls();
                answer = player.makeDecision();
                verifyDecision(answer);
                break;
            case "go":
                //verifyRoomMovement(secondWord);
                if (secondWord.equals("south")){
                    verifyRoomMovement(secondWord);
                }
                else if(secondWord.equals("north")){
                    verifyRoomMovement(secondWord);
                }
                answer = player.makeDecision();
                verifyDecision(answer);
                break;
            case "inventory":
                System.out.println(getInventory());
                answer = player.makeDecision();
                verifyDecision(answer);
                break;
            case "get":
                if (secondWord.equals(bedroom.getItem()) || secondWord.equals(bedroom.getItem2())){
                    inventory.add(secondWord);
                    if(secondWord.equals(bedroom.getItem())){
                        bedroom.getItem().isEmpty();
                    }
                    else {
                        bedroom.getItem2().isEmpty();
                    }
                    System.out.println("You grabbed this item, enter 'INVENTORY' to see item ");
                }
                answer = player.makeDecision();
                verifyDecision(answer);
                break;
            case "q":
                System.out.println("Thank you for Playing!");
                TimeUnit.SECONDS.sleep(1);
                break;
            case "play":
                if (secondWord.equals("music")){
                    System.out.println("These are the directions for the music player");
                    runMusic("Music/intro wav 2_1.wav");
                }
                answer = player.makeDecision();
                verifyDecision(answer);
                break;
            default:
                System.out.println("incorrect command. Check game control for options");
                answer = player.makeDecision();
                verifyDecision(answer);
                break;


        }
    }



    //this is to run the main game music...after the intro.
    public static void runMusic(String path) {
        try {
            Scanner scanner = new Scanner(System.in);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(1);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
            String response = "";


            while (!response.equals("Q")){
                System.out.println("P = Play Music, S= Stop Music, R=Reset, V =Volume, Q= quit music player");
                System.out.println("Enter your choice: ");
                response = scanner.next();
                response = response.toUpperCase();

                switch (response){
                    case ("P"):
                        clip.start();
                        break;
                    case ("S"):
                        clip.stop();
                        break;
                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;
                    case ("V") :
                        System.out.println("what do you want the level to be? :  (-17 is lower volume " +
                                "and 0.0 is higher volume)");
                        String setVolume = scanner.next();
                        gainControl.setValue(Float.parseFloat(String.valueOf(setVolume)));
                        break;
                    case ("Q"):
                        System.out.println("the music will stop playing... forever, until you activate again");
                        clip.stop();
                        break;
                    default:
                        System.out.println("not a valid response for music player");


                }
            }


        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }


    }

    public static void introMusic(String path) {
        try {
            Scanner scanner = new Scanner(System.in);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(1);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-3.0f); // Reduce volume by 10 decibels.
            String response = "";


            while (!response.equals("Q")){
                System.out.println("P = Play Music, S= Stop Music, R=Reset, V =Volume Q= STOP MUSIC.. and Begin Game");
                System.out.println("Enter your choice: ");
                response = scanner.next();
                response = response.toUpperCase();

                switch (response){
                    case ("P"):
                        clip.start();
                        break;
                    case ("S"):
                        clip.stop();
                        break;
                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;
                    case ("V") :
                        System.out.println("what do you want the level to be? :  (-17 is lower)");
                        String setVolume = scanner.next();
                        gainControl.setValue(Float.parseFloat(String.valueOf(setVolume)));
                        break;
                    case ("Q"):
                        System.out.println("Thank you for quitting the player... Now Let's PLAY!");
                        clip.close();
                        System.out.println();
                        break;
                    default:
                        System.out.println("not a valid response for music player");


                }
            }


        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public Boolean getGameOver() {
        return gameOver;
    }



}




