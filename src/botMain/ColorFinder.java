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
    public BufferedImage image;

    public ColorFinder() {
        State.window.getPos();
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        image = robot.createScreenCapture(new Rectangle(State.window.returnPos().x, State.window.returnPos().y, 1280, 720));

    }

    public BufferedImage getScreen() {
        image = robot.createScreenCapture(new Rectangle(State.window.returnPos().x, State.window.returnPos().y, 1280, 720));
        return image;
    }

    public float[] getPixelColor(Point point) {
        Color color = robot.getPixelColor(point.x, point.y);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float hue = hsb[0];
        float saturation = hsb[1];
        float brightness = hsb[2];
        return hsb;
    }

    public float[] getPixelColorTest(Point point) {
        image = robot.createScreenCapture(new Rectangle(State.window.returnPos().x, State.window.returnPos().y, 1300, 900));
        int tempColor, red, blue, green;
        float[] hsv = new float[2];
        tempColor = image.getRGB(point.x, point.y);
        red = (tempColor >> 16) & 0xFF;
        green = (tempColor >> 8) & 0xFF;
        blue = tempColor & 0xFF;
        hsv = Color.RGBtoHSB(red, green, blue, null);
        return hsv;
    }

    public float[] getRelPixelColor(Point point) {
        Color color = robot.getPixelColor(point.x + State.window.returnPos().x, point.y + State.window.returnPos().y);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return hsb;
    }

    public List findColor(Rectangle area, float color1[]) {
        int x1 = area.x + State.window.returnPos().x;
        int y1 = area.y + State.window.returnPos().y;
        int x2 = area.width;
        int y2 = area.height;
        float[] hsv = new float[2];
        int tempColor, red, blue, green;
        List<Point> list1 = new ArrayList<Point>();
        try {
        image = robot.createScreenCapture(new Rectangle(x1, y1, x2, y2));
        } catch (StackOverflowError e){
        }
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                try {
                tempColor = image.getRGB(i, j);
                red = (tempColor >> 16) & 0xFF;
                green = (tempColor >> 8) & 0xFF;
                blue = tempColor & 0xFF;
                hsv = Color.RGBtoHSB(red, green, blue, null);
                if ((hsv[0] == color1[0]) && (hsv[1] == color1[1]) && (hsv[2] == color1[2])) {
                    list1.add(new Point(i + area.x, j + area.y));
                }
                } catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            }

        }
        return list1;
    }

    public Point findExactColors(Rectangle area, float color1[], float color2[], int distance) {
        int x1 = area.x + State.window.returnPos().x;
        int y1 = area.y + State.window.returnPos().y;
        int x2 = area.width;
        int y2 = area.height;
        float[] hsv = new float[2];
        int tempColor, red, blue, green;
        List<Point> list1 = new ArrayList<Point>();
        List<Point> list2 = new ArrayList<Point>();
        List<Point> monsters = new ArrayList<Point>();
        try{
        image = robot.createScreenCapture(new Rectangle(x1, y1, x2, y2));
        } catch (StackOverflowError e){
        }


        //Find color1
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                try {
                    tempColor = image.getRGB(i, j);
                    red = (tempColor >> 16) & 0xFF;
                    green = (tempColor >> 8) & 0xFF;
                    blue = tempColor & 0xFF;
                    hsv = Color.RGBtoHSB(red, green, blue, null);
                    if ((hsv[0] == color1[0])&&(hsv[1] == color1[1])&&(hsv[2] == color1[2])) {
                        image.setRGB(i, j, 100);
                        list1.add(new Point(i + area.x, j + area.y));
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            }

        }
        //Find color2
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                try {
                    tempColor = image.getRGB(i, j);
                    red = (tempColor >> 16) & 0xFF;
                    green = (tempColor >> 8) & 0xFF;
                    blue = tempColor & 0xFF;
                    hsv = Color.RGBtoHSB(red, green, blue, null);
                    if ((hsv[0] == color2[0])&&(hsv[1] == color2[1])&&(hsv[2] == color2[2])) {
                        image.setRGB(i, j, 10);
                        list2.add(new Point(i + area.x, j + area.y));
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            }

        }
        System.out.println(list1.size());
        System.out.println(list2.size());
        //Pad Short List
        if (list1.size() > list2.size()){
            for(int i =list1.size() - list2.size(); i > 0; i--){
                list2.add(new Point(0, 0));
            }
        }
        else {
            for(int i = list2.size() - list1.size(); i > 0; i--){
                list1.add(new Point(0, 0));
            }
        }

        System.out.println(list1.size());
        System.out.println(list2.size());





        //See if any of the colors are close enough for the distance
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {


                double ac = Math.abs(list2.get(j).y - list1.get(i).y);
                double cb = Math.abs(list2.get(j).x - list1.get(i).x);
                double dist = Math.hypot(ac, cb);






                if (dist < distance) {
                    int midX = (list2.get(j).x + list1.get(i).x) / 2;
                    int midY = (list2.get(j).y + list1.get(i).y) / 2;
                    monsters.add(new Point(midX, midY));
                }
            }
        }
        //Get closest Point to us
        int bestPoint = 1000;
        double bestDist = 20000;
        for (int i = 0; i < monsters.size(); i++) {
            double ac = Math.abs(monsters.get(i).y - 334);
            double cb = Math.abs(monsters.get(i).x - 552);
            double dist = Math.hypot(ac, cb);
            if (dist < bestDist) {
                bestDist = dist;
                bestPoint = i;
            }
        }



        if (monsters.size() == 0)
            return null;
        else return new Point(new Point(monsters.get(bestPoint).x, monsters.get(bestPoint).y));
    }

    public BufferedImage getTest(Rectangle area, float color1[], float color2[], int distance) {
        int x1 = area.x + State.window.returnPos().x;
        int y1 = area.y + State.window.returnPos().y;
        int x2 = area.width;
        int y2 = area.height;
        float[] hsv = new float[2];
        int tempColor, red, blue, green;
        List<Point> list1 = new ArrayList<Point>();
        List<Point> list2 = new ArrayList<Point>();
        List<Point> monsters = new ArrayList<Point>();
        image = robot.createScreenCapture(new Rectangle(x1, y1, x2, y2));


        //Find color1
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                try {
                tempColor = image.getRGB(i, j);
                red = (tempColor >> 16) & 0xFF;
                green = (tempColor >> 8) & 0xFF;
                blue = tempColor & 0xFF;
                hsv = Color.RGBtoHSB(red, green, blue, null);
                    if ((hsv[0] == color1[0])&&(hsv[1] == color1[1])&&(hsv[2] == color1[2])) {
                    image.setRGB(i, j, 100);
                    list1.add(new Point(i + area.x, j + area.y));
                }
            } catch (ArrayIndexOutOfBoundsException e){
                break;
            }
            }

        }
        //Find color2
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                try {
                tempColor = image.getRGB(i, j);
                red = (tempColor >> 16) & 0xFF;
                green = (tempColor >> 8) & 0xFF;
                blue = tempColor & 0xFF;
                hsv = Color.RGBtoHSB(red, green, blue, null);
                    if ((hsv[0] == color2[0])&&(hsv[1] == color2[1])&&(hsv[2] == color2[2])) {
                    image.setRGB(i, j, 10);
                    list2.add(new Point(i + area.x, j + area.y));
                }
                } catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            }

        }
        System.out.println(list1.size());
        System.out.println(list2.size());
        //Pad Short List
        if (list1.size() > list2.size()){
            System.out.println("List 1 Biger");
            for(int i =list1.size() - list2.size(); i > 0; i--){
                list2.add(new Point(0, 0));
            }
        }
        else {
            System.out.println("List 2 biger");
            for(int i = list2.size() - list1.size(); i > 0; i--){
                System.out.println("List Diff " + (list2.size() - list1.size())+ "i is:" + i);
                list1.add(new Point(0, 0));
            }
        }

        System.out.println(list1.size());
        System.out.println(list2.size());





        //See if any of the colors are close enough for the distance
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {


                    double ac = Math.abs(list2.get(j).y - list1.get(i).y);
                    double cb = Math.abs(list2.get(j).x - list1.get(i).x);
                    double dist = Math.hypot(ac, cb);






                    if (dist < distance) {
                        int midX = (list2.get(j).x + list1.get(i).x) / 2;
                        int midY = (list2.get(j).y + list1.get(i).y) / 2;
                        image.setRGB(midX - area.x, midY - area.y, 255);
                        monsters.add(new Point(midX, midY));
                    }
                }
            }

//        int bestPoint = 1000;
//        double bestDist = 20000;
//        for (int i = 0; i < monsters.size(); i++) {
//            double ac = Math.abs(monsters.get(i).y - 334);
//            double cb = Math.abs(monsters.get(i).x - 552);
//            double dist = Math.hypot(ac, cb);
//            if (dist < bestDist) {
//                image.setRGB(monsters.get(i).x- area.x, monsters.get(i).y- area.y, 255);
//                bestDist = dist;
//                bestPoint = i;
//            }
//        }


        //S
        for (int i = 0; i < monsters.size(); i++){
            //image.setRGB(monsters.get(i).x, monsters.get(i).y, 255);
        }

    return image;
    }
}

