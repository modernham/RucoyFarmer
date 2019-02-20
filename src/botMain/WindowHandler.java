package botMain;

import java.awt.*;
import java.util.Arrays;
import com.sun.jna.*;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


public class WindowHandler {
    static String WINDOWNAME;
    public static Point windowPos;


    public WindowHandler(String name){
        WINDOWNAME = name;
    }

        public interface User32 extends StdCallLibrary {
            User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class,
                    W32APIOptions.DEFAULT_OPTIONS);

            HWND FindWindow(String lpClassName, String lpWindowName);

            int GetWindowRect(HWND handle, int[] rect);
        }

        public static int[] getRect (){
            HWND hwnd = User32.INSTANCE.FindWindow(null, WINDOWNAME);

            int[] rect = {0, 0, 0, 0};
            int result = User32.INSTANCE.GetWindowRect(hwnd, rect);
            return rect;
        }

    public static Point getPos (){
        HWND hwnd = User32.INSTANCE.FindWindow(null, WINDOWNAME);
        int[] rect = {0, 0, 0, 0};
          int result = User32.INSTANCE.GetWindowRect(hwnd, rect);
          windowPos = new Point(rect[0], rect[1]);
        return new Point(rect[0], rect[1]);
    }

    public static Point returnPos (){
        return windowPos;
    }

        @SuppressWarnings("serial")
        public static class WindowNotFoundException extends Exception {
            public WindowNotFoundException(String className, String WINDOWNAME) {
                super(String.format("Window null for className: %s; windowName: %s",
                        className, WINDOWNAME));
            }
        }

        @SuppressWarnings("serial")
        public static class GetWindowRectException extends Exception {
            public GetWindowRectException(String WINDOWNAME) {
                super("Window Rect not found for " + WINDOWNAME);
            }
        }

}
