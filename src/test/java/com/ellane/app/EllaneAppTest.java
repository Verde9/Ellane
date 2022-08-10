package com.ellane.app;

import com.ellane.model.Player;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EllaneAppTest {

    @Test
    public void initialize_shouldPresentAWelcomeMesaggeAndAskUserToStartGame_StopMusic_ManageVolueme() throws IOException, InterruptedException {
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
    public void createPlayerOneCharacter_shouldPromptUserToTakeNameOfTheUserPlayer() throws IOException, InterruptedException {
        EllaneApp player = new EllaneApp();
        player.createPlayerOneCharacter();
    }

}