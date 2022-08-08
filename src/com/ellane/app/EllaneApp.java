package com.ellane.app;

import com.ellane.model.Locations;
import com.ellane.model.Player;
import com.ellane.view.EllaneView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class EllaneApp {
    private String firstWord;
    private String secondWord;
    private Locations currentRoom;
    Boolean gameOver = false;
    List<Locations> location;


    Player player = new Player();
    private static EllaneView view = new EllaneView();
    Scanner scan = new Scanner(System.in);


    //this will run the app in the main class
    public void initialize() throws InterruptedException, IOException {
        view = new EllaneView();
        view.renderWelcomeGameMessage();
        promptToStartGame();
    }

    public void checkEndGameConditions() throws NullPointerException {
        if (player.getHealth() <= 0 || firstWord.equals("quit") || gameOver == true) {
            gameOver = true;
            view.renderEndGameMessageAndResults(player.getHealth());
            System.exit(0);
        }
    }

    private void promptToStartGame() throws InterruptedException, IOException {
        introMusic("src/Music/For Work 2_1.wav");
        view.renderBeginningPlayGameMessage();
        createPlayerOneCharacter();
    }


    public void generateLocation() {
        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("rooms.json"));

            location = new Gson().fromJson(reader, new TypeToken<List<Locations>>() {}.getType());

            currentRoom = location.get(0);

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createPlayerOneCharacter() throws IOException, InterruptedException {
        boolean valid = false;
        String playerName = "";
        while (playerName.length() <= 0) {
            System.out.println();
            view.renderEnterNameMessage();
            playerName = scan.nextLine();
            if (playerName.length() <= 0) {
                view.renderEnterNameEnforceMessage();
            } else {
                player.setName(playerName);
            }
        }
        startGame();
    }

    private void startGame() throws InterruptedException, IOException {
        view.renderDisplayGameInfo();
        generateLocation();
        while (!gameOver) {
            promptPlayerForDecision();
        }
        view.renderEndGameMessageAndResults(player.getHealth());
    }

    private void promptPlayerForDecision() throws InterruptedException {
        System.out.println("What should you do?");

        Scanner in = new Scanner(System.in);
        System.out.println();
        TimeUnit.SECONDS.sleep(1);
        String decision = in.nextLine();

        String[] stringArr = decision.split(" ");
        firstWord = stringArr[0].toLowerCase();

        if (stringArr.length == 1) {
            verifyFirstWord(firstWord);
        } else {
            secondWord = stringArr[1].toLowerCase();
            verifyFirstWord(firstWord);
        }

    }


    private void verifyFirstWord(String firstWord) throws InterruptedException {

        switch (firstWord) {
            case "look":
                System.out.println("You are in " + currentRoom.getName() + "\nYour inventory is: " + player.getInventory());
                System.out.println();
                player.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player.getHealth());
                promptPlayerForDecision();
                break;
            case "help":
                view.renderShowGameControls();
                promptPlayerForDecision();
                break;
            case "go":
                System.out.println();
                verifyRoomMovement();
                System.out.println();
                player.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player.getHealth());
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "inventory":
                System.out.println("Your inventory is: " + player.getInventory());
                break;
            case "grab":
            case "get":
            case "take":
            case "pick":
                //Todo: logic to grab item
            case "drop":
                if (secondWord != null) {
                    if (player.getInventory().contains(secondWord)) {
                        player.getInventory().remove(secondWord);
                        System.out.println("You have removed this " + secondWord);
                        System.out.println();
                        player.decreaseHealth(2);
                        view.renderRemainingPlayerHealth(player.getHealth());
                        checkEndGameConditions();
                        promptPlayerForDecision();
                    } else{
                        System.out.println("You don't have that item to drop.");
                        System.out.println();
                    }
                    break;
                }
                else {
                    System.out.println("What do you want to drop?");
                    System.out.println("Your inventory is: " + player.getInventory());
                    System.out.println();
                }
                break;
            case "health":
                System.out.println("Your current health is: " + player.getHealth());
                System.out.println();
                break;
            case "use":
                // TODO: way to use items.
            case "quit":
                checkEndGameConditions();
                break;
            case "play":
                if (secondWord.equals("music")) {
                    System.out.println("These are the directions for the music player");
                    runMusic("Music/intro wav 2_1.wav");
                }
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            default:
                view.renderInvalidCommandMessage();
                view.renderShowGameControls();
                promptPlayerForDecision();
                System.out.println();
        }
    }

    private void verifyRoomMovement() throws InterruptedException {
        String decision;

        if (firstWord.equals("go")) {
            if (secondWord != null) {
                switch (secondWord) {
                    case "east":
                    case "west":
                    case "north":
                    case "south":
                        System.out.println();
                        verifyLocation();
                        break;
                    default:
                        System.out.println("You can't go that way.");
                        break;
                }
            }
            else {
                System.out.println("Go where?\n[north, south, east, west]");
                System.out.println();
                promptPlayerForDecision();
            }
        }

    }

    private void verifyLocation() {

        switch (secondWord) {
            case "north":
                if (currentRoom.getNorth().equals("N/A")) {
                    System.out.println("You can't go that way.");
                } else {
                    for (Locations room : location) {
                        if (room.getName().equals(currentRoom.getNorth())) {
                            currentRoom = room;
                            System.out.println("You are now in the " + currentRoom.getName());
                        }
                    }

                }
                break;
            case "south":
                if (currentRoom.getSouth().equals("N/A")) {
                    System.out.println("You can't go that way.");
                } else {
                    for (Locations room : location) {
                        if (room.getName().equals(currentRoom.getSouth())) {
                            currentRoom = room;
                            System.out.println("You are now in the " + currentRoom.getName());
                        }
                    }
                }
                break;
            case "east":
                if (currentRoom.getEast().equals("N/A")) {
                    System.out.println("You can't go that way.");
                } else {
                    for (Locations room : location) {
                        if (room.getName().equals(currentRoom.getEast())) {
                            currentRoom = room;
                            System.out.println("You are now in the " + currentRoom.getName());
                        }
                    }
                }
                break;
            case "west":
                if (currentRoom.getWest().equals("N/A")) {
                    System.out.println("You can't go that way.");
                } else {
                    for (Locations room : location) {
                        if (room.getName().equals(currentRoom.getWest())) {
                            currentRoom = room;
                            System.out.println("You are now in the " + currentRoom.getName());
                        }
                    }
                }
                break;
            default:
                System.out.println("You can't go that way.");
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


            while (!response.equals("Q")) {
                System.out.println("P = Play Music, S= Stop Music, R=Reset, V =Volume, Q= quit music player");
                System.out.println("Enter your choice: ");
                response = scanner.next();
                response = response.toUpperCase();

                switch (response) {
                    case ("P"):
                        clip.start();
                        break;
                    case ("S"):
                        clip.stop();
                        break;
                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;
                    case ("V"):
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
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
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


            while (!response.equals("Q")) {
                System.out.println("P = Play Music, S= Stop Music, R=Reset, V =Volume Q= STOP MUSIC.. and Begin Game");
                System.out.println("Enter your choice: ");
                response = scanner.next();
                response = response.toUpperCase();

                switch (response) {
                    case ("P"):
                        clip.start();
                        break;
                    case ("S"):
                        clip.stop();
                        break;
                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;
                    case ("V"):
                        System.out.println("what do you want the level to be? :  (-17 is lower)");
                        String setVolume = scanner.next();
                        gainControl.setValue(Float.parseFloat(String.valueOf(setVolume)));
                        break;
                    case ("Q"):
                        System.out.println("Thank you for quitting the music player... Now Let's PLAY!");
                        clip.close();
                        System.out.println();
                        break;
                    default:
                        System.out.println("not a valid response for music player");
                        break;
                }
            }

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }


    }

    public Boolean getGameOver () {
        return gameOver;
    }

}