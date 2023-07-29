package com.idoisraeli.mobileminigames;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameEngine {

    static BackgroundImage backgroundImage;
    // BackgroundImage backgroundImage; // Non-Singleton form
    private GameEngine() {
        backgroundImage = new BackgroundImage();
    }

    private static class GameEngineHolder {
        private static final GameEngine INSTANCE = new GameEngine();
    }

    public static GameEngine getInstance() {
        return GameEngineHolder.INSTANCE;
    }

    // With BitmapBank!!!!!!!
    public void updateAndDrawBackgroundImage(Canvas canvas, BitmapBank bitmapBank) {
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
        if(backgroundImage.getX() < (-1) * bitmapBank.getWidth())
            backgroundImage.setX(0);

        canvas.drawBitmap(
                bitmapBank.getBitmap(),
                backgroundImage.getX(),
                backgroundImage.getY(),
                null
        );

        if(backgroundImage.getX() < (-1)*(bitmapBank.getWidth() - AppConstants.getScreenWidth())) {
            canvas.drawBitmap(
                    bitmapBank.getBitmap(),
                    backgroundImage.getX() + bitmapBank.getWidth(),
                    backgroundImage.getY(),
                    null
            );
        }
    }

    // With Bitmap
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

        if(backgroundImage.getX() < (-1)*(bm.getWidth() - AppConstants.getScreenWidth())) {
            canvas.drawBitmap(
                    bm,
                    backgroundImage.getX() + bm.getWidth(),
                    backgroundImage.getY(),
                    null
            );
        }
    }

    public void updateAndDrawSprite(Canvas canvas, Sprite sprite, int xPos, int yPos) {
        sprite.incrementFrame();
        canvas.drawBitmap(sprite.getCurrentFrame(), xPos, yPos, null);
    }

    public void drawSprite(Canvas canvas, Sprite sprite, int xPos, int yPos) {
        canvas.drawBitmap(sprite.getCurrentFrame(), xPos, yPos, null);
    }

    // OG
//    public void updateAndDrawSprite(Canvas canvas, Sprite sprite, int xPos, int yPos) {
//        canvas.drawBitmap(sprite.getCurrentFrame(), xPos, yPos, null);
//        sprite.incrementFrame();
//    }

    // OG
//    public void updateAndDrawBackgroundImage(Canvas canvas) {
//        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
//        if(backgroundImage.getX() < (-1) * AppConstants.getBitmapBank().getWidth()) {
//            backgroundImage.setX(0);
//        }
//        canvas.drawBitmap(
//                AppConstants.getBitmapBank().getBitmap(),
//                backgroundImage.getX(),
//                backgroundImage.getY(),
//                null
//        );
//
//        if(backgroundImage.getX() < (-1) * (AppConstants.getBitmapBank().getWidth() - AppConstants.getScreenWidth())) {
//            canvas.drawBitmap(
//                    AppConstants.getBitmapBank().getBitmap(),
//                    backgroundImage.getX() + AppConstants.getBitmapBank().getWidth(),
//                    backgroundImage.getY(),
//                    null
//            );
//        }
//    }

}
