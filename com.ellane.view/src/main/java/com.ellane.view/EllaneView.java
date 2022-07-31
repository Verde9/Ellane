package com.ellane.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class EllaneView {

    public EllaneView() {
    }

    public void renderWelcomeGameMessage() {
        //app.displayGameWelcomeMessage();
        String banner = null;
        System.out.println("testing from view..");
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

    public void renderRemainingRounds(int rounds) {
        System.out.println(rounds + " rounds remaining...");
    }

    public void renderRemainingPlayerHealth(int health) {
        System.out.println("You lost blood from your wound & your health drops..");
        System.out.println(health + "% health remaining...");
    }

    public void renderDisplayGameInfo() throws InterruptedException {
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

        renderGameLevelOneInfo();
    }

    public void renderGameLevelOneInfo() throws InterruptedException {
        System.out.println("You suddenly awake & realize you were knocked out from the impact of an explosion..");
        System.out.println("It's dark and find yourself in the basement parking of a building..");
        System.out.println("");
        TimeUnit.SECONDS.sleep(2);

        renderShowGameControls();

    }

    public void renderShowGameControls() {
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

    public void renderPromptToPlayOrQuitGame() {
        System.out.println("Do you wish to play? type 'yes' or type 'quit game'");
    }

    public void renderImmediateQuitGameMessage() {
        System.out.println("...Thanks abandoning Ellane! Goodbye!");
    }

    public void renderBeginningPlayGameMessage() {
        System.out.println("Let's Play!");
    }

    public void renderInvalidCommandMessage() {
        System.err.println("INVALID COMMAND");
        System.out.println();

        System.err.println("ENTER A VALID COMMAND... ");
    }

    public void renderInvalidMovementCommandMessage(String direction) {
        System.err.println("INVALID COMMAND. \n MOVING TO DIRECTION " + direction + " FROM CURRENT ROOM NOT POSSIBLE");
        System.out.println();
        System.err.println("MAKE ANOTHER DECISION");
    }

    public void renderEndGameMessageAndResults(int roundCount,int playerHealth) {
        System.out.println("GAME HAS ENDED...");
        System.out.println("You ended the game with " + roundCount + " round remaining and \n Players Health was: " + playerHealth);
        System.out.println("Thanks for playing!");
    }

    public void renderGrabbedItemMessage(String item) {
        System.out.println("you now have this item " + item);
    }

    public void renderMusicCommandsAndPromptMessage() {
        System.out.println("P = Play Music, S= Stop Music, R=Reset, V =Volume, Q= quit music player");
        System.out.println("Enter your choice: ");
    }

    public void renderCommandIsRequiredMessage() {
        System.err.println("MUST ENTER A COMMAND TO CONTINUE...");
    }

    public void renderEnterNameMessage() {
        System.out.println("Enter your name:");
    }

    public void renderEnterNameEnforceMessage() {
        System.err.println("YOU MUST ENTER A NAME");
    }

    public void renderInvalidCharacterSelectedMessage() {
        System.err.println("INVALID CHARACTER SELECTED");
    }

    public void renderPlayerNameBeginGameMessage(String playerName) {
        System.out.println(playerName + " lets go save Ellane!");
    }

    public void renderPlayerCreatedMessage() {
        System.out.println("New Player Created!");
    }

    public void renderBombFoundExplosionMessage() {
        System.err.println("KABOOOM!!");
        System.out.println("A bomb exploded when you enter the room");
    }

    public void renderFoundEllaneLocationMessage(String location) {
        System.out.println("ellane is hiding in the : " + location);
        System.out.println("You've found Ellane!");
    }

    public void renderSelectCharacterMessage() {
        System.out.println("Select character to play as:");
    }
}