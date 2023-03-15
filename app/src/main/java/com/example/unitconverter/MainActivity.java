package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.AdapterView;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter_input;
    ArrayAdapter<String> adapter_output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText userInput = findViewById(R.id.editTextNumber);
        TextView userOutput = findViewById(R.id.textView4);
        Button button = findViewById(R.id.button);
        Spinner spin_input = findViewById(R.id.spinner);
        Spinner spin_output = findViewById(R.id.spinner2);

        // Spinner (categories)
        Spinner spin_categories = findViewById(R.id.spinner3);
        List<String> categories = new ArrayList<String>();
        categories.add("Length");
        categories.add("Weight");
        categories.add("Temperature");
        // Adapter for Spinner (categories)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        spin_categories.setAdapter((adapter));

        // Length
        List<String> units_input_l = new ArrayList<String>();
        units_input_l.add("Inch");
        units_input_l.add("Foot");
        units_input_l.add("Yard");
        units_input_l.add("Mile");
        List<String> units_output_l = new ArrayList<String>();
        units_output_l.add("Centimeter");
        units_output_l.add("Meter");
        units_output_l.add("Kilometer");
        // Weight
        List<String> units_input_w = new ArrayList<String>();
        units_input_w.add("Pound");
        units_input_w.add("Ounce");
        units_input_w.add("Ton");
        List<String> units_output_w = new ArrayList<String>();
        units_output_w.add("Kilogram");
        units_output_w.add("Gram");
        // Temperature
        List<String> units_t = new ArrayList<String>();
        units_t.add("Celsius");
        units_t.add("Fahrenheit");
        units_t.add("Kelvin");

        spin_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0)
                {
                    adapter_input = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, units_input_l);
                    adapter_output = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, units_output_l);
                }
                else if (i == 1)
                {
                    adapter_input = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, units_input_w);
                    adapter_output = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, units_output_w);
                }
                else if (i == 2)
                {
                    adapter_input = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, units_t);
                    adapter_output = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, units_t);
                }
                else
                    Toast.makeText(getApplicationContext(), "INVALID", Toast.LENGTH_LONG).show();
                adapter_input.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
                spin_input.setAdapter((adapter_input));
                adapter_output.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
                spin_output.setAdapter((adapter_output));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double amount = Double.parseDouble(userInput.getText().toString());
                String result = Converter(spin_input.getSelectedItem().toString(), spin_output.getSelectedItem().toString(), amount);
                userOutput.setText("Answer: " + result + " " + spin_output.getSelectedItem().toString().toLowerCase());
            }
        });
    }
    public String Converter(String input, String output, Double amount) {
        Double result;
        // Length
        if (input == "Inch" && output == "Centimeter")
            result = amount * 2.54;
        else if (input == "Inch" && output == "Meter")
            result = amount * 0.0254;
        else if (input == "Inch" && output == "Kilometer")
            result = amount * 0.0000254;
        else if (input == "Foot" && output == "Centimeter")
            result = amount * 30.48;
        else if (input == "Foot" && output == "Meter")
            result = amount * 0.3048;
        else if (input == "Foot" && output == "Kilometer")
            result = amount * 0.0003048;
        else if (input == "Yard" && output == "Centimeter")
            result = amount * 91.44;
        else if (input == "Yard" && output == "Meter")
            result = amount * 0.9144;
        else if (input == "Yard" && output == "Kilometer")
            result = amount * 0.0009144;
        else if (input == "Mile" && output == "Centimeter")
            result = amount * 160934.4;
        else if (input == "Mile" && output == "Meter")
            result = amount * 1609.344;
        else if (input == "Mile" && output == "Kilometer")
            result = amount * 160934.4;
        // Weight
        else if (input == "Pound" && output == "Kilogram")
            result = amount * 0.453592;
        else if (input == "Pound" && output == "Gram")
            result = amount * 453.592;
        else if (input == "Ounce" && output == "Kilogram")
            result = amount * 0.02835;
        else if (input == "Ounce" && output == "Gram")
            result = amount * 28.3495;
        else if (input == "Ton" && output == "Kilogram")
            result = amount *  907.185;
        else if (input == "Ton" && output == "Gram")
            result = amount *  907185;
        // Temperature
        else if (input == "Celsius" && output == "Fahrenheit")
            result = (amount *  1.8) + 32;
        else if (input == "Fahrenheit" && output == "Celsius")
            result = (amount - 32) / 1.8;
        else if (input == "Celsius" && output == "Kelvin")
            result = amount + 273.15;
        else if (input == "Kelvin" && output == "Celsius")
            result = amount - 273.15;
        else if (input == "Fahrenheit" && output == "Kelvin")
            result = (amount + 459.67) * 5/9;
        else if (input == "Kelvin" && output == "Fahrenheit")
            result = 1.8 * (amount - 273) + 32;
        else
        {
            result = 0.0;
            Toast.makeText(this, "INVALID!", Toast.LENGTH_SHORT).show();
        }
        return result.toString();
    }
}