package com.example.cv_builder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class preview extends AppCompatActivity {

    TextView username1;
    Button sharecv;
    TextView summary1;
    TextView education1;
    TextView certification1;
    TextView experience1;
    TextView reference1;
    TextView email1;
    TextView contact1;

    ImageView pfpimage;

    String imageuri;






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        username1=(TextView)findViewById(R.id.usernamePrev);
        summary1=(TextView)findViewById(R.id.summaryTextPrev);
        education1=(TextView)findViewById(R.id.educationTextPrev);
        certification1=(TextView)findViewById(R.id.certificationTextPrev);
        experience1=(TextView)findViewById(R.id.experienceTextPrev);
        reference1=(TextView)findViewById(R.id.referenceTextPrev);
        email1=(TextView)findViewById(R.id.numPrev);
        contact1=(TextView)findViewById(R.id.emailPrev);
        pfpimage=(ImageView)findViewById(R.id.profilepic);
        sharecv=(Button)findViewById(R.id.sharePrev);


        LinearLayout sumlayout=(LinearLayout) findViewById(R.id.summaryLayout);
        LinearLayout edulayout=(LinearLayout)findViewById(R.id.educationLayout);
        LinearLayout certlayout=(LinearLayout)findViewById(R.id.certificationLayout);
        LinearLayout explayout=(LinearLayout)findViewById(R.id.experienceLayout);
        LinearLayout reflayout=(LinearLayout)findViewById(R.id.referenceLayout);


        SharedPreferences sharedPreferences = getSharedPreferences("CVData", MODE_PRIVATE);
        String username = sharedPreferences.getString("name","Your Name");

        String summary2 = sharedPreferences.getString("summary","Your Summary");
        String education = sharedPreferences.getString("education","Your Education");
        String certification = sharedPreferences.getString("certification","Your Certifications");
        String experience=sharedPreferences.getString("experience","Your Experiences");
        String reference=sharedPreferences.getString("reference","Your References");
        String email=sharedPreferences.getString("email","Your Email");
        String contact=sharedPreferences.getString("contact","Your Contact");
        String imageURI=sharedPreferences.getString("profileImageUri", null);

        Boolean sum=sharedPreferences.getBoolean("sumBool",false);
        Boolean edu=sharedPreferences.getBoolean("eduBool",false);
        Boolean cert=sharedPreferences.getBoolean("certBool",false);
        Boolean exp=sharedPreferences.getBoolean("expBool",false);
        Boolean ref=sharedPreferences.getBoolean("refBool",false);
        Boolean mailbool=sharedPreferences.getBoolean("emailBool",false);
        Boolean contactbool=sharedPreferences.getBoolean("contactBool",false);



        if(!sum){
            sumlayout.setVisibility(View.GONE);

        }
        if(!edu){
            edulayout.setVisibility(View.GONE);
        }
        if(!cert){
            certlayout.setVisibility(View.GONE);
        }
        if(!exp){
            explayout.setVisibility(View.GONE);
        }
        if(!ref){
            reflayout.setVisibility(View.GONE);
        }
        if(!mailbool){
            email1.setVisibility(View.GONE);

        }
        if(!contactbool){
            contact1.setVisibility(View.GONE);
        }


        if (imageURI != null) {
            pfpimage.setImageURI(Uri.parse(imageURI));
        }
        username1.setText(username);
        summary1.setText(summary2);
        education1.setText(education);
        certification1.setText(certification);
        experience1.setText(experience);
        reference1.setText(reference);
        email1.setText("Email: " + email);
        contact1.setText("Contact: " +contact );

        sharecv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharecv();
            }
        });





    }

    private void goHomePrev(View v){

        startActivity(new Intent(preview.this, MainActivity.class));
    }
    private void sharecv(){
        String cvText = "My CV Details:\n\n"
                + " Name: " + username1 + "\n"
                + " Email: " + email1 + "\n"
                + " Phone: " + contact1 + "\n"
                + " Education: " + education1 + "\n"
                + " Experience: " + experience1 + "\n"
                + " Certifications: " + certification1 + "\n"
                + " References: " + reference1 + "\n";

        // Create an Intent for sharing
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My CV");
        shareIntent.putExtra(Intent.EXTRA_TEXT, cvText);

        // Show the share options
        startActivity(Intent.createChooser(shareIntent, "Share CV via"));
    }

}