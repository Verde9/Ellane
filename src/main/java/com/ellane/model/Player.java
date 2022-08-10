package com.ellane.model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Player {
    private String name;
    private Integer health = 60;
    ArrayList<String> inventory = new ArrayList<>();

    // Make constructor for properties

    public Player() {

    }

    public Player(String name) {
        this.name = name;
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

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void decreaseHealth(int decreaseAmount) {
        health -= decreaseAmount;
    }

    public void increaseHealth(int increaseAmount) {
        health += increaseAmount;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public int setHealth(Integer health) {
        this.health = health;
        return health;
    }
}