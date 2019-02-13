package scripts;
import GUI.SlayerGUI;
import Tools.getState;
import botMain.State;

import javax.swing.*;
import java.awt.*;

import static Tools.getState.STATE.ATTACKING;
import static Tools.getState.STATE.IDLE;

public  class Slayer implements Runnable {
    public static Rectangle MAINSCREEN = new Rectangle(110,82,900,525);

    private static  float[] color1 = new float[3], color2 = new float[3];


    public  void run(){
        //Assign monster Colors
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
        }

        //Start StateGetter
        Thread stateGetter = new Thread(new getState());
        stateGetter.start();

        //MainLoop
        while(SlayerGUI.Running = true) {
            if (getState.CURRENT_STATE == IDLE) {
                System.out.println("IDLE");
                point = null;
                point = State.colorfinder.findExactColors(MAINSCREEN, color1, color2, 50);
                if (point != null) {
                    State.mouse.moveRelMouse(new Point(point.x, point.y));
                    State.mouse.mouseClick();
                }
            }
            if (getState.CURRENT_STATE == ATTACKING) {
                System.out.println("ATTACKING");
            }
           // System.out.println("Finished Loop Slayer");
       }
    }

}
