package com.ellane.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class test {

    public static void main(String[] args) {

        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("rooms.json"));

            List<Locations> location = new Gson().fromJson(reader, new TypeToken<List<Locations>>() {}.getType());

            System.out.println(location);

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}