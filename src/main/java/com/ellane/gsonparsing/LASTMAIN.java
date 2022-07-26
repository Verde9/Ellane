package com.ellane.gsonparsing;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;

class LASTMAIN {

    public static void main(String[] args) throws JsonProcessingException {


        ObjectMapper mapper = new ObjectMapper();
        try {
            String userDataJSON ="{\"ROOMS\":\n" +
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

            GsonNestedUserSimple[] userFromJSON = mapper.readValue(userDataJSON, GsonNestedUserSimple[].class);
            System.out.println(userFromJSON);

        } catch (JsonGenerationException e) {
            System.out.println(e);
        } catch (JsonMappingException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }



    }





}


