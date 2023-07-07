package com.idoisraeli.mobileminigames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoadingScreenActivity extends AppCompatActivity {

    private int delay = 3000;
    private final int [] tetroidsDrawableIds = {
            R.drawable.line_piece,
            R.drawable.square_piece,
            R.drawable.el_piece,
            R.drawable.reverse_el_piece,
            R.drawable.squigly_piece,
            R.drawable.reverse_squigly_piece,
    };

    private int tetroidIdx = 0;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        this.layout = findViewById(R.id.loadingScreenLayout);

        ImageView item = (ImageView) findViewById(R.id.loadingScreenItemImgView);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(delay/tetroidsDrawableIds.length);
//        rotateAnimation.setDuration(5000);

        Button changeTetroidBtn = (Button) findViewById(R.id.changeTetroidButton);
        changeTetroidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setImageResource(tetroidsDrawableIds[tetroidIdx]);
                item.startAnimation(rotateAnimation);
                if((++tetroidIdx) >= tetroidsDrawableIds.length)
                    tetroidIdx = 0;
            }
        });

//        while(tetroidIdx < tetroidsDrawableIds.length){
//            if(!rotateAnimation.hasEnded())
//                continue;
//            else {
//                item.setImageResource(tetroidsDrawableIds[tetroidIdx]);
////                if ((++tetroidIdx) >= tetroidsDrawableIds.length)
////                    tetroidIdx = 0;
//            }
////            animateRotation(item, rotateAnimation);
//        }

//        for (int i = 0; i < tetroidsDrawableIds.length; i++) {
//            item.setImageResource(tetroidsDrawableIds[i]);
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    animateRotation(item, rotateAnimation);
//                }
//            });
//            t.start();
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }


//            while(!rotateAnimation.hasEnded());
//        }

//        ObjectAnimator rotator = ObjectAnimator.ofFloat(item, "rotation", 0, 360).setDuration(1000);


//        rotateAnimation.setRepeatCount(2);
//        rotateAnimation.setRepeatMode(Animation.RESTART);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    synchronized (this) {
//                        for (int drawableId:
//                             tetroidsDrawableIds) {
//                            item.setImageResource(drawableId);
//                            item.startAnimation(rotateAnimation);
//                            Toast.makeText(LoadingScreenActivity.this, "animated for " + drawableId, Toast.LENGTH_SHORT).show();
//                            wait(500);
//                        }
//                    }
//                }catch(InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


//        for (int drawableId:
//             tetroidsDrawableIds) {
//            item.setImageResource(drawableId);
////            rotator.start();
//            item.startAnimation(rotateAnimation);
//            Toast.makeText(LoadingScreenActivity.this, "animated for " + drawableId, Toast.LENGTH_SHORT).show();
//            try {
//                wait(500);
//            } catch (InterruptedException e) {
//                Toast.makeText(LoadingScreenActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
//                throw new RuntimeException(e);
//            }
//        }

//        startAnimation();


//        ObjectAnimator rotator = ObjectAnimator.ofFloat(loadingScreenPieceImgView, "rotation", 0, 360).setDuration(1000);
//
//        for (int drawableId:
//             tetroidsDrawableIds) {
//            loadingScreenPieceImgView.setImageResource(drawableId);
//            rotator.start();
//        }

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(LoadingScreenActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, this.delay);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    synchronized (this) {
//                        wait(2000);
//                        Intent intent = new Intent(LoadingScreenActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                }catch(InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    public void setDelay(int delay) {
        if(delay > 0)
            this.delay = delay;
    }

    private void animateRotation(ImageView img, RotateAnimation rotateAnimation) {
        img.startAnimation(rotateAnimation);
    }

    private void startAnimation(){
        ImageView loadingScreenPieceImgView = new ImageView(LoadingScreenActivity.this);
        loadingScreenPieceImgView.setAdjustViewBounds(true);
        loadingScreenPieceImgView.setScaleType(ImageView.ScaleType.FIT_CENTER);

//        loadingScreenPieceImgView.setImageResource(tetroidsDrawableIds[0]);
        loadingScreenPieceImgView.setImageResource(R.drawable.line_piece);

        int width = 100;//ConstraintLayout.LayoutParams.MATCH_PARENT;
        int height = 100;//ConstraintLayout.LayoutParams.MATCH_PARENT;
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(width, height);
//        layoutParams.setMargins(0, 10, 0, 10);

        loadingScreenPieceImgView.setLayoutParams(layoutParams);

        layout.addView(loadingScreenPieceImgView);
    }
}