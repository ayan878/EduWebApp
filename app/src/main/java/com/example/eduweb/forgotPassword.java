package com.example.eduweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {
    private Button btnReset,BtnSignup;
    private EditText emailEditText;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnReset=findViewById(R.id.btnReset);
        BtnSignup=findViewById(R.id.BtnSignup);
        auth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.email);


        BtnSignup.setOnClickListener(view -> {
            startActivity(new Intent(forgotPassword.this, MainActivity.class));
        });



        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(forgotPassword.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(forgotPassword.this,
                                    "Password reset email sent. Check your email.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(forgotPassword.this,
                                    "Failed to send password reset email. Please try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}