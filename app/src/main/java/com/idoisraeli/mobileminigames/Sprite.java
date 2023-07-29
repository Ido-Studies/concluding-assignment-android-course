package com.idoisraeli.mobileminigames;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class Sprite {

    private Bitmap[] frames;
    private int currentFrameNum = 0;

    public Sprite(Resources resources, int[] frameIds) {
        frames = new Bitmap[frameIds.length];
        for(int i = 0; i < frames.length; i++)
            frames[i] = BitmapService.getBitmapFromId(resources, frameIds[i]);
    }

    public Bitmap[] getFrames() {
        return frames;
    }

    public Bitmap getCurrentFrame() {
        return frames[currentFrameNum];
    }

    public int getCurrentFrameNum() {
        return currentFrameNum;
    }

    public void setCurrentFrameNum(int frameNum) {
        this.currentFrameNum = frameNum % frames.length;
    }

    private int calculateFrameNum(int delta) {
        return (currentFrameNum + delta) % frames.length;
    }

    public void incrementFrame() {
        currentFrameNum = calculateFrameNum(1);
    }

    public void decrementFrame() {
        currentFrameNum = calculateFrameNum(-1);
    }

    public void moveFrames(int numberOfFrames) {
        currentFrameNum = calculateFrameNum(numberOfFrames);
    }

    public Bitmap getNextFrame() {
        return frames[calculateFrameNum(1)];
    }

    public Bitmap getPreviousFrame() {
        return frames[calculateFrameNum(-1)];
    }
}
