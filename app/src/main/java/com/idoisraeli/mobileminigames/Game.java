package com.idoisraeli.mobileminigames;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Vector;

// need to see how I fit abstract
public abstract class Game implements IGame{

    int score;
    Vector<GameEntity> gameEntities;

//    IDrawable[] drawables;

//    Bitmap backgroundBitmap;
//    Sprite playerSprite;
//    GameEngine gameEngine;

//    public Game(Context context, int backgroundId, int[] spriteIds) {
//        backgroundBitmap = BitmapService.getBitmapFromIdScaledToHeight(
//                context.getResources(),
//                backgroundId,
//                AppConstants.getScreenHeight()
//        );
//        playerSprite = new Sprite(context.getResources(), spriteIds);
//        gameEngine = GameEngine.getInstance();
//    }
//
//    public Bitmap getBackgroundBitmap() {
//        return backgroundBitmap;
//    }
//
//    public Sprite getPlayerSprite() {
//        return playerSprite;
//    }
//
//    public GameEngine getGameEngine() {
//        return gameEngine;
//    }
//
//    public void drawFrame(Canvas canvas) {
//        for (IDrawable drawable:
//             drawables) {
//            drawable.draw(canvas);
//        }
//    }
//
//    public void updateAndDrawFrame(Canvas canvas) {
//        for (IDrawable drawable:
//                drawables) {
//            drawable.draw(canvas);
//        }
//    }
//
//    public void drawNextGameFrame(Canvas canvas) {
//        gameEngine.updateAndDrawBackgroundImage(canvas, backgroundBitmap);
////        gameEngine.updateAndDrawSprite(canvas, playerSprite);
//    }
}
