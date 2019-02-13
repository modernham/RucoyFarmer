package Tools;

import GUI.Debug;
import botMain.DebugThread;
import botMain.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class getState implements Runnable {

    public static Rectangle MAINSCREEN1 = new Rectangle(110,82,900,525);

    private static  float[] selectionColor = new float[3];

    public enum STATE{
        ATTACKING, HEALING, IDLE, USING_ABILITY, SEARCHING;
    }
    public static  STATE CURRENT_STATE;
    public boolean selected;

    public void run(){
        List<Point> list1= new ArrayList<Point>();
        selectionColor[0] = 0.0f;
        selectionColor[1] = 0.7584541f;
        selectionColor[2] = 0.8117647f;


       while (true) {
           list1 = State.colorfinder.findColor(MAINSCREEN1, selectionColor);
           for (int i = 0; i < list1.size(); i++) {
               for (int j = 0; j < list1.size(); j++) {
                   if ((list1.get(j).getX() == list1.get(i).getX() + 57) && (list1.get(j).getY() == list1.get(i).getY())) {
                       System.out.println("Found Point 2");
                       for (int b = 0; b < list1.size(); b++) {
                           if ((list1.get(j).getY() == list1.get(i).getY() + 57) && (list1.get(j).getX() == list1.get(i).getX())) {
                               System.out.println("Found Point 3");
                               for (int c = 0; c < list1.size(); c++) {
                                   if ((list1.get(j).getY() == list1.get(i).getY() + 57) && (list1.get(j).getX() == list1.get(i).getX() + 57)) {
                                       System.out.println("Found Point 4");
                                       selected = true;
                                       DebugThread.debugGUI.textArea2.setText("True");
                                       CURRENT_STATE = CURRENT_STATE.ATTACKING;
                                   } else {
                                       selected = false;
                                       DebugThread.debugGUI.textArea2.setText("False");
                                       CURRENT_STATE = CURRENT_STATE.IDLE;
                                   }
                               }
                           }
                       }
                   }
               }
           }
           System.out.println("Finished Loop State Getter");
       }



    }



}
