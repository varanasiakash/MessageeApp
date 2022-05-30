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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity {

    private EditText username, email, password, conPassword;
    private Button register , login;
    private ImageView back;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        auth = FirebaseAuth.getInstance();

        username = findViewById(R.id.usernameRegister);
        email = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);
        conPassword = findViewById(R.id.confirmPassword);
         back = findViewById(R.id.backMessageRegister);
         login = findViewById(R.id.loginRegister);

        register = findViewById(R.id.createAccount);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterPage.this,LoginPage.class));
                finish();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty() && email.getText().toString().isEmpty() &&
                        password.getText().toString().isEmpty() && conPassword.getText().toString().isEmpty())
                {
                    if(password.getText().toString() == conPassword.getText().toString())
                    {
                        handleSignUp();
                    }
                    else
                    {
                        Toast.makeText(RegisterPage.this, "Password in not in Sync", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(RegisterPage.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }

            }
        });

      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(RegisterPage.this,LoginPage.class));
              finish();
          }
      });
    }

    private void handleSignUp() {
        auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            //adding username, emailID and profile pic in the database(firebase)
                            FirebaseDatabase.getInstance().getReference("user/"+FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(new UsersChat(username.getText().toString(),email.getText().toString(),""));

                            startActivity(new Intent(RegisterPage.this,LoginPage.class));
                            Toast.makeText(RegisterPage.this, "Created an Account", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}