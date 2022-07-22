package com.ellane.gsonparsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class roomTestMAIN {
    public static void main(String[] args) {


        //serializeRoom();
        deserializeRoom();



    }

    //bedroom","OPEN AREA", "sword",
    //                "inside of display case. It is Unlocked",
    //                "gun", "its a MF gun, but it doesnt do anything without bullets",
    //                "20", "This room is dope"


    private static void serializeRoom() {
        List<roomTest.RoomItemsAndDesc> roomItemsAndDescs = new ArrayList<>();
        roomItemsAndDescs.add(new roomTest.RoomItemsAndDesc("bedroom","OPEN AREA", "sword",
                "inside of display case. It is Unlocked",
                "gun", "its a MF gun, but it doesnt do anything without bullets",
                "20", "This room is dope"));
        roomItemsAndDescs.add(new roomTest.RoomItemsAndDesc("bedroom1","OPEN AREA2", "sword3",
                "inside of display case. It is Unlocked4",
                "gun5", "6its a MF gun, but it doesnt do anything without bullets",
                "204", "This room is dop0e"));

       String json1 = new Gson().toJson(roomItemsAndDescs);
        System.out.println(json1);



       roomTest roomTest = new roomTest(roomItemsAndDescs);

       String json= new Gson().toJson(roomTest);
        System.out.println(json);
    }

    private static void deserializeRoom() {
        String deSerialRoomTest = "[{\n" +
                "  \"currentRoom\": \"BEDROOM\",\n" +
                "  \"south\": \"OPEN AREA\",\n" +
                "  \"item\": \"sword\",\n" +
                "  \"item_status\": \" inside of a display case. It is unlocked\",\n" +
                "  \"item2\": \"gun\",\n" +
                "  \"item_status2\": \"its a MF gun..but it doesnt do anything with out bullets\",\n" +
                "  \"randenc\": \"20\",\n" +
                "  \"desc\": \"You are in a bedroom. There is nothing of use in this room. It stinks and everything looks crappy, but you see, to the SOUTH; an ugly OPEN AREA\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "  {\n" +
                "    \"currentRoom\": \"OPEN AREA\",\n" +
                "    \"north\": \"ROOM\",\n" +
                "    \"south\": \"FRONT DOOR\",\n" +
                "    \"east\": \"LIVING ROOM\",\n" +
                "    \"west\": \"KITCHEN\",\n" +
                "    \"randenc\": \"0\",\n" +
                "    \"desc\": \"You are in an open area. The House has a terrible flor plan for you to see. To the WEST: a decrepit KITCHEN, To the North: a hellhole of a BEDROOM, to the SOUTH: a wooden FRONT DOOR, and to the EAST: a fugly LIVING ROOM, and I mean FUUUUUUUGGGGGGGGLLLLLY... There is nothing in this room to use.\"\n" +
                "  }\n" +
                "]";
        DeSerialRoomTest[] deSerialRoomTests = new Gson().fromJson(deSerialRoomTest, DeSerialRoomTest[].class);

        System.out.println(Arrays.asList(deSerialRoomTests));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        JsonElement jsonElement = gson.fromJson(Arrays.toString(deSerialRoomTests), JsonElement.class);
//        String jsonINString = gson.toJson(jsonElement);
//        System.out.println(jsonINString);



    }

}