package oharg;

public class Game {
    public static final String TEXTURES = "/res/textures";
    
    public static boolean lock = false;
    public static int width = 960;
    public static int height = (int)(width * 9 / 16.0);
    public static boolean fullScreen = false;
    public static int ups = 200;
    
    public static boolean status() { return lock; }
    
    public static void lock() { lock = true; }
    
    public static void unLock() { lock = false; }
}