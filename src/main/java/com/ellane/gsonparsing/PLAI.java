package com.ellane.gsonparsing;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class PLAI {



    public void generateMap(){

        PlayerLocationsAndItems playerLocationsAndItems = new PlayerLocationsAndItems("'BEDROOM'","OPEN AREA", "sword",
                "inside of display case. It is Unlocked",
                "gun", "its a MF gun, but it doesnt do anything without bullets",
                "20", "This room is dope");
        try {
            // create a map
            Map<String, Object> map = new HashMap<>();
            map.put("ROOM", new String[]{"bedroom", "open area", "den"});
            map.put("items", new String[]{"sword","gun", "candy", "bread"});
            map.put("Magic", new String[]{"fire", "ice"});
            map.put("admin", true);

            // create a writer
            Writer writer = new FileWriter("testBedroom.json");

            // convert map to JSON File
            new Gson().toJson(map, writer);

            // close the writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}