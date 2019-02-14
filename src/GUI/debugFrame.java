package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class debugFrame implements Runnable {

    public void run() {
        File file = new File("image.jpg");

        while(true)
        try {
            BufferedImage image = ImageIO.read(file);
            Icon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            final JFrame f = new JFrame("Debug Frame");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.getContentPane().add(label);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            f.dispose();
        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }
    }
