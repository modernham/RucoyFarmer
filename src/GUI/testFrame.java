package GUI;

import botMain.State;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class testFrame implements Runnable  {
    public static Rectangle MAINSCREEN = new Rectangle(110,82,900,525);

    public void run() {
        final JFrame f = new JFrame("Test Configuration (Enemy should be highlighted in blue)");
        BufferedImage image = State.colorfinder.getTest(MAINSCREEN, State.hsv1, State.hsv2, 50);
            Icon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            JLabel inst = new JLabel();
            inst.setText("Enemies should be highlighted in blue, if none are try new colors");
            f.getContentPane().add(label);
            //f.getContentPane().add(inst);
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
