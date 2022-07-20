import javax.print.event.PrintJobEvent;
import java.util.Scanner;

public class Player {
    private String name;
    private String decision;
    private String inventory;
    // Make constructor for properties

    public Player(String name, String decision) {
        this.name = name;
        this.decision = decision;
        this.inventory = inventory  ["item1", "item2"];
    }

    public String getName() {
        return name;
    }
     Scanner scan = new Scanner(System.in); //"GO NORTH"


    String userInput = scan.toString();

    public  String getPlayerDecision(String decision){
        //CONVERT STRING TO ARRAY ->" GO NORTH" -> ["GO", "NORTH"] ->decision[0] = "GO" decision[1] = "NORTH"
        //CHECK IF DECISION[0] is a VALID ACTION CHOOSE(JUMP, LOOK, MOVE, PICK, USE)
        //IF DECISION[0] is valid choice, then check decision[1] to see if its valid

        if(decision[0] == "MOVE") {
            if(decision[1] == "NORTH") {

            }
            if(decision[1] == "WEST") {

            }
        }

        if(decision[0] == "JUMP") {
            if(decision[1] == "") {

            }
        }



    }
}