import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    private String currentRoom;
    private String firstWord;
    private String secondWord;
    ArrayList<String> inventory = new ArrayList<>();
    private Array Room[] = new Array[1];

    Scanner in = new Scanner(System.in);

    // Make constructor for properties

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

    public void makeDecision() {
        System.out.println("What do you want to do: ");
        String decision = in.nextLine();
        verifyDecision(decision);
    }

    private void verifyDecision(String decision) {
        String stringArr[] = decision.split(" ", 2);
        System.out.println(stringArr);
        firstWord = stringArr[0].toLowerCase();
        secondWord = stringArr[1].toLowerCase();
        System.out.println("first Word: " + firstWord);
        System.out.println("second Word: " + secondWord);

        verifyFirstWord(firstWord);
    }

    private void verifyFirstWord(String firstWord) {
        if (firstWord.equals("look")){
            lookInCurrentRoom();
        }

        if(firstWord.equals("controls")) {
            showGameControls();
        }

        if(firstWord.equals("go")) {
            verifyRoomMovement(secondWord);
        } else {
            System.out.println("incorrect command. Check game control for options");
            makeDecision();
        }
    }

    private void lookInCurrentRoom() {
        System.out.println("current Room Description");
        System.out.println("Items in room are going to be provided");// replace code later
        //Call Room details here from temporary room
        makeDecision();
    }

    private void verifyRoomMovement(String secondWord) {
        switch(secondWord) {
            case "east":
                System.out.println();
                //verify there is an east room to move to
                   //update currentroom property
                   //display currentRoom description
                   //display currentRoom items by looping over them all
                   //call makeDecision()
                //If not valid, throw ERROR MESSAGE
                makeDecision();

                break;
            case "west":
                System.out.println();
                break;
        }
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    private void showGameControls() {
        System.out.println("The game commands are as follows: ");
        System.out.println("valid action commands: LOOK, USE, GO, JUMP, DROP, PICKUP, CONTROLS");
        System.out.println("eg. 'LOOK UP', 'PICKUP SWORD', JUMP DOWN, MOVE, ");
        makeDecision();
    }
}