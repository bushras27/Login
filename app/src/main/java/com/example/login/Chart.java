package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

 class ChartActivity extends AppCompatActivity {

    private TextView loadDataButton, expenseTextView, incomeTextView;
    private ProgressBar progressBar;
    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart); // Reference to your XML layout

        // Initialize Views
        loadDataButton = findViewById(R.id.loadDataButton);
        expenseTextView = findViewById(R.id.expense);
        incomeTextView = findViewById(R.id.income);
        progressBar = findViewById(R.id.progressBar); // Assuming you have a progress bar with ID progressBar
        barChart = findViewById(R.id.barChart); // Initialize the BarChart

        // Hide ProgressBar initially
        progressBar.setVisibility(View.INVISIBLE);

        // Load Data when the button is clicked
        loadDataButton.setOnClickListener(v -> loadData());
    }

    private void loadData() {
        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Fetch data from the database (replace with your actual database logic)
        ArrayList<BarEntry> incomeEntries = getIncomeDataFromDatabase();
        ArrayList<BarEntry> expenseEntries = getExpenseDataFromDatabase();

        if (incomeEntries.isEmpty() || expenseEntries.isEmpty()) {
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        // Create BarDataSets for income and expenses
        BarDataSet incomeDataSet = new BarDataSet(incomeEntries, "Income");
        incomeDataSet.setColor(getResources().getColor(R.color.green)); // Set the color for income

        BarDataSet expenseDataSet = new BarDataSet(expenseEntries, "Expense");
        expenseDataSet.setColor(getResources().getColor(R.color.red)); // Set the color for expenses

        // Create BarData from the data sets
        BarData barData = new BarData(incomeDataSet, expenseDataSet);

        // Set data to the chart
        barChart.setData(barData);

        // Set up X-Axis labels (months)
        ArrayList<String> months = getMonths();
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months);
        barChart.getXAxis().setValueFormatter(formatter);

        // Customize chart appearance
        barChart.getDescription().setEnabled(false); // Remove description
        barChart.getAxisLeft().setAxisMinimum(0f); // Start Y-axis at 0
        barChart.getAxisRight().setEnabled(false); // Disable right axis
        barChart.getLegend().setEnabled(true); // Enable legend

        // Hide ProgressBar after loading data
        progressBar.setVisibility(View.INVISIBLE);
    }

    private ArrayList<BarEntry> getIncomeDataFromDatabase() {
        ArrayList<BarEntry> incomeEntries = new ArrayList<>();
        // Dummy data (replace with actual data from your database)
        for (int i = 0; i < 12; i++) {
            incomeEntries.add(new BarEntry(i, (float) (Math.random() * 1000 + 1000))); // Example random income data
        }
        return incomeEntries;
    }

    private ArrayList<BarEntry> getExpenseDataFromDatabase() {
        ArrayList<BarEntry> expenseEntries = new ArrayList<>();
        // Dummy data (replace with actual data from your database)
        for (int i = 0; i < 12; i++) {
            expenseEntries.add(new BarEntry(i, (float) (Math.random() * 500 + 100))); // Example random expense data
        }
        return expenseEntries;
    }

    private ArrayList<String> getMonths() {
        ArrayList<String> months = new ArrayList<>();
        months.add("Jan");
        months.add("Feb");
        months.add("Mar");
        months.add("Apr");
        months.add("May");
        months.add("Jun");
        months.add("Jul");
        months.add("Aug");
        months.add("Sep");
        months.add("Oct");
        months.add("Nov");
        months.add("Dec");
        return months;
    }
}
