package com.ellane.view;


import com.ellane.app.EllaneApp;
import com.ellane.model.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TitleScreen implements ActionListener {
    public TitleScreen(){
        JFrame title = new JFrame();
        TopMenu topMenu = new TopMenu();
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(topMenu.musicMenu);
        menuBar.add(topMenu.saveGame);
        menuBar.add(topMenu.gameMap);

        JPanel image = new JPanel();
        image.setBackground(Color.black);
        title.add(image);

        JTextArea asciiEllane = new JTextArea();
        asciiEllane.setText(EllaneView.renderWelcomeGameMessage());
        asciiEllane.setForeground(Color.white);
        asciiEllane.setBackground(Color.black);
        asciiEllane.setFont(new Font("Courier", Font.PLAIN, 8 ));
        asciiEllane.setEditable(false);
        image.add(asciiEllane);

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setLayout(new GridLayout(5,1));
        startButtonPanel.setBackground(Color.black);
        title.add(startButtonPanel);

        JLabel callToAction = new JLabel();
        callToAction.setHorizontalAlignment(JLabel.CENTER);
        callToAction.setText("Can you save Ellane in time?");
        callToAction.setForeground(Color.white);
        callToAction.setBackground(Color.black);
        callToAction.setFont(new Font("Interstate", Font.BOLD, 26 ));
        startButtonPanel.add(callToAction);
        startButtonPanel.add(Box.createRigidArea(new Dimension(5, 5)));

        JButton startButton = new JButton();
        startButton.setText("Start Game");
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
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EllaneApp.player.setName (JOptionPane.showInputDialog("What is your Name?"));
                new StoryScreen();
            }
        });

        JPanel buttonJPanel = new JPanel();
        buttonJPanel.setBackground(Color.black);
        buttonJPanel.setPreferredSize(new Dimension(200,200));
        buttonJPanel.add(startButton);

        startButtonPanel.add(buttonJPanel);

        title.setTitle("Ellane");
        title.setLayout(new GridLayout(2,1));
        title.setJMenuBar(menuBar);
        title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        title.setSize(2300, 1500);
        title.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }


}