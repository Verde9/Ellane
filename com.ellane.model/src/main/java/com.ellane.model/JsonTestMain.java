package com.ellane.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

class JsonTestMain {
    public static void main(String[] args) {

        String jsonSource = "testRooms.json";
        try {
            JsonNode node = Json.parse(new File(jsonSource));
            System.out.println(node.get("currentRoom").asText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}