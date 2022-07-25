import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class PlayerClient {
    public static void main(String[] args) {
        Scanner action = new Scanner(System.in);
        Random rand = new Random();



        String[] heroes = {"Soldier", "Doctor", "kid", "dog"};
        String[] direction1 = {"GO", "PUSH", "KICK", "KNOCK"};
        String[] item = {"flashlight", "Water", "Cigarette", "White bread",
        "Wheat bread", };


        //Make new player object
        Player player1 = new Player("Chris");
        System.out.println("Hello " + player1.getName());

        System.out.println("Choose your hero: ");
        String userInput = action.nextLine();

        System.out.println("What will you like to do: ");
        userInput = action.nextLine();
        if(userInput.equals(direction1[0].toLowerCase())){
            System.out.println("You are in the room");
            System.out.println("What item would you like to choose; ");
            userInput = action.nextLine();
        }
        else {
            System.out.println("wrong move");
        }

        //System.out.println(direction1[3].toLowerCase(Locale.ROOT));
        //player1.getPlayerDecision(action);
    }
}