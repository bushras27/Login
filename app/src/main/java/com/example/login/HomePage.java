package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomePage extends AppCompatActivity {

    private EditText searchEditText;
    private TabLayout TabLayout;
    private TextView recordTextView, chartTextView, addTextView, reportTextView, meTextView;
    private ImageView calendarImageView, alertImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page); // Make sure the layout file name is correct

        // Initialize views
        searchEditText = findViewById(R.id.search);  // EditText for search bar
        TabLayout = findViewById(R.id.tablayout);      // TabLayout for tabs
        recordTextView = findViewById(R.id.record); // TextView for Record
        chartTextView = findViewById(R.id.chart);   // TextView for Charts
        addTextView = findViewById(R.id.add);       // TextView for Add
        reportTextView = findViewById(R.id.report); // TextView for Reports
        meTextView = findViewById(R.id.me);         // TextView for Me
        calendarImageView = findViewById(R.id.calender);
        alertImageView = findViewById(R.id.alert);// ImageView for calendar

        // Setup event listeners and other logic
        setupListeners();
    }


    private void setupListeners() {
        // Example: Set up a listener for the 'calendar' image view
        calendarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalculator();
            }
        });

        alertImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Alerts.class);
                startActivity(intent);
            }
        });

        // Example: Set up a listener for the 'search' EditText
        searchEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // When EditText gains focus, you can add actions here
                // For example: highlight the EditText, show a hint, or clear existing text
                searchEditText.setHint("Search here...");

                // Optionally, show the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);

            } else {
                // When EditText loses focus, you can add actions here
                searchEditText.setBackgroundColor(Color.WHITE);  // Reset background color when focus is lost


                // Optionally, you can validate the text entered in the EditText
                String input = searchEditText.getText().toString();
                if (input.isEmpty()) {
                    // Show error message if no input was entered
                    searchEditText.setError("Please enter a search term.");
                }
            }
        });


        // Set up TabLayout listener (if you want to handle tab changes)
        TabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Handle when a tab is selected
                switch (tab.getPosition()) {
                    case 0:

                        break;
                    case 1:
                        startActivity(new Intent(HomePage.this, Expense.class));
                        break;
                    case 2:
                        startActivity(new Intent(HomePage.this, Income.class));
                        break;
                    case 3:
                        startActivity(new Intent(HomePage.this, Balance.class));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle when a tab is unselected (optional)
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle when a tab is reselected (optional)
            }
        });

        // Example: Set click listeners for the bottom grid items
        recordTextView.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Record.class);
            startActivity(intent);
        });

        chartTextView.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Chart.class);
            startActivity(intent);
        });

        addTextView.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Add.class);
            startActivity(intent);
        });

        reportTextView.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Report.class);
            startActivity(intent);
        });

        meTextView.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Me.class);
            startActivity(intent);
        });
    }

    private void openCalculator() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_calender, null);
        dialog.setContentView(dialogView);
        dialog.show();  // Don't forget to show the dialog
    }
}
