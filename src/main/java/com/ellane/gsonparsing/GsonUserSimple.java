package com.ellane.gsonparsing;

public class GsonUserSimple {

    private String ROOM;

    private PlayerLocationsAndItems playerLocationsAndItems;

    public GsonUserSimple(PlayerLocationsAndItems playerLocationsAndItems) {
        this.playerLocationsAndItems = playerLocationsAndItems;
    }

    public GsonUserSimple(String whatRoomAreyouin, PlayerLocationsAndItems playerLocationsAndItems) {
        this.ROOM = whatRoomAreyouin;
        this.playerLocationsAndItems = playerLocationsAndItems;
    }

    public PlayerLocationsAndItems getGsonNestedUserSimple() {
        return playerLocationsAndItems;
    }

    public void setGsonNestedUserSimple(PlayerLocationsAndItems playerLocationsAndItems) {
        this.playerLocationsAndItems = playerLocationsAndItems;
    }
}