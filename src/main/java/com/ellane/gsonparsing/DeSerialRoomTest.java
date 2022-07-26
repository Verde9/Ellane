package com.ellane.gsonparsing;

import java.util.Objects;

class DeSerialRoomTest {

    String currentRoom,south,item,item_status,item2,item_status2,randenc,desc;


    public DeSerialRoomTest(String currentRoom, String south, String item, String item_status, String item2, String item_status2, String randenc, String desc) {
        this.currentRoom = currentRoom;
        this.south = south;
        this.item = item;
        this.item_status = item_status;
        this.item2 = item2;
        this.item_status2 = item_status2;
        this.randenc = randenc;
        this.desc = desc;
    }
}