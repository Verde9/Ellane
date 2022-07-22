package com.ellane.gsonparsing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GsonUserSimpleTest {


    public static void main(String[] args) {


        serializeUserSimple();
        //deserializeUserSimpleTestCase();

    }

    public static void serializeUserSimple() {

        GsonNestedUserSimple bedroom = new
        GsonNestedUserSimple("bedroom","OPEN AREA", "sword",
                "inside of display case. It is Unlocked",
                "gun", "its a MF gun, but it doesnt do anything without bullets",
                "20", "This room is dope");
        GsonNestedUserSimple open_area = new GsonNestedUserSimple("OPEN AREA","Please Work", "GIN",
                "PLEASE ITEM STATUS WORK",
                "gun", "its a MF gun, but it doesnt do anything without bullets",
                "20", "This room is dope");


        String bedroom2 = "{\n" +
                "  \"currentRoom\": \"BEDROOM\",\n" +
                "  \"south\": \"OPEN AREA\",\n" +
                "  \"item\": \"sword\",\n" +
                "  \"item_status\": \" inside of a display case. It is unlocked\",\n" +
                "  \"item2\": \"gun\",\n" +
                "  \"item_status2\": \"its a MF gun..but it doesnt do anything with out bullets\",\n" +
                "  \"randenc\": \"20\",\n" +
                "  \"desc\": \"You are in a bedroom. There is nothing of use in this room. It stinks and everything looks crappy, but you see, to the SOUTH; an ugly OPEN AREA\"\n" +
                "\n" +
                "}";

        //GsonNestedUserSimple front_door = new GsonNestedUserSimple("FRONT DOOR");


        //GsonUserSimple user = new GsonUserSimple();

        //String firstJson = new Gson().toJson(front_door);
//        System.out.println(firstJson);

        //Below is correct
        Gson json = new Gson();
        GsonNestedUserSimple bedroom3 = json.fromJson(bedroom2, GsonNestedUserSimple.class);


        System.out.println(bedroom3.getDesc());


    }





    public static void deserializeUserSimpleTestCase() {

        String gsonJson = "{\"ROOMS\":\n" +
                "[{\n" +
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
                "    \"south\": \"PLEASE WORK\",\n" +
                "    \"item\": \"GIN\",\n" +
                "    \"item_status\": \" this wants to make me drink\",\n" +
                "    \"item2\": \"gun\",\n" +
                "    \"item_status2\": \"its a MF gun..but it doesnt do anything with out bullets\",\n" +
                "    \"randenc\": \"0\",\n" +
                "    \"desc\": \"You are in an open area. The House has a terrible flor plan for you to see. To the WEST: a decrepit KITCHEN, To the North: a hellhole of a BEDROOM, to the SOUTH: a wooden FRONT DOOR, and to the EAST: a fugly LIVING ROOM, and I mean FUUUUUUUGGGGGGGGLLLLLY... There is nothing in this room to use.\"\n" +
                "  }\n" +
                "]\n" +
                "}";


        String inputString = "[{\n" +
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

        List<GsonNestedUserSimple> inputList = Arrays.asList(new GsonNestedUserSimple("bedroom","OPEN AREA", "sword",
                        "inside of display case. It is Unlocked",
                        "gun", "its a MF gun, but it doesnt do anything without bullets",
                        "20", "This room is dope"), new GsonNestedUserSimple("OPEN AREA","Please Work", "GIN",
                        "PLEASE ITEM STATUS WORK",
                        "gun", "its a MF gun, but it doesnt do anything without bullets",
                        "20", "This room is dope"));


        Type listOfMyClassObject = new TypeToken<ArrayList<GsonNestedUserSimple>>() {}.getType();

        Gson gson = new Gson();
        List<GsonNestedUserSimple> outputList = gson.fromJson(inputString, listOfMyClassObject);



        System.out.println(inputList);
        //GsonParsing gsonParsing = new Gson().fromJson(gsonJson, GsonParsing.class);
        //System.out.println(gsonParsing);

    }



}