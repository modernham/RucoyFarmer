package Tools;

import GUI.Debug;
import botMain.DebugThread;
import botMain.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class getState implements Runnable {

    public static Rectangle MAINSCREEN1 = new Rectangle(110, 82, 900, 525);
    public static String status;

    private static float[] selectionColor = new float[3];

    public boolean selected;

    public void run() {
        List<Point> list1 = new ArrayList<Point>();
        selectionColor[0] = 0.0f;
        selectionColor[1] = 0.7584541f;
        selectionColor[2] = 0.8117647f;
        List<Point> templist = new ArrayList<Point>();

        while (true) {
            list1 = State.colorfinder.findColor(MAINSCREEN1, selectionColor);
            System.out.println("Getting State");
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
                selected = false;
            status = "notattacking";
                DebugThread.debugGUI.textArea2.setText("False");





        }


    }
}
