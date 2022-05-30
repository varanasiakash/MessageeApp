package com.example.internshipchatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class VoiceCall extends AppCompatActivity {

    ImageView profile_big, callCancel, mute, contactList, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_call);

        back = findViewById(R.id.backMessageVoice);

        profile_big = findViewById(R.id.Profile_pic);

        callCancel = findViewById(R.id.callCancel);

        mute = findViewById(R.id.mute);
        contactList = findViewById(R.id.contactList);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VoiceCall.this,Dashboard.class));
                finish();
            }
        });
    }
}