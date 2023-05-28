package com.idoisraeli.mobileminigames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button[] deadEndButtons = {
                (Button) findViewById(R.id.btnLogin),
                (Button) findViewById(R.id.btnLeaderboards),
                (Button) findViewById(R.id.btnGame1),
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
}