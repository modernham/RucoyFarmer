package GUI;

import botMain.State;
import scripts.Slayer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlayerGUI {
    private JButton startButton;
    private JButton stopButton;
    public JPanel herbPanel;
    private JButton configureButton;
    public static boolean Running;

    public static JFrame configFrame = new JFrame("Configuration");
    public static config configGUI = new config();

    public SlayerGUI() {
        configureButton.setEnabled(true);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Running = true;
                Thread Slayer = new Thread(new Slayer());
                Slayer.start();
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Running = false;
            }
        });
        configureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configGUI = new config();
                configFrame.setSize(500, 600);
                configFrame.setContentPane(configGUI.frame);
                configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                configFrame.pack();
                configFrame.setVisible(true);
            }
        });
    }
}
