package com.example.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView forgotPasswordTextView;
    private TextView signUpTextView;
    private ImageView fingerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure your layout file name is correct

        // Initialize views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.btnlogin);
        forgotPasswordTextView = findViewById(R.id.forgotpass);
        signUpTextView = findViewById(R.id.signin);

        fingerImageView = findViewById(R.id.finger);

        // Set click listener for the fingerprint ImageView
        fingerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start biometric authentication when the fingerprint image is clicked
                startBiometricAuthentication();
            }
        });

        // Set up listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Forget Password Activity
                Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Sign-Up Activity
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }

    // Method to start biometric authentication
    private void startBiometricAuthentication() {
        // Create an executor to run the biometric authentication process on a background thread
        Executor executor = ContextCompat.getMainExecutor(this);

        // Create a BiometricPrompt instance
        BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {

            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                // Handle authentication error (e.g., too many failed attempts)
                Toast.makeText(MainActivity.this, "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // Handle failed authentication (e.g., fingerprint mismatch)
                Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // Handle successful authentication (e.g., unlock app)
                Toast.makeText(MainActivity.this, "Authentication succeeded", Toast.LENGTH_SHORT).show();

                // Optionally, you can navigate to a new activity after successful authentication
                // Intent intent = new Intent(MainActivity.this, HomePage.class);
                // startActivity(intent);
            }
        });

        // Create a BiometricPrompt.PromptInfo object to display authentication prompt
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Fingerprint Authentication")
                .setSubtitle("Use your fingerprint to authenticate")
                .setNegativeButtonText("Cancel")
                .build();

        // Show the biometric prompt
        biometricPrompt.authenticate(promptInfo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // Use BiometricPrompt for Android 9 (Pie) and above
            startBiometricAuthentication();
        } else {
            // Fall back to older fingerprint API for older devices (optional)
            // You can use FingerprintManager for legacy devices
            Toast.makeText(this, "Fingerprint authentication is not supported on this device", Toast.LENGTH_SHORT).show();
        }
    }

    // Handle the login logic
    private void handleLogin() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate input fields
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Here, you can implement authentication logic, such as checking credentials from a database
            // For now, we assume that the login is successful

            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

            // Redirect to HomePage after successful login
            Intent intent = new Intent(MainActivity.this, HomePage.class); // Replace with your HomePage activity
            startActivity(intent);

            // Optionally, you can close the current activity to prevent going back to the login screen:
            finish();
        }
    }


}
