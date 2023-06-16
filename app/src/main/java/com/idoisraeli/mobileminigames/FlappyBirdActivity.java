package com.idoisraeli.mobileminigames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class FlappyBirdActivity extends AppCompatActivity {

    ImageView playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flappy_bird);

        playBtn = findViewById(R.id.flappyBirdPlayBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FlappyBirdActivity.this, "Let's Play", Toast.LENGTH_SHORT).show();
            }
        });
    }
}