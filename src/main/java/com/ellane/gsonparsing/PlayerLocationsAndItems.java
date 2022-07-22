package com.ellane.gsonparsing;

import com.google.gson.Gson;

public class PlayerLocationsAndItems {


    String currentRoom,south,item,item_status,item2,item_status2,randenc,desc;



    //private GsonUserSimple gsonUserSimple;

    public PlayerLocationsAndItems(String currentRoom, String south,
                                   String item, String item_status,
                                   String item2, String item_status2,
                                   String randenc, String desc) {
        this.currentRoom = currentRoom;
        this.south = south;
        this.item = item;
        this.item_status = item_status;
        this.item2 = item2;
        this.item_status2 = item_status2;
        this.randenc = randenc;
        this.desc = desc;
    }



    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem_status() {
        return item_status;
    }

    public void setItem_status(String item_status) {
        this.item_status = item_status;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem_status2() {
        return item_status2;
    }

    public void setItem_status2(String item_status2) {
        this.item_status2 = item_status2;
    }

    public String getRandenc() {
        return randenc;
    }

    public void setRandenc(String randenc) {
        this.randenc = randenc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "PlayerLocationsAndItems{" +
                "currentRoom='" + currentRoom + '\'' +
                ", south='" + south + '\'' +
                ", item='" + item + '\'' +
                ", item_status='" + item_status + '\'' +
                ", item2='" + item2 + '\'' +
                ", item_status2='" + item_status2 + '\'' +
                ", randenc='" + randenc + '\'' +
                ", desc='" + desc + '\'' +
                ", gsonUserSimple=" + //gsonUserSimple +
                '}';
    }
}