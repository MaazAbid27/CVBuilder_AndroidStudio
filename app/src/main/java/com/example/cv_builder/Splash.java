package com.example.cv_builder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView logoPic= findViewById(R.id.logo_pic);
        Animation fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade);
        logoPic.startAnimation(fadeAnimation);


        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Creating a new intent
            Intent i = new Intent(Splash.this, MainActivity.class);

            // Starting a new activity
            startActivity(i);

            // Finishing current activity
            finish();
        }, 2000);
    }
}