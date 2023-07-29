package com.idoisraeli.mobileminigames;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class GameGlobalVariables {

    private static Context CONTEXT;

    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;

    public static void initialization(Context context) {
        CONTEXT = context;
        setScreenSize(context);
//        gameEngine = new GameEngine();
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    private static void setScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        GameGlobalVariables.SCREEN_WIDTH = metrics.widthPixels;
        GameGlobalVariables.SCREEN_HEIGHT = metrics.heightPixels;
    }

}
