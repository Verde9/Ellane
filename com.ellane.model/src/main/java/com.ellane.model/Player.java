package com.ellane.model;
import com.google.gson.Gson;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Player {
    private String name;
    private Integer health = 100;
    private com.ellane.model.Characters CHARACTERTYPE;
    ArrayList<String> inventory = new ArrayList<>();
    private Array Room[] = new Array[1];

    String decision;


    Scanner in = new Scanner(System.in);

    // Make constructor for properties
    public Player(String name,  Characters character) {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public Integer getHealth() {
        return health;
    }

    public void setCharacterType(Characters CHARACTERS) {
        this.CHARACTERTYPE = CHARACTERS;
    }

    public Characters getCharacterType() {
        return CHARACTERTYPE;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }


     //--------------METHODS------------------//
    public String makeDecision() throws InterruptedException {

        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to do: ");
        System.out.println("Enter CONTROLS to get game controls");
        System.out.println();
        //TimeUnit.SECONDS.sleep(1);
        String decision = in.nextLine();
        return  decision;
    }



    private void dropItemFromInventory() {

    }

    private void decreaseHealth(int decreaseAmount) {
        health -= decreaseAmount;
    }

    private void increaseHealth(int increaseAmount) {
        health += increaseAmount;
    }
}