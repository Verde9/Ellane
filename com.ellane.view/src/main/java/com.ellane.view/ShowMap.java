package com.ellane.view;

import javax.swing.*;
import java.awt.*;

public class ShowMap {
    ShowMap(){
        JFrame f = new JFrame();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        f.setUndecorated(false);

        ImageIcon image = new ImageIcon("img/map.png");

        JLabel lbl = new JLabel(image);

        f.getContentPane().add(lbl);

        f.setSize(image.getIconWidth(), image.getIconHeight());

        int x = (screenSize.width - f.getSize().width)/2;
        int y = (screenSize.height - f.getSize().height)/2;

        f.setLocation(x, y); //sets the location of the jframe
        f.setVisible(true); //makes the jframe visible
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setTitle("Map");
    }
}