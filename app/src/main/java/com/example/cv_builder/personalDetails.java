package com.example.cv_builder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class personalDetails extends AppCompatActivity {

    String userName;
    String email;
    String contact;
    EditText usernameInput;
    EditText emailInput;
    EditText contactInput;
    Button saveButton;


    Button cancelButton;


    boolean emailbool=false;
    boolean contactbool=false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        saveButton=(Button) findViewById(R.id.savePD);
        cancelButton=(Button)findViewById(R.id.cancelPD);
        usernameInput=(EditText) findViewById(R.id.usernamePD);
        emailInput=(EditText)findViewById(R.id.emailPD);
        contactInput=(EditText)findViewById(R.id.contactPD);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=usernameInput.getText().toString();


                if (userName.isEmpty()) {
                    usernameInput.setError("Please Fill!");
                    usernameInput.requestFocus();
                    return;
                }

                email=emailInput.getText().toString();
                String email = emailInput.getText().toString().trim();


                if (email.isEmpty()) {
                    emailInput.setError("Email is required!");
                    emailInput.requestFocus();
                    return;
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailInput.setError("Enter a valid email address!");
                    emailInput.requestFocus();
                    return;
                }




                contactInput.requestFocus();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                contact=contactInput.getText().toString().trim();
                if (contact.isEmpty()) {
                    contactInput.setError("Phone number is required!");
                    contactInput.requestFocus();
                    return;
                } else if (contact.length() < 11) {
                    contactInput.setError("Enter a valid 11-digit phone number!");
                    contactInput.requestFocus();
                    return;
                }


                contactbool=true;
                emailbool=true;
                Intent intent = new Intent(personalDetails.this, MainActivity.class);

                SharedPreferences sharedPreferences = getSharedPreferences("CVData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", userName);
                editor.putString("email",email);
                editor.putString("contact",contact);
                editor.putBoolean("contactBool",contactbool);
                editor.putBoolean("emailBool",emailbool);
                editor.apply();



                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(v -> finish());
    }



}