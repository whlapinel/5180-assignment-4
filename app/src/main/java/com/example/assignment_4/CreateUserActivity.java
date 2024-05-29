package com.example.assignment_4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class CreateUserActivity extends AppCompatActivity {
    private EditText nameEditText, emailEditText, ageEditText;
    private TextView countryTextView, dobTextView;
    private Button selectCountryButton, selectDobButton, submitButton;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_user);

        // Initialize views
        nameEditText = findViewById(R.id.editTextText);
        emailEditText = findViewById(R.id.EditTextEmailAddress);
        ageEditText = findViewById(R.id.editAgeTextNumber);
        countryTextView = findViewById(R.id.CountryValuetextView7);
        dobTextView = findViewById(R.id.DOBValuetextView7);
        selectCountryButton = findViewById(R.id.CountryButton);
        selectDobButton = findViewById(R.id.DOBButton);
        submitButton = findViewById(R.id.Submit);

        // Set onClickListener for selectCountryButton
        selectCountryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show single-choice AlertDialog to select country
                // Array of countries
                final String[] countries = Data.countries;

                AlertDialog.Builder builder = new AlertDialog.Builder(CreateUserActivity.this);
                builder.setTitle("Choose a Country");
                builder.setSingleChoiceItems(countries, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Set the selected country to the TextView
                        countryTextView.setText(countries[which]);
                        dialog.dismiss(); // Close the dialog
                    }
                });

                // Positive button (OK)
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Close the dialog
                    }
                });

                // Negative button (Cancel)
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Close the dialog
                    }
                });

                // Create and show the AlertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }



        });

        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        String DOB = data.getStringExtra("dob");
                        if (DOB != null) {
                            dobTextView.setText(DOB);
                            //name.setText(user.name);

                        }
                    }
                }
            }
        });
        // Set onClickListener for selectDobButton
        selectDobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivityForResult(new Intent(CreateUserActivity.this, SelectDoBActivity.class), Activity.REQUEST_SELECT_DOB);
                Intent intent = new Intent(CreateUserActivity.this, SelectDoBActivity.class);
                //intent.putExtra("user", user);
                startForResult.launch(intent);
            }
        });

        // Set onClickListener for submitButton
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String country = countryTextView.getText().toString();
                String dob = dobTextView.getText().toString();

                if (name.isEmpty() || email.isEmpty() || age.isEmpty()) {
                    Toast.makeText(CreateUserActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (country.equals("N/A") || dob.equals("N/A")) {
                    Toast.makeText(CreateUserActivity.this, "Country and DoB are required", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(name, email, Integer.parseInt(age), country, dob);
                    Intent intent = new Intent(CreateUserActivity.this, ProfileActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}