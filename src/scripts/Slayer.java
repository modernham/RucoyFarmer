package scripts;
import GUI.SlayerGUI;
import Tools.getState;
import botMain.State;

import javax.swing.*;
import java.awt.*;


public  class Slayer implements Runnable {
    public static Rectangle MAINSCREEN = new Rectangle(110,82,900,525);

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
