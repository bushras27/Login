package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private EditText username, password, rpassword, email;
    private Button btnRegister, btnReset;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Make sure your layout file name is correct

        // Initialize the views
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rpassword = findViewById(R.id.rpassword);
        email = findViewById(R.id.email);
        btnRegister = findViewById(R.id.btnregister);
        btnReset = findViewById(R.id.btnreset);
        login = findViewById(R.id.login);  // Initialize the login TextView

        // Set up listeners
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate the inputs before proceeding
                validateInputs();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();  // Reset the input fields
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the login activity
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void validateInputs() {
        String usernameInput = username.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();
        String rpasswordInput = rpassword.getText().toString().trim();
        String emailInput = email.getText().toString().trim();

        // Validate username
        if (usernameInput.isEmpty()) {
            username.setError("Username is required");
            username.requestFocus();
            return;
        }

        // Validate password
        if (passwordInput.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        // Check if passwords match
        if (!passwordInput.equals(rpasswordInput)) {
            rpassword.setError("Passwords do not match");
            rpassword.requestFocus();
            return;
        }

        // Validate email
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        // If validation passes, proceed with registration
        showSuccessMessageAndNavigate();
    }

    private void showSuccessMessageAndNavigate() {
        // Show a success message
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();

        // Navigate to MainActivity after a delay
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
        }, 2000); // 2-second delay
    }

    private void resetFields() {
        username.setText("");
        password.setText("");
        rpassword.setText("");
        email.setText("");
    }
}
