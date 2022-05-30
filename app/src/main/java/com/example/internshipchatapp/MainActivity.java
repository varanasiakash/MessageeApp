package com.example.internshipchatapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Handler handler;

    FirebaseUser user_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        handler.postDelayed(() ->
        {
            user_check = FirebaseAuth.getInstance().getCurrentUser();
            Intent intent;
            if (user_check != null)
            {
                intent = new Intent(MainActivity.this, Dashboard.class);
            }
            else
            {
                intent = new Intent(MainActivity.this, SkipPage.class);

            }
            startActivity(intent);
            finish();


        },3000);

    }
}