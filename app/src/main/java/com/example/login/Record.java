package com.example.login;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Record extends AppCompatActivity {

    private RecyclerView recyclerView = findViewById(R.id.reportRecyclerView);
    private ReportAdapter reportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        List<ReportItem> reportData = new ArrayList<>();
        reportData.add(new ReportItem("Item 1", "Value 1"));
        reportData.add(new ReportItem("Item 2", "Value 2"));
        reportData.add(new ReportItem("Item 3", "Value 3"));

        // Set up the adapter
        reportAdapter = new ReportAdapter(reportData);
        recyclerView.setAdapter(reportAdapter);
    }
}