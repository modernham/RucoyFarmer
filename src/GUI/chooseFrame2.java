package GUI;

import botMain.State;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class chooseFrame2 implements Runnable  {
    public float[] hsv2 = new float[2];
    public static boolean colorChosen = false;

    public void run() {
        final JFrame f = new JFrame("Color Picker");
        if (State.colorfinder.image != null) {
            State.colorfinder.getScreen();
            BufferedImage image = State.colorfinder.image;
            Icon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            f.getContentPane().add(label);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            f.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    State.hsv2 =  State.colorfinder.getPixelColor(State.mouse.getPosition());
                    SlayerGUI.configGUI.textArea1.setText("Hue:" + State.hsv2[0] + "\n Saturation: " + State.hsv2[1] + "\n Brightness: " + State.hsv2[2]);
                    colorChosen = true;
                    f.dispose();
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                @Override
                public void mouseExited(MouseEvent e) {
                }
                @Override
                public void mousePressed(MouseEvent e) {
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
