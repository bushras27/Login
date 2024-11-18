package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);

    }public class ForgotPasswordActivity extends AppCompatActivity {

        private EditText enteremail;
        private Button btnResetPassword;
        private TextView tvBackToLogin;
        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forget_password);

            // Initialize FirebaseAuth
            mAuth = FirebaseAuth.getInstance();

            // Find views by ID
            enteremail = findViewById(R.id.enteremail);
            btnResetPassword = findViewById(R.id.btnResetPassword);
            tvBackToLogin = findViewById(R.id.tvBackToLogin);

            // Set up listeners
            btnResetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = enteremail.getText().toString().trim();
                    if (email.isEmpty()) {
                        enteremail.setError("Email is required");
                        enteremail.requestFocus();
                    } else {
                        sendPasswordResetEmail(email);
                    }
                }
            });

            tvBackToLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate back to Login screen
                    startActivity(new Intent(ForgotPasswordActivity.this, MainActivity.class));
                }
            });
        }

        private void sendPasswordResetEmail(String email) {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Inform user of success and navigate back to login screen
                            Toast.makeText(ForgotPasswordActivity.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPasswordActivity.this, MainActivity.class));
                            finish();
                        } else {
                            // Show error message
                            Toast.makeText(ForgotPasswordActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}