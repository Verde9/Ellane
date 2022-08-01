package com.ellane.app;

import com.ellane.model.LocationsAndDirections;
import com.ellane.model.Json;
import com.ellane.model.Player;
import com.ellane.model.Characters;
import com.ellane.model.Items;
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
import java.lang.reflect.Type;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EllaneApp {
    private String firstWord;
    private String secondWord;
    private String currentRoom = "basement";
    private int roundCount = 50;
    Boolean gameOver = false;
    ArrayList<String> inventory = new ArrayList<>(3);
    Boolean ellaneFound = false;
    Boolean ellaneCured = false;
    String ellaneLocation = "";
    String bombLocation = "";
    Boolean bombFound = false;
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

    Player player1;
    private static EllaneView view = new EllaneView();
    Scanner scan = new Scanner(System.in);
    Player player = new Player("LB", com.ellane.model.Characters.MALE_SOLDIER);


    //this will run the app in the main class
    public void initialize() throws InterruptedException, IOException {
        view = new EllaneView();
        view.renderWelcomeGameMessage();
        promptToStartGame();
        randomizeEllaneLocation();
        randomizeBombLocation();
    }

    public void checkEndGameConditions() throws NullPointerException {
        if (roundCount <= 0 || player1.getHealth() <= 0 || firstWord.equals("quit") || gameOver == true) {
            gameOver = true;
            view.renderEndGameMessageAndResults(roundCount, player1.getHealth());
            System.exit(0);
        }
    }

    public void randomizeEllaneLocation() {
        Random ran = new Random();
        int randomLocation = ran.nextInt(9) + 1;
        try {
            if (randomLocation == 1) {
                ellaneLocation = "basement";
            } else if (randomLocation == 2) {
                ellaneLocation = "lobby";
            } else if (randomLocation == 3) {
                ellaneLocation = "common area";
            } else if (randomLocation == 4) {
                ellaneLocation = "mechanical room";
            } else if (randomLocation == 5) {
                ellaneLocation = "office_1";
            } else if (randomLocation == 6) {
                ellaneLocation = "office_floor1";
            } else if (randomLocation == 7) {
                ellaneLocation = "office_floor2";
            } else if (randomLocation == 8) {
                ellaneLocation = "office_floor3";
            } else if (randomLocation == 9) {
                ellaneLocation = "office_floor4";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkIfEllaneIsHere() {
        if (currentRoom.equals(ellaneLocation)) {
            ellaneFound = true;
            view.renderFoundEllaneLocationMessage(ellaneLocation);
        }
    }

    public void randomizeBombLocation() {
        Random ran = new Random();
        int randomLocation = ran.nextInt(9) + 1;
        try {
            if (randomLocation == 1) {
                bombLocation = "basement";
            } else if (randomLocation == 2) {
                bombLocation = "lobby";
            } else if (randomLocation == 3) {
                bombLocation = "common area";
            } else if (randomLocation == 4) {
                bombLocation = "mechanical room";
            } else if (randomLocation == 5) {
                bombLocation = "office_1";
            } else if (randomLocation == 6) {
                bombLocation = "office_floor1";
            } else if (randomLocation == 7) {
                bombLocation = "office_floor2";
            } else if (randomLocation == 8) {
                bombLocation = "office_floor3";
            } else if (randomLocation == 9) {
                bombLocation = "office_floor4";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        introMusic("Music/For Work 2_1.wav");
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
        checkIfEllaneIsHere();
        checkForBombInRoom();
        while (!gameOver) {
            promptPlayerForDecision();
        }
        view.renderEndGameMessageAndResults(roundCount, player1.getHealth());
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
        generateLocation();
        generateLocation2();

        switch (firstWord) {
            case "look":
                System.out.println(currentRoom);
                verifyRoomMovement();
                System.out.println("you are in " + currentRoom+ "\nthis is in your inventory \n " + getInventory());
                System.out.println();
                decreaseRoundCount(1);
                player1.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player1.getHealth());
                view.renderRemainingRounds(roundCount);
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "help":
                view.renderShowGameControls();
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "go":
            case "climb":
                System.out.println();
                verifyRoomMovement();
                System.out.println();
                decreaseRoundCount(1);
                player1.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player1.getHealth());
                view.renderRemainingRounds(roundCount);
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "inventory":
                System.out.println(getInventory());
                checkEndGameConditions();
                promptPlayerForDecision();
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
                decreaseRoundCount(1);
                player1.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player1.getHealth());
                view.renderRemainingRounds(roundCount);
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "drop":
                if (inventory.contains(secondWord)) {
                    inventory.remove(secondWord);
                    System.out.println("you have removed this item");
                }
                System.out.println();
                decreaseRoundCount(1);
                player1.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player1.getHealth());
                view.renderRemainingRounds(roundCount);
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "health":
                System.out.println(player1.getHealth());
                System.out.println();
                checkEndGameConditions();
                promptPlayerForDecision();
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
                decreaseRoundCount(1);
                player1.decreaseHealth(2);
                view.renderRemainingPlayerHealth(player1.getHealth());
                view.renderRemainingRounds(roundCount);
                checkEndGameConditions();
                promptPlayerForDecision();
                break;
            case "quit":
                //gameOver = true;
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
        generateLocation();
        generateLocation2();
        generatePlayerItems();
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
                    System.out.println("testing from go..");
                    //view.renderInvalidMovementCommandMessage(secondWord);
                    view.renderShowGameControls();
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
                    System.out.println("testing..");
                   // view.renderInvalidMovementCommandMessage(secondWord);
                    view.renderShowGameControls();
                    decision = player1.makeDecision();
                    verifyDecision(decision);
                    break;
            }
        }
    }

    private void verifyLocation() {
        if(generateBasementLocation.containsKey(secondWord) && secondWord.equals("east")){
            System.out.println(generateBasementLocation.get("east"));
            currentRoom = generateOffice_1_Location.get("name");
        }
        else if(generateBasementLocation.containsKey(secondWord) && secondWord.equals("west")){
            System.out.println(generateBasementLocation.get("west"));
            currentRoom = generateOffice_1_Location.get("name");
        }
        else if(generateBasementLocation.containsKey(secondWord) && secondWord.equals("south")) {
            System.out.println(generateBasementLocation.get("south"));
            currentRoom = generateOffice_1_Location.get("name");
        }
        else if(generateBasementLocation.containsKey(secondWord) && secondWord.equals("north")) {
            System.out.println(generateBasementLocation.get("north"));
            currentRoom = generateOffice_1_Location.get("name");
        }

        else if (generateCommon_Area_Location.containsKey(secondWord) && secondWord.equals("east")){
            System.out.println(generateCommon_Area_Location.get("east"));
            currentRoom = generateCommon_Area_Location.get("name");
        }
        else if (generateCommon_Area_Location.containsKey(secondWord) && secondWord.equals("west")) {
            System.out.println(generateCommon_Area_Location.get("west"));
            currentRoom = generateCommon_Area_Location.get("name");
        }
        else if (generateCommon_Area_Location.containsKey(secondWord) && secondWord.equals("south")) {
            System.out.println(generateCommon_Area_Location.get("south"));
            currentRoom = generateCommon_Area_Location.get("name");
        }
        else if (generateCommon_Area_Location.containsKey(secondWord) && secondWord.equals("north")) {
            System.out.println(generateCommon_Area_Location.get("north"));
            currentRoom = generateCommon_Area_Location.get("name");
        }

        else if (generateLobbyLocation.containsKey(secondWord) && secondWord.equals("east")) {
            System.out.println(generateLobbyLocation.get("east"));
            currentRoom = generateLobbyLocation.get("name");
        }
        else if (generateLobbyLocation.containsKey(secondWord) && secondWord.equals("west")) {
            System.out.println(generateLobbyLocation.get("west"));
            currentRoom = generateLobbyLocation.get("name");
        }
        else if (generateLobbyLocation.containsKey(secondWord) && secondWord.equals("south")) {
            System.out.println(generateLobbyLocation.get("south"));
            currentRoom = generateLobbyLocation.get("name");
        }
        else if (generateLobbyLocation.containsKey(secondWord) && secondWord.equals("north")) {
            System.out.println(generateLobbyLocation.get("north"));
            currentRoom = generateLobbyLocation.get("name");
        }

        else if (generateMechanicalRoomLocation.containsKey(secondWord) && secondWord.equals("east")) {
            System.out.println(generateMechanicalRoomLocation.get("east"));
            currentRoom = generateMechanicalRoomLocation.get("name");
        }
        else if (generateMechanicalRoomLocation.containsKey(secondWord) && secondWord.equals("west")) {
            System.out.println(generateMechanicalRoomLocation.get("west"));
            currentRoom = generateMechanicalRoomLocation.get("name");
        }
        else if (generateMechanicalRoomLocation.containsKey(secondWord) && secondWord.equals("south")) {
            System.out.println(generateMechanicalRoomLocation.get("south"));
            currentRoom = generateMechanicalRoomLocation.get("name");
        }
        else if (generateMechanicalRoomLocation.containsKey(secondWord) && secondWord.equals("north")) {
            System.out.println(generateMechanicalRoomLocation.get("north"));
            currentRoom = generateMechanicalRoomLocation.get("name");
        }
        else if (generateOffice_1_Location.containsKey(secondWord) && secondWord.equals("east")) {
            System.out.println(generateOffice_1_Location.get("east"));
            currentRoom = generateOffice_1_Location.get("name");
        }
        else if (generateOffice_1_Location.containsKey(secondWord) && secondWord.equals("south")) {
            System.out.println(generateOffice_1_Location.get("south"));
            currentRoom = generateOffice_1_Location.get("name");
        }
        else if (generateOffice_1_Location.containsKey(secondWord) && secondWord.equals("west")) {
            System.out.println(generateOffice_1_Location.get("west"));
            currentRoom = generateOffice_1_Location.get("name");
        }
        else if (generateOffice_1_Location.containsKey(secondWord) && secondWord.equals("north")) {
            System.out.println(generateOffice_1_Location.get("north"));
            currentRoom = generateOffice_1_Location.get("name");
        }
        else if (generateOffice_Floor_1_location.containsKey(secondWord) && secondWord.equals("east")) {
            System.out.println(generateOffice_Floor_1_location.get("east"));
            currentRoom = generateOffice_Floor_1_location.get("name");
        }
        else if (generateOffice_Floor_1_location.containsKey(secondWord) && secondWord.equals("north")) {
            System.out.println(generateOffice_Floor_1_location.get("north"));
            currentRoom = generateOffice_Floor_1_location.get("name");
        }
        else if (generateOffice_Floor_1_location.containsKey(secondWord) && secondWord.equals("south")) {
            System.out.println(generateOffice_Floor_1_location.get("south"));
            currentRoom = generateOffice_Floor_1_location.get("name");
        }
        else if (generateOffice_Floor_1_location.containsKey(secondWord) && secondWord.equals("west")) {
            System.out.println(generateOffice_Floor_1_location.get("west"));
            currentRoom = generateOffice_Floor_1_location.get("name");
        }
        else if (generateOffice_Floor_2_location.containsKey(secondWord) && secondWord.equals("east")) {
            System.out.println(generateOffice_Floor_2_location.get("east"));
            currentRoom = generateOffice_Floor_2_location.get("name");
        }
        else if (generateOffice_Floor_2_location.containsKey(secondWord) && secondWord.equals("south")) {
            System.out.println(generateOffice_Floor_2_location.get("south"));
            currentRoom = generateOffice_Floor_2_location.get("name");
        }
        else if (generateOffice_Floor_2_location.containsKey(secondWord) && secondWord.equals("west")) {
            System.out.println(generateOffice_Floor_2_location.get("west"));
            currentRoom = generateOffice_Floor_2_location.get("name");
        }
        else if (generateOffice_Floor_2_location.containsKey(secondWord) && secondWord.equals("north")) {
            System.out.println(generateOffice_Floor_2_location.get("north"));
            currentRoom = generateOffice_Floor_2_location.get("name");
        }
        else if (generateOffice_Floor_3_location.containsKey(secondWord) && secondWord.equals("east")) {
            System.out.println(generateOffice_Floor_3_location.get("east"));
            currentRoom = generateOffice_Floor_3_location.get("name");
        }
        else if (generateOffice_Floor_3_location.containsKey(secondWord) && secondWord.equals("south")) {
            System.out.println(generateOffice_Floor_3_location.get("south"));
            currentRoom = generateOffice_Floor_3_location.get("name");
        }
        else if (generateOffice_Floor_3_location.containsKey(secondWord) && secondWord.equals("west")) {
            System.out.println(generateOffice_Floor_3_location.get("west"));
            currentRoom = generateOffice_Floor_3_location.get("name");
        }
        else if (generateOffice_Floor_3_location.containsKey(secondWord) && secondWord.equals("north")) {
            System.out.println(generateOffice_Floor_3_location.get("north"));
            currentRoom = generateOffice_Floor_3_location.get("name");
        }
        else if (generateOffice_Floor_4_location.containsKey(secondWord) && secondWord.equals("east")) {
            System.out.println(generateOffice_Floor_4_location.get("east"));
            currentRoom = generateOffice_Floor_4_location.get("name");
        }
        else if (generateOffice_Floor_4_location.containsKey(secondWord) && secondWord.equals("south")) {
            System.out.println(generateOffice_Floor_4_location.get("south"));
            currentRoom = generateOffice_Floor_4_location.get("name");
        }else if (generateOffice_Floor_4_location.containsKey(secondWord) && secondWord.equals("west")) {
            System.out.println(generateOffice_Floor_4_location.get("west"));
            currentRoom = generateOffice_Floor_4_location.get("name");
        }else if (generateOffice_Floor_4_location.containsKey(secondWord) && secondWord.equals("north")) {
            System.out.println(generateOffice_Floor_4_location.get("north"));
            currentRoom = generateOffice_Floor_4_location.get("name");
        }
        else if (generateRooftop_location.containsKey(secondWord) && secondWord.equals("east")) {
            System.out.println(generateRooftop_location.get("east"));
            currentRoom = generateRooftop_location.get("name");
        }
        else if (generateRooftop_location.containsKey(secondWord) && secondWord.equals("south")) {
            System.out.println(generateRooftop_location.get("south"));
            currentRoom = generateRooftop_location.get("name");
        }
        else if (generateRooftop_location.containsKey(secondWord) && secondWord.equals("west")) {
            System.out.println(generateRooftop_location.get("west"));
            currentRoom = generateRooftop_location.get("name");
        }
        else if (generateRooftop_location.containsKey(secondWord) && secondWord.equals("north")) {
            System.out.println(generateRooftop_location.get("north"));
            currentRoom = generateRooftop_location.get("name");
        }

        else {
            System.out.println("This location does not exist on floor");
        }

    }

    private void displayRemainingRounds() {
        System.out.println(roundCount + " rounds remaining...");
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

    private void decreaseRoundCount(int decreaseAmount) {
        this.roundCount -= decreaseAmount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount -= roundCount;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getRoundCount() {
        return roundCount;
    }
}