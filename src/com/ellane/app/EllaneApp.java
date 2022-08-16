package com.ellane.app;

import com.ellane.character.Ellane;
import com.ellane.character.Terrorist;
import com.ellane.client.Main;
import com.ellane.gui.MainScreen;
import com.ellane.model.Items;
import com.ellane.model.Locations;
import com.ellane.character.Player;
import com.ellane.view.EllaneView;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class EllaneApp {

    // First word and second word variables are created here as well as the gameover Boolean. The locations list and items list are created here as well.

    private String firstWord;
    private String secondWord;
    private Locations currentRoom;
    Boolean gameOver = false;
    List<Locations> locations;
    List<Items> items = new ArrayList<>();


    // The character objects and items are created here as well as the 'Empty' objects which will be used later in the code.

    public static Player player = new Player();
    public static Terrorist terrorist = new Terrorist("Ivan");
    public static Terrorist terroristBlank = new Terrorist("Empty");
    public static Ellane ellane = new Ellane("Ellane");
    public static Ellane ellaneBlank = new Ellane("Empty");
    Items item = new Items("empty");
    EllaneView view = new EllaneView();


    // This will run the app in the main class

//    public void initialize() throws InterruptedException {
////        view = new EllaneView();
////        view.renderWelcomeGameMessage();
////        promptToStartGame();
//        startGame();
//
//    }

    // This method checks the end game conditions when the following values are met.

    public void checkEndGameConditions() throws NullPointerException {
        if (player.getHealth() <= 0 || firstWord.equals("quit") || gameOver) {
            gameOver = true;
            MainScreen.outPut.setText(view.renderEndGameMessageAndResults(player.getHealth()));
        }
    }

    // This method starts the intro music method and lets the user set their name.

//    private void promptToStartGame() throws InterruptedException {
//        introMusic("resources/music/For Work 2_1.wav");
//        setPlayerName();
//    }

    // This method creates all the items in the game and then add them to the items list which was created at the top of this page. It will also shuffle the items each time the game starts.

    public void generateItems() {
        Items gun = new Items("gun", "I see a gun on the ground! Let me check the mag...... and it's fully loaded! I should probably pick this up.");
        Items pocketKnife = new Items("pocket knife", "it looks like there is a pocket knife on the ground! It Looks dull, but it could help in the future.");
        Items bat = new Items("bat", "I think that's a bat in the corner. Should probably take that, it could come in handy.");
        Items pocketKnife2 = new Items("pocket knife", "it looks like there is a pocket knife on the ground! It Looks dull, but it could help in the future.");
        Items bat2 = new Items("bat", "I think that's a bat in the corner. Should probably take that, it could come in handy.");
        Items gasMask = new Items("gas mask", "I see a gas mask! This could help me breath through all of this smog!");
        Items pole = new Items("pole", "Hmm a pole, this could be used as a weapon, should maybe take this with me!");
        Items keys = new Items("keys", "No... it looks like Ralph the maintenance man didn't make it.. May he rest in peace. I should probably check if he has his keys on him though.");
        Items blank3 = new Items("empty");
        Items blank4 = new Items("empty");
        Items blank5 = new Items("empty");


        items.add(gun);
        items.add(pocketKnife);
        items.add(bat);
        items.add(gasMask);
        items.add(pole);
        items.add(keys);
        items.add(pocketKnife2);
        items.add(bat2);
        items.add(blank3);
        items.add(blank4);
        items.add(blank5);

        // This will shuffle the items each time the game starts.

        Collections.shuffle(items);
    }

    public void updateLocation(){
        MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getDescription());
    }


    // This method will pull the data from the json file and create new location objects. The locations also have the ellane, terrorists, and item objects attached here as well.

    public void generateLocation() {
        // This list sets number values to a list called num so that they can be used  to the 'itemPlacement' variables in the locations list.

        List<Integer> num = new ArrayList<>();

        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);

        // This try catch will use gson to pull the locations data from the rooms.json file and save it to the locations list.

        try {

            Reader reader = Files.newBufferedReader(Paths.get("resources/rooms/rooms.json"));
            Gson gson = new Gson();

            locations = gson.fromJson(reader, new TypeToken<List<Locations>>() {
            }.getType());

            currentRoom = locations.get(0);

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FILE NOT LOADING!");
        }

        // These methods generate the items as well as randomize the terrorist and ellane objects.

        generateItems();
        randomizeTerrorist();
        randomizeEllane();

        // This for loop will shuffle and place the items on each location's north, east, south, and west itemPlacement variables each time the game is started.

        for (int i = 0; i < locations.size() - 3; i++) {
            Locations currentLocation = locations.get(i);

            currentLocation.setItem(items.get(i));

            Collections.shuffle(num);

            currentLocation.setItemPlacementNorth(num.get(0));
            currentLocation.setItemPlacementEast(num.get(1));
            currentLocation.setItemPlacementSouth(num.get(2));
            currentLocation.setItemPlacementWest(num.get(3));

        }


    }

    public void randomizeTerrorist() {
        // This list sets number values to a list called num so that they can be used for the 'terroristPlacement' variables in the locations list.

        List<Integer> num = new ArrayList<>();

        num.add(0);
        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);
        num.add(5);
        num.add(6);
        num.add(7);

        Collections.shuffle(num);

        // This for loop will set the terrorist object in all the rooms except for the rooms ellan is possibly in, the roof, the roof staircase, and the lobby when the game starts.

        for (int i = 0; i < locations.size() - 3; i++) {
            Locations currentLocation = locations.get(i);

            if (i != 0) {
                currentLocation.setTerroristPlacement(num.get(i));

                if (currentLocation.getTerroristPlacement() == 3) {
                    currentLocation.setTerrorist(terrorist);
                }
            }

        }

    }

    public void randomizeEllane() {
        // This list sets number values to a list called num so that they can be used for the 'ellanePlacement' variables in the locations list.

        List<Integer> num = new ArrayList<>();

        num.add(0);
        num.add(1);

        Collections.shuffle(num);

        // These will only place Ellane in rooms 205 or rooms 310 in the locations list.

        locations.get(9).setEllanePlacement(num.get(0));
        locations.get(10).setEllanePlacement(num.get(1));

        if (locations.get(9).getEllanePlacement() == 1) {
            locations.get(9).setEllane(ellane);
        } else {
            locations.get(10).setEllane(ellane);
        }
    }


    // Allows user to set their name

