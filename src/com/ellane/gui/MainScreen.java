package com.ellane.gui;

import com.ellane.app.EllaneApp;
import com.ellane.character.Terrorist;
import com.ellane.view.EllaneView;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.InsetsUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainScreen {

    public static JTextArea outPut = new JTextArea();
    public static JTextField input = new JTextField(40);
    public static JTextArea invHealth = new JTextArea();
    public static JLabel destImageLabel = new JLabel();
    public static Button submitButton = new Button("Submit");
    public static boolean isTerrorist = false;
    public MainScreen(){
//
//        JFrame mainFrame = new JFrame();
//        TopMenu topMenu = new TopMenu();
//        JMenuBar menuBar = new JMenuBar();
//
//        menuBar.add(topMenu.musicMenu);
//        menuBar.add(topMenu.saveGame);
//        menuBar.add(topMenu.gameMap);
//
//        JPanel dialogPane = new JPanel();
//        dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
//        dialogPane.setLayout(new GridLayout(2, 2));
//
//        JPanel emptyPanel = new JPanel(new GridLayout(1, 1));
//        emptyPanel.setBackground(Color.black);
//        dialogPane.add(emptyPanel);
//
//        JPanel directionsPanel = new JPanel(new GridLayout(1, 1));
//        directionsPanel.setBackground(Color.black);
//        dialogPane.add(directionsPanel);
//
//        JScrollPane scrollPane = new JScrollPane();
//        JTextPane gameTextPane = new JTextPane();
//        scrollPane.setViewportView(gameTextPane);
//        dialogPane.add(directionsPanel);
//
//        JPanel inputOutput = new JPanel(new GridLayout(2, 1));
//        JScrollPane scrollPaneIO = new JScrollPane();
//        JTextPane textPaneIO = new JTextPane();
//        JTextField textFieldIO = new JTextField();
//        scrollPaneIO.setViewportView(textPaneIO);
//        inputOutput.add(scrollPaneIO);
//        inputOutput.add(textFieldIO);
//        dialogPane.add(inputOutput);
//
//        JPanel imagePanel = new JPanel(new GridLayout(1, 1));
//        dialogPane.add(imagePanel);
//
//
//
//
////        JPanel container = new JPanel();
////        container.setLayout(new GridLayout(1, 2));
////        container.setBackground(Color.black);
////
////
////        JTextArea commands = new JTextArea();
////        commands.setText(EllaneView.printGameCommands());
////        commands.setBackground(Color.BLACK);
////        commands.setForeground(Color.WHITE);
////        commands.setBorder(BorderFactory.createLineBorder(Color.WHITE));
////        commands.setEditable(false);
////        commands.setFont(new Font("Interstate", Font.BOLD, 17 ));;
////        container.add(commands);
////
////
////        JPanel info = new JPanel();
////        info.setLayout(new BorderLayout());
////        info.setBackground(Color.black);
////        mainFrame.add(info);
////
////        JTextArea inv = new JTextArea();
////        inv.setText("Inventory: []");
////        inv.setBackground(Color.BLACK);
////        inv.setForeground(Color.WHITE);
////        inv.setBorder(BorderFactory.createLineBorder(Color.WHITE));
////        inv.setEditable(false);
////        inv.setFont(new Font("Interstate", Font.BOLD, 20 ));;
////        info.add(inv);
////
////
////        JTextArea currHealth = new JTextArea();
////        currHealth.setText("Health: 100%");
////        currHealth.setBackground(Color.BLACK);
////        currHealth.setForeground(Color.WHITE);
////        currHealth.setBorder(BorderFactory.createLineBorder(Color.WHITE));
////        currHealth.setEditable(false);
////        currHealth.setFont(new Font("Interstate", Font.BOLD, 20 ));;
////        info.add(currHealth);
//
//
//
//        mainFrame.add(dialogPane, BorderLayout.CENTER);
//        mainFrame.setTitle("Ellane");
//        mainFrame.setLayout(new BorderLayout());
//        mainFrame.setJMenuBar(menuBar);
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame.setSize(2300, 1500);
//        mainFrame.setVisible(true);
//
//    }
//}
        EllaneApp app = new EllaneApp();
        try {
            app.startGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JFrame mainFrame = new JFrame();


        TopMenu topMenu = new TopMenu();
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(topMenu.musicMenu);
        menuBar.add(topMenu.saveGame);
        menuBar.add(topMenu.gameMap);

        JPanel dialogPane = new JPanel();
        dialogPane.setBackground(Color.white);
        dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        dialogPane.setLayout(new GridLayout(2, 2));

        invHealth.setText(" Health: " + EllaneApp.player.getHealth() +"\n Inventory: []");
        invHealth.setFont(new Font("Interstate", Font.BOLD, 24 ));
        invHealth.setForeground(Color.cyan);
        invHealth.setBackground(Color.black);
        invHealth.setEditable(false);


        JPanel emptyPanel = new JPanel(new GridLayout(1, 1));
        emptyPanel.setBackground(Color.black);
        emptyPanel.add(invHealth);
        dialogPane.add(emptyPanel);

        JPanel directionsPanel = new JPanel(new BorderLayout());
        directionsPanel.setBackground(Color.black);
        dialogPane.add(directionsPanel, BorderLayout.EAST);


        JTextArea gameTextPane = new JTextArea();
        gameTextPane.setText(EllaneView.renderShowGameControls());
        gameTextPane.setFont(new Font("Interstate", Font.BOLD, 24 ));
        gameTextPane.setForeground(Color.MAGENTA);
        gameTextPane.setBackground(Color.black);
        gameTextPane.setEditable(false);
        directionsPanel.add(gameTextPane, BorderLayout.AFTER_LINE_ENDS);

        JPanel inputOutput = new JPanel(new GridLayout(2, 1));
        inputOutput.setBackground(Color.black);

        JPanel outPutWithButton = new JPanel();
        outPutWithButton.setBackground(Color.black);


        outPut.setEditable(false);
        outPut.setFont(new Font("Interstate", Font.BOLD, 20 ));
        outPut.setForeground(Color.WHITE);
        outPut.setBackground(Color.black);
        outPut.setBorder(BorderFactory.createLineBorder(Color.yellow));
        outPut.setLineWrap(true);
        inputOutput.add(outPut);


        input.setBorder(BorderFactory.createLineBorder(Color.yellow));
        input.setFont(new Font("Interstate", Font.BOLD, 24 ));
        input.setForeground(Color.WHITE);
        input.setBackground(Color.black);
        submitButton.setFont(new Font("Interstate", Font.BOLD, 28 ));
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent event){
                submitButton.setBackground(Color.decode("#FFE81F"));
            }
            public void mouseExited(MouseEvent event){
                submitButton.setBackground(Color.black);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isTerrorist) {
                    try {
                        app.promptPlayerForDecision();
                        input.setText("");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                else{
                    Terrorist.PlayerDetected(EllaneApp.player, EllaneApp.terrorist, input.getText());
                    input.setText("");
                }
            }
        });
        outPutWithButton.add(input);
        outPutWithButton.add(submitButton);
        inputOutput.add(outPutWithButton);

        dialogPane.add(inputOutput);

        JPanel imagePanel = new JPanel(new GridLayout(1,1));
        imagePanel.setBackground(Color.black);
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        ImageIcon destImage = new ImageIcon("resources/img/lobby.jpeg");
        destImageLabel.setForeground(Color.white);
        destImageLabel.setHorizontalTextPosition(JLabel.CENTER);
        destImageLabel.setVerticalTextPosition(JLabel.TOP);
        destImageLabel.setIcon(destImage);
        destImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(destImageLabel, BorderLayout.NORTH);
        dialogPane.add(imagePanel);


        mainFrame.add(dialogPane);
        mainFrame.setTitle("Ellane");
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(2300, 1500);
        mainFrame.setVisible(true);


    }
}