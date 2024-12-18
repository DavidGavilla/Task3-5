package com.example.practical_tast_42_dgavilla;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput, phoneInput;
    private Spinner phoneSpinner;
    private Button alertButton, dateButton, timeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        emailInput = findViewById(R.id.emailInput);
        phoneInput = findViewById(R.id.phoneInput);
        phoneSpinner = findViewById(R.id.phoneSpinner);
        alertButton = findViewById(R.id.alertButton);
        dateButton = findViewById(R.id.dateButton);
        timeButton = findViewById(R.id.timeButton);

        // Set up spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.phone_labels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        phoneSpinner.setAdapter(adapter);

        // Email Input
        emailInput.setOnEditorActionListener((v, actionId, event) -> {
            String email = emailInput.getText().toString();
            Toast.makeText(this, "Email entered: " + email, Toast.LENGTH_SHORT).show();
            return false;
        });

        // Phone Input
        phoneInput.setOnEditorActionListener((v, actionId, event) -> {
            String phone = phoneInput.getText().toString();
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(android.net.Uri.parse("tel:" + phone));
            startActivity(dialIntent);
            return false;
        });

        // Alert Dialog
        alertButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert Dialog")
                    .setMessage("Do you want to proceed?")
                    .setPositiveButton("OK", (dialog, which) ->
                            Toast.makeText(this, "OK clicked", Toast.LENGTH_SHORT).show())
                    .setNegativeButton("Cancel", (dialog, which) ->
                            Toast.makeText(this, "Cancel clicked", Toast.LENGTH_SHORT).show())
                    .show();
        });

        // Date Picker
        dateButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, month, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        Toast.makeText(this, "Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        // Time Picker
        timeButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {
                        String selectedTime = hourOfDay + ":" + minute;
                        Toast.makeText(this, "Time: " + selectedTime, Toast.LENGTH_SHORT).show();
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true);
            timePickerDialog.show();
        });
    }
}
