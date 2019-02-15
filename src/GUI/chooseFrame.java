package GUI;

import botMain.State;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class chooseFrame implements Runnable {

    public void run() {

            if (State.colorfinder.image != null) {
                State.colorfinder.getScreen();
                BufferedImage image = State.colorfinder.image;
                Icon icon = new ImageIcon(image);
                JLabel label = new JLabel(icon);
                final JFrame f = new JFrame("Color Picker");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.getContentPane().add(label);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

        }
}
