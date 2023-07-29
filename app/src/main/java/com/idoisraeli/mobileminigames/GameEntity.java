package com.idoisraeli.mobileminigames;

import android.graphics.Bitmap;

import java.util.function.Consumer;

public class GameEntity {

    private int xPos = 0;
    private int yPox = 0;
    private int width;
    private int height;

    Sprite sprite;

    private Consumer<Object[]> updateFunction;

    public GameEntity(Sprite sprite) {
        this.sprite = sprite;
        setSizes();
    }

    public void update(Object... args) {
        if(updateFunction != null)
            updateFunction.accept(args);
    }

    public void setUpdateFunction(Consumer<Object[]> updateFunction) {
        this.updateFunction = updateFunction;
    }

    private void setSizes(){
        Bitmap bm = sprite.getCurrentFrame();
        this.width = bm.getWidth();
        this.height = bm.getHeight();
    }

    public int getX() {
        return xPos;
    }

    public void setX(int xPos) {
        this.xPos = xPos;
    }

    public int getY() {
        return yPox;
    }

    public void setY(int yPox) {
        this.yPox = yPox;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
