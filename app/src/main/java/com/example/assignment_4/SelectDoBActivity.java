package com.example.assignment_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SelectDoBActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Button submitButton, cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_do_bactivity);
        datePicker = findViewById(R.id.calendarView);
        submitButton = findViewById(R.id.SubmitDOB);
        cancelButton = findViewById(R.id.CancelbuttonDOB);

        // Set max date to 18 years from today
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        datePicker.setMaxDate(calendar.getTimeInMillis());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1; // Month is zero-based
                int year = datePicker.getYear();
                String dob = month + "/" + day + "/" + year;
                Intent intent = new Intent();
                intent.putExtra("dob", dob);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }
}