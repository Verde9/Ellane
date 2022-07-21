import javax.print.event.PrintJobEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    ArrayList<String> inventory = new ArrayList<>();

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
        String decision = in.nextLine();
        return decision;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }
}