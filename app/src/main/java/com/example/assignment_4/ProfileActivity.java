package com.example.assignment_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class ProfileActivity extends AppCompatActivity {

    private TextView nameTextView, emailTextView, ageTextView, countryTextView, dobTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        nameTextView = findViewById(R.id.NameValue);
        emailTextView = findViewById(R.id.EmailValue);
        ageTextView = findViewById(R.id.AgeValue);
        countryTextView = findViewById(R.id.CountryValuetextView);
        dobTextView = findViewById(R.id.DOBValuetextView);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            User user = (User) intent.getSerializableExtra("user");
            if (user != null) {
                nameTextView.setText(user.getName());
                emailTextView.setText(user.getEmail());
                ageTextView.setText(String.valueOf(user.getAge()));
                countryTextView.setText(user.getCountry());
                dobTextView.setText(user.getDob());
            }
        }

    }
}