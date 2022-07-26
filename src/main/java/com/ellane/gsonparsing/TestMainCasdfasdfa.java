package com.ellane.gsonparsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

class TestMainCasdfasdfa {
    public static void main(String[] args) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PlayerLocationsAndItems playerLocationsAndItems = new PlayerLocationsAndItems("'BEDROOM'","OPEN AREA", "sword",
                "inside of display case. It is Unlocked",
                "gun", "its a MF gun, but it doesnt do anything without bullets",
                "20", "This room is dope");
        String jsonStr = gson.toJson(playerLocationsAndItems, PlayerLocationsAndItems.class);
        System.out.println("JsonRules String: \n" + jsonStr);
        JsonElement jsonElement = gson.toJsonTree(playerLocationsAndItems);
        jsonElement.getAsJsonObject().addProperty("big gun", "BMFG");
        jsonStr = gson.toJson(jsonElement);
        System.out.println("JsonRules string after inserting additional property" + jsonStr);

/*        String bedroom2 = "{\n" +
                "  \"currentRoom\": \"BEDROOM\",\n" +
                "  \"south\": \"OPEN AREA\",\n" +
                "  \"item\": \"sword\",\n" +
                "  \"item_status\": \" inside of a display case. It is unlocked\",\n" +
                "  \"item2\": \"gun\",\n" +
                "  \"item_status2\": \"its a MF gun..but it doesnt do anything with out bullets\",\n" +
                "  \"randenc\": \"20\",\n" +
                "  \"desc\": \"You are in a bedroom. There is nothing of use in this room. It stinks and everything looks crappy, but you see, to the SOUTH; an ugly OPEN AREA\"\n" +
                "\n" +
                "}";*/
        //Gson json = new Gson();
        //PlayerLocationsAndItems bedroom3 = json.fromJson(bedroom2, PlayerLocationsAndItems.class);
        //System.out.println(bedroom3.getItem2());
        //System.out.println(bedroom3.getSouth());
    }

}