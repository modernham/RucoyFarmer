import java.awt.*;


public class ColorFinder {
    public Color pix1 = new Color(0,0,0,0);
    Robot robot;

    public ColorFinder(){
        try {
        robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }



    public float[] getPixelColor(Point point){
        Color color = robot.getPixelColor(point.x, point.y);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float hue = hsb[0];
        float saturation = hsb[1];
        float brightness = hsb[2];

        return hsb;
    }

    public float[] getRelPixelColor(Point point){
        point.x = point.x - State.window.getPos().x;
        point.y = point.y - State.window.getPos().y;
        pix1 = robot.getPixelColor(point.x, point.y);
       float[] hsb = Color.RGBtoHSB(pix1.getRed(), pix1.getGreen(), pix1.getBlue(), null);
        float hue = hsb[0];
       float saturation = hsb[1];
        float brightness = hsb[2];

        return hsb;
    }

    public void printColor(Point point){}
}
