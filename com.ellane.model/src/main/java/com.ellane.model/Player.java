package com.ellane.model;
import com.google.gson.Gson;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Player {
    private String name;
    private Integer health = 100;
    private com.ellane.model.Characters CHARACTERTYPE;
    ArrayList<String> inventory = new ArrayList<>();
    private LocationsAndDirections locationsAndDirections;

    Scanner in = new Scanner(System.in);

    // Make constructor for properties
    public Player(String name,  Characters character) {
        setName(name);
        setCharacterType(character);
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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
        System.out.println("What should you do?");

        Scanner in = new Scanner(System.in);
        System.out.println();
        TimeUnit.SECONDS.sleep(1);
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

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

}