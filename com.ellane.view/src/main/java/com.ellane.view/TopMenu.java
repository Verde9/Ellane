package com.ellane.view;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TopMenu extends JFrame {
    public JMenu gameMap;
    public JMenu saveGame;
    public JMenu musicMenu;
    public static AudioInputStream ais;
    public static Clip clip;

    public TopMenu(){


        gameMap = new JMenu("Map");
        saveGame = new JMenu("Save");
        musicMenu = new JMenu("Music");

        JMenuItem showMap = new JMenuItem("Show Map");
        showMap.addActionListener(e -> new ShowMap());
        gameMap.add(showMap);


        JMenuItem showSave = new JMenuItem("Save Game");
        saveGame.add(showSave);
        //todo: code to save game

        JMenuItem playMusic = new JMenuItem("Play Music");
        musicMenu.add(playMusic);
        playMusic.addActionListener(e -> {
            if(ais != null){
                clip.close();
            }
            try {
                clip = AudioSystem.getClip();
                ais = AudioSystem.getAudioInputStream(new File("music/For Work 2_1.wav"));
                clip.open(ais);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            } catch (LineUnavailableException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        JMenuItem stopMusic = new JMenuItem("Stop Music");
        musicMenu.add(stopMusic);
        stopMusic.addActionListener(e -> {
            try {
                ais.close();
                clip.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


    }

}