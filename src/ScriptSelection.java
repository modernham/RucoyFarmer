import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScriptSelection {
    private JButton startButton;
    private JList list1;
    public JPanel selectionPanel;

    public ScriptSelection() {
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                State.scriptIndex =   list1.getAnchorSelectionIndex();

            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChromoMain.frame.setVisible(false);
                State.initScript();
            }
        });
    }
}
