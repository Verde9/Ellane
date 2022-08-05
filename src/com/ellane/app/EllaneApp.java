package com.ellane.app;

import com.ellane.model.*;
import com.ellane.view.EllaneView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EllaneApp {
    private String firstWord;
    private String secondWord;
    private Locations currentRoom;
    Boolean gameOver = false;
    ArrayList<String> inventory = new ArrayList<>(3);
    Boolean ellaneFound = false;
    Boolean ellaneCured = false;
    String ellaneLocation = "";
    String bombLocation = "";
    Boolean bombFound = false;
    List<Items> gameItems = new ArrayList<>();
    List<LocationsAndDirections> playerLocations = new ArrayList<>();
    List<Locations> location;
    Map<String, String> playerlocations3;
    Map<String, String> generateBasementLocation;
    Map<String, String> generateCommon_Area_Location;
    Map<String, String> generateLobbyLocation;
    Map<String, String> generateMechanicalRoomLocation;
    Map<String, String> generateOffice_1_Location;
    Map<String, String> generateOffice_Floor_1_location;
    Map<String, String> generateOffice_Floor_2_location;
    Map<String, String> generateOffice_Floor_3_location;
    Map<String, String> generateOffice_Floor_4_location;
    Map<String, String> generateRooftop_location;


    Player player1;
    private static EllaneView view = new EllaneView();
    Scanner scan = new Scanner(System.in);
    Player player = new Player("LB", com.ellane.model.Characters.MALE_SOLDIER);


    //this will run the app in the main class
    public void initialize() throws InterruptedException, IOException {
        view = new EllaneView();
        view.renderWelcomeGameMessage();
        promptToStartGame();
    }

    public void checkEndGameConditions() throws NullPointerException {
        if (player1.getHealth() <= 0 || firstWord.equals("quit") || gameOver == true) {
            gameOver = true;
            view.renderEndGameMessageAndResults(player1.getHealth());
            System.exit(0);
        }
    }


    public void checkIfEllaneIsHere() {
        if (currentRoom.equals(ellaneLocation)) {
            ellaneFound = true;
            view.renderFoundEllaneLocationMessage(ellaneLocation);
        }
    }


    public void checkForBombInRoom() {
        bombFound = true;
        if (currentRoom.equals(bombLocation)) {
            bombFound = true;
            gameOver = true;
            view.renderBombFoundExplosionMessage();
        }
    }

    private void promptToStartGame() throws InterruptedException, IOException {
        introMusic("src/Music/For Work 2_1.wav");
        view.renderPromptToPlayOrQuitGame();
        String userInput = scan.nextLine().toLowerCase();
        if (userInput.equals("quit game")) {
            gameOver = true;
            view.renderImmediateQuitGameMessage();
            System.exit(0);
        }
        if (userInput.equals("yes")) {
            System.out.println();
            view.renderBeginningPlayGameMessage();
            createPlayerOneCharacter();
        } else {
            view.renderInvalidCommandMessage();
            System.out.println();
            promptToStartGame();
        }
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
            }
        }
        System.out.println();
        while (!valid) {
            view.renderSelectCharacterMessage();
            for (Characters character : Characters.values()) {
                System.out.println(character.getCharacterType());
            }
            System.out.println();
            String characterChoice = scan.nextLine().toLowerCase();
            for (Characters character : Characters.values()) {
                if (characterChoice.equals(character.getCharacterType())) {
                    System.out.println();
                    player1 = new Player(playerName, character);
                    view.renderPlayerCreatedMessage();
                    view.renderPlayerNameBeginGameMessage(player1.getName());
                    startGame();
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                System.out.println();
                view.renderInvalidCharacterSelectedMessage();
            }
        }
    }

    private void startGame() throws InterruptedException, IOException {
        view.renderDisplayGameInfo();
        generateLocation();
        checkIfEllaneIsHere();
        checkForBombInRoom();
        while (!gameOver) {
            promptPlayerForDecision();
        }
        view.renderEndGameMessageAndResults(player1.getHealth());
    }

    private void promptPlayerForDecision() throws InterruptedException {
        String decision = player1.makeDecision();
        verifyDecision(decision);
    }

    //TODO: (delete this comment later),this is making a String Array of our words so like ["john", "doe"]
    private void verifyDecision(String decision) throws InterruptedException {
        String[] stringArr = decision.split(" ");
        firstWord = stringArr[0].toLowerCase();
        try {
            if (stringArr.length < 1) {
                view.renderCommandIsRequiredMessage();
                promptPlayerForDecision();
            } else if (stringArr.length == 1) {
                verifyFirstWord(firstWord);
            } else {
                secondWord = stringArr[1].toLowerCase();
                verifyFirstWord(firstWord);
            }
        } catch (Exception e) {
            view.renderInvalidCommandMessage();
        }
    }

    //TODO: MAIN GAME LOGIC
    private void verifyFirstWord(String firstWord) throws InterruptedException, IOException {

        switch (firstWord) {
            case "look":
                System.out.println("You are in " + currentRoom.getName()+ "\nYour inventory is: " + getInventory());
                System.out.println();
                player1.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player1.getHealth());
                promptPlayerForDecision();
                break;
            case "help":
                view.renderShowGameControls();
                promptPlayerForDecision();
                break;
            case "go":
            case "climb":
                System.out.println();
                verifyRoomMovement();
                System.out.println();
                player1.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player1.getHealth());
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "inventory":
                System.out.println("Your inventory is: " + getInventory());
                break;
            case "grab":
            case "get":
            case "take":
            case "pick":
                for (LocationsAndDirections items: playerLocations){
                    verifyRoomMovement();
                    if (items.getItem().equals(secondWord)) {
                        inventory.add(items.getItem());
                        System.out.println(items.getItem_status());
                        System.out.println();
                        break;
                    }
                    if (items.getItem2().equals(secondWord)) {
                        inventory.add(items.getItem2());
                        System.out.println(items.getItem_status2());
                        System.out.println();
                        break;
                    }
                    else {
                        System.out.println();
                        System.out.println("you should probably check your inventory...");
                    }
                }
                player1.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player1.getHealth());
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "drop":
                if (inventory.contains(secondWord)) {
                    inventory.remove(secondWord);
                    System.out.println("you have removed this item");
                }
                System.out.println();
                player1.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player1.getHealth());
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "health":
                System.out.println("Your current health is: " + player1.getHealth());
                System.out.println();
                break;
            case "use":
                if (inventory.contains("bazooka")) {
                    if (generateBasementLocation.containsKey("item2") && generateBasementLocation.containsValue("bazooka")) {
                        System.out.println("you used the bazooka the BLOW THE PLACE DOWWWWWWWN");
                        int Min = 1;
                        int Max = 20;
                        int newHealth = (int) (Math.random() * ((Max - Min) + 1));
                        player.decreaseHealth(newHealth);
                        System.out.println("You Fucking hit yourself dummy... the player health is now "
                                + newHealth);
                        if (newHealth >= 10) {
                            System.out.println("its dangerous to use the damn bazooka...");
                        }
                        if (newHealth < 10 && newHealth > 1) {
                            System.out.println("Im never doing that stupid shit again");
                        }
                        if (newHealth <= 0) {
                            System.out.println("...AND you fucking died.... using the bazooka you fucking idiot," +
                                    "damn that sucks");
                            System.out.println();
                            gameOver = true;
                        }
                    }
                } else {
                    System.out.println("The world aint ready for that");
                }
                System.out.println();
                player1.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player1.getHealth());
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
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

    private void verifyRoomMovement() throws InterruptedException, IOException {
        String decision;

        if (firstWord.equals("go")) {
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
                    decision = player1.makeDecision();
                    verifyDecision(decision);
                    break;
            }
        }

        if (firstWord.equals("climb")) {
            switch (secondWord) {
                case "up":
                case "down":
                    System.out.println();
                    verifyLocation();
                    break;
                default:
                    System.out.println("You can't do that.");
                    decision = player1.makeDecision();
                    verifyDecision(decision);
                    break;
            }
        }
    }

    private void verifyLocation() {

        switch (secondWord) {
            case "north":
              if(currentRoom.getNorth().equals(null)) {
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
                if(currentRoom.getSouth().equals(null)) {
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
                if(currentRoom.getEast().equals(null)) {
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
                if(currentRoom.getWest().equals(null)) {
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

    public ArrayList<String> getInventory () {
        return inventory;
    }

    public Boolean getGameOver () {
        return gameOver;
    }

}