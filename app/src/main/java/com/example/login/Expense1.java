package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

public class Expense1 extends AppCompatActivity {

    private TextView cancel, add;
    private ImageView setting;
    private TabLayout tabLayout;
    private TextView resultField;
    private StringBuilder input = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense1); // This is your layout

        setupClickableItems();  // Call the method to set up click listeners

        // Initialize the views
        cancel = findViewById(R.id.cancel);
        add = findViewById(R.id.add);
        setting = findViewById(R.id.setting);
        tabLayout = findViewById(R.id.tablayout);

        // Make the "Cancel" button clickable
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the current activity and return to the previous one
                finish();  // Close the current activity
            }
        });


        // Make the "Setting" image clickable
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the SettingsActivity when the setting icon is clicked
                Intent intent = new Intent(Expense1.this, Settings.class);
                startActivity(intent);
            }
        });

        // Set up the TabLayout listener for tab selection
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Handle the tab selection
                switch (tab.getPosition()) {
                    case 0: // Expense tab
                        // Open the ExpenseActivity when Expense tab is selected
                        break;

                    case 1: // Income tab
                        // Open the IncomeActivity when Income tab is selected
                        Intent incomeIntent = new Intent(Expense1.this, Income1.class); // Replace with your actual IncomeActivity
                        startActivity(incomeIntent);
                        break;

                    case 2: // Transfer tab (optional, if needed)
                        // Handle Transfer tab selection here if necessary
                        Intent transferIntent = new Intent(Expense1.this, Transfer.class); // Replace with your actual IncomeActivity
                        startActivity(transferIntent);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // This method is called when a tab is unselected
                // Handle when the tab is unselected, if needed (usually for UI changes)
                switch (tab.getPosition()) {
                    case 0:
                        // Handle unselecting the "Expense" tab, if needed
                        break;
                    case 1:
                        // Handle unselecting the "Income" tab, if needed
                        break;
                    case 2:
                        // Handle unselecting the "Transfer" tab, if needed
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // This method is called when a tab is re-selected (i.e., clicked again)
                // You can refresh the content or perform any necessary action when the tab is clicked again
                switch (tab.getPosition()) {
                    case 0:
                        // Handle reselecting the "Expense" tab
                        break;
                    case 1:
                        // Handle reselecting the "Income" tab
                        break;
                    case 2:
                        // Handle reselecting the "Transfer" tab
                        break;
                    default:
                        break;
                }
            }
        });
    }

    // Set up click listeners for the categories
    private void setupClickableItems() {
        findViewById(R.id.grocery).setOnClickListener(v -> openCalculator());
        findViewById(R.id.milk).setOnClickListener(v -> openCalculator());
        findViewById(R.id.utility).setOnClickListener(v -> openCalculator());
        findViewById(R.id.crockery).setOnClickListener(v -> openCalculator());
        findViewById(R.id.phone).setOnClickListener(v -> openCalculator());
        findViewById(R.id.edu).setOnClickListener(v -> openCalculator());
    }

    // Open the calculator dialog when any category is clicked
    private void openCalculator() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_calculator, null);
        dialog.setContentView(dialogView);

        resultField = dialogView.findViewById(R.id.result_field);

        // Set up calculator buttons
        setupCalculatorButtons(dialogView);

        dialog.show();
    }

    // Set up the calculator buttons
    private void setupCalculatorButtons(View dialogView) {
        dialogView.findViewById(R.id.button0).setOnClickListener(v -> appendToInput("0"));
        dialogView.findViewById(R.id.button1).setOnClickListener(v -> appendToInput("1"));
        dialogView.findViewById(R.id.button2).setOnClickListener(v -> appendToInput("2"));
        dialogView.findViewById(R.id.button3).setOnClickListener(v -> appendToInput("3"));
        dialogView.findViewById(R.id.button4).setOnClickListener(v -> appendToInput("4"));
        dialogView.findViewById(R.id.button5).setOnClickListener(v -> appendToInput("5"));
        dialogView.findViewById(R.id.button6).setOnClickListener(v -> appendToInput("6"));
        dialogView.findViewById(R.id.button7).setOnClickListener(v -> appendToInput("7"));
        dialogView.findViewById(R.id.button8).setOnClickListener(v -> appendToInput("8"));
        dialogView.findViewById(R.id.button9).setOnClickListener(v -> appendToInput("9"));

        dialogView.findViewById(R.id.button_add).setOnClickListener(v -> appendToInput("+"));
        dialogView.findViewById(R.id.button_subtract).setOnClickListener(v -> appendToInput("-"));
        dialogView.findViewById(R.id.button_multiply).setOnClickListener(v -> appendToInput("*"));
        dialogView.findViewById(R.id.button_divide).setOnClickListener(v -> appendToInput("/"));

        dialogView.findViewById(R.id.button_clear).setOnClickListener(v -> clearInput());
    }

    // Append a value to the input
    private void appendToInput(String value) {
        input.append(value);
        updateResult();
    }

    // Clear the input field
    private void clearInput() {
        input.setLength(0);
        updateResult();
    }

    // Update the result display on the calculator
    private void updateResult() {
        resultField.setText(input.toString());
    }
}
