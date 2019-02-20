package GUI;

import Tools.configSaver;
import Tools.savedColors;
import botMain.State;
import Tools.configSaver;

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
    private JButton selectGroundColorButton;
    public JTextArea textArea3;
    configSaver save = new configSaver();
    savedColors colors = new savedColors();

    public config() {
        color1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.window.getPos();
                Thread chooseFrameShower = new Thread(new GUI.chooseFrame());
                chooseFrameShower.start();
            }
        }
        );
        color2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.window.getPos();
                Thread chooseFrameShower2 = new Thread(new GUI.chooseFrame2());
                chooseFrameShower2.start();
            }
        });
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.window.getPos();
                if ((chooseFrame.colorChosen == true) &&(chooseFrame2.colorChosen ==true)&&(chooseGround.colorChosen ==true)) {
                    State.herbGUI.startButton.setEnabled(true);
                }
                State.herbGUI.configFrame.dispose();
            }
        });
        testConfigurationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.window.getPos();
                   if ((chooseFrame.colorChosen == true) &&(chooseFrame2.colorChosen ==true)&&(chooseGround.colorChosen ==true)) {
                    Thread test = new Thread(new GUI.testFrame());
                    test.start();
                }else
                    JOptionPane.showMessageDialog(null, "You must select all Colors first.");
            }
        });
        selectGroundColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.window.getPos();
                Thread chooseGroundShower = new Thread(new GUI.chooseGround());
                chooseGroundShower.start();

            }
        });
        saveConfigurationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((chooseFrame.colorChosen == true) &&(chooseFrame2.colorChosen ==true)&&(chooseGround.colorChosen ==true)) {
                    colors.savedColors(State.hsv1, State.hsv2, State.ground);
                    save.saveConfig(colors);
                }               else
                    JOptionPane.showMessageDialog(null, "You must select all Colors first.");
        }
        });
        loadConfigurationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            save.loadConfigChoose();
            savedColors temp;
            temp = save.loadedColors;
            State.hsv1 = save.loadedColors.getCOLOR1();
            State.hsv2 = save.loadedColors.getCOLOR2();
            State.ground= save.loadedColors.getCOLOR3();
                System.out.println("Hue:" + State.hsv1[0] + "\n Saturation: " + State.hsv1[1] + "\n Brightness: " + State.hsv1[2]);
            textArea2.setText("Hue:" + State.hsv1[0] + "\n Saturation: " + State.hsv1[1] + "\n Brightness: " + State.hsv1[2]);
            textArea1.setText("Hue:" + State.hsv2[0] + "\n Saturation: " + State.hsv2[1] + "\n Brightness: " + State.hsv2[2]);
                textArea3.setText("Hue:" + State.ground[0] + "\n Saturation: " + State.ground[1] + "\n Brightness: " + State.ground[2]);
            }
        });
    }

}


