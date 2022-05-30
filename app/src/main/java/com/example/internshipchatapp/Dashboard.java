package com.example.internshipchatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    private Button chat, call;

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        chat = findViewById(R.id.chatButton);
        call = findViewById(R.id.callButton);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.background));
                chat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                fragment = new ChatFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.dashboardFragment,fragment).commit();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.background));
                call.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                fragment = new CallFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.dashboardFragment,fragment).commit();
            }
        });
    }
}