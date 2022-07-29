package com.ellane.app;

import com.ellane.model.LocationsAndDirections;
import com.ellane.model.Json;
import com.ellane.model.Player;
import com.ellane.model.Characters;
import com.ellane.model.Items;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class EllaneApp {
    private String firstWord;
    private String secondWord;
    private String currentRoom = "basement";
    private int roundCount = 50;
    Boolean gameOver = false;
    Player player1;
    Boolean ellaneFound = false;
    Boolean ellaneCured = false;
    String ellaneLocation = "";
    ArrayList<String> inventory = new ArrayList<>();
    List<Items> gameItems = new ArrayList<>();
    List<LocationsAndDirections> playerLocations = new ArrayList<>();
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

    Scanner scan = new Scanner(System.in);

    //this will run the app in the main class
    public void initialize() throws InterruptedException, IOException {
       gameWelcomeMessage();
        promptToStartGame();
        randomizeEllaneLocation();
        checkEndGameConditions();
    }

    private void checkEndGameConditions() {
        if(roundCount <= 0 || player1.getHealth() <= 0) {
            gameOver = true;
            endGame();
        }
    }

    private void checkIfEllaneIsHere() {
        if (currentRoom.equals(ellaneLocation)) {
            ellaneFound = true;
            System.out.println("You've found Ellane!");
        }
    }

    public void randomizeEllaneLocation() {
        Random ran = new Random();

        int randomLocation = ran.nextInt(9) + 1;

        try {
            if(randomLocation == 1) {
                ellaneLocation = "basement";
                System.out.println("ellane is hiding in the : " + ellaneLocation);
            }
            else if(randomLocation == 2) {
                ellaneLocation = "lobby";
                System.out.println("ellane is hiding in the : " + ellaneLocation);
            }
            else if(randomLocation == 3) {
                ellaneLocation = "common area";
                System.out.println("ellane is hiding in the : " + ellaneLocation);
            }
            else if(randomLocation == 4) {
                ellaneLocation = "mechanical room";
                System.out.println("ellane is hiding in the : " + ellaneLocation);
            }
            else if(randomLocation == 5) {
                ellaneLocation = "office_1";
                System.out.println("ellane is hiding in the : " + ellaneLocation);
            }
            else if(randomLocation == 6) {
                ellaneLocation = "office_floor1";
                System.out.println("ellane is hiding in the : " + ellaneLocation);
            }
            else if(randomLocation == 7) {
                ellaneLocation = "office_floor2";
                System.out.println("ellane is hiding in the : " + ellaneLocation);
            }
            else if(randomLocation == 8) {
                ellaneLocation = "office_floor3";
                System.out.println("ellane is hiding in the : " + ellaneLocation);
            }
            else if(randomLocation == 9) {
                ellaneLocation = "office_floor4";
                System.out.println("ellane is hiding in the : " + ellaneLocation);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //after initialize is called, the "ellane" picture will show
    //the image is pretty big now.... should we make it smaller?
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

    //this would be one of the first things asked... Needs to make a introGame function again to call before
    //the sout line to play music
    private void promptToStartGame() throws InterruptedException, IOException {
        introMusic("Music/For Work 2_1.wav");
        System.out.println("Do you wish to play? type 'yes' or type 'quit game'");
        String userInput = scan.nextLine().toLowerCase();

        if (userInput.equals("quit game")) {
            System.out.println("...Thanks for playing! Goodbye!");
        }

        if (userInput.equals("yes")) {
            System.out.println();
            System.out.println("Let's Play!");
            createPlayerOneCharacter();

        } else {
            System.err.println("INVALID INPUT");
            System.out.println();
            promptToStartGame();
        }
    }

    public void generatePlayerItems() throws IOException {
        JsonNode bedroomNode = Json.parse(new File("basementItems.json"));
        Items bedroomItems = Json.fromJson(bedroomNode, Items.class);
        gameItems.add(bedroomItems);

        JsonNode office_1_Node = Json.parse(new File("office_1_Items.json"));
        Items office_1_Items = Json.fromJson(office_1_Node, Items.class);
        gameItems.add(office_1_Items);

        JsonNode mechanical_Room_Node = Json.parse(new File("mechanicalRoomItems.json"));
        Items mechanical_Room_Node_Items = Json.fromJson(mechanical_Room_Node, Items.class);
        gameItems.add(mechanical_Room_Node_Items);

        JsonNode common_Area_Node = Json.parse(new File("common_areaItems.json"));
        Items common_Area_Node_Items = Json.fromJson(common_Area_Node, Items.class);
        gameItems.add(common_Area_Node_Items);

        JsonNode lobby_Node = Json.parse(new File("lobbyItems.json"));
        Items lobby_Items = Json.fromJson(lobby_Node, Items.class);
        gameItems.add(lobby_Items);

        JsonNode office_Floor_1_Node = Json.parse(new File("office_floor_1_Items.json"));
        Items office_Floor_1_Items = Json.fromJson(office_Floor_1_Node, Items.class);
        gameItems.add(office_Floor_1_Items);


        JsonNode office_Floor_2_Node = Json.parse(new File("office_floor_2_Items.json"));
        Items office_Floor_2_Items = Json.fromJson(office_Floor_2_Node, Items.class);
        gameItems.add(office_Floor_2_Items);

        JsonNode office_Floor_3_Node = Json.parse(new File("office_floor_3_Items.json"));
        Items office_Floor_3_Items = Json.fromJson(office_Floor_3_Node, Items.class);
        gameItems.add(office_Floor_3_Items);


        JsonNode office_Floor_4_Node = Json.parse(new File("office_floor_4_Items.json"));
        Items office_Floor_4_Items = Json.fromJson(office_Floor_4_Node, Items.class);
        gameItems.add(office_Floor_4_Items);

        JsonNode rooftop_Node = Json.parse(new File("rooftop_items.json"));
        Items rooftop_Items = Json.fromJson(rooftop_Node, Items.class);
        gameItems.add(rooftop_Items);


    }

    public void generateLocation() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<LocationsAndDirections> objects = objectMapper.readValue(new File("AlLLLLLLLROOMS.json"),
                new TypeReference<List<LocationsAndDirections>>() {
                });
        playerLocations = objects;
        playerLocations.addAll(objects);
    }

    public void generateLocation2() throws IOException {
        Gson gson = new Gson();
        Type basementLocation = new TypeToken<Map<String, String>>() {
        }.getType();
        generateBasementLocation = gson.fromJson(new FileReader("basement.json"),
                basementLocation);
        Type office_1_Location = new TypeToken<Map<String, String>>() {
        }.getType();
        generateOffice_1_Location = gson.fromJson(new FileReader("office_1.json"),
                office_1_Location);
        Type mechanical_Room_Location = new TypeToken<Map<String, String>>() {
        }.getType();
        generateMechanicalRoomLocation = gson.fromJson(new FileReader("mechanicalRoom.json"),
                mechanical_Room_Location);
        Type common_Area_Location = new TypeToken<Map<String, String>>() {
        }.getType();
        generateCommon_Area_Location = gson.fromJson(new FileReader("common_area.json"),
                common_Area_Location);
        Type lobby_Location = new TypeToken<Map<String, String>>() {
        }.getType();
        generateLobbyLocation = gson.fromJson(new FileReader("lobby.json"),
                lobby_Location);
        Type office_Floor_1_Location = new TypeToken<Map<String, String>>() {
        }.getType();
        generateOffice_Floor_1_location = gson.fromJson(new FileReader("office_Floor_1.json"),
                office_Floor_1_Location);
        Type office_Floor_2_Location = new TypeToken<Map<String, String>>() {
        }.getType();
        generateOffice_Floor_2_location = gson.fromJson(new FileReader("office_Floor_2.json"),
                office_Floor_2_Location);
        Type office_Floor_3_Location = new TypeToken<Map<String, String>>() {
        }.getType();
        generateOffice_Floor_3_location = gson.fromJson(new FileReader("office_Floor_3.json"),
                office_Floor_3_Location);
        Type office_Floor_4_Location = new TypeToken<Map<String, String>>() {
        }.getType();
        generateOffice_Floor_4_location = gson.fromJson(new FileReader("office_Floor_4.json"),
                office_Floor_4_Location);
        Type rooftop_Location = new TypeToken<Map<String, String>>() {
        }.getType();
        generateRooftop_location = gson.fromJson(new FileReader("rooftop.json"),
                rooftop_Location);


    }

     private void createPlayerOneCharacter() throws IOException, InterruptedException {
            boolean valid = false;
            String playerName = "";
            while (playerName.length() <= 0) {
                System.out.println();
                System.out.println("Enter your name:");
                playerName = scan.nextLine();
                if (playerName.length() <= 0) {
                    System.err.println("YOU MUST ENTER A NAME");
                }
            }

            System.out.println();

            while (!valid) {
                System.out.println("Select character to play as:");

                for (Characters character : Characters.values()) {
                    System.out.println(character.getCharacterType());
                }

                System.out.println();
                String characterChoice = scan.nextLine().toLowerCase();

                for (Characters character : Characters.values()) {
                    if (characterChoice.equals(character.getCharacterType())) {
                        System.out.println();
                        player1 = new Player(playerName, character);
                        System.out.println("New Player Created!");
                        System.out.println(player1.getName() + " lets go save Ellane!");
                        startGame();
                        valid = true;
                        break;
                    }
                }

                if (!valid) {
                    System.out.println();
                    System.err.println("INVALID CHARACTER SELECTED");
                }
            }
        }

    private void startGame () throws InterruptedException, IOException {
            displayGameInfo();
            while (!gameOver) {
                String answer = player1.makeDecision();
            }
    }

            //TODO: (Delete this comment later)--- this is the main game info screen
    private void displayGameInfo () throws InterruptedException {
                System.out.println("The chaos spreads & the bombs keep exploding around the city");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("The fire is spreading from building to building & most signs of life as gone!");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("you get stuck inside of a building, but it can collapse at any minute & fire is spreading");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Luckily there id a helicopter on the roof evacuating the survivors that made it to the roof");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Unfortunately, you have been wounded & are losing blood as more time passes");
                TimeUnit.SECONDS.sleep(1);
                System.out.println();
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Time is ticking & you don't have much time!");
                TimeUnit.SECONDS.sleep(1);
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
            TimeUnit.SECONDS.sleep(2);
            displayGameLevelOneInfo();
            }

    private void showGameControls() throws InterruptedException {
        System.out.println("GAME COMMANDS: \n" +
                "    GO + [north, south, east, west]\n" +
                "    CLIMB + [up, down]\n" +
                "    GRAB + [item_name]\n" +
                "    DROP + [item_name]\n" +
                "    USE + [item_name]\n" +
                "    LOOK\n" +
                "    INVENTORY\n" +
                "    HEALTH \n" +
                "    HELP\n" +
                "    QUIT\n");
    }


    private void displayGameLevelOneInfo() throws InterruptedException {
                System.out.println("You suddenly awake & realize you were knocked out from the impact of an explosion..");
                System.out.println("It's dark and find yourself in the basement parking of a building..");
                System.out.println("");

                TimeUnit.SECONDS.sleep(2);
                showGameControls();

                checkIfEllaneIsHere();

                String decision = player1.makeDecision();
                verifyDecision(decision);
        }

        //TODO: (delete this comment later),this is making a String Array of our words so like ["john", "doe"]
        private void verifyDecision(String decision) throws InterruptedException {
                String[] stringArr = decision.split(" ");
                firstWord = stringArr[0].toLowerCase();

                try {
                    if (stringArr.length < 1) {
                        System.err.println("MUST ENTER A COMMAND TO CONTINUE...");
                        verifyDecision(player1.makeDecision());
                    }  else if (stringArr.length == 1) {
                        verifyFirstWord(firstWord);
                    } else {
                        secondWord = stringArr[1].toLowerCase();
                        verifyFirstWord(firstWord);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }

            //TODO: MAIN GAME LOGIC
        private void verifyFirstWord(String firstWord) throws InterruptedException, IOException {
            generateLocation();
            generateLocation2();

                String decision;
                switch (firstWord) {
                    case "look":
                        for (LocationsAndDirections locationsAndDirections : playerLocations) {
                            System.out.println(" you are in the: " + locationsAndDirections.getName().toUpperCase() +
                                    "\n and this is in your inventory: " + getInventory());
                        }
                        System.out.println();
                        roundCount--;
                        player1.decreaseHealth(2);
                        displayRemainingPlayerHealth();
                        displayRemainingRounds();
                        checkEndGameConditions();
                        decision = player1.makeDecision();
                        verifyDecision(decision);
                        break;
                    case "help":
                        showGameControls();

                        checkEndGameConditions();
                        decision = player1.makeDecision();
                        verifyDecision(decision);
                        break;
                    case "go":
                    case "climb":
                        System.out.println();
                        verifyRoomMovement(secondWord);
                        System.out.println();
                        roundCount--;
                        player1.decreaseHealth(2);
                        displayRemainingPlayerHealth();
                        displayRemainingRounds();
                        checkEndGameConditions();
                        decision = player1.makeDecision();
                        verifyDecision(decision);
                        break;
                    case "inventory":
                        System.out.println(getInventory());
                        checkEndGameConditions();
                        decision = player1.makeDecision();
                        verifyDecision(decision);
                        break;
                    case "grab":
                    case "get":
                        for (LocationsAndDirections andDirections : playerLocations) {
                            if (andDirections.getItem().equals(secondWord) || andDirections.getItem2().equals(secondWord)) {
                                System.out.println("you now have this item " + secondWord);
                                System.out.println();
                                roundCount--;
                                player1.decreaseHealth(2);
                                displayRemainingPlayerHealth();
                                displayRemainingRounds();
                                checkEndGameConditions();
                                decision = player1.makeDecision();
                                verifyDecision(decision);
                                break;
                            } else {
                                System.out.println("you cant get this");
                            }
//                            roundCount--;
//                            displayRemainingRounds();
//                            decision = player1.makeDecision();
//                            verifyDecision(decision);
//                            break;
                        }
                        for (LocationsAndDirections items : playerLocations) {
                            if (items.getItem().equals(secondWord)) {
                                inventory.add(items.getItem());
                                System.out.println(items.getItem_status());
                                System.out.println();
                            }
                            if (items.getItem2().equals(secondWord)) {
                                inventory.add(items.getItem2());
                                System.out.println(items.getItem_status2());
                                System.out.println();
                            } else {
                                System.out.println("nothing to add to inventory");
                            }
                        }
                        roundCount--;
                        player1.decreaseHealth(2);
                        displayRemainingPlayerHealth();
                        displayRemainingRounds();
                        checkEndGameConditions();
                        decision = player1.makeDecision();
                        verifyDecision(decision);
                        break;
                    case "drop":
                        //implement logic
                        System.out.println();
                        roundCount--;
                        player1.decreaseHealth(2);
                        displayRemainingPlayerHealth();
                        displayRemainingRounds();
                        checkEndGameConditions();
                        decision = player1.makeDecision();
                        verifyDecision(decision);
                        break;
                    case "health":
                        //implement logic
                        System.out.println(player1.getHealth());
                        System.out.println();
                        checkEndGameConditions();
                        decision = player1.makeDecision();
                        verifyDecision(decision);
                        break;
                    case "use":
                        //implement logic
                        System.out.println();
                        roundCount--;
                        player1.decreaseHealth(2);
                        displayRemainingPlayerHealth();
                        displayRemainingRounds();
                        checkEndGameConditions();
                        decision = player1.makeDecision();
                        verifyDecision(decision);
                        break;
                    case "quit":
                        gameOver = true;
                        endGame();
                        TimeUnit.SECONDS.sleep(1);
                        break;
                    case "play":
                        if (secondWord.equals("music")) {
                            System.out.println("These are the directions for the music player");
                            runMusic("Music/intro wav 2_1.wav");
                        }
                        checkEndGameConditions();
                        decision = player1.makeDecision();
                        verifyDecision(decision);
                        break;
                    default:
                        System.err.println("INVALID COMMAND...");
                        System.out.println();

                        System.err.println("ENTER A VALID COMMAND... ");
                        showGameControls();
                        System.out.println();

                        checkEndGameConditions();
                        decision = player1.makeDecision();
                        verifyDecision(decision);
                        break;
                }

                //Chris is working on this method, but we may not need it... depending on the Gson to Json
                //but have him code this still, just in case
        }

    private void displayRemainingPlayerHealth() {
        System.out.println("You lost blood from your wound & your health drops..");
        System.out.println("Player Health: " + player1.getHealth());
    }

    private void displayRemainingRounds() {
        System.out.println(roundCount + " rounds remaining...");
    }

    private void verifyRoomMovement (String secondWord) throws InterruptedException, IOException {
                generateLocation();
                generateLocation2();
                String decision;

                if(firstWord.equals("go")) {
                    switch (secondWord) {
                        case "east":
                            System.out.println();
                            currentRoom = secondWord;
                            if (playerlocations3.containsKey(currentRoom)) {
                                System.out.println(playerlocations3.get("east"));
                            }
                            //verify there is an east room to move to
                            //update currentroom property
                            //display currentRoom description
                            //display currentRoom items by looping over them all
                            //call player.makeDecision()
                            //If not valid, throw ERROR MESSAGE
                            //makeDecision();

                            break;
                        case "west":
                            System.out.println();
                            if (playerlocations3.containsKey(currentRoom)) {
                                System.out.println(playerlocations3.get("west"));
                            }
                            break;
                        case "north":
                            System.out.println();
                            if (playerlocations3.containsKey(currentRoom))
                                System.out.println(playerlocations3.get("north"));
                            break;
                        case "south":
                            System.out.println();
                            if (playerlocations3.containsKey(currentRoom))
                                System.out.println(playerlocations3.get("south"));
                            break;
                        default:
                            System.err.println("INVALID COMMAND. \n MOVING TO DIRECTION " + secondWord + " FROM CURRENT ROOM NOT POSSIBLE");
                            System.out.println();
                            System.err.println("MAKE ANOTHER DECISION");

                            showGameControls();
                            decision = player1.makeDecision();
                            verifyDecision(decision);
                            break;
                    }
                }

                if(firstWord.equals("climb")) {
                    switch (secondWord) {
                        case "up":
                            System.out.println();
                            //Implement logic
                            break;
                        case "down":
                            System.out.println();
                            //Implement logic
                            break;
                        default:
                            System.err.println("INVALID COMMAND. \n MOVING TO DIRECTION " + secondWord + " FROM CURRENT ROOM NOT POSSIBLE");
                            System.out.println();
                            System.err.println("MAKE ANOTHER DECISION");

                            showGameControls();
                            decision = player1.makeDecision();
                            verifyDecision(decision);
                            break;
                    }
                }

            }

            //TODO: need to make a GameControllerClass--for MVC
            //TODO: View---- the player types in "look"
            //TODO: Controller -this is the response to the command--- responds as a method call

            //this is to run the main game music...after the intro.
            public static void runMusic (String path){
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

            public static void introMusic (String path){
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
                                System.out.println("Thank you for quitting the player... Now Let's PLAY!");
                                clip.close();
                                System.out.println();
                                break;
                            default:
                                System.out.println("not a valid response for music player");


                        }
                    }


                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }


            }

            public ArrayList<String> getInventory () {
                return inventory;
            }

            public void endGame() {
                System.out.println("GAME HAS ENDED...");
                System.out.println("You ended the game with " + roundCount + " round remaining and \n Players Health was: " + player1.getHealth());
                System.out.println("Thanks for playing!");
            }

        }


