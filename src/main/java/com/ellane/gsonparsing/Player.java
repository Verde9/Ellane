package com.ellane.gsonparsing;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Player {
    private String name;
    private String currentRoom;
    private String firstWord; //take this out.. Not a characteristic by the player
    private String secondWord; //take this out.. Not a characteristic by the player
    ArrayList<String> inventory = new ArrayList<>();
    private Array Room[] = new Array[1];
    GsonNestedUserSimple bedroom = new GsonNestedUserSimple("'BEDROOM'","OPEN AREA", "sword",
            "inside of display case. It is Unlocked",
            "gun", "its a MF gun, but it doesnt do anything without bullets",
            "20", "This room is dope");

    Scanner in = new Scanner(System.in);

    // Make constructor for properties

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

    public void makeDecision() {
        System.out.println("What do you want to do: ");
        String decision = in.nextLine();
        System.out.println(decision);
        verifyDecision(decision);
    }

    private void verifyDecision(String decision) {
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

    private void verifyFirstWord(String firstWord) {
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
                if (secondWord.equals(bedroom.getItem()) || secondWord.equals(bedroom.getItem2()))
               inventory.add(secondWord);
                System.out.println("You grabbed this item " + bedroom.item);
                makeDecision();
                break;

        }
    }

    private void lookInCurrentRoom() {
        System.out.println("current Room Description");
        System.out.println("Items in room are going to be provided");// replace code later
        //Call Room details here from temporary room
        makeDecision();
    }

    private void verifyRoomMovement(String secondWord) {
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

    private void showGameControls() {
        System.out.println("The game commands are as follows: ");
        System.out.println("valid action commands: LOOK, USE, GO, JUMP, DROP, PICKUP, CONTROLS");
        System.out.println("eg. 'LOOK UP', 'PICKUP SWORD', JUMP DOWN, MOVE, ");
        makeDecision();
    }
}