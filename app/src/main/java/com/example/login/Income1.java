package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Income1 extends AppCompatActivity {

    private TextView cancel, add;
    private ImageView setting;
    private TabLayout tabLayout;    private TextView resultField;
    private StringBuilder input = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income1); // Your main layout

        setupClickableItems();
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
                Intent intent = new Intent(Income1.this, Settings.class);
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
                        Intent expenseIntent = new Intent(Income1.this, Add.class);
                        startActivity(expenseIntent);
                        break;

                    case 1: // Income tab

                        break;

                    case 2: // Transfer tab (optional, if needed)
                        // Handle Transfer tab selection here if necessary
                        Intent transferIntent = new Intent(Income1.this, Transfer.class);
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

    private void setupClickableItems() {
        // Set up click listeners for items
        findViewById(R.id.salary).setOnClickListener(v -> openCalculator());
        findViewById(R.id.bonus).setOnClickListener(v -> openCalculator());
        findViewById(R.id.parttime).setOnClickListener(v -> openCalculator());
        findViewById(R.id.other).setOnClickListener(v -> openCalculator());
    }

    private void openCalculator() {
        // Create a new BottomSheetDialog instance
        BottomSheetDialog dialog = new BottomSheetDialog(this);

        // Inflate the layout without attaching it to a parent (set attachToRoot to false)
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_calculator, null, false);

        // Set the content view for the dialog
        dialog.setContentView(dialogView);

        // Optionally show the dialog
        dialog.show();
    }

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

    private void appendToInput(String value) {
        input.append(value);
        updateResult();
    }

    private void clearInput() {
        input.setLength(0);
        updateResult();
    }

    private void updateResult() {
        resultField.setText(input.toString());
    }
}
