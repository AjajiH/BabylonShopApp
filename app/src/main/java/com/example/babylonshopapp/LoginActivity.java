package com.example.babylonshopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText emailLogin, passwordLogin;
    Button LoginButton, goToRegistration;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        emailLogin = (EditText) findViewById(R.id.emailLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        LoginButton = (Button) findViewById(R.id.loginButton);

        Login();
    }

    public void Login() {
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = emailLogin.getText().toString();
                String Pass = passwordLogin.getText().toString();

                if (Email.equals("") || Pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all fields!", Toast.LENGTH_LONG).show();
                else {
                    Boolean checkUserPass = databaseHelper.validateUserAndPassword(Email, Pass);
                    if (checkUserPass == true) {
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                        Intent goToHomePageActivity = new Intent(getApplicationContext(), HomeActivity.class);
                        goToHomePageActivity.putExtra("USERNAME", Email);
                        startActivity(goToHomePageActivity);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}