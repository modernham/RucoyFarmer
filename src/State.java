import javax.swing.*;

public class State {

    //Initial Script Selection Window
    public static String windowName = "Old School RuneScape";
    static public WindowHandler window = new WindowHandler(windowName);
    public static ScriptSelection Gui = new ScriptSelection();

    //Tools Creation
    public static ColorFinder colorfinder = new ColorFinder();
    public static Mouse mouse = new Mouse();
    static int scriptIndex;

    //Create Windows
    public static JFrame herbFrame = new JFrame("Herb Cleaner");
    public static HerbCleaner herbGUI = new HerbCleaner();


    public static void createTools()

    {
       windowName = "Old School RuneScape";
       window = new WindowHandler(windowName);
       colorfinder = new ColorFinder();
       Gui = new ScriptSelection();
       mouse = new Mouse();
    }

    public static void initScript(){
        switch(scriptIndex){
            case 0:
                herbGUI = new HerbCleaner();
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
