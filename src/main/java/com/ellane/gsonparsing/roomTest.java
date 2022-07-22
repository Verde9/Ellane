package com.ellane.gsonparsing;

import java.util.List;

class roomTest {

    String ROOMS;
    List<RoomItemsAndDesc> roomItemsAndDescs;

    public roomTest(List<RoomItemsAndDesc> roomItemsAndDescs) {
        this.roomItemsAndDescs = roomItemsAndDescs;
    }

    public roomTest(String ROOMS, List<RoomItemsAndDesc> roomItemsAndDescs) {
        this.ROOMS = ROOMS;
        this.roomItemsAndDescs = roomItemsAndDescs;
    }

    public static class RoomItemsAndDesc {
        String currentRoom,south,item,item_status,item2,item_status2,randenc,desc;

        public RoomItemsAndDesc(String currentRoom, String south, String item, String item_status, String item2,
                                String item_status2, String randenc, String desc) {
            this.currentRoom = currentRoom;
            this.south = south;
            this.item =item;
            this.item_status = item_status;
            this.item2 =item2;
            this.item_status2 = item_status2;
            this.randenc =randenc;
            this.desc = desc;
        }
    }


}