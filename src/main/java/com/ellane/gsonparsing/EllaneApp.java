package com.ellane.gsonparsing;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class EllaneApp {
    private int roundCount = 50;
    Boolean gameEnds = false;


    Scanner scan = new Scanner(System.in);

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
        introMusic("src/Music/For Work 2_1.wav");
        System.out.println("Do you wish to play? type 'yes' or type 'quit game'");
        String userInput = scan.nextLine().toLowerCase();
        System.out.println("user input: " + userInput);

        if(userInput.equals("quit game")) {
            System.out.println("...Thanks for playing! Goodbye!");
        }

        if(userInput.equals("yes")) {
            System.out.println("Let's Play!");
            startGame();
        } else {
            System.out.println("INVALID INPUT");
        }
    }

    private void startGame() throws InterruptedException {
        introMusic("src/Music/For Work 2_1.wav");
        displayGameInfo();

    }

    private void displayGameInfo() throws InterruptedException {
        System.out.println("The chaos spreads & the bombs keep exploding around the city");
        TimeUnit.SECONDS.sleep((long) 2.5);
        System.out.println("The fire is spreading from building to building & most signs of life as gone!");
        TimeUnit.SECONDS.sleep((long) 2.5);
        System.out.println("you get stuck inside of a building, but it can collapse at any minute & fire is spreading");
        TimeUnit.SECONDS.sleep((long) 2.5);
        System.out.println("Luckily there id a helicopter on the roof evacuating the survivors that made it to the roof");
        TimeUnit.SECONDS.sleep((long) 2.5);
        System.out.println("Unfortunately, you have been wounded & are losing blood as more time passes");
        TimeUnit.SECONDS.sleep((long) 2.5);
        System.out.println();
        TimeUnit.SECONDS.sleep((long) 2.5);
        System.out.println("Time is ticking & you don't have much time!");
        TimeUnit.SECONDS.sleep((long) 2.5);
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
        Player player = new Player();
        player.makeDecision();

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
                System.out.println("P = Play Music, S= Stop Music, R=Reset, V =Volume");
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
                    default:
                        System.out.println("not a valid response for music player");


                }
            }


        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }


    }
}


