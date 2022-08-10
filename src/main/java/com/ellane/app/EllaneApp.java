package com.ellane.app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ellane.model.Items;
import com.ellane.model.Locations;
import com.ellane.model.Player;
import com.ellane.view.EllaneView;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.CookieHandler;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class EllaneApp {
    private String firstWord;
    private String secondWord;
    private Locations currentRoom;
    Boolean gameOver = false;
    List<Locations> locations;
    List<Items> items = new ArrayList<>();


    Player player = new Player();
    Items item = new Items("empty");
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
        introMusic("src/main/resources/Music/For Work 2_1.wav");
        createPlayerOneCharacter();
    }

    public void generateItems() {
        Items gun = new Items("gun","I see a gun on the ground! Let me check the mag...... and it's fully loaded! I should probably pick this up.");
        Items pocketKnife = new Items("pocket knife", "it looks like there is a pocket knife on the ground! It Looks dull, but it could help in the future.");
        Items bat = new Items("bat", "I think that's a bat in the corner. Should probably take that, it could come in handy.");
        Items gasMask = new Items("gas mask", "I see a gas mask! This could help me breath through all of this smog!");
        Items pole = new Items("pole", "Hmm a pole, this could be used as a weapon, should maybe take this with me!");
        Items keys = new Items("keys", "No... it looks like Ralph the maintenance man didn't make it.. May he rest in peace. I should probaly check if he has his keys on him though." );
        Items blank = new Items("empty");
        Items blank2 = new Items("empty");
        Items blank3 = new Items("empty");
        Items blank4 = new Items("empty");
        Items blank5 = new Items("empty");


        items.add(gun);
        items.add(pocketKnife);
        items.add(bat);
        items.add(gasMask);
        items.add(pole);
        items.add(keys);
        items.add(blank);
        items.add(blank2);
        items.add(blank3);
        items.add(blank4);
        items.add(blank5);

        Collections.shuffle(items);
    }


    public void generateLocation() {
        List<Integer> num = new ArrayList<>();

        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);

        generateItems();

        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/RoomsAndContent/rooms.json"));

            locations = new Gson().fromJson(reader, new TypeToken<List<Locations>>() {}.getType());

            currentRoom = locations.get(0);

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        for(int i = 0; i < locations.size() - 4; i++) {
            Locations currentLocation = locations.get(i);

            currentLocation.setItem(items.get(i));

            Collections.shuffle(num);

            currentLocation.setItemPlacementNorth(num.get(0));
            currentLocation.setItemPlacementEast(num.get(1));
            currentLocation.setItemPlacementSouth(num.get(2));
            currentLocation.setItemPlacementWest(num.get(3));

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
        System.out.println("You are currently in the " + currentRoom.getName() + " and you current health is: " + player.getHealth());
        System.out.println();
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

        String[] stringArr = decision.split(" ", 2);
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
            case "go":
                System.out.println();
                verifyRoomMovement();
                player.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player.getHealth());
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "help":
                view.renderShowGameControls();
                promptPlayerForDecision();
                break;
            case "status":
                System.out.println();
                System.out.println(currentRoom.getDescription());
                System.out.println();
                System.out.println(player.getName() + "'s inventory is: " + player.getInventory());
                System.out.println();
                System.out.println(player.getName() + "'s current health is: " + player.getHealth());
                System.out.println();
                promptPlayerForDecision();
                break;
            case "inventory":
                System.out.println(player.getName() + "'s inventory is: " + player.getInventory());
                break;
            case "grab":
            case "get":
            case "take":
                if (secondWord.equals(currentRoom.getItem().getName())) {
                    player.getInventory().add(secondWord);
                    System.out.println(player.getName() + " added the " + currentRoom.getItem().getName() + " to their inventory! ");
                    currentRoom.setItem(item);
                }
                player.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player.getHealth());
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "drop":
                if (player.getInventory().contains(secondWord)) {
                    player.getInventory().remove(secondWord);
                    System.out.println(player.getName() + "removed " + secondWord + " from your inventory." );
                }
                System.out.println();
                player.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player.getHealth());
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "health":
                System.out.println(player.getName() + "'s  current health is: " + player.getHealth());
                System.out.println();
                break;
            case "quit":
                checkEndGameConditions();
                break;
            default:
                System.out.println("Invalid Command");
                view.renderShowGameControls();
                promptPlayerForDecision();
                System.out.println();
        }
    }

    private void verifyRoomMovement() throws InterruptedException {


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
                    System.out.println(player.getName() + ": " +"I can't go that way.");
                    System.out.println();
                    break;
            }
        }

        if (firstWord.equals("look")) {
            switch (secondWord) {
                case "east":
                    System.out.println(player.getName() + ": " + currentRoom.getEastDescription());
                    if(!currentRoom.getItem().getName().equals("empty") && (currentRoom.getItemPlacementEast() == 1)) {
                        System.out.println(player.getName() + ": " + currentRoom.getItem().getItem_description());
                    }
                    System.out.println();
                    break;
                case "west":
                    System.out.println(player.getName() + ": " + currentRoom.getWestDescription());
                    if(!currentRoom.getItem().getName().equals("empty") && (currentRoom.getItemPlacementWest() == 1)) {
                        System.out.println(player.getName() + ": " + currentRoom.getItem().getItem_description());
                    }
                    System.out.println();
                    break;
                case "south":
                    System.out.println(player.getName() + ": " + currentRoom.getSouthDescription());
                    if(!currentRoom.getItem().getName().equals("empty") && (currentRoom.getItemPlacementSouth() == 1)) {
                        System.out.println(player.getName() + ": " + currentRoom.getItem().getItem_description());
                    }
                    System.out.println();
                    break;
                case "north":
                    System.out.println(player.getName() + ": " + currentRoom.getNorthDescription());
                    if(!currentRoom.getItem().getName().equals("empty") && (currentRoom.getItemPlacementNorth() == 1)) {
                        System.out.println(player.getName() + ": " + currentRoom.getItem().getItem_description());
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid Commnad: Try 'look' + [north, south, east, west]");
                    System.out.println();
            }
        }
    }

    private void verifyLocation() {

        switch (secondWord) {
            case "north":
                if (currentRoom.getNorth().equals("N/A")) {
                    System.out.println(player.getName() + ": " + " I can't go that way.");
                } else {
                    for (Locations room : locations) {
                        if (room.getName().equals(currentRoom.getNorth())) {
                            currentRoom = room;
                            System.out.println(player.getName() + ": " + currentRoom.getDescription());
                            break;
                        }
                    }

                }
                System.out.println();
                break;
            case "south":
                if (currentRoom.getSouth().equals("N/A")) {
                    System.out.println("I can't go that way.");
                } else {
                    for (Locations room : locations) {
                        if (room.getName().equals(currentRoom.getSouth())) {
                            currentRoom = room;
                            System.out.println(player.getName() + ": " + currentRoom.getDescription());
                            break;
                        }
                    }
                }
                System.out.println();
                break;
            case "east":
                if (currentRoom.getEast().equals("N/A")) {
                    System.out.println("I can't go that way.");
                } else {
                    for (Locations room : locations) {
                        if (room.getName().equals(currentRoom.getEast())) {
                            currentRoom = room;
                            System.out.println(player.getName() + ": " + currentRoom.getDescription());
                            break;
                        }
                    }
                }
                System.out.println();
                break;
            case "west":
                if (currentRoom.getWest().equals("N/A")) {
                    System.out.println("I can't go that way.");
                } else {
                    for (Locations room : locations) {
                        if (room.getName().equals(currentRoom.getWest())) {
                            currentRoom = room;
                            System.out.println(player.getName() + ": " + currentRoom.getDescription());
                            break;
                        }
                    }
                }
                System.out.println();
                break;
            default:
                System.out.println(player.getName() + ": " + "I can't go that way.");
                System.out.println();
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
