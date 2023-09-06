package com.example.eduweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    // Declare your views
    private Button btnSignup;
    private Button btnLogin;
    private TextView textView;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;
    private Button signupButton;
    private Button loginButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize your views by finding them using their IDs
        // ... (code for initializing views)

        btnSignup = findViewById(R.id.BtnSignup);
        btnLogin = findViewById(R.id.BtnLogin);
        textView = findViewById(R.id.textView);
        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        repeatPasswordEditText = findViewById(R.id.repeatpassword);
        signupButton = findViewById(R.id.btnSignup);
        loginButton = findViewById(R.id.btnLogin);

        auth = FirebaseAuth.getInstance();

        // Set click listener for the Signup button
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Capture user input from EditText fields
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String repeatPassword = repeatPasswordEditText.getText().toString();

                // Perform input validation (e.g., checking for empty fields or password match)
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                    // Display a toast message for empty fields
                    Toast.makeText(MainActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(repeatPassword)) {
                    // Display a toast message for password mismatch
                    Toast.makeText(MainActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Login.class));
                            }else{
                                Toast.makeText(MainActivity.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    // Signup successful, perform further actions (e.g., send data to server)
                    // You can add your signup logic here
//                    // For demonstration, we'll show a toast message indicating success
//                    Toast.makeText(MainActivity.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
//                    Intent intent =new Intent(MainActivity.this, Login.class);
//                    startActivity(intent);

                }
            }
        });

        // Set click listener for the Login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the Login button click event (add your login logic here)
                // For demonstration, we'll show a toast message
//                Toast.makeText(MainActivity.this, "Login Clicked", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        //this login btn is beside the textview (you have already account)
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        // You can continue to set up and manipulate your views as needed
    }
}
