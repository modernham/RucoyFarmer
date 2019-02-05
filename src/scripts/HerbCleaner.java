package scripts;
import botMain.State;

import javax.swing.*;
import java.awt.*;

public abstract class HerbCleaner {
    public static Rectangle MAINSCREEN = new Rectangle(27,35,520,330);

    private static  float[] myAxe= new float[3], myCape = new float[3];
    private static  float[] myAxeTol = new float[3], myCapeTol = new float[3];


    public static void start(){
        Point point = new Point();
        System.out.println("Starting Herb Cleaner");
        myAxe[0] = 0.13f;
        myAxe[1] = 0.88f;
        myAxe[2] = 0.42f;
        myCape[0] = 0.06f;
        myCape[1] = 0.91f;
        myCape[2] = 0.39f;
        myAxeTol[0] = 0.01f;
        myAxeTol[1] = 0.01f;
        myAxeTol[2] = 0.01f;
        myCapeTol[0] = 0.01f;
        myCapeTol[1] = 0.01f;
        myCapeTol[2] = 0.01f;
        point =  State.colorfinder.findColors(MAINSCREEN, myAxe, myCape, myAxeTol, myCapeTol, 50);
    }
}
