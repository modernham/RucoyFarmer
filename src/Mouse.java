

import java.awt.*;
import java.awt.event.InputEvent;

public class Mouse {
    Robot robot;


    public void Mouse(){
        try {
        robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public Point getPosition(){
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        return  b;
    }

    public void moveMouse(Point point){
        try {
            Robot moveRobot = new Robot();
            moveRobot.mouseMove(point.x, point.y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void mouseClick(){
        try {
            Robot clickRobot = new Robot();
        clickRobot.mousePress(InputEvent.BUTTON1_MASK);
        clickRobot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void moveRelMouse(Point point){
        try {
            point.x = point.x - State.window.getPos().x;
            point.y = point.y - State.window.getPos().y;
            Robot moveRobot = new Robot();
            moveRobot.mouseMove(point.x, point.y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public Point getRelPosition(){
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        b.x = b.x - State.window.getPos().x;
        b.y = b.y - State.window.getPos().y;
        return b;
    }

    public void moveRelPosition(Point pos){

        try {
            Robot moveRobot = new Robot();
            pos.x = pos.x - State.window.getPos().x;
            pos.y = pos.y - State.window.getPos().y;
            moveRobot.mouseMove(pos.x, pos.y);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

}
