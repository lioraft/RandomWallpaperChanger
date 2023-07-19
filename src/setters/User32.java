package setters;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;


/* this is the interface that will be used to call the SystemParametersInfo function from the user32.dll library */
public interface User32 extends Library {
    User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

    int SystemParametersInfo(int uAction, int uParam, String lpvParam, int fuWinIni);
}
