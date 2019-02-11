package GUI;

import botMain.State;
import scripts.Slayer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlayerGUI {
    private JList list1;
    private JButton startButton;
    private JButton stopButton;
    public JPanel herbPanel;

    public SlayerGUI() {
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                State.monsterIndex =   list1.getAnchorSelectionIndex();
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Slayer.start();
            }
        });
    }
}
