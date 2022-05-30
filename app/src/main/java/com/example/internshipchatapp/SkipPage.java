package com.example.internshipchatapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SkipPage extends AppCompatActivity {

    private Button skip, next;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip_page);

        skip = findViewById(R.id.skip);
        next = findViewById(R.id.next);



        skip.setOnClickListener(view -> {
            startActivity(new Intent(SkipPage.this,RegisterPage.class));
            finish();
        });

        next.setOnClickListener(view -> {
            startActivity(new Intent(SkipPage.this,LoginPage.class));
            finish();
        });
    }
}