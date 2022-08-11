package com.ellane.app;

import com.ellane.character.Player;
import com.ellane.character.Terrorist;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EllaneAppTest {

    @Test
    public void initialize_shouldPresentAWelcomeMessageAndAskUserToStartGame_StopMusic_ManageVolueme() throws IOException, InterruptedException {
        EllaneApp ini = new EllaneApp();
        ini.initialize();
    }


    @Test
    public void generateItems_shouldShuffleItemAddedInTheGame() {
        EllaneApp gen = new EllaneApp();
        System.out.println("Generating item first time");
        gen.generateItems();
        System.out.println(gen.items);
        System.out.println("Generating item second time");
        gen.generateItems();
        System.out.println(gen.items);
    }

    @Test
    public void generateLocation_shouldLocationsAfterItGenerate() {
        EllaneApp gen = new EllaneApp();
        gen.generateLocation();
        System.out.println(gen.locations);
    }

    @Test
    public void setPlayerName_shouldPromptUserToTakeNameOfTheUserPlayer() throws IOException, InterruptedException {
        EllaneApp player = new EllaneApp();
        player.setPlayerName();
    }

    @Test
    public void randomizeTerrorist_shouldShuffleTheTerroristEnemy_everyTimeIsCall(){
        EllaneApp terror = new EllaneApp();
        terror.generateLocation();
       System.out.println("After calling the method randomizeTerrorist() ");
       for (int i = 0; i < terror.locations.size(); i++) {
           if (terror.locations.get(i).getTerrorist() != null) {
               System.out.println(terror.locations.get(i).getName() + "Has placement number: " + terror.locations.get(i).getTerroristPlacement() + " and has terrorist named" +
                       terror.locations.get(i).getTerrorist().getName());
           }
       }

    }

}