package com.ellane.view;

import com.ellane.app.EllaneApp;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainScreen {
    MainScreen(){

        JFrame title = new JFrame();
        TopMenu topMenu = new TopMenu();
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(topMenu.musicMenu);
        menuBar.add(topMenu.saveGame);
        menuBar.add(topMenu.gameMap);

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(1, 2));
        container.setBackground(Color.black);
        title.add(container);

        JTextArea commands = new JTextArea();
        commands.setText(EllaneView.printGameCommands());
        commands.setBackground(Color.BLACK);
        commands.setForeground(Color.WHITE);
        commands.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        commands.setEditable(false);
        commands.setFont(new Font("Interstate", Font.BOLD, 17 ));;
        container.add(commands);


        JPanel info = new JPanel();
        info.setLayout(new BorderLayout());
        info.setBackground(Color.black);
        title.add(info);

        JTextArea inv = new JTextArea();
        inv.setText("Inventory: []");
        inv.setBackground(Color.BLACK);
        inv.setForeground(Color.WHITE);
        inv.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        inv.setEditable(false);
        inv.setFont(new Font("Interstate", Font.BOLD, 20 ));;
        info.add(inv);


        JTextArea currHealth = new JTextArea();
        currHealth.setText("Health: 100%");
        currHealth.setBackground(Color.BLACK);
        currHealth.setForeground(Color.WHITE);
        currHealth.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        currHealth.setEditable(false);
        currHealth.setFont(new Font("Interstate", Font.BOLD, 20 ));;
        info.add(currHealth);




        title.setTitle("Ellane");
        title.setLayout(new GridLayout(4,1));
        title.setJMenuBar(menuBar);
        title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        title.setSize(2300, 1500);
        title.setVisible(true);

    }
}