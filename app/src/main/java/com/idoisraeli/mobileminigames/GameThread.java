package com.idoisraeli.mobileminigames;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread{

    SurfaceHolder surfaceHolder;
    IGame game;
    boolean isRunning;

    // In milliseconds
    long startTime;

    // In milliseconds
    long loopTime;

    // Delay in milliseconds between screen refreshes
    static final long DELAY = 33;

    public GameThread(SurfaceHolder surfaceHolder, IGame game) {
        this.surfaceHolder = surfaceHolder;
        this.game = game;
        isRunning = true;
    }

    @Override
    public void run() {
        while(isRunning) {
            startTime = SystemClock.uptimeMillis();
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if(canvas != null) {
                synchronized (surfaceHolder) {
                    game.updateAndDrawFrame(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            loopTime = SystemClock.uptimeMillis() - startTime;

            // Make sure we update the correct amount per second
            if(loopTime < DELAY) {
                try {
                    Thread.sleep(DELAY - loopTime);
                } catch(InterruptedException e) {
                    Log.e("Interrupted", "Interrupted while sleeping");
                }
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean running) {
        isRunning = running;
    }
}
