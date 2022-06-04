package com.example.internshipchatapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Handler handler;

    FirebaseUser user_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        user_check = FirebaseAuth.getInstance().getCurrentUser();

        handler = new Handler();
        handler.postDelayed(() ->
        {
            if(user_check != null)
            {
                startActivity(new Intent(this, Dashboard.class));
            }
            else
            {
                startActivity(new Intent(this,SkipPage.class));
            }
            finish();

        },3000);

    }
}