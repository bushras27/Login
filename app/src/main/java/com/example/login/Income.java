package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Income extends AppCompatActivity {

    private ImageView arrow;
    private Spinner date;
    private TextView total;
    private TextView balance;
    private TextView exb;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income); // Change to your layout file name

        // Initialize UI components
        arrow = findViewById(R.id.arrow);
        date = findViewById(R.id.date);
        total = findViewById(R.id.total);
        balance = findViewById(R.id.bal);
        exb = findViewById(R.id.exb);

        // Set up the Spinner with an example array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dates_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        date.setAdapter(adapter);

        date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDate = parent.getItemAtPosition(position).toString();
                // Handle the selected date
                // Update your TextView or other components based on this selection
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Set the total and balance values (example values)
        total.setText("Total Balance");
        balance.setText("0.00");
        exb.setText("Expense: 0.00  Balance: 0.00");

        // Set up BottomNavigationView (if you have specific actions)
        
    }
}