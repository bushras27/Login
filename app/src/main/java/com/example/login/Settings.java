package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.biometric.BiometricManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.File;
import java.util.concurrent.Executor;

public class Settings extends AppCompatActivity {

    private TextView btnPersonalInfo, btnalerts, btnFingerprint, btnDataSharing, btnDeleteAll, btnAboutUs, btnClearCache, btnFeedback;
    private Switch exportDataSwitch, soundSwitch;
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);




        // Initialize the views
        btnPersonalInfo = findViewById(R.id.btn_personal_info);
        btnalerts = findViewById(R.id.btn_alerts);
        btnFingerprint = findViewById(R.id.btn_set_fingerprint);
        btnDataSharing = findViewById(R.id.btn_data_sharing);
        btnDeleteAll = findViewById(R.id.btn_delete_all);
        btnAboutUs = findViewById(R.id.btn_about_us);
        btnClearCache = findViewById(R.id.btn_clear_cache);
        btnFeedback = findViewById(R.id.btn_feedback);
        exportDataSwitch = findViewById(R.id.export_data_switch);
        soundSwitch = findViewById(R.id.sound_switch);

        // Set OnClickListeners
        btnPersonalInfo.setOnClickListener(v -> openPersonalInfo());
        btnalerts.setOnClickListener(v -> openChangePassword());
        btnFingerprint.setOnClickListener(v -> openFingerprintSettings());
        btnDataSharing.setOnClickListener(v -> openDataSharing());
        btnDeleteAll.setOnClickListener(v -> openDeleteAll());
        btnAboutUs.setOnClickListener(v -> openAboutUs());
        btnClearCache.setOnClickListener(v -> openClearCache());
        btnFeedback.setOnClickListener(v -> openFeedback());

        // Handle switch state changes
        exportDataSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> handleExportData(isChecked));
        soundSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> handleSound(isChecked));

        // Initialize the "Personal Information" TextView
        btnPersonalInfo = findViewById(R.id.btn_personal_info);

        // Set OnClickListener for the TextView
        btnPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the dialog with Sign In and Cancel options
                showSignInDialog();
            }
        });


        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFeedbackDialog();
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });

        btnClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAppCache();
            }
        });

        btnalerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the dialog with multiple options for reminders
                showReminderOptionsDialog();
            }
        });

    }



    private void openPersonalInfo() {
        // Start a new activity or perform any action

    }

    private void openChangePassword() {
        // Navigate to Change Password screen
    }

    private void openFingerprintSettings() {
        // Navigate to Fingerprint or Face lock settings
    }

    private void openDataSharing() {
        // Navigate to Data Sharing settings
    }

    private void openDeleteAll() {
        // Navigate to Delete All screen
    }

    private void openAboutUs() {
        // Open About Us page
    }

    private void openClearCache() {
        // Clear app cache
    }

    private void openFeedback() {
        // Open feedback screen
    }

    private void handleExportData(boolean isChecked) {
        // Handle export data action
    }

    private void handleSound(boolean isChecked) {
        // Handle sound settings
    }

    private void showFeedbackDialog() {
        // Create an AlertDialog Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
        builder.setTitle("Send Feedback");

        // Create the layout for the dialog (a simple EditText for feedback input)
        final EditText feedbackInput = new EditText(Settings.this);
        feedbackInput.setHint("Enter your feedback here...");

        // Set the layout for the dialog
        builder.setView(feedbackInput);

        // Add a Submit button
        builder.setPositiveButton("Submit", (dialog, which) -> {
            // Get the feedback entered by the user
            String feedback = feedbackInput.getText().toString().trim();

            // If feedback is empty, show a Toast message
            if (feedback.isEmpty()) {
                Toast.makeText(Settings.this, "Please enter some feedback.", Toast.LENGTH_SHORT).show();
            } else {
                // Show a success message (can be changed based on your requirements)
                Toast.makeText(Settings.this, "Feedback submitted successfully!", Toast.LENGTH_SHORT).show();

                // You can also handle the feedback submission logic here (e.g., sending to a server)
            }
        });

        // Add a Cancel button
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        // Create and show the dialog
        builder.create().show();
    }

        // Method to show a confirmation dialog for deleting all data
        private void showDeleteConfirmationDialog() {
            // Create an AlertDialog Builder
            AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
            builder.setTitle("Are you sure?");
            builder.setMessage("This will delete all your data. This action cannot be undone.");

            // Add a "Delete" button
            builder.setPositiveButton("Delete", (dialog, which) -> {
                // Call method to delete all user data
                deleteAllUserData();
            });

            // Add a "Cancel" button
            builder.setNegativeButton("Cancel", (dialog, which) -> {
                // Dismiss the dialog if "Cancel" is pressed
                dialog.dismiss();
            });

            // Show the confirmation dialog
            builder.create().show();
        }

        // Method to delete all user data
        private void deleteAllUserData() {
            // Example: Clear SharedPreferences (if you're storing data in SharedPreferences)
            SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear(); // Clears all data stored in the shared preferences
            editor.apply(); // Apply the changes

            // Example: Clear other data like database entries or files (optional)
            // If you're using a database, you can delete the records using SQL statements here.

            // Show a success message
            Toast.makeText(Settings.this, "All data has been deleted.", Toast.LENGTH_SHORT).show();
        }

    private void clearAppCache() {
        try {
            // Get the cache directory
            File cacheDirectory = getCacheDir();

            // Recursively delete all files inside the cache directory
            deleteFilesInDir(cacheDirectory);

            // Show a confirmation message
            Toast.makeText(Settings.this, "Cache has been cleared.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(Settings.this, "Failed to clear cache.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to recursively delete files in a directory
    private void deleteFilesInDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null) {
                for (String child : children) {
                    deleteFilesInDir(new File(dir, child));
                }
            }
        }
        dir.delete(); // Delete the file/directory
    }



