package com.ellane.gsonparsing;

class GsonUserSimple {

    private String ROOM;

    private GsonNestedUserSimple gsonNestedUserSimple;

    public GsonUserSimple(GsonNestedUserSimple gsonNestedUserSimple) {
        this.gsonNestedUserSimple = gsonNestedUserSimple;
    }

    public GsonUserSimple(String whatRoomAreyouin, GsonNestedUserSimple gsonNestedUserSimple) {
        this.ROOM = whatRoomAreyouin;
        this.gsonNestedUserSimple = gsonNestedUserSimple;
    }

    public GsonNestedUserSimple getGsonNestedUserSimple() {
        return gsonNestedUserSimple;
    }

    public void setGsonNestedUserSimple(GsonNestedUserSimple gsonNestedUserSimple) {
        this.gsonNestedUserSimple = gsonNestedUserSimple;
    }
}