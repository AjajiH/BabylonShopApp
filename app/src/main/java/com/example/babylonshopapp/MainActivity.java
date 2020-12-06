package com.example.babylonshopapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private Button HomeLoginBtn, HomeCreateAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeLoginBtn = (Button)findViewById(R.id.HomeLoginBtn);
        HomeCreateAccountBtn = (Button)findViewById(R.id.HomeCreateAccountBtn);

//        GetAllUsers();

        HomeLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forwardLoginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                forwardLoginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(forwardLoginIntent);
            }
        });

        HomeCreateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


    }

}