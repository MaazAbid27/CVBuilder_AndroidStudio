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

public class Education extends AppCompatActivity {

    private String edutxt;
    Button saveButton;
    Button cancelButton;
    EditText edu1;
    Boolean edu=false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_education);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        saveButton= findViewById(R.id.saveEdu);
        cancelButton= findViewById(R.id.cancelEdu);
        edu1= findViewById(R.id.educationText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edutxt=edu1.getText().toString();
                if (edutxt.isEmpty()) {
                    edu1.setError("Please Fill!");
                    edu1.requestFocus();
                    return;
                }

                edu=true;

                Intent intent = new Intent(Education.this, MainActivity.class);

                SharedPreferences sharedPreferences = getSharedPreferences("CVData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("education", edutxt);
                editor.putBoolean("eduBool", edu);
                editor.apply();
                startActivity(intent);

            }
        });

        cancelButton.setOnClickListener(v -> finish());
    }
}