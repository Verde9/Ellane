package com.ellane.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EllaneApp {
    public void initialize() {
        gameWelcomeMessage();
    }

    private void gameWelcomeMessage() {

        String banner = null;
        if (Files.exists(Path.of("com.ellane.app/resources/gameArt.txt"))) {
            try {
                banner = Files.readString(Path.of("com.ellane.app/resources/gameArt.txt"));
                System.out.println(banner);
                System.out.println("\n");
                System.out.println("Ellane Needs your Help!");
                System.out.println("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("file wasn't found");
        }
    }
}