package com.example.lab2_20192858;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toast.makeText(this, "Estás en Perfil Activity", Toast.LENGTH_SHORT).show();
        ImageView profileImage = findViewById(R.id.profileImage);
        TextView usernameText = findViewById(R.id.usernameText);
        String username = getIntent().getStringExtra("username");
        String imageUrl = getIntent().getStringExtra("imageUrl");
        usernameText.setText(username);
        Glide.with(this).load(imageUrl).into(profileImage);
        Button cronometroButton = findViewById(R.id.cronometro_button);
        cronometroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChronometerActivity.class);
                startActivity(intent);
            }
        });
    }



}
