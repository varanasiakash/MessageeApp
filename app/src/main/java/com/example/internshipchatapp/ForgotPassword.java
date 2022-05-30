package com.example.internshipchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText emailForget;
    private Button resend , createAccount;
    private String email;
    private ImageView back;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();

        emailForget = findViewById(R.id.mailId);
        resend = findViewById(R.id.logINForgot);

        back = findViewById(R.id.backMessageForgot);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassword.this,LoginPage.class));
                finish();
            }
        });

        createAccount = findViewById(R.id.createAccountForgot);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassword.this,RegisterPage.class));
                finish();

            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateDate();
            }
        });
    }

    private void validateDate() {
        email = emailForget.getText().toString();

        if (email.isEmpty())
        {
            emailForget.setError("Required");
        }
        else
        {
            forgetPassword();
        }
    }

    private void forgetPassword() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(ForgotPassword.this, "Check your Email", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotPassword.this,LoginPage.class));
                    finish();
                }
                else
                {
                    Toast.makeText(ForgotPassword.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}