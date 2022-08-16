package com.ellane.view;


import com.ellane.character.Ellane;
import com.ellane.character.Player;
import com.ellane.character.Terrorist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


// This class contains most of the dialogue used this the app.

public class EllaneView {

    public EllaneView() {
    }

    public static String renderWelcomeGameMessage() {
        String banner = null;
        if (Files.exists(Path.of("resources/gameArt.txt"))) {
            try {
                banner = Files.readString(Path.of("resources/gameArt.txt"));
                return banner;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

//    public static String renderRemainingPlayerHealth(int health) {
//        return("You lost blood from your wound & your health drops..\n" +
//        health + "% health remaining...");
//    }

    public static String renderDisplayGameInfo() {
        return("The chaos spreads & and mayhem erupts around the city\n" +
                "The City of Seattle is under attack by Terrorists!\n" +
                "Fire is spreading from building to building \n& most signs of life are gone!\n" +
                "You're apartment building is on fire and you are injured by falling debris!\n" +
                "You're bleeding out but can still manage to move \nbut you need to move quickly becuase the fire is rapily growing!\n" +
                "Luckily there is a helicopter on the roof evacuating \nthe survivors that made it to the roof\n" +
                "Adding more chaos to the mix, a women who was hit by \nthe same debris as you is now dying and \nasks you to save her 3 year old daughter that's in her apartment\n" +
                "To make matters even worse, the women lost the key to \nher apartment during all the chaos\n" +
                "The objectives are simple but will be a challenge:\n" +
                " - Find the keys to get Ellane from her apartment\n" +
                " - Save Ellane \n" +
                " - Make it to the roof before the Helicopter leaves\n" +
                "Keep in mind that you're bleeding out so you have \nuntil your health reaches 0 to make it to the roof safely\n" +
                "Also beware of terrorists while navigating through the building, \nif you run into one and do not have a weapon you will die.."
        );
    }


    public static String renderShowGameControls() {
        return("HERE ARE THE GAME COMMANDS: \n" +
                "    GO + [north, south, east, west]\n" +
                "    LOOK + [north, south, east, west]\n" +
                "    GRAB | GET | TAKE + [item_name]\n" +
                "    DROP + [item_name]\n" +
                "    QUIT - will end the game.\n");
    }

    public void renderImmediateQuitGameMessage() {
        System.out.println("...Thanks for abandoning Ellane! Goodbye!");
    }



    public static String renderEndGameMessageAndResults(int playerHealth) {

        return("GAME HAS ENDED...\n" +
                "You ended the game with:  " + playerHealth + ": Player Health\n" +
                "Thanks for playing Ellane!");
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

    public static String renderTerroristDialogue(Terrorist terrorist, Player player) {

        return("\n"+terrorist.getName() + ": " + "Американец! Иди сюда быстро!...\n"+
                "(Translation: American! Come here now!.. ) \n"+
                player.getName() + ": " + "Oh shit! This guy must be part of this invasion.\n"+
                player.getName() + ": " + "Fuck!! Have to think fast! Now! Or I'm literally going to die.\n"+
                terrorist.getName() + ": " + "К счастью для тебя, американец, я не беру пленных!!...\n"+
                "(Translation: Lucky for you American, I take no prisoners.) \n"+
                player.getName() + ": " + " Should I fight or should I run?");
    }

    public static String renderEllaneDialogue(Ellane ellane, Player player) {

        return("\n"+ellane.getName() + ": " + "* SHRIEKS *\n"+
                player.getName() + ": " + "Ellane.. ELLANE !! It's ok! I'm not going to hurt you! Your mother sent me to come get you.\n"+
                ellane.getName() + ": " + "Why did mommy send you?? Where is mommy???\n"+
                player.getName() + ": " + "........ she's somewhere safe. She asked me to come get you so that I can get you to safety as well.\n"+
                player.getName() + ": " + "Now we don't have much time Ellane, I'm not doing so well and don't have much time. We have to go NOW!!\n"+
                ellane.getName() + ": " + "*Cries and sniffs* Ok mister, but I'm scared. I heard people screaming earlier.\n"+
                player.getName() + ": " + "Don't worry Ellane, I promised your mother that I would protect you and get you to safety but if we don't go now I can't keep that promise!\n"+
                ellane.getName() + ": " + "Ok, ok *sniffs* Let's go to mommy.\n"+
                player.getName() + ": " + "Yes, let's go..");
    }

    public static String renderWinGameDialogue(Ellane ellane, Player player) {

        return("\nHelicopter Pilot: Hold on! we have a few more!\n"+
                player.getName() + ": " + "Thank God! Look Ellane we made it!\n"+
                ellane.getName() + ": " + "Thanks Mister! I can't wait to see my mommy!\n"+
                player.getName() + ": " + "........ yeah.. I'm sure she'll be happy to know that you're safe\n"+
                "Helicopter Pilot: Alright! That's it, let's MOVE MOVE MOVE!\n"+
                "CONGRATULATIONS, you saved Ellane and yourself\n"+
                "Thanks for Playing Ellane!\n"+
                "GAME OVER");

    }
}