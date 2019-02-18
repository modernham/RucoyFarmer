package Tools;

import GUI.Debug;
import GUI.SlayerGUI;
import botMain.DebugThread;
import botMain.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class getState implements Runnable {

    public static Rectangle MAINSCREEN1 = new Rectangle(110, 82, 900, 525);
    public static Rectangle healthBar = new Rectangle(5, 61, 336, 1);
    public static Rectangle manaBar = new Rectangle(5, 70, 336, 1);
    public static Rectangle manaBae = new Rectangle(110, 82, 900, 525);
    public static Rectangle deathBar = new Rectangle(349, 347, 403, 108);
    public static String status;
    public static float health;
    public static float mana;

    private static float[] selectionColor = new float[3];
    private static float[] interfaceColor = new float[3];
    private static float[] healthBarColor = new float[3];
    private static float[] manaBarColor = new float[3];
    private static float[] healthPotionSpotColor = new float[3];
    private static float[] manaPotionSpotColor = new float[3];
    private static float[] deathBarColor = new float[3];
    List<Point> healthPoints = new ArrayList<Point>();
    List<Point> manaPoints = new ArrayList<Point>();
    List<Point> deathBarPoints = new ArrayList<Point>();
    private float healthPotionColor;

    public boolean selected;
    public static boolean interfaceOpen;
    public static boolean hasHealthPotion = true;
    public static boolean hasManaPotion = true;
    public static boolean dead = false;

    public void run() {
        List<Point> list1 = new ArrayList<Point>();
        selectionColor[0] = 0.0f;
        selectionColor[1] = 0.7584541f;
        selectionColor[2] = 0.8117647f;
        interfaceColor[0] = 0.0f;
        interfaceColor[1] = 0.0f;
        interfaceColor[2] = 0.7882353f;
        healthBarColor[0] = 0.0f;
        healthBarColor[1] = 0.7584541f;
        healthBarColor[2] = 0.8117647f;
        manaBarColor[0] = 0.5555556f;
        manaBarColor[1] = 0.7619048f;
        manaBarColor[2] = 0.9882353f;
        deathBarColor[0] = 0.0f;
        deathBarColor[1] = 0.7584541f;
        deathBarColor[2] = 0.8117647f;
        List<Point> templist = new ArrayList<Point>();

        while (SlayerGUI.Running == true) {
            list1 = State.colorfinder.findColor(MAINSCREEN1, selectionColor);


            //Detect Interface
            if (State.colorfinder.getRelPixelColor(new Point(1065, 63))[2] == interfaceColor[2]){
                interfaceOpen = true;
            }
            else {
                interfaceOpen = false;
            }

            //Get Health and Mana Information
            healthPoints = State.colorfinder.findColor(healthBar, healthBarColor);
            health =  (float)healthPoints.size() / 336f;
            State.herbGUI.healthLabel.setText("Health: " + (int)(health * 100) + "%");
            manaPoints = State.colorfinder.findColor(manaBar, manaBarColor);
            mana =  (float)manaPoints.size() / 336f;
            State.herbGUI.manaLabel.setText("Mana: " + (int)(mana * 100) + "%");
            healthPotionSpotColor =  State.colorfinder.getRelPixelColor(new Point(53,619));
            manaPotionSpotColor = State.colorfinder.getRelPixelColor(new Point(53,513));
            if (healthPotionSpotColor[2] == 0.5686275f) {
                hasHealthPotion = false;
                State.herbGUI.hasHealthPot.setText("Has Health Potion: False");
            }
            else {
                hasHealthPotion = true;
                State.herbGUI.hasHealthPot.setText("Has Health Potion: True");
            }
            if (manaPotionSpotColor[2] == 0.5686275f) {
                hasManaPotion = false;
                State.herbGUI.hasManaPot.setText("Has Mana Potion: False");
            }
            else {
                hasManaPotion = true;
                State.herbGUI.hasManaPot.setText("Has Mana Potion: True");
            }


            //DetectDeath
            deathBarPoints =  State.colorfinder.findColor(deathBar, deathBarColor);
            if (deathBarPoints.size() > 20000) {
                dead = true;
            }
            else dead = false;



            //Detect Selected Old
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list1.size(); j++) {
                    if ((list1.get(j).x == list1.get(i).x + 57) && (list1.get(j).y == list1.get(i).y)) {
                        for (int k = 0; k < list1.size(); k++) {
                            if ((list1.get(k).x == list1.get(i).x) && (list1.get(k).y == list1.get(i).y + 57)) {
                                for (int l = 0; l < list1.size(); l++) {
                                    if ((list1.get(l).x == list1.get(i).x + 57) && (list1.get(l).y == list1.get(i).y + 57)) {
                                        selected = true;
                                        status = "attacking";
                                        DebugThread.debugGUI.textArea2.setText("True");

                                        run();
                                    }
                                }
                            }

                        }
                    }

                }
            }
//Detect Selected New
//            for (int i = 0; i < list1.size(); i++) {
//                for (int j = 0; j < list1.size(); j++) {
//                    if ((list1.get(j).x == list1.get(i).x + 57) && (list1.get(j).y == list1.get(i).y)) {
//                                        selected = true;
//                                        status = "attacking";
//                                        DebugThread.debugGUI.textArea2.setText("True");
//
//                                        run();
//
//
//                            }
//
//                        }
//                    }

                selected = false;
            status = "notattacking";
                DebugThread.debugGUI.textArea2.setText("False");





        }


    }
}
