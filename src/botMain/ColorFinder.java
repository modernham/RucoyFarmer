package botMain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


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

    public Point findColors(Rectangle area, float color1[], float color2[], float tol1[], float tol2[], int distance){
        int x1 = area.x + State.window.getPos().x;
        int y1 = area.y + State.window.getPos().y;
        int x2 = area.width;
        int y2 = area.height;
        float[] hsv = new float[2];
        int tempColor, red, blue, green;
        System.out.println(x1 +", " + y1);
        BufferedImage image = robot.createScreenCapture(new Rectangle(x1, y1, x2, y2));

try{
        ImageIO.write(image, "jpg", new File("C://Users/billy/Documents/image.jpg"));

    } catch (IOException e) {
        System.out.println("Exception occured :" + e.getMessage());
    }


        for (int i = 0; i < image.getWidth(); i++){
            for (int j = 0; j < image.getHeight(); j++){
                tempColor = image.getRGB(i,j);
                red = (tempColor >> 16) & 0x000000FF;
                green = (tempColor >>8 ) & 0x000000FF;
                blue = (tempColor) & 0x000000FF;
                hsv = Color.RGBtoHSB(red, green, blue, null);
                if((hsv[0] > hsv[0] - tol1[0]) && (hsv[0] < hsv[0] + tol1[0]) &&
                (hsv[1] > hsv[1] - tol1[1]) && (hsv[1] < hsv[1] + tol1[0])&&
                (hsv[2] > hsv[2] - tol1[2]) && (hsv[2] < hsv[2] + tol1[2])){
                    System.out.println("Point found at:" + (i + area.x) + ", " + (j + area.y));
                }

            }
        }
        return new Point(0,0);
    }

    public void printColor(Point point){}
}
