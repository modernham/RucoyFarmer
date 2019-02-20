package Tools;

import java.io.Serializable;

public class savedColors implements Serializable {
    public static float[] COLOR1 = new float[2];
    public static float[] COLOR2 = new float[2];
    public static float[] COLOR3 = new float[2];

    public static float[] getCOLOR1() {
        return COLOR1;
    }

    public static void setCOLOR1(float[] COLOR1) {
        savedColors.COLOR1 = COLOR1;
    }

    public static float[] getCOLOR2() {
        return COLOR2;
    }

    public static void setCOLOR2(float[] COLOR2) {
        savedColors.COLOR2 = COLOR2;
    }

    public static float[] getCOLOR3() {
        return COLOR3;
    }

    public static void setCOLOR3(float[] COLOR3) {
        savedColors.COLOR3 = COLOR3;
    }

    public void savedColors(float[] color1, float[] color2, float[] color3){
        this.COLOR1 = color1;
        this.COLOR2 = color2;
        this.COLOR3 = color3;


    }
}
