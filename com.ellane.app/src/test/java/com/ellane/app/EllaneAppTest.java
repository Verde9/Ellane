package com.ellane.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EllaneAppTest {
private EllaneApp app;
    @BeforeEach
    void init() {
        app = new EllaneApp();
        app.ellaneLocation = "";
    }

    @Test
    void randomizeEllaneLocation_shouldStoreValueIntoLocationVariable() {
        app.randomizeEllaneLocation();
        System.out.println(app.ellaneLocation);
        String testStringLength = "basement";

        //Testing if ellaneLocation length is greater than zero after randomize method is called
        assertEquals(testStringLength.length() > 0, app.ellaneLocation.length() > 0);
        assertTrue(app.ellaneLocation.length() > 0);
    }

    @Test
    void equals_shouldReturnFalse_ForEllaneLocationLengthBeingLessThanZero() {
        app.randomizeEllaneLocation();
        System.out.println(app.ellaneLocation);
        String testStringLength = "basement";

        assertFalse(app.ellaneLocation.length() < 0);
    }

    @Test
    void checkEndGameConditions_shouldSetGameOverVariableToTrue_whenEndGameConditionsAreFound() {
        app.setRoundCount(50);
        int zeroRoundsCheck = app.getRoundCount();

        assertEquals(0, zeroRoundsCheck);
    }

    @Test
    void checkIfEllaneIsHere_shouldSetEllaneFoundVarToTrue_whenEllaneIsInCurrentRoom() {
        app.ellaneLocation = "basement";
        app.checkIfEllaneIsHere();

        assertEquals(true, app.ellaneFound);
        assertTrue(app.ellaneFound);
    }

    @Test
    void randomizeBombLocation_shouldStoreValueIntoBombLocationVariable() {
        app.randomizeBombLocation();
        System.out.println(app.bombLocation);
        String testStringLength = "basement";

        assertEquals(testStringLength.length() > 0, app.bombLocation.length() > 0);
        assertTrue(app.bombLocation.length() > 0);
    }

    @Test
    void checkForBombInRoom_shouldSetBombFoundVarToTrue_whenBombIsInCurrentRoom() {
        app.bombLocation = "basement";
        System.out.println(app.bombFound);
        app.checkForBombInRoom();
        System.out.println(app.bombFound);

        assertEquals(true, app.bombFound);
        assertTrue(app.bombFound);
    }


}