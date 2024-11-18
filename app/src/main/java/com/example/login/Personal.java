package com.example.login;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Personal extends AppCompatActivity {

    private EditText editName, editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        // Initialize the EditTexts to capture user details
        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);

        // Optionally: pre-fill the fields with the current details if you have them
        // For example:
        // editName.setText(currentUserName);
        // editEmail.setText(currentUserEmail);
    }

    // Example method to save changes
    private void savePersonalInfo() {
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();

        // Save the data (in SharedPreferences, Database, etc.)
        // Example: SharedPreferences, Firebase, etc.

        Toast.makeText(this, "Personal information updated", Toast.LENGTH_SHORT).show();
    }
}