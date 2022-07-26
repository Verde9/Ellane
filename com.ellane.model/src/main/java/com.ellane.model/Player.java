package com.ellane.model;
import com.google.gson.Gson;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Player {
    private String name;
    private Integer health;
    //private CHARACTER CharacterType;
    ArrayList<String> inventory = new ArrayList<>();
    private Array Room[] = new Array[1];

    String decision;


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

    public String makeDecision() throws InterruptedException {

        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to do: ");
        System.out.println("Enter CONTROLS to get game controls");
        System.out.println();
        //TimeUnit.SECONDS.sleep(1);
        String decision = in.nextLine();
        return  decision;
    }




    public ArrayList<String> getInventory() {
        return inventory;
    }

}