package utils;

public class ThreadUtility {
    public static int milis = 1000;
    
    public static void waitTime() {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException ignored) {}
    }
}