//    public void setPlayerName() throws InterruptedException {
//        String playerName = "";
//        while (playerName.length() <= 0) {
//            System.out.println();
//            view.renderEnterNameMessage();
//            playerName = scan.nextLine();
//            if (playerName.length() <= 0) {
//                view.renderEnterNameEnforceMessage();
//            } else {
//                player.setName(playerName);
//            }
//        }
//        startGame();
//    }

    // This will displayGameInfo and generate the locations while also prompting the player for a decision.

    public void startGame() throws InterruptedException {
//        view.renderDisplayGameInfo();
        generateLocation();
        MainScreen.outPut.setText("You are currently in the " + currentRoom.getName() + " and your current health is: " + player.getHealth());
//        System.out.println();
//        while (!gameOver) {
//            promptPlayerForDecision();
//        }
//        view.renderEndGameMessageAndResults(player.getHealth());
    }

    // This method will split the users input into two strings and save it to a string array while also saving them to the first and secondword variables.

    public void promptPlayerForDecision() throws InterruptedException {

        String decision = MainScreen.input.getText();

        String[] stringArr = decision.split(" ", 2);
        firstWord = stringArr[0].toLowerCase();

        if (stringArr.length == 1) {
            verifyFirstWord(firstWord);
        } else {
            secondWord = stringArr[1].toLowerCase();
            verifyFirstWord(firstWord);
        }

    }


    // This method go through a switch case based on the user's first word

    private void updateHealthInv(){
        MainScreen.invHealth.setText(" Health: " + player.getHealth() + "\n Inventory: " + player.getInventory().toString());
    }

    private void verifyFirstWord(String firstWord) throws InterruptedException {

        switch (firstWord) {
            case "look":
            case "go":
                verifyRoomMovement();
                if (player.getInventory().contains("gas mask")) {
                    player.decreaseHealth(1);
                    updateHealthInv();
                } else {
                    player.decreaseHealth(2);
                    updateHealthInv();
                }
//                MainScreen.outPut.setText(view.renderRemainingPlayerHealth(player.getHealth()));
                checkEndGameConditions();
                break;
            case "grab":
            case "get":
            case "take":
                if (secondWord != null) {
                    if (secondWord.equals(currentRoom.getItem().getName())) {
                        if (currentRoom.getItem().getName().equals("gas mask")) {
                            player.getInventory().add(secondWord);
                            MainScreen.outPut.setText(player.getName() + " added the " + currentRoom.getItem().getName() + " to their inventory! \n"+
                                    player.getName() + ":" + " I'm going to put this gas mask on right away so I can breath easier!\n"+
                                    "Player health will now decrease by 1!");
                            currentRoom.setItem(item);
                        } else {
                            player.getInventory().add(secondWord);
                            MainScreen.outPut.setText(player.getName() + " added the " + currentRoom.getItem().getName() + " to their inventory! ");
                            currentRoom.setItem(item);
                        }
                        if (player.getInventory().contains("gas mask")) {
                            player.decreaseHealth(1);
                            updateHealthInv();
                        } else {
                            player.decreaseHealth(2);
                            updateHealthInv();
                        }
//                        view.renderRemainingPlayerHealth(player.getHealth());
                        checkEndGameConditions();

                    } else {
                        MainScreen.outPut.setText("Invalid Command. Try [grab, get, take] + item.");
                    }
                }
                break;
            case "drop":
                if (secondWord != null) {
                    if (player.getInventory().contains(secondWord)) {
                        player.getInventory().remove(secondWord);
                        MainScreen.outPut.setText(player.getName() + "removed " + secondWord + " from your inventory.");
                    }
                    if (player.getInventory().contains("gas mask")) {
                        player.decreaseHealth(1);
                        updateHealthInv();
                    } else {
                        player.decreaseHealth(2);
                        updateHealthInv();
                    }
//                    view.renderRemainingPlayerHealth(player.getHealth());
                    checkEndGameConditions();

                } else {
                    MainScreen.outPut.setText("Invalid Command. Try 'drop' + item in inventory.");
                }
                break;
            case "health":
                MainScreen.outPut.setText(player.getName() + "'s  current health is: " + player.getHealth());
                break;
            case "quit":
                checkEndGameConditions();
                break;
            default:
                MainScreen.outPut.setText("Invalid Command");
        }
    }

    // This method verifies the room movement while also doing a switch case on the second word.

    private void verifyRoomMovement() throws InterruptedException {


        if (firstWord.equals("go")) {
            if (secondWord != null) {
                switch (secondWord) {
                    case "east":
                    case "west":
                    case "north":
                    case "south":
                        verifyLocation();
                        updateLocation();
                        MainScreen.destImageLabel.setText("Current Room: "+ currentRoom.getName());
                        ImageIcon icon = new ImageIcon(currentRoom.getImage());
                        MainScreen.destImageLabel.setIcon(icon);

                        break;
                    default:
                        MainScreen.outPut.setText(player.getName() + ": " + "I can't go that way.");
                        break;
                }
            } else {
                MainScreen.outPut.setText("Invalid Command: Try 'go' + [north, south, east, west]");
            }
        }

        if (firstWord.equals("look")) {
            if (secondWord != null) {
                switch (secondWord) {
                    case "east":
                        MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getEastDescription());
                        if (currentRoom.getItem() != null) {
                            if (!currentRoom.getItem().getName().equals("empty") && (currentRoom.getItemPlacementEast() == 1)) {
                                MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getItem().getItem_description());
                            }
                        }
                        break;
                    case "west":
                        MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getWestDescription());
                        if (currentRoom.getItem() != null) {
                            if (!currentRoom.getItem().getName().equals("empty") && (currentRoom.getItemPlacementWest() == 1)) {
                                MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getItem().getItem_description());
                            }
                        }
                        break;
                    case "south":
                        MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getSouthDescription());
                        if (currentRoom.getItem() != null) {
                            if (!currentRoom.getItem().getName().equals("empty") && (currentRoom.getItemPlacementSouth() == 1)) {
                                MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getItem().getItem_description());
                            }
                        }
                        break;
                    case "north":
                        MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getNorthDescription());
                        if (currentRoom.getItem() != null) {
                            if (!currentRoom.getItem().getName().equals("empty") && (currentRoom.getItemPlacementNorth() == 1)) {
                                MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getItem().getItem_description());
                            }
                        }
                        break;
                    default:
                        MainScreen.outPut.setText(player.getName() + ": " + "I can't look that way.");
                        break;
                }
            } else {
                MainScreen.outPut.setText("Invalid Command: Try 'look' + [north, south, east, west]");
            }
        }
    }

    // This method does a switch case on the second word. This will allow the player to move through each room.

    private void verifyLocation() {

        switch (secondWord) {
            case "north":
                if (currentRoom.getNorth().equals("N/A")) {
                    MainScreen.outPut.setText(player.getName() + ": " + " I can't go that way.");
                } else {
                    for (Locations room : locations) {
                        if (room.getName().equals(currentRoom.getNorth())) {
                            currentRoom = room;
                            if (currentRoom.getName().equals("Roof")) {
                                if (locations.get(9).getEllane() == ellaneBlank || locations.get(10).getEllane() == ellaneBlank) {
                                    view.renderWinGameDialogue(ellane, player);
                                } else {
                                    MainScreen.outPut.setText(player.getName() + ":" + " I can't leave yet! I don't have Ellane!");
                                    currentRoom = locations.get(7);
                                    break;
                                }
                            }
                            currentRoom = room;
                            MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getDescription());
                            if (currentRoom.getTerrorist() == terrorist) {
                                MainScreen.isTerrorist = true;
                                Terrorist.terrorStart(player, terrorist);
                                currentRoom.setTerrorist(terroristBlank);
                            }
                            break;
                        }
                    }
                }
                break;
            case "south":
                if (currentRoom.getSouth().equals("N/A")) {
                    MainScreen.outPut.setText("I can't go that way.");
                } else {
                    MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getDescription());
                    for (Locations room : locations) {
                        if (room.getName().equals(currentRoom.getSouth())) {
                            currentRoom = room;
                            if (currentRoom.getTerrorist() == terrorist) {
                                MainScreen.isTerrorist = true;
                                Terrorist.terrorStart(player, terrorist);
                                currentRoom.setTerrorist(terroristBlank);
                            }
                            break;
                        }
                    }
                }
                break;
            case "east":
                if (currentRoom.getEast().equals("N/A")) {
                    MainScreen.outPut.setText("I can't go that way.");
                } else {
                    MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getDescription());
                    for (Locations room : locations) {
                        if (room.getName().equals(currentRoom.getEast())) {
                            currentRoom = room;
                            if (currentRoom.getTerrorist() == terrorist) {
                                MainScreen.isTerrorist = true;
                                Terrorist.terrorStart(player, terrorist);
                                currentRoom.setTerrorist(terroristBlank);
                            }
                            break;
                        }
                    }
                }
                break;
            case "west":
                if (currentRoom.getWest().equals("N/A")) {
                    MainScreen.outPut.setText("I can't go that way.");
                } else {
                    for (Locations room : locations) {
                        if (room.getName().equals(currentRoom.getWest())) {
                            if (room.getEllane() == ellane) {
                                if (player.getInventory().contains("keys")) {
                                    MainScreen.outPut.setText(player.getName() + ":" + " The doors locked. Let me see if a key off of the set of Ralph's can open this door.\n"+
                                            "* door unlocks *\n" +
                                            player.getName() + ":" + " Great it worked!\n"+
                                            view.renderEllaneDialogue(ellane, player));
                                    currentRoom = room;
                                    currentRoom.setEllane(ellaneBlank);
                                } else {
                                    MainScreen.outPut.setText(player.getName() + ":" + " The doors locked. I'm going to need a key to get in");
                                    break;
                                }
                            }
                            currentRoom = room;
                            MainScreen.outPut.setText(player.getName() + ": " + currentRoom.getDescription());
                            if (currentRoom.getTerrorist() == terrorist) {
                                MainScreen.isTerrorist = true;
                                currentRoom.setTerrorist(terroristBlank);
                            }

                            break;
                        }
                    }
                }
                break;
            default:
                MainScreen.outPut.setText(player.getName() + ": " + "I can't go that way.");
        }

    }

    // This method isn't used but can help the user run music.

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

    // This method starts the intro music in the beginning of the method.

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


}