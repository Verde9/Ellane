package com.ellane.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class EllaneView {

    public EllaneView() {
    }

    public void renderWelcomeGameMessage() {
        String banner = null;
        if (Files.exists(Path.of("resources/gameArt.txt"))) {
            try {
                banner = Files.readString(Path.of("resources/gameArt.txt"));
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

    public void renderRemainingPlayerHealth(int health) {
        System.out.println("You lost blood from your wound & your health drops..");
        System.out.println(health + "% health remaining...");
    }

    public void renderDisplayGameInfo() throws InterruptedException {
        System.out.println("The chaos spreads & and mayhem erupts around the city\n" +
                "The City of Seattle is under attack by Terrorists!\n" +
                "Fire is spreading from building to building & most signs of life are gone!\n" +
                "You're apartment building is on fire and you are injured by falling debris!\n" +
                "You're bleeding out but can still manage to move but you need to move quickly becuase the fire is rapily growing!\n" +
                "Luckily there is a helicopter on the roof evacuating the survivors that made it to the roof\n" +
                "Adding more chaos to the mix, a women who was hit by the same debris as you is now dying and asks you to save her 3 year old daughter that's in her apartment\n" +
                "To make matters even worse, the women lost the key to her apartment during all the chaos\n" +
                "The objectives are simple but will be a challenge:\n" +
                " - Find the keys to get Ellane from her apartment\n" +
                " - Save Ellane \n" +
                " - Make it to the roof before the Helicopter leaves\n" +
                "Keep in mind that you're bleeding out so you have until your health reaches 0 to make it to the roof safely\n" +
                "Also beware of terrorists while navigating through the building, if you run into one and do not have a weapon you will die.."
        );

        renderShowGameControls();

        System.out.println("Ready? Now let's go save Ellane!");

        System.out.println();

    }


    public void renderShowGameControls() {
        System.out.println("HERE ARE THE GAME COMMANDS: \n" +
                "    GO + [north, south, east, west]\n" +
                "    LOOK + [north, south, east, west]\n" +
                "    GRAB + [item_name]\n" +
                "    DROP + [item_name]\n" +
                "    STATUS - shows current room, inventory, and health.\n" +
                "    HELP - will display the commands\n" +
                "    QUIT - will end the game.\n");
    }

    public void renderImmediateQuitGameMessage() {
        System.out.println("...Thanks for abandoning Ellane! Goodbye!");
    }



    public void renderEndGameMessageAndResults(int playerHealth) {
        System.out.println();
        System.out.println("GAME HAS ENDED...");
        System.out.println();
        System.out.println("You ended the game with: \n " + playerHealth + ": Player Health");
        System.out.println();
        System.out.println("Thanks for playing Ellane!");
    }

    public void renderGrabbedItemMessage(String item) {
        System.out.println("you now have this item " + item);
    }

    public void renderMusicCommandsAndPromptMessage() {
        System.out.println("P = Play Music, S= Stop Music, R=Reset, V =Volume, Q= quit music player");
        System.out.println("Enter your choice: ");
    }

    public void renderEnterNameMessage() {
        System.out.println("Enter your name:");
    }

    public void renderEnterNameEnforceMessage() {
        System.err.println("YOU MUST ENTER A NAME");
    }

}