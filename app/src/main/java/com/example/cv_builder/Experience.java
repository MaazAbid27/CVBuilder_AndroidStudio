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

public class Experience extends AppCompatActivity {

    private String exptxt;
    Button saveButton;
    Button cancelButton;

    EditText exp1;

    Boolean exp=false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_experience);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        saveButton= findViewById(R.id.saveExp);
        cancelButton= findViewById(R.id.cancelExp);
        exp1= findViewById(R.id.experienceText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exptxt = exp1.getText().toString();
                if (exptxt.isEmpty()) {
                    exp1.setError("Please Fill!");
                    exp1.requestFocus();
                    return;
                }

                exp=true;
                Intent intent = new Intent(Experience.this, MainActivity.class);

                SharedPreferences sharedPreferences = getSharedPreferences("CVData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("experience", exptxt);
                editor.putBoolean("expBool",exp);
                editor.apply();
                startActivity(intent);
            }
        });
        cancelButton.setOnClickListener(v -> finish());


    }
}