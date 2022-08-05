package com.ellane.model;

public enum Directions {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west"),
    UP("up"),
    DOWN("down");



    private final String value;


    Directions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}