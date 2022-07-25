package com.ellane.gsonparsing;

import com.google.gson.Gson;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Player {
    private String name;
    private String currentRoom;
    private String firstWord; //take this out.. Not a characteristic by the player
    private String secondWord; //take this out.. Not a characteristic by the player
    ArrayList<String> inventory = new ArrayList<>();
    private Array Room[] = new Array[1];







    //change player locations to GSON Json soon.
    PlayerLocationsAndItems bedroom = new PlayerLocationsAndItems("'BEDROOM'","OPEN AREA", "sword",
            "inside of display case. It is Unlocked",
            "gun", "its a MF gun, but it doesnt do anything without bullets",
            "20", "This room is dope");
    PlayerLocationsAndItems open_area = new PlayerLocationsAndItems("'BEDROOM'","OPEN AREA", "sword",
            "inside of display case. It is Unlocked",
            "gun", "its a MF gun, but it doesnt do anything without bullets",
            "20", "This room is dope");


    public void playAllGames() throws InterruptedException {

        makeDecision();
    }



    public void GsonParsing() {
        String bedroom2 = "{\n" +
                "  \"currentRoom\": \"BEDROOM\",\n" +
                "  \"south\": \"OPEN AREA\",\n" +
                "  \"item\": \"sword\",\n" +
                "  \"item_status\": \" inside of a display case. It is unlocked\",\n" +
                "  \"item2\": \"gun\",\n" +
                "  \"item_status2\": \"its a MF gun..but it doesnt do anything with out bullets\",\n" +
                "  \"randenc\": \"20\",\n" +
                "  \"desc\": \"You are in a bedroom. There is nothing of use in this room. It stinks and everything looks crappy, but you see, to the SOUTH; an ugly OPEN AREA\"\n" +
                "\n" +
                "}";
        Gson json = new Gson();
        PlayerLocationsAndItems bedroom3 = json.fromJson(bedroom2, PlayerLocationsAndItems.class);
        System.out.println(bedroom3.getItem2());
    }




    Scanner in = new Scanner(System.in);

    // Make constructor for properties

    public Player(){}


    public Player(String name) {
        this.name = name;
        //this.inventory = inventory  ["item1", "item2"];
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
     }

    public void makeDecision() throws InterruptedException {
        System.out.println();
        System.out.println("What do you want to do: ");
        System.out.println("Enter CONTROLS to get game controls");
        TimeUnit.SECONDS.sleep(1);
        String decision = in.nextLine();
        System.out.println(decision);
        verifyDecision(decision);
    }

    private void verifyDecision(String decision) throws InterruptedException {
        //String[] stringArr = decision.split("");
        String[] stringArr = decision.split(" ");
        //System.out.println(Arrays.toString(stringArr));
        //System.out.println(Arrays.toString(stringArr));
        firstWord = stringArr[0].toLowerCase();
        if (stringArr.length <= 1){
            verifyFirstWord(firstWord);
        }
        else {

            secondWord = stringArr[1].toLowerCase();
            verifyFirstWord(firstWord);
        }
        //System.out.println("first Word: " + firstWord);
        //System.out.println("second Word: " + secondWord);

        //verifyFirstWord(firstWord);
    }






    private void verifyFirstWord(String firstWord) throws InterruptedException {
        switch (firstWord) {
            case "look":
                System.out.println("this is in your inventory " + getInventory());
                System.out.println("You are in  " +bedroom.getCurrentRoom() + " " + " I can see a "+
                        bedroom.getItem() + " and I can sea " + bedroom.getItem2() +
                        " "+ bedroom.getItem_status());
                makeDecision();
                break;
            case "controls":
                showGameControls();
                break;
            case "go":
                verifyRoomMovement(secondWord);
                break;
            default:
                System.out.println("incorrect command. Check game control for options");
                makeDecision();
                break;
            case "inventory":
                System.out.println(getInventory());
                makeDecision();
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
                    System.out.println("You grabbed this item, press enter ");
                }
                makeDecision();
                break;
            case "q":
                System.out.println("Thank you for Playing!");
                TimeUnit.SECONDS.sleep(1);
                break;

            case "play":
                if (secondWord.equals("music")){
                    System.out.println("These are the directions for the music player");
                    runMusic("src/Music/intro wav 2_1.wav");
                }
                makeDecision();
                break;


        }
    }



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




    //not really using this method
    private void lookInCurrentRoom() throws InterruptedException {
        System.out.println("current Room Description");
        System.out.println("Items in room are going to be provided");// replace code later
        //Call Room details here from temporary room
        makeDecision();
    }

    private void verifyRoomMovement(String secondWord) throws InterruptedException {
        switch(secondWord) {
            case "east":
                System.out.println();

                //verify there is an east room to move to
                   //update currentroom property
                   //display currentRoom description
                   //display currentRoom items by looping over them all
                   //call makeDecision()
                //If not valid, throw ERROR MESSAGE
                makeDecision();

                break;
            case "west":
                System.out.println();
                break;
        }
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
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
        makeDecision();;
    }


}