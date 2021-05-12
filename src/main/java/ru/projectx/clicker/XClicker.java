package ru.projectx.clicker;

import javax.swing.*;
import java.awt.*;

public class XClicker extends JFrame {

    public XClicker() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new Panel());
        this.setTitle(Constants.title);
        this.setPreferredSize(new Dimension(Constants.width, Constants.height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    //todo сделать поумнее
    public static void main(String[] args) { new XClicker(); }
}
