package com.ellane.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypeWriter {
    private Timer timer;
    private int characterIndex = 0;
    private String input;
    private JTextArea textArea;

    public TypeWriter(JTextArea textArea, String input) {
        this.textArea = textArea;
        this.input = input;
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (characterIndex < input.length()) {
                    textArea.append(Character.toString(input.charAt(characterIndex)));
                    characterIndex++;
                } else {
                    stop();
                }
            }
        });
    }

    public void start() {
        textArea.setText(null);
        characterIndex = 0;
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

}