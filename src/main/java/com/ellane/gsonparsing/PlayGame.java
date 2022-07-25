package com.ellane.gsonparsing;

import com.backupfiles.SoundHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

class PlayGame {
    public static void main(String[] args) throws InterruptedException {
        //SoundHandler.runMusic("src/Music/intro wav 2_1.wav");
        EllaneApp players = new EllaneApp();
        players.initialize();
        //SoundHandler

    }

}