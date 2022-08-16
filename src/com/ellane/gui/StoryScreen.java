package com.ellane.gui;

import com.ellane.view.EllaneView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoryScreen {
    public StoryScreen(){
        String testText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "\nsed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "\nVenenatis a condimentum vitae sapien pellentesque. " +
                "\nPretium fusce id velit ut tortor pretium viverra. " +
                "\nEgestas erat imperdiet sed euismod nisi porta lorem. " +
                "\nUt ornare lectus sit amet est. Viverra tellus in hac habitasse platea dictumst vestibulum. " +
                "\nAdipiscing at in tellus integer feugiat scelerisque varius morbi enim.";

        JFrame title = new JFrame();
        TopMenu topMenu = new TopMenu();
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(topMenu.musicMenu);
        menuBar.add(topMenu.saveGame);
        menuBar.add(topMenu.gameMap);

        JPanel story = new JPanel();
        story.setBackground(Color.black);
        title.add(story);

        JTextArea storyText = new JTextArea();
        TypeWriter typeWriter = new TypeWriter(storyText, EllaneView.renderDisplayGameInfo());
        typeWriter.start();
        storyText.setForeground(Color.white);
        storyText.setBackground(Color.black);
        storyText.setFont(new Font("Interstate", Font.BOLD, 26 ));
        storyText.setEditable(true);
        story.add(storyText);

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBackground(Color.black);
        title.add(startButtonPanel);



        JButton startButton = new JButton();
        startButton.setText("Ready? Let's Save Ellane!");
        startButton.setOpaque(true);
        startButton.setFont(new Font("Interstate", Font.BOLD, 28 ));
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent event) {
                startButton.setBackground(Color.decode("#FFE81F"));
            }

            public void mouseExited(MouseEvent event) {
                startButton.setBackground(Color.black);
            }
        });
        startButton.addActionListener(e -> {
            title.setVisible(false);
            new MainScreen();
        });

        startButtonPanel.add(startButton);

        title.setTitle("Ellane");
        title.setLayout(new GridLayout(2,1));
        title.setJMenuBar(menuBar);
        title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        title.setSize(2300, 1500);
        title.setVisible(true);
    }
}