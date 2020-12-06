package com.example.babylonshopapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    Button submit ;
    EditText fullname , city, postalcode, cardnumber, Edate, cvv;
    DatabaseHelper db;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        TextView total = findViewById(R.id.Total);

        Bundle b = getIntent().getExtras();
        String Total_receivedmessage = b.getString("Total_Price");
        email = b.getString("email");
        total.setText("Total Price is "+Total_receivedmessage + " SAR");

        db = new DatabaseHelper(this);
        fullname = (EditText) findViewById(R.id.editTextTextPersonName);
        city = findViewById(R.id.editTextTextcity);
        postalcode = findViewById(R.id.editTextTextPostalcode);
        cardnumber = findViewById(R.id.editTextcardNumber);
        Edate = findViewById(R.id.editTextdate);
        cvv = findViewById(R.id.editTextCvv);


        submit = findViewById(R.id.ch_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullname.getText().toString().trim().length() == 0 ||
                    city.getText().toString().trim().length() == 0 ||
                    postalcode.getText().toString().trim().length() == 0 ||
                    cardnumber.getText().toString().trim().length() == 0||
                    Edate.getText().toString().trim().length() == 0 ||
                    cvv.getText().toString().trim().length() == 0){

                    Toast.makeText(CheckoutActivity.this, "Please enter all fields!", Toast.LENGTH_LONG).show();
                }else{
                    db.addOrder(email);
                    Toast.makeText(CheckoutActivity.this, "Order Placed", Toast.LENGTH_LONG).show();


                    Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);
                }
            }
        });
    }



}