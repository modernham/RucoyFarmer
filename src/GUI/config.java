package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class config{


    static int windowSize = 11; // odd should look nice. Set to 1 for an invisible window
    static int clickDelay = 0; // Delay in ms between closing window and forwarding click. 0 seems to work fine.
    static int trackingSpeed = 10; // How often to move the window (ms)


    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton color1Button;
    private JButton color2Button;
    private JButton loadConfigurationButton;
    private JButton saveConfigurationButton;
    private JButton doneButton;
    public JPanel frame;
    private JButton testConfigurationButton;

    public config() {
        color1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread chooseFrameShower = new Thread(new GUI.chooseFrame());
                chooseFrameShower.start();
            }
        }
        );
    }

}


