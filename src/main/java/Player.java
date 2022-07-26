import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    private String currentRoom;
    private String firstWord;
    private String secondWord;
    private String CharacterType;
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

    public String makeDecision() {
        System.out.println("What do you want to do: ");
        String decision = in.nextLine();

        return decision;
       // verifyDecision(decision);
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }


}