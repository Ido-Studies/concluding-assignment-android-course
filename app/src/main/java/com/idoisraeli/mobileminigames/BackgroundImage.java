package com.idoisraeli.mobileminigames;

public class BackgroundImage {

    // The X coordinate
    private int backgroundImageX = 0;
    // The Y coordinate
    private int backgroundImageY = 0;
    // The velocity
    private int backgroundVelocity = 3;

    public BackgroundImage() {

    }

    // Getters

    public int getX() {
        return backgroundImageX;
    }

    public int getY() {
        return backgroundImageY;
    }

    public int getVelocity() {
        return backgroundVelocity;
    }

    // Setters

    public void setX(int backgroundImageX) {
        this.backgroundImageX = backgroundImageX;
    }

    public void setY(int backgroundImageY) {
        this.backgroundImageY = backgroundImageY;
    }

    public void setVelocity(int backgroundVelocity) {
        this.backgroundVelocity = backgroundVelocity;
    }
}
