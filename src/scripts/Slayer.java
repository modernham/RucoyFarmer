package scripts;
import GUI.SlayerGUI;
import Tools.getState;
import botMain.State;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public  class Slayer implements Runnable {
    public static Rectangle MAINSCREEN = new Rectangle(110,82,900,525);
    public static Rectangle north = new Rectangle(170, 90, 828, 82);
    public static Rectangle south = new Rectangle(160, 500,  807, 92);
    public static Rectangle east = new Rectangle(126, 202,  139, 328);
    public static Rectangle west = new Rectangle(800, 179, 200, 395);
    List<Point> northPoints = new ArrayList<Point>();
    List<Point> southPoints = new ArrayList<Point>();
    List<Point> eastPoints = new ArrayList<Point>();
    List<Point> westPoints = new ArrayList<Point>();

    private static  float[] color1 = new float[3], color2 = new float[3];


    public  void run(){


        //Assign monster Colors
        Point point = new Point();
        System.out.println("Starting Slayer");

            color1  = State.hsv1;
            color2 = State.hsv2;

        //Start StateGetter
        Thread stateGetter = new Thread(new getState());
        stateGetter.start();

        //MainLoop
        while(SlayerGUI.Running == true) {

            if (getState.dead == true){
                State.herbGUI.statusLabel.setText("Status: Dead");
                SlayerGUI.Running = false;
                State.herbGUI.startButton.setEnabled(true);
                State.herbGUI.stopButton.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Bot terminated Reason: Died.");
            }


            if (getState.interfaceOpen)
                closeInterface();

            if ((getState.health *100< 50f)&&(State.herbGUI.useHealthPotionCheckBox.isSelected()))
                useHealthPot();

            if ((getState.mana *100< 50f)&&(State.herbGUI.useManaPotionCheckBox.isSelected()))
                useManaPot();


            if (getState.status == "notattacking") {
                State.herbGUI.statusLabel.setText("Status: Looking for target");
                point = null;
                point = State.colorfinder.findExactColors(MAINSCREEN, color1, color2, 50);
                if (point == null)
                    navigate();
                if (point != null) {
                    State.mouse.moveRelMouse(new Point(point.x, point.y));
                    State.mouse.mouseClick();
                }
            }
            if (getState.status == "attacking") {
                State.herbGUI.statusLabel.setText("Status: Attacking");
            }
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
       }
    }

    public void closeInterface(){
        System.out.println("Closing Interface");
        State.mouse.moveRelMouse(new Point(1064,63));
        State.mouse.mouseClick();
    }
    public void navigate(){
       northPoints =  State.colorfinder.findColor(north, State.ground);
        southPoints =  State.colorfinder.findColor(south, State.ground);
        eastPoints =  State.colorfinder.findColor(east, State.ground);
        westPoints =  State.colorfinder.findColor(west, State.ground);
        Random rand = new Random();
        int value = rand.nextInt(4);

        switch (value){
            case 0:
                if (northPoints.size() > 1){
                    int value1 = rand.nextInt(northPoints.size());
                    State.mouse.moveRelMouse(new Point(northPoints.get(value1).x, northPoints.get(value1).y));
                    State.mouse.mouseClick();
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }

                }
                break;
            case 1:
                if (southPoints.size() > 1){
                    int value2 = rand.nextInt(southPoints.size());
                    State.mouse.moveRelMouse(new Point(southPoints.get(value2).x, southPoints.get(value2).y));
                    State.mouse.mouseClick();
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }

                }
                break;
            case 2:
                if (eastPoints.size() > 1){
                    int value3 = rand.nextInt(eastPoints.size());
                    State.mouse.moveRelMouse(new Point(eastPoints.get(value3).x, eastPoints.get(value3).y));
                    State.mouse.mouseClick();
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }

                }
                break;
            case 3:
                if (westPoints.size() > 1){
                    int value4 = rand.nextInt(westPoints.size());
                    State.mouse.moveRelMouse(new Point(westPoints.get(value4).x, westPoints.get(value4).y));
                    State.mouse.mouseClick();
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }

                }
                break;
            default:
                break;
        }


    }

    public void useHealthPot(){
        if (getState.hasHealthPotion) {
            State.mouse.moveRelMouse(new Point(53, 619));
            State.mouse.mouseClick();
            State.herbGUI.statusLabel.setText("Status: Using Health Potion");
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        else {
            SlayerGUI.Running = false;
            State.herbGUI.startButton.setEnabled(true);
            State.herbGUI.stopButton.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Bot terminated Reason: No More Health Potions.");
        }
    }

    public void useManaPot(){
        if (getState.hasManaPotion) {
            State.mouse.moveRelMouse(new Point(53, 513));
            State.mouse.mouseClick();
            State.herbGUI.statusLabel.setText("Status: Using Mana Potion");
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        else{
            SlayerGUI.Running = false;
            State.herbGUI.startButton.setEnabled(true);
            State.herbGUI.stopButton.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Bot terminated Reason: No More Mana Potions.");

        }

    }

}
