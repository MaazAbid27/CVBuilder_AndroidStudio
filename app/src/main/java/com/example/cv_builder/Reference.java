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

public class Reference extends AppCompatActivity {

    private String reftxt;
    Button saveButton;
    Button cancelButton;

    EditText ref1;

    Boolean ref=false;


    @SuppressLint("MissingInflatedId")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reference);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        saveButton= findViewById(R.id.saveRef);
        cancelButton= findViewById(R.id.cancelRef);
        ref1= findViewById(R.id.referenceText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reftxt = ref1.getText().toString();
                if (reftxt.isEmpty()) {
                    ref1.setError("Please Fill!");
                    ref1.requestFocus();
                    return;
                }
                ref=true;
                Intent intent = new Intent(Reference.this, MainActivity.class);

                SharedPreferences sharedPreferences = getSharedPreferences("CVData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("reference", reftxt);
                editor.putBoolean("refBool",ref);
                editor.apply();
                startActivity(intent);
            }
        });
        cancelButton.setOnClickListener(v -> finish());

    }
}