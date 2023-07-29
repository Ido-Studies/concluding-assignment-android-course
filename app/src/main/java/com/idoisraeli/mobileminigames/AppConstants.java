package com.idoisraeli.mobileminigames;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {

    private static BitmapBank bitmapBank;
    private static GameEngine gameEngine;

    private static Context CONTEXT;

    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;

    public static void initialization(Context context) {
        CONTEXT = context;
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources(), R.drawable.flappy_bird_game_bg, SCREEN_HEIGHT); // R.drawable.flappy_bird_game_bg should be modifiable
        gameEngine = GameEngine.getInstance();
    }

    public static BitmapBank getBitmapBank() {
        return bitmapBank;
    }

    public static GameEngine getGameEngine() {
        return gameEngine;
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

        AppConstants.SCREEN_WIDTH = metrics.widthPixels;
        AppConstants.SCREEN_HEIGHT = metrics.heightPixels;
    }

}
