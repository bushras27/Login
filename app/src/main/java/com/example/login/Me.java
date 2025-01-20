package com.example.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.Chart;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Me extends AppCompatActivity {

        // Declare views
        private ImageView searchImageView;
        private TextView item1, item2, item3;
        private TextView recordTextView, chartTextView, addTextView, reportTextView, meTextView;

        // Google Sign-In
        private GoogleSignInClient mGoogleSignInClient;
        private static final int RC_SIGN_IN = 9001;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_me); // Your layout XML file

            // Initialize views
            searchImageView = findViewById(R.id.search);
            item1 = findViewById(R.id.item1);
            item2 = findViewById(R.id.item2);
            item3 = findViewById(R.id.item3);
            recordTextView = findViewById(R.id.record);
            chartTextView = findViewById(R.id.chart);
            addTextView = findViewById(R.id.add);
            reportTextView = findViewById(R.id.report);
            meTextView = findViewById(R.id.me);

            // Initialize Google Sign-In Client
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

            // Set listeners
            setUpClickListeners();
        }

        private void setUpClickListeners() {
            // Item 1 - Recommend to Friends
            item1.setOnClickListener(v -> showRecommendationPopup());

            // Item 2 - Rate App
            item2.setOnClickListener(v -> showRateAppPopup());

            // Item 3 - Settings (New Activity)
            item3.setOnClickListener(v -> openSettingsActivity());

            // Google Sign-In ImageView (search)
            searchImageView.setOnClickListener(v -> openSignInWindow());

            // Grid Layout Items
            recordTextView.setOnClickListener(v -> showToast("Record clicked"));
            chartTextView.setOnClickListener(v -> showToast("Charts clicked"));
            addTextView.setOnClickListener(v -> showToast("Add clicked"));
            reportTextView.setOnClickListener(v -> showToast("Reports clicked"));
            meTextView.setOnClickListener(v -> showToast("Me clicked"));


            // Example: Set click listeners for the bottom grid items
            recordTextView.setOnClickListener(v-> {
                Intent intent = new Intent(Me.this, Record.class);
                startActivity(intent);
            });

            chartTextView.setOnClickListener(v -> {
                Intent intent = new Intent(Me.this, Chart.class);
                startActivity(intent);
            });

            addTextView.setOnClickListener(v -> {
                Intent intent = new Intent(Me.this, Add.class);
                startActivity(intent);
            });

            reportTextView.setOnClickListener(v -> {
                Intent intent = new Intent(Me.this, Report.class);
                startActivity(intent);
            });

            meTextView.setOnClickListener(v -> {
                Intent intent = new Intent(Me.this, Me.class);
                startActivity(intent);
            });
        }

        private void showRecommendationPopup() {
            // Create a simple popup dialog for "Recommend to Friends"
            new AlertDialog.Builder(this)
                    .setTitle("Recommend to Friends")
                    .setMessage("Do you want to recommend this app to your friends?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // Implement your recommendation logic (e.g., share via intent)
                        showToast("App recommended to friends!");
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        private void showRateAppPopup() {
            // Create a simple popup for "Rate Us"
            new AlertDialog.Builder(this)
                    .setTitle("Rate Us")
                    .setMessage("Would you like to rate our app?")
                    .setPositiveButton("Rate Now", (dialog, which) -> {
                        // Redirect user to the Play Store
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=" + getPackageName()));
                        startActivity(intent);
                    })
                    .setNegativeButton("Later", null)
                    .show();
        }

        private void openSettingsActivity() {
            // Navigate to Settings Activity
            Intent intent = new Intent(Me.this, Settings.class);
            startActivity(intent);
        }

        private void openSignInWindow() {
            // Start the Google Sign-In process
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }

        // Handle the result from Google Sign-In
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    // Sign in was successful, handle the signed-in account
                    showToast("Signed in as: " + account.getEmail());
                } catch (ApiException e) {
                    // Google Sign-In failed, handle failure
                    showToast("Google Sign-In failed: " + e.getStatusCode());
                }
            }
        }

        private void showToast(String message) {
            Toast.makeText(Me.this, message, Toast.LENGTH_SHORT).show();
        }



}
