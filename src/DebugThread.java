import javax.swing.*;
import java.awt.*;

public class DebugThread implements Runnable {

    public static JFrame debugFrame = new JFrame("Debug");
    public static Debug debugGUI = new Debug();
    private static Color color = new Color(0,0,0);
    public static float[] hsb = new float[2];

    public void run() {
        debugGUI = new Debug();
        debugFrame.setContentPane(debugGUI.debugPanel);
        debugFrame.pack();
        debugFrame.setVisible(true);

        while (true) {
            debugGUI.mousePos.setText("(" + State.mouse.getRelPosition());
            hsb = State.colorfinder.getPixelColor(State.mouse.getPosition());
            debugGUI.textArea1.setText("Hue:" + hsb[0] + "\n Saturation: " + hsb[1] + "\n Brightness: " + hsb[2]);
        }
    }

    }