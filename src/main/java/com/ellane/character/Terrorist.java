package com.ellane.character;

import com.ellane.view.EllaneView;

import java.util.Locale;
import java.util.Scanner;


public class Terrorist {

    private String name;


    public Terrorist(String name) {
        this.name = name;
    }


    public void PlayerDetected(Player player, Terrorist terrorist) {
        Scanner scanner = new Scanner(System.in);
        EllaneView view = new EllaneView();
        boolean valid = true;
        String firstWord = "";
        String secondWord = "";

        view.terroristDialogue(terrorist, player);

        while (valid) {
            System.out.println(player.getName() + ":" + " Should I fight or should I run?");

            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "fight":
                    if (player.getInventory().size() != 0) {
                        boolean decision = true;
                        int number;
                        System.out.println(player.getName() + ":" + "Fuck... I guess there's no other way out of this.");

                        while (decision) {
                            System.out.println("Here is your current inventory: " + player.getInventory());
                            System.out.println();
                            System.out.println("Commands: Type 'use' + item in inventory.");
                            System.out.println();
                            System.out.println("What should you do?");


                            String fightChoice = scanner.nextLine().toLowerCase();


                            try {
                                String[] stringArr = fightChoice.split(" ", 2);
                                firstWord = stringArr[0];
                                secondWord = stringArr[1];
                            } catch (Exception ignored) {

                            }


                            if (firstWord.equals("use")) {
                                if (player.getInventory().contains(secondWord)) {

                                    switch (secondWord) {
                                        case "gun":
                                            player.getInventory().remove("gun");
                                            System.out.println(player.getName() + ":" + " Oh I got something for you!");
                                            System.out.println();
                                            System.out.println("*BANG BANG BANG BANG BANG*");

                                            number = (int) (Math.random() * (100 - 1 + 1) + 1);

                                            if (number > 10) {
                                                System.out.println(getName() + ":" + "Вы выиграли эту битву, американец, но вы не выиграли...");
                                                System.out.println("(Translation: You've won this battle American, but you have not won the.. ) ");
                                                System.out.println();
                                                System.out.println(getName() + ":" + " *Dies*");
                                            } else {
                                                System.out.println("You tried to shoot Ivan but missed. Ivan unloads a whole mag into you.");
                                                System.out.println();
                                                System.out.println("You die. Game Over");
                                                System.exit(0);
                                            }
                                            decision = false;
                                            valid = false;
                                            break;
                                        case "pocket knife":
                                            player.getInventory().remove("pocket knife");
                                            System.out.println(player.getName() + ":" + " Oh I got something for you!");
                                            System.out.println();
                                            System.out.println("* Attempts to stab Ivan *");

                                            number = (int) (Math.random() * (100 - 1 + 1) + 1);

                                            if (number > 50) {
                                                System.out.println(getName() + ":" + "Вы выиграли эту битву, американец, но вы не выиграли...");
                                                System.out.println("(Translation: You've won this battle American, but you have not won the.. ) ");
                                                System.out.println();
                                                System.out.println(getName() + ":" + " *Dies*");

                                            } else {
                                                System.out.println("You tried to stab Ivan but missed. Ivan unloads a whole mag into you.");
                                                System.out.println();
                                                System.out.println("You die. Game Over");
                                                System.exit(0);
                                            }
                                            decision = false;
                                            valid = false;
                                            break;
                                        case "bat":
                                        case "pole":
                                            player.getInventory().remove(secondWord);
                                            System.out.println(player.getName() + ":" + " Oh I got something for you!");
                                            System.out.println();
                                            System.out.println("* Attempts to hit Ivan with pole *");

                                            number = (int) (Math.random() * (100 - 1 + 1) + 1);

                                            if (number > 30) {
                                                System.out.println(getName() + ":" + "Вы выиграли эту битву, американец, но вы не выиграли...");
                                                System.out.println("(Translation: You've won this battle American, but you have not won the.. ) ");
                                                System.out.println();
                                                System.out.println(getName() + ":" + " *Dies*");

                                            } else {
                                                System.out.println("You tried to hit Ivan with a pole but missed. Ivan unloads a whole mag into you.");
                                                System.out.println();
                                                System.out.println("You die. Game Over");
                                                System.exit(0);
                                            }
                                            decision = false;
                                            valid = false;
                                            break;

                                    }
                                }
                            } else {
                                System.out.println("Invalid Command. Type 'use' + item in inventory.");
                            }

                        }

                    } else {
                        System.out.println("You're inventory is empty! You're going to have to run to hopefully survive!");
                    }
                    break;
                case "run":
                    System.out.println(getName() + ":" + "Пытаешься уйти, да? Дай я тебе кое-что возьму с собой!");
                    System.out.println("(Translation: Trying to get away huh? Let me give you something to take with you!)");
                    player.decreaseHealth((int) (Math.random() * (60 - 30 + 1) + 30));
                    if (player.getHealth() <= 0) {
                        System.out.println(getName() + " shot you in the back while you tried to run and your health is now " + player.getHealth());
                        System.out.println("You've died trying to escape. Game Over.");
                        System.exit(0);
                    } else {
                        System.out.println(getName() + " shot you in the back while you tried to run");
                        System.out.println("You're severely injured but you've managed to escape. Your health is now at " + player.getHealth());
                        System.out.println();
                    }
                    valid = false;
                    break;
                default:
                    System.out.println("Invalid Input. Type 'fight' or 'run");

            }

        }


    }

    public String getName() {
        return name;
    }

}