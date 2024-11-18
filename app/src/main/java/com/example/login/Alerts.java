package com.example.login;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Alerts extends AppCompatActivity {

    private EditText billAmountInput, remainingBalanceInput;
    private TimePicker timePicker;
    private Button setReminderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts); // The XML layout for AlertsActivity

        // Initialize views
        billAmountInput = findViewById(R.id.billAmountInput);
        remainingBalanceInput = findViewById(R.id.remainingBalanceInput);
        timePicker = findViewById(R.id.timePicker);
        setReminderButton = findViewById(R.id.setReminderButton);

        // Set up click listener for the button
        setReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReminder();
            }
        });
    }

    private void setReminder() {
        // Get user input
        String billAmountStr = billAmountInput.getText().toString();
        String remainingBalanceStr = remainingBalanceInput.getText().toString();
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        // Check if inputs are valid
        if (billAmountStr.isEmpty() || remainingBalanceStr.isEmpty()) {
            Toast.makeText(Alerts.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert strings to doubles
        double billAmount = Double.parseDouble(billAmountStr);
        double remainingBalance = Double.parseDouble(remainingBalanceStr);

        // Set up the time for the reminder
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        // Set up AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(Alerts.this, NotificationReceiver.class);
        intent.putExtra("billAmount", billAmount);
        intent.putExtra("remainingBalance", remainingBalance);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(Alerts.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        Toast.makeText(this, "Reminder set!", Toast.LENGTH_SHORT).show();
    }

    // BroadcastReceiver for handling notifications
    public static class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // Get bill amount and remaining balance from intent
            double billAmount = intent.getDoubleExtra("billAmount", 0);
            double remainingBalance = intent.getDoubleExtra("remainingBalance", 0);

            // Check if the remaining balance is low or expenses are increasing
            String message = "";
            if (remainingBalance <= billAmount * 0.2) {
                message = "Your remaining balance is low! Time to pay your bill!";
            } else if (remainingBalance < billAmount) {
                message = "Your expenses are increasing! Pay soon!";
            } else {
                message = "Reminder to pay your bill.";
            }

            // Create a notification
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            String channelId = "bill_payment_reminder";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, "Bill Payment Reminders", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }

            Notification notification = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notification = new Notification.Builder(context, channelId)
                        .setContentTitle("Bill Payment Reminder")
                        .setContentText(message)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .build();
            }

            notificationManager.notify(0, notification);
        }
    }
}
