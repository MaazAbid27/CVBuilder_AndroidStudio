package com.example.cv_builder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView profileImage;
    private Uri imageUri;

    final int REQUEST_CODE_PERSONAL_DETAILS = 1;
    Button pfp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        pfp=(Button) findViewById(R.id.pfpButton);
        
        pfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        
    }
    
    private void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imageUri = data.getData();


        SharedPreferences sharedPreferences = getSharedPreferences("CVData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profileImageUri", imageUri.toString());
        editor.apply();
        startActivity(new Intent(MainActivity.this, Summary.class));
    }
    
    public void setPersonalDetails(View v){
        startActivity(new Intent(MainActivity.this, personalDetails.class));
    }
    public void setSummary(View v){
        startActivity(new Intent(MainActivity.this, Summary.class));
    }
    public void setCertifications(View v){
        startActivity(new Intent(MainActivity.this, Certifications.class));
    }
    public void setEductation(View v){
        startActivity(new Intent(MainActivity.this, Education.class));
    }
    public void setExperience(View v){
        startActivity(new Intent(MainActivity.this, Experience.class));
    }
    public void setReference(View v){
        startActivity(new Intent(MainActivity.this, Reference.class));
    }

    public void goPrevScreen(View v){
        startActivity(new Intent(MainActivity.this, preview.class));
    }

    public void clearData(View v){
        SharedPreferences sharedPreferences = getSharedPreferences("CVData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        CharSequence text = "Data CLeared!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this , text, duration);
        toast.show();
    }

}