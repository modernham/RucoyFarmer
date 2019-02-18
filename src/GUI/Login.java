package GUI;

import botMain.ChromoMain;
import botMain.DebugThread;
import botMain.State;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JButton startButton;
    public JPanel selectionPanel;
    private JButton debugWindowButton;
    private JTextField textField1;
    private JTextField textField2;


    public Login() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChromoMain.frame.setVisible(false);
                State.initScript();
            }
        });
        debugWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Thread debugger = new Thread(new DebugThread());
                debugger.start();

            }
        });
    }
}
