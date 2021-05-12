package ru.projectx.clicker;

import javax.swing.*;
import java.awt.*;

public class XClicker extends JFrame {

    public XClicker() {
        EventQueue.invokeLater(() -> { // поток диспетчеризации событий
            this.setIconImage(ResourcesManager.getIcon("icon"));
            BorderLayout layout = new BorderLayout();
            this.getContentPane().setLayout(layout);
            this.getContentPane().add(new Background());
            this.setTitle(Constants.title);
            this.setResizable(false);
            this.setPreferredSize(new Dimension(Constants.width, Constants.height));
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setVisible(true);
        });
    }

    //todo сделать поумнее
    public static void main(String[] args) { new XClicker(); }

    public static class Background extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon background = new ImageIcon(ResourcesManager.getImage("background"));
            g.drawImage(background.getImage().getScaledInstance(Constants.width, Constants.height, Image.SCALE_AREA_AVERAGING), 0, 0, this);
        }
    }
}