// Method to show the dialog with Sign In and Cancel buttons
private void showSignInDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Sign In")
            .setMessage("Sign in with your Google account to access your personal information.")
            .setPositiveButton("Sign In", (dialog, which) -> signInWithGoogle())  // Sign In action
            .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())  // Cancel action
            .create()
            .show();
}

// Method to sign in with Google
private void signInWithGoogle() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
}

// Handling the result from Google Sign-In
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...)
    if (requestCode == RC_SIGN_IN) {
        GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Google Sign In was successful, authenticate with Firebase
                        GoogleSignInAccount account = task.getResult();
                    } else {
                        // Google Sign In failed, handle failure
                        Toast.makeText(Settings.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

    private void showReminderOptionsDialog() {
        // Create an array of options for reminders
        final String[] reminderOptions = {
                "Bill Payment Reminder",
                "Low Balance Alert",
                "Upcoming Subscription Alert"
        };

        // Create an AlertDialog to display the options
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set Reminders")
                .setItems(reminderOptions, (dialog, which) -> {
                    // Handle the selected option
                    switch (which) {
                        case 0: // Bill Payment Reminder
                            setBillPaymentReminder();
                            break;
                        case 1: // Low Balance Alert
                            setLowBalanceAlert();
                            break;
                        case 2: // Upcoming Subscription Alert
                            setSubscriptionAlert();
                            break;
                        default:
                            break;
                    }
                })
                .create()
                .show();
    }

    // Method to set Bill Payment Reminder
    private void setBillPaymentReminder() {
        // Show a Toast or open a new screen to set the bill payment reminder
        Toast.makeText(Settings.this, "Setting Bill Payment Reminder", Toast.LENGTH_SHORT).show();
        // You can use DatePickerDialog or TimePickerDialog to allow user to set a date/time for the reminder
    }

    // Method to set Low Balance Alert
    private void setLowBalanceAlert() {
        // Show a Toast or open a new screen to set the low balance alert
        Toast.makeText(Settings.this, "Setting Low Balance Alert", Toast.LENGTH_SHORT).show();
        // You can allow the user to specify the threshold for low balance
    }

    // Method to set Upcoming Subscription Alert
    private void setSubscriptionAlert() {
        // Show a Toast or open a new screen to set the subscription alert
        Toast.makeText(Settings.this, "Setting Upcoming Subscription Alert", Toast.LENGTH_SHORT).show();
        // You can allow the user to specify the date for the subscription reminder
    }



}
