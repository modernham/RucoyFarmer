package GUI;

import botMain.State;
import scripts.Slayer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlayerGUI {
    public JButton startButton;
    public JButton stopButton;
    public JPanel herbPanel;
    private JButton configureButton;
    public static boolean Running;

    public static JFrame configFrame = new JFrame("Configuration");
    public static config configGUI = new config();

    public SlayerGUI() {
        startButton.setEnabled(false);
        stopButton.setEnabled(false);
        configureButton.setEnabled(true);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Running = true;
                Thread Slayer = new Thread(new Slayer());
                Slayer.start();
                State.herbGUI.stopButton.setEnabled(true);
                State.herbGUI.startButton.setEnabled(false);
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Running = false;
                State.herbGUI.startButton.setEnabled(true);
                State.herbGUI.stopButton.setEnabled(false);
            }
        });
        configureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configGUI = new config();
                configFrame.setSize(500, 600);
                configFrame.setContentPane(configGUI.frame);
                configFrame.pack();
                configFrame.setVisible(true);
            }
        });
    }
}
