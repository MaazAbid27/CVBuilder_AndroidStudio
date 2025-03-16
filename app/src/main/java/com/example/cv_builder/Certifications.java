package com.example.cv_builder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Certifications extends AppCompatActivity {

    private String certtxt;
    Button saveButton;
    Button cancelButton;

    Boolean cert=false;
    EditText cert1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_certifications);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        saveButton= findViewById(R.id.saveCert);
        cancelButton= findViewById(R.id.cancelCert);
        cert1= findViewById(R.id.certificationText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                certtxt = cert1.getText().toString();
                if (certtxt.isEmpty()) {
                    cert1.setError("Please Fill!");
                    cert1.requestFocus();
                    return;
                }


                cert=true;
                Intent intent = new Intent(Certifications.this, MainActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("CVData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("certBool",cert);
                editor.putString("certification", certtxt);
                editor.apply();
                startActivity(intent);
            }
        });
        cancelButton.setOnClickListener(v -> finish());


    }
}