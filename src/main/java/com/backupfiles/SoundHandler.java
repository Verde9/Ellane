package com.backupfiles;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class SoundHandler {



    public static void runMusic(String path) {
        try {
            Scanner scanner = new Scanner(System.in);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(1);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
            String response = "";


            while (!response.equals("Q")){
                //System.out.println("P = Play Music, S= Stop Music, R=Reset, V =Volume");
                //System.out.println("Enter your choice: ");
                response = scanner.next();
                response = response.toUpperCase();

                switch (response){
                    case ("P"):
                        clip.start();
                        break;
                    case ("S"):
                        clip.stop();
                        break;
                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;
                    case ("V") :
                        //System.out.println("what do you want the level to be? :  (-17 is lower)");
                        String setVolume = scanner.next();
                        gainControl.setValue(Float.parseFloat(String.valueOf(setVolume)));
                        break;
                    default:
                        //System.out.println("not a valid response for music player");


                }
            }


        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }


    }

}