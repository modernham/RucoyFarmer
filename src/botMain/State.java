package botMain;

import GUI.SlayerGUI;
import GUI.ScriptSelection;
import scripts.Slayer;

import javax.swing.*;

public class State {

    //Initial Script Selection Window
    public static String windowName = "NoxPlayer";
    static public WindowHandler window = new WindowHandler(windowName);
    public static ScriptSelection Gui = new ScriptSelection();

    //Tools Creation
    public static ColorFinder colorfinder = new ColorFinder();
    public static Mouse mouse = new Mouse();
    public static int scriptIndex;
    public static int monsterIndex;

    //Create Windows
    public static JFrame herbFrame = new JFrame("RatSlayer");
    public static SlayerGUI herbGUI = new SlayerGUI();


    public static void createTools()

    {
       windowName = "NoxPlayer";
       window = new WindowHandler(windowName);
       colorfinder = new ColorFinder();
       Gui = new ScriptSelection();
       mouse = new Mouse();
    }

    public static void initScript(){
        switch(scriptIndex){
            case 0:
                herbGUI = new SlayerGUI();
                herbFrame.setSize(500, 600);
                herbFrame.setContentPane(herbGUI.herbPanel);
                herbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                herbFrame.pack();
                herbFrame.setVisible(true);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}
