package com.idoisraeli.mobileminigames;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private Map<String, EditText> registrationInputs = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

                if(validateRegistrationInputs(registrationInputs)) {
                    Toast.makeText(RegisterActivity.this,
                            "Passed all the tests",
                            Toast.LENGTH_SHORT).show();
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

    public boolean validateRegistrationInputs(Map<String, EditText> registrationInputs) {
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

}