package com.ellane.gsonparsing;

import com.google.gson.Gson;

class TestMainCasdfasdfa {
    public static void main(String[] args) {
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
        Gson json = new Gson();
        GsonNestedUserSimple bedroom3 = json.fromJson(bedroom2, GsonNestedUserSimple.class);


        System.out.println(bedroom3.getItem2());
    }

}