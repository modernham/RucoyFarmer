package botMain;

import GUI.ScriptSelection;

import javax.swing.*;


public class ChromoMain {
    public static ScriptSelection Gui = new ScriptSelection();
    public static JFrame frame = new JFrame("ChromoScape");
    public static String windowName = "NoxPlayer";
    static public WindowHandler window = new WindowHandler(windowName);

    public static void main(String[] args) {
        //Create GUI
        frame = new JFrame("RucoyFarmerStandalone");
        frame.setSize(500, 600);
        frame.setContentPane(Gui.selectionPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Create Varribles and Tools
        State.createTools();
    }
}
