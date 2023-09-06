package com.example.eduweb;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private Button btnLogin,btnSignup,BtnSignup,btnForgot;
    private EditText emailEditText,passwordEditText;

    private ImageButton instaButton;
    private ImageButton twitterButton;
    private ImageButton githubButton;
    private ImageButton facebookButton;
    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin =findViewById(R.id.btnLogin);
        emailEditText=findViewById(R.id.email);
        passwordEditText=findViewById(R.id.password);
        btnSignup=findViewById(R.id.btnSignup);
        BtnSignup=findViewById(R.id.BtnSignup);
        btnForgot=findViewById(R.id.btnForgot);
        facebookButton = findViewById(R.id.fb);
        instaButton = findViewById(R.id.insta);
        twitterButton = findViewById(R.id.twitter);
        githubButton = findViewById(R.id.github);
        auth=FirebaseAuth.getInstance();


        // Set click listener for the SignIn button

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Capture user input from EditText fields
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                // Perform input validation (e.g., checking for empty fields or password match)
                if (email.isEmpty() || password.isEmpty() ) {
                    // Display a toast message for empty fields
                    Toast.makeText(Login.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else {

                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Login success
                                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this, MainActivity.class));
                                        finish();
                                    } else {
                                        // Login failed
//                                        Toast.makeText(Login.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        if (task.getException().getMessage().contains("password is invalid") ||
                                                task.getException().getMessage().contains("There is no user record corresponding to this identifier")) {
                                            // Display a specific error message for wrong email or password
                                            Toast.makeText(Login.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });

                    // Signup successful, perform further actions (e.g., send data to server)
                    // You can add your signin logic here
                    // For demonstration, we'll show a toast message indicating success
//                    Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//                    Intent intent =new Intent(Login.this, MainActivity.class);
//                    startActivity(intent);

                }
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,forgotPassword.class));
//                Intent intent = new Intent(Login.this,forgotPassword.class);
//                startActivity(intent);
            }
        });
        //this is top signup button
        BtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i =new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.facebook.com"));
                        startActivity(i);
            }
        });
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.twitter.com"));
                startActivity(i);
            }
        });
        instaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.instagram.com"));
                startActivity(i);
            }
        });
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.github.com"));
                startActivity(i);
            }
        });

    }
}
