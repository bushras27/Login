package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    private List<ReportItem> reportList;

    // Constructor
    public ReportAdapter(List<ReportItem> reportList) {
        this.reportList = reportList;
    }

    // ViewHolder to hold the views
    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView value;

        public ReportViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            value = itemView.findViewById(R.id.itemValue);
        }
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_report, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        ReportItem reportItem = reportList.get(position);
        holder.title.setText(reportItem.getTitle());
        holder.value.setText(reportItem.getValue());
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }
}
