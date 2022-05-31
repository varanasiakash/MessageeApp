package com.example.internshipchatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SkipPage extends AppCompatActivity {

    private Button skip, next;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_skip_page);

        skip = findViewById(R.id.skip);
        next = findViewById(R.id.next);



        skip.setOnClickListener(view -> {
            startActivity(new Intent(SkipPage.this,RegisterPage.class));
            Toast.makeText(this, "Register page open successful", Toast.LENGTH_SHORT).show();
            finish();
        });

        next.setOnClickListener(view -> {
            startActivity(new Intent(SkipPage.this,LoginPage.class));
            finish();
        });
    }
}