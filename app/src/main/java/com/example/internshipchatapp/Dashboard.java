package com.example.internshipchatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    private Button chat, call;
    private ImageView logout;
    private Fragment fragment;
    private ConstraintLayout dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        chat = findViewById(R.id.chatButton);
        call = findViewById(R.id.callButton);

        dashboard = findViewById(R.id.dashboard);

        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(dashboard,"Are you sure you want to logout",Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(Dashboard.this,MainActivity.class).
                                        setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                finish();

                            }
                        });
                snackbar.show();
            }
        });


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