package com.idoisraeli.mobileminigames;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth auth;
    private Map<String, EditText> registrationInputs = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        EditText emailInput = (EditText) findViewById(R.id.emailInput);
        registrationInputs.put("Email", emailInput);

        EditText passwordInput = (EditText) findViewById(R.id.passwordInput);
        registrationInputs.put("Password", passwordInput);

        EditText nicknameInput = (EditText) findViewById(R.id.nicknameInput);
        registrationInputs.put("Nickname", nicknameInput);

        EditText firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        registrationInputs.put("First Name", firstNameInput);

        EditText lastNameInput = (EditText) findViewById(R.id.lastNameInput);
        registrationInputs.put("Last Name", lastNameInput);

        EditText ageInput = (EditText) findViewById(R.id.ageInput);
        registrationInputs.put("Age", ageInput);

        EditText phoneNumberInput = (EditText) findViewById(R.id.phoneNumberInput);
        registrationInputs.put("Phone Number", phoneNumberInput);

        Button registerBtn = (Button) findViewById(R.id.registerButton);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateRegistrationInputs()) {
                    registerUser(
                            registrationInputs.get("Email").getText().toString(),
                            registrationInputs.get("Password").getText().toString(),
                            registrationInputs.get("Nickname").getText().toString(),
                            Integer.parseInt(registrationInputs.get("Age").getText().toString()),
                            PhoneNumberUtils.formatNumberToE164(registrationInputs.get("Phone Number").getText().toString(), "IL"),
                            registrationInputs.get("First Name").getText().toString(),
                            registrationInputs.get("Last Name").getText().toString()
                    );
                }

//                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        Button goBackBtn = (Button) findViewById(R.id.goBackButton);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private boolean validateRegistrationInputs() {
        boolean areAllValid = true;

        for (String key:
             registrationInputs.keySet()) {
            EditText value = registrationInputs.get(key);

            if(TextUtils.isEmpty(value.getText().toString())) {
                Toast.makeText(RegisterActivity.this,
                        "Please enter your " + key,
                        Toast.LENGTH_SHORT).show();
                value.setError(key + " is a required field");
                value.requestFocus();
                areAllValid = false;
            }
            else {
                switch(key) {
                    case "Email":
                        if(!Patterns.EMAIL_ADDRESS.matcher(value.getText().toString()).matches()) {
                            Toast.makeText(RegisterActivity.this,
                                    "Please re-enter your Email",
                                    Toast.LENGTH_SHORT).show();
                            value.setError("Invalid Email Address");
                            value.requestFocus();
                            areAllValid = false;
                        }
                        break;

                    case "Age":
                        boolean isInvalidAge = false;
                        try {
                            if(Integer.parseInt(value.getText().toString()) <= 0)
                                isInvalidAge = true;
                            else
                                break;
                        } catch (NumberFormatException nfe) {
                            isInvalidAge = true;
                        }
                        if(isInvalidAge) {
                            Toast.makeText(RegisterActivity.this,
                                    "Please re-enter your age",
                                    Toast.LENGTH_SHORT).show();
                            value.setError("Invalid age");
                            value.requestFocus();
                            areAllValid = false;
                        }
                        break;

                    case "Phone Number":
//                                Toast.makeText(RegisterActivity.this,
//                                        PhoneNumberUtils.formatNumberToE164(value.getText().toString(), "IL"),
//                                        Toast.LENGTH_SHORT).show();
                        if(value.getText().toString().length() != 10) {
                            Toast.makeText(RegisterActivity.this,
                                    "Please re-enter your Phone Number",
                                    Toast.LENGTH_SHORT).show();
                            value.setError("Invalid Phone Number");
                            value.requestFocus();
                            areAllValid = false;
                        }
                        break;

                    case "Password":
                        if(value.getText().toString().length() < 8) {
                            Toast.makeText(RegisterActivity.this,
                                    "Please re-enter your Password",
                                    Toast.LENGTH_SHORT).show();
                            value.setError("Password must be 8 characters or more");
                            value.requestFocus();
                            areAllValid = false;
                        }
                        break;
                }
            }
        }

        return areAllValid;
    }

    private void registerUser(String email, String password, String nickname, int age, String phone, String firstname, String lastname) {
        this.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            User user = new User(email, nickname, age, phone, firstname, lastname);
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                            reference.child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    SharedPreferences sp = getSharedPreferences("MobileMiniGames", 0);
                                    SharedPreferences.Editor sedt = sp.edit();
                                    sedt.putString("User_Uid", firebaseUser.getUid());
                                    sedt.putString("User_Nickname", user.nickname);
                                    sedt.apply();

                                    sendSms(user.phone, "Thank you " + user.fullname + " for the registration!");
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                                    // Finish the current activity to prevent the user from navigating back to it
                                    finish();
                                }
                            });
                        }
                    }
                });
    }

    private void sendSms(String phoneNumber, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, msg, null, null);
            Toast.makeText(RegisterActivity.this,
                    "Message Sent to " + phoneNumber,
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(RegisterActivity.this,
                    e.getMessage(),
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}