package botMain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ColorFinder {
    public Color pix1 = new Color(0, 0, 0, 0);
    Robot robot;

    public ColorFinder() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public float[] getPixelColor(Point point) {
        Color color = robot.getPixelColor(point.x, point.y);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float hue = hsb[0];
        float saturation = hsb[1];
        float brightness = hsb[2];

        return hsb;
    }

    public float[] getRelPixelColor(Point point) {
        point.x = point.x + State.window.getPos().x;
        point.y = point.y + State.window.getPos().y;
        pix1 = robot.getPixelColor(point.x, point.y);
        float[] hsb = Color.RGBtoHSB(pix1.getRed(), pix1.getGreen(), pix1.getBlue(), null);
        float hue = hsb[0];
        float saturation = hsb[1];
        float brightness = hsb[2];

        return hsb;
    }

    public Point findColors(Rectangle area, float color1[], float color2[], float tol1[], float tol2[], int distance) {
        int x1 = area.x + State.window.getPos().x;
        int y1 = area.y + State.window.getPos().y;
        int x2 = area.width;
        int y2 = area.height;
        float[] hsv = new float[2];
        int tempColor, red, blue, green;
        System.out.println(x1 + ", " + y1);
        BufferedImage image = robot.createScreenCapture(new Rectangle(x1, y1, x2, y2));

        try {
            ImageIO.write(image, "jpg", new File("C://Users/billy/Documents/image.jpg"));
        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                tempColor = image.getRGB(i, j);
                red = (tempColor >> 16) & 0xFF;
                green = (tempColor >> 8) & 0xFF;
                blue = tempColor & 0xFF;
                hsv = Color.RGBtoHSB(red, green, blue, null);
                if ((hsv[0] > color1[0] - tol1[0]) && (hsv[0] < color1[0] + tol1[0]) &&
                        (hsv[1] > color1[1] - tol1[1]) && (hsv[1] < color1[1] + tol1[0]) &&
                        (hsv[2] > color1[2] - tol1[2]) && (hsv[2] < color1[2] + tol1[2])) {
                    System.out.println("Colros found:" + (hsv[0]) + ", " + (hsv[1]));
                    State.mouse.moveRelMouse(new Point(i + area.x, j + area.y));
                }

            }
        }
        return new Point(0, 0);
    }

    public Point findExactColors(Rectangle area, float color1[], float color2[], int distance){
        int x1 = area.x + State.window.getPos().x;
        int y1 = area.y + State.window.getPos().y;
        int x2 = area.width;
        int y2 = area.height;
        float[] hsv = new float[2];
        int tempColor, red, blue, green;
        List<Point> list1= new ArrayList<Point>();
        List<Point> list2= new ArrayList<Point>();
        List<Point> monsters= new ArrayList<Point>();
        BufferedImage image = robot.createScreenCapture(new Rectangle(x1, y1, x2, y2));


        try{
            ImageIO.write(image, "jpg", new File("C://Users/billy/Documents/image.jpg"));
        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
        //Find color1
        for (int i = 0; i < image.getWidth(); i++){
            for (int j = 0; j < image.getHeight(); j++){
                tempColor = image.getRGB(i,j);
                red = (tempColor >> 16) & 0xFF;
                green = (tempColor >> 8) & 0xFF;
                blue = tempColor& 0xFF;
                hsv = Color.RGBtoHSB(red, green, blue, null);
                if((hsv[0] == color1[0]))
                list1.add(new Point(i+ area.x, j+ area.x));
                }

            }
        System.out.println("Found Color1:" + list1.size());
            //Find color2
        for (int i = 0; i < image.getWidth(); i++){
            for (int j = 0; j < image.getHeight(); j++){
                tempColor = image.getRGB(i,j);
                red = (tempColor >> 16) & 0xFF;
                green = (tempColor >> 8) & 0xFF;
                blue = tempColor& 0xFF;
                hsv = Color.RGBtoHSB(red, green, blue, null);
                if((hsv[0] == color2[0]))
                list2.add(new Point(i + area.x, j+ area.y));
            }

        }
        System.out.println("Found Color2:" + list2.size());
        //See if any of the colors are close enough for the distance
        if (list1.size() > list2.size()){
            for (int i=0; i< list2.size(); i++){
                    double ac = Math.abs(list2.get(i).y - list1.get(i).y);
                    double cb = Math.abs(list2.get(i).x - list1.get(i).x);
                    double dist = Math.hypot(ac, cb);
                    if (dist < distance){
                        int midX = (list2.get(i).x + list1.get(i).x)/2;
                        int midY = (list2.get(i).y + list1.get(i).y)/2;
                        monsters.add(new Point(midX, midY));
                    }
                }
                }
        else {
            for (int i=0; i< list1.size(); i++){
                double ac = Math.abs(list1.get(i).y - list2.get(i).y);
                double cb = Math.abs(list1.get(i).x - list2.get(i).x);
                double dist = Math.hypot(ac, cb);
                if (dist < distance){
                    int midX = (list1.get(i).x + list2.get(i).x)/2;
                    int midY = (list1.get(i).y + list2.get(i).y)/2;
                    monsters.add(new Point(midX, midY));
                }
            }
        }
        //Get closest Point to us
        int bestPoint = 1000;
        double bestDist = 20000;
        for (int i = 0; i < monsters.size(); i++) {
            double ac = Math.abs(monsters.get(i).y - 334);
            double cb = Math.abs(list2.get(i).x - 552);
            double dist = Math.hypot(ac, cb);
            if (dist < bestDist){
                bestDist = dist;
                bestPoint = i;
                System.out.println("New Best Dist:" + bestDist);
            }
        }
        System.out.println("Found Monsters:" + monsters.size());
        if (monsters.size() == 0)
            return null;
        else return new Point(new Point(monsters.get(bestPoint).x, monsters.get(bestPoint).y));
    }



}
