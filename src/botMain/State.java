package botMain;

import GUI.SlayerGUI;
import GUI.Login;

import javax.swing.*;

public class State {

    //Initial Script Selection Window
    public static String windowName = "NoxPlayer";
    static public WindowHandler window = new WindowHandler(windowName);
    public static Login Gui = new Login();

    //Tools Creation
    public static ColorFinder colorfinder = new ColorFinder();
    public static Mouse mouse = new Mouse();
    public static int monsterIndex;

    //Create Windows
    public static JFrame herbFrame = new JFrame("RucoySlayer");
    public static SlayerGUI herbGUI = new SlayerGUI();

    //Create ColorsVars
    public static float[] hsv1 = new float[2];
    public static float[] hsv2 = new float[2];

    public static void createTools()

    {
       windowName = "NoxPlayer";
       window = new WindowHandler(windowName);
       colorfinder = new ColorFinder();
       Gui = new Login();
       mouse = new Mouse();
    }

    public static void initScript(){
                herbGUI = new SlayerGUI();
                herbFrame.setSize(500, 600);
                herbFrame.setContentPane(herbGUI.herbPanel);
                herbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                herbFrame.setLocationRelativeTo(null);
                herbFrame.pack();
                herbFrame.setVisible(true);
    }
}
