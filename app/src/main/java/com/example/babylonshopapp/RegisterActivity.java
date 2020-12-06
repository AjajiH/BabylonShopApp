package com.example.babylonshopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    EditText firstName, lastName, email, password, repassword, phoneNumber;
    Button registerBtn;

    //ProgressDialog loadingBar;a

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        registerBtn = (Button) findViewById(R.id.registerBtn);

        //loadingBar = new ProgressDialog(this);
        RegisterUser();
    }

    public void RegisterUser() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String RePassword = repassword.getText().toString();
                String FirstName = firstName.getText().toString();
                String LastName = lastName.getText().toString();
                String PhoneNumber = phoneNumber.getText().toString();

                if (Email.equals("") || Password.equals("") || RePassword.equals("") || FirstName.equals("") || LastName.equals("") || PhoneNumber.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all fields!", Toast.LENGTH_LONG).show();
                else {
                    if (Password.equals(RePassword)) {
                        Boolean checkUser = databaseHelper.validateUser(Email);
                        if (checkUser == false) {
                            Boolean insertData = databaseHelper.addUser(Email, Password, FirstName, LastName, PhoneNumber);
                            if (insertData == true) {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_LONG).show();
                                Intent goToLogin = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(goToLogin);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "User Already Exists! Please go to Login", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Passwords do not match!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}