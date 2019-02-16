package GUI;

import botMain.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class config{


    static int windowSize = 11; // odd should look nice. Set to 1 for an invisible window
    static int clickDelay = 0; // Delay in ms between closing window and forwarding click. 0 seems to work fine.
    static int trackingSpeed = 10; // How often to move the window (ms)


    public  JTextArea textArea1;
    public  JTextArea textArea2;
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
        color2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread chooseFrameShower2 = new Thread(new GUI.chooseFrame2());
                chooseFrameShower2.start();
            }
        });
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((chooseFrame.colorChosen == true) &&(chooseFrame2.colorChosen ==true)) {
                    State.herbGUI.startButton.setEnabled(true);
                }
                State.herbGUI.configFrame.dispose();
            }
        });
        testConfigurationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   if ((chooseFrame.colorChosen == true) &&(chooseFrame2.colorChosen ==true)) {
                    Thread test = new Thread(new GUI.testFrame());
                    test.start();
                }else
                    JOptionPane.showMessageDialog(null, "You must select 2 Colors first.");
            }
        });
    }

}


