package com.example.login; // Change to your actual package name

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class calculator extends AppCompatActivity {

    private TextView resultField;
    private StringBuilder currentInput = new StringBuilder();
    private String operator;
    private double firstOperand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator); // Ensure this matches your layout file

        resultField = findViewById(R.id.result_field);

        // Number buttons
        findViewById(R.id.button0).setOnClickListener(this::onNumberClick);
        findViewById(R.id.button1).setOnClickListener(this::onNumberClick);
        findViewById(R.id.button2).setOnClickListener(this::onNumberClick);
        findViewById(R.id.button3).setOnClickListener(this::onNumberClick);
        findViewById(R.id.button4).setOnClickListener(this::onNumberClick);
        findViewById(R.id.button5).setOnClickListener(this::onNumberClick);
        findViewById(R.id.button6).setOnClickListener(this::onNumberClick);
        findViewById(R.id.button7).setOnClickListener(this::onNumberClick);
        findViewById(R.id.button8).setOnClickListener(this::onNumberClick);
        findViewById(R.id.button9).setOnClickListener(this::onNumberClick);

        // Operator buttons
        findViewById(R.id.button_add).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.button_subtract).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.button_multiply).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.button_divide).setOnClickListener(this::onOperatorClick);

        // Equals button
        findViewById(R.id.button_equals).setOnClickListener(this::onEqualsClick);

        // Clear button
        findViewById(R.id.button_clear).setOnClickListener(v -> clear());
    }

    private void onNumberClick(View view) {
        Button button = (Button) view;
        currentInput.append(button.getText());
        resultField.setText(currentInput.toString());
    }

    private void onOperatorClick(View view) {
        if (currentInput.length() > 0) {
            firstOperand = Double.parseDouble(currentInput.toString());
            operator = ((Button) view).getText().toString();
            currentInput.setLength(0); // Clear input for the next number
        }
    }

    private void onEqualsClick(View view) {
        if (currentInput.length() > 0 && operator != null) {
            double secondOperand = Double.parseDouble(currentInput.toString());
            double result = performOperation(firstOperand, secondOperand, operator);
            resultField.setText(String.valueOf(result));
            currentInput.setLength(0); // Clear input after calculation
        }
    }

    private double performOperation(double first, double second, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                if (second == 0) {
                    resultField.setText("Error");
                    return 0;
                }
                return first / second;
            default:
                return 0;
        }
    }


    private void clear() {
        currentInput.setLength(0);
        resultField.setText("");
        operator = null;
    }
}
