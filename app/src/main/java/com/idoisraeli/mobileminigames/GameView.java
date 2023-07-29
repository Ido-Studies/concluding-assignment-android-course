package com.idoisraeli.mobileminigames;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;
    IGame game;

    public GameView(Context context, IGame game) {
        super(context);
        initView(game);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if(!gameThread.isRunning()) {
            gameThread = new GameThread(surfaceHolder, game);
            // gameThread.start();
        }
//        else {
//            gameThread.start();
//        }
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if(!gameThread.isRunning()) {
            gameThread.setIsRunning(false);
            boolean retry = true;
            while(retry) {
                try {
                    gameThread.join();
                    retry = false;
                } catch(InterruptedException e) { }
            }
        }
    }

    void initView(IGame game) {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);

        this.game = game;
        gameThread = new GameThread(holder, game);
    }

}
