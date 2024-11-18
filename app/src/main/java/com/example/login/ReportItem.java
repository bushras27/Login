package com.example.login;

public class ReportItem {
    private String title;
    private String value;

    // Constructor
    public ReportItem(String title, String value) {
        this.title = title;
        this.value = value;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }
}
