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

public class Summary extends AppCompatActivity {
    private String summarytxt;
    Button saveButton;
    Button cancelButton;

    EditText Summary1;

    Boolean sum=false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        saveButton= findViewById(R.id.saveSummary);
        cancelButton= findViewById(R.id.cancelSummary);
        Summary1= findViewById(R.id.summaryText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summarytxt=Summary1.getText().toString();
                if (summarytxt.isEmpty()) {
                    Summary1.setError("Please Fill!");
                    Summary1.requestFocus();
                    return;
                }
                sum=true;
                Intent intent = new Intent(Summary.this, MainActivity.class);

                SharedPreferences sharedPreferences = getSharedPreferences("CVData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("summary", summarytxt);
                editor.putBoolean("sumBool", sum);
                editor.apply();
                startActivity(intent);

            }
        });

        cancelButton.setOnClickListener(v -> finish());



    }
}