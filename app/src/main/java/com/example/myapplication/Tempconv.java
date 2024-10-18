package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tempconv extends AppCompatActivity {
    Spinner spinnerFrom, spinnerTo;
    EditText inputNum;
    TextView resultText;
    Button convertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempconv);

        // เชื่อมโยง View กับ Java
        spinnerFrom = findViewById(R.id.spinner1);
        spinnerTo = findViewById(R.id.spinner2);
        inputNum = findViewById(R.id.getnum);
        resultText = findViewById(R.id.result);
        convertBtn = findViewById(R.id.converbtn);

        // Set up spinner options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.temp, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        // Set OnClickListener for convert button
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromUnit = spinnerFrom.getSelectedItem().toString();
                String toUnit = spinnerTo.getSelectedItem().toString();
                String input = inputNum.getText().toString();

                if (input.isEmpty()) {
                    Toast.makeText(Tempconv.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                double inputValue = Double.parseDouble(input);
                double resultValue = convertTemperature(fromUnit, toUnit, inputValue);

                resultText.setText("Result: " + resultValue);
            }
        });
    }

    private double convertTemperature(String fromUnit, String toUnit, double value) {
        // Convert from 'fromUnit' to Celsius first
        double tempInCelsius;

        switch (fromUnit) {
            case "Celsius":
                tempInCelsius = value;
                break;
            case "Fahrenheit":
                tempInCelsius = (value - 32) * 5 / 9;
                break;
            case "Kelvin":
                tempInCelsius = value - 273.15;
                break;
            default:
                return 0;
        }

        // Convert from Celsius to 'toUnit'
        switch (toUnit) {
            case "Celsius":
                return tempInCelsius;
            case "Fahrenheit":
                return (tempInCelsius * 9 / 5) + 32;
            case "Kelvin":
                return tempInCelsius + 273.15;
            default:
                return 0;
        }
    }
}
