package com.idoisraeli.mobileminigames;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameEngine {

    static BackgroundImage backgroundImage;

    private GameEngine() {
        backgroundImage = new BackgroundImage();
    }

    private static class GameEngineHolder {
        private static final GameEngine INSTANCE = new GameEngine();
    }

    public static GameEngine getInstance() {
        return GameEngineHolder.INSTANCE;
    }

    public void updateAndDrawBackgroundImage(Canvas canvas, Bitmap bm) {
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
        if(backgroundImage.getX() < (-1) * bm.getWidth())
            backgroundImage.setX(0);

        canvas.drawBitmap(
                bm,
                backgroundImage.getX(),
                backgroundImage.getY(),
                null
        );

        if(backgroundImage.getX() < (-1)*(bm.getWidth() - GameGlobalVariables.getScreenWidth())) {
            canvas.drawBitmap(
                    bm,
                    backgroundImage.getX() + bm.getWidth(),
                    backgroundImage.getY(),
                    null
            );
        }
    }

}
