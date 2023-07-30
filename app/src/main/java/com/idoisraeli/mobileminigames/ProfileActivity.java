package com.idoisraeli.mobileminigames;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private String UserUid;
    private User user;
    private Map<String, TextView> infoViews = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        UserUid = getSharedPreferences("MobileMiniGames", 0).getString("User_Uid", null);
        if(UserUid == null) {
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            finish();
        }

        TextView emailView = (TextView) findViewById(R.id.email);
        infoViews.put("Email", emailView);

        TextView nicknameView = (TextView) findViewById(R.id.nickname);
        infoViews.put("Nickname", nicknameView);

        TextView fullnameView = (TextView) findViewById(R.id.fullname);
        infoViews.put("Full Name", fullnameView);

        TextView ageView = (TextView) findViewById(R.id.age);
        infoViews.put("Age", ageView);

        TextView phoneNumberView = (TextView) findViewById(R.id.phoneNumber);
        infoViews.put("Phone Number", phoneNumberView);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference("Users");

        ref.child(UserUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                infoViews.get("Full Name").setText(user.fullname);
                infoViews.get("Nickname").setText(user.nickname);
                infoViews.get("Email").setText(user.email);
                infoViews.get("Phone Number").setText(user.phone);
                infoViews.get("Age").setText(String.valueOf(user.age));

                Toast.makeText(ProfileActivity.this,
                        "Read: " + user.toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,
                        "The read failed: " + error.getCode(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button logoutBtn = (Button) findViewById(R.id.logoutButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this,
                        "Logging Out...",
                        Toast.LENGTH_SHORT).show();
                logout();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                finish();
            }
        });

        Button goBackBtn = (Button) findViewById(R.id.goBackButton);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

    }

    private void logout() {
        getSharedPreferences("MobileMiniGames", 0).edit().clear().commit();
    }

}