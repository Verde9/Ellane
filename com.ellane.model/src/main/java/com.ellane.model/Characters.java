package com.ellane.model;

public enum Characters {
    MALE_SOLDIER("soldier"),
    KID("kid"),
    DOG("dog");

    private final String characterType;

    Characters(String characterType) {
        this.characterType = characterType;
    }

    public String getCharacterType() {
        return characterType;
    }
}