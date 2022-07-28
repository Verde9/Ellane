package com.ellane.model;

public enum ActionCommands {
    GO("go"),
    CONTROLS("controls"),
    CLIMB("climb"),
    LOOK("look"),
    USE("use"),
    DROP("drop"),
    GRAB("grab"),
    HEALTH("health"),
    INVENTORY("inventory"),
    PLAY_MUSIC("play music"),
    STOP_MUSIC("stop music");

    private final String value;

    ActionCommands(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}