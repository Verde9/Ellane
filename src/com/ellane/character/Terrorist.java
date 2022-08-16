package com.ellane.character;

import com.ellane.app.EllaneApp;
import com.ellane.gui.MainScreen;
import com.ellane.gui.TypeWriter;
import com.ellane.view.EllaneView;

import java.util.Scanner;


public class Terrorist {

    private String name;


    public Terrorist(String name) {
        this.name = name;
    }

    // This method initiates the terrorist and player fight depending on which room the terrorist is in.

    public static void terrorStart(Player player, Terrorist terrorist){

        // This will render the terrorist dialogue in the Ellane view class.
        TypeWriter typeWriter = new TypeWriter(MainScreen.outPut, EllaneView.renderTerroristDialogue(terrorist, player));
        typeWriter.start();


//        //MainScreen.outPut.setText(EllaneView.renderTerroristDialogue(terrorist, player));
//
//        // The while loop starts a prompt that asks the player if they want to fight or run
//
//        String[] choiceArr = choice.split(" ", 2);
//
//        String decision = MainScreen.input.getText();
//        return decision.split(" ", 2);
    }

    public static void PlayerDetected(Player player, Terrorist terrorist, String choice ){
        String[] choiceArr = choice.split(" ", 2);
        String firstWord = choiceArr[0];
        String secondWord = "";

            switch (firstWord) {

                // In the fight case it'll check if the players inventory is 0 in which case you cannot fight and have to select run.
                // This will also check if the player only has a gas mask or keys in their inventory in which case you can't fight and will have to run.
                // The else case here will allow the player to use certain items in their inventory to attack the terrorist in which case certain items will have either a higher probability or a lower probability of working and killing the terrorist.

                case "fight":
                    if (player.getInventory().size() != 0) {
                        if(player.getInventory().size() == 1 && player.getInventory().contains("keys") || player.getInventory().size() == 1 && player.getInventory().contains("gas mask")) {
                            MainScreen.outPut.setText("You can't do anything with the item in your inventory you need to run to possibly survive.");
                        } else {
                            int number;
                            MainScreen.outPut.setText(player.getName() + ":" + "Fuck... I guess there's no other way out of this.\n I should use an item in my inventory!");

                            if (true) {


                                String fightChoice = MainScreen.input.getText();

                                if (firstWord.equals("use")) {
                                    if (player.getInventory().contains(secondWord)) {

                                        switch (secondWord) {
                                            case "gun":
                                                player.getInventory().remove("gun");
                                                MainScreen.outPut.setText(player.getName() + ":" + " Oh I got something for you!\n"+
                                                        "*BANG BANG BANG BANG BANG*");

                                                number = (int) (Math.random() * (100 - 1 + 1) + 1);

                                                if (number > 10) {
                                                    MainScreen.outPut.setText(player.getName() + ":" + " Oh I got something for you!\n"+
                                                            "*BANG BANG BANG BANG BANG*\n"+
                                                            player.getName() + ":" + "Вы выиграли эту битву, американец, но вы не выиграли...\n"+
                                                            "(Translation: You've won this battle American, but you have not won the.. ) \n"+
                                                            player.getName() + ":" + " *Dies*");
                                                } else {
                                                    MainScreen.outPut.setText(player.getName() + ":" + " Oh I got something for you!\n"+
                                                            "*BANG BANG BANG BANG BANG*\n"+
                                                            "You tried to shoot Ivan but missed. Ivan unloads a whole mag into you.\n"+
                                                            "You die. Game Over");
                                                }
                                                break;
                                            case "pocket knife":
                                                player.getInventory().remove("pocket knife");
                                                number = (int) (Math.random() * (100 - 1 + 1) + 1);

                                                if (number > 50) {
                                                    MainScreen.outPut.setText(player.getName() + ":" + " Oh I got something for you!\n"+
                                                            "* Attempts to stab Ivan *\n"+
                                                            player.getName() + ":" + "Вы выиграли эту битву, американец, но вы не выиграли...\n"+
                                                            "(Translation: You've won this battle American, but you have not won the.. ) \n"+
                                                            player.getName() + ":" + " *Dies*");

                                                } else {
                                                    MainScreen.outPut.setText(player.getName() + ":" + " Oh I got something for you!\n"+
                                                            "* Attempts to stab Ivan *\n"+
                                                            "You tried to stab Ivan but missed. Ivan unloads a whole mag into you.\n"+
                                                            "You die. Game Over");
                                                }
                                                break;
                                            case "bat":
                                            case "pole":
                                                player.getInventory().remove(secondWord);
                                                number = (int) (Math.random() * (100 - 1 + 1) + 1);

                                                if (number > 30) {
                                                    MainScreen.outPut.setText(player.getName() + ":" + " Oh I got something for you!\n"+
                                                            "* Attempts to hit Ivan with pole *\n"+
                                                            player.getName() + ":" + "Вы выиграли эту битву, американец, но вы не выиграли...\n"+
                                                            "(Translation: You've won this battle American, but you have not won the.. ) \n"+
                                                            player.getName() + ":" + " *Dies*");

                                                } else {
                                                    MainScreen.outPut.setText(player.getName() + ":" + " Oh I got something for you!\n"+
                                                            "* Attempts to stab Ivan *\n"+
                                                            "You tried to hit Ivan with a pole but missed. Ivan unloads a whole mag into you.\n"+
                                                            "You die. Game Over");
                                                }
                                                break;
                                            default:
                                                MainScreen.outPut.setText(player.getName() + ":" + "I can't use that item, I need to use something else!");
                                                break;
                                        }
                                    }
                                } else {
                                    MainScreen.outPut.setText("Invalid Command. Type 'use' + item in inventory.");
                                }

                            }
                        }
                    } else {
                        MainScreen.outPut.setText("You're inventory is empty! You're going to have to run to hopefully survive!");
                    }
                    break;

                // The run case will allow the player to try to escape, but they will still take damage which could potentially kill them and end the game.

                case "run":
                    player.decreaseHealth((int) (Math.random() * (60 - 30 + 1) + 30));
                    if (player.getHealth() <= 0) {
                        MainScreen.outPut.setText(player.getName() + ":" + "Пытаешься уйти, да? Дай я тебе кое-что возьму с собой!\n"+
                                "(Translation: Trying to get away huh? Let me give you something to take with you!)\n"+
                                player.getName() + " shot you in the back while you tried to run and your health is now " + player.getHealth()+
                                "\nYou've died trying to escape. Game Over.");
                    } else {
                        MainScreen.invHealth.setText(" Health: " + player.getHealth() + "\n Inventory: " + player.getInventory().toString());
                        MainScreen.outPut.setText(player.getName() + ":" + "Пытаешься уйти, да? Дай я тебе кое-что возьму с собой!\n"+
                                "(Translation: Trying to get away huh? Let me give you something to take with you!)\n"+
                                player.getName() + " shot you in the back while you tried to run\n"+
                                "You're severely injured but you've managed to escape.\n Your health is now at " + player.getHealth());
                        MainScreen.isTerrorist= false;
                    }
                    break;
                default:
                    MainScreen.outPut.setText("Invalid Input. Type 'fight' or 'run");

            }
    }

    public String getName() {
        return name;
    }

}