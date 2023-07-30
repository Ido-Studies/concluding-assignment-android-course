package com.idoisraeli.mobileminigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String userNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("MobileMiniGames", 0);
        userNickname = sp.getString("User_Nickname", null);

        Button loginBtn = (Button) findViewById(R.id.btnLogin);
        if(userNickname == null) {
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
        } else {
            loginBtn.setText(userNickname);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                }
            });
        }


        Button[] gameButtons = {
                (Button) findViewById(R.id.btnGame1)
        };

        gameButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FlappyBirdActivity.class));
            }
        });

        Button[] deadEndButtons = {
//                (Button) findViewById(R.id.btnLogin),
                (Button) findViewById(R.id.btnLeaderboards),
//                (Button) findViewById(R.id.btnGame1),
                (Button) findViewById(R.id.btnGame2),
                (Button) findViewById(R.id.btnGame3),
        };

        View.OnClickListener partNotReady = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "This part of the App isn't ready yet.", Toast.LENGTH_SHORT).show();
            }
        };

        for (Button btn:
                deadEndButtons) {
            btn.setOnClickListener(partNotReady);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
    }
}