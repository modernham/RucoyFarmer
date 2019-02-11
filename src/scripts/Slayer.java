package scripts;
import botMain.State;

import javax.swing.*;
import java.awt.*;

public abstract class Slayer {
    public static Rectangle MAINSCREEN = new Rectangle(110,82,900,525);

    private static  float[] color1 = new float[3], color2 = new float[3];


    public static void start(){
        Point point = new Point();
        System.out.println("Starting Slayer");
        switch (State.monsterIndex){
            case 0:
            color1 [0] = 0.13586956f;
            color1 [1] = 1.0f;
            color1 [2] = 0.72156864f;
            color2 [0] = 0.036231887f;
            color2 [1] = 0.57261413f;
            color2 [2] = 0.94509804f;
            point =  State.colorfinder.findExactColors(MAINSCREEN, color1 , color2 , 50);
        }

        if (inCombat() == false)
            point = null;
            point =  State.colorfinder.findExactColors(MAINSCREEN, color1 , color2 , 50);
            if (point != null){
                State.mouse.moveRelMouse(new Point(point.x, point.y));
                State.mouse.mouseClick();
            }
    }

    private static boolean inCombat(){
        //Code to search for selection window
        return true;
    }
}
