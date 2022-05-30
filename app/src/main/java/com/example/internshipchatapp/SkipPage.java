package com.example.internshipchatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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


        if (auth.getCurrentUser()!= null)
        {
            startActivity(new Intent(SkipPage.this,Dashboard.class));
            finish();
        }


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SkipPage.this,LoginPage.class));
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SkipPage.this,LoginPage.class));
                finish();
            }
        });
    }
}