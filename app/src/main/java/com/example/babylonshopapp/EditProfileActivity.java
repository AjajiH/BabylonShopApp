package com.example.babylonshopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    TextView editEmail;
    TextView editPass;

    EditText editFirstName;
    EditText editLastName;
    EditText editPhone;

    Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        databaseHelper = new DatabaseHelper(this);

        editFirstName = (EditText) findViewById(R.id.et_first_name);
        editLastName = (EditText) findViewById(R.id.et_last_name);
        editEmail = findViewById(R.id.et_email);
        editPass = findViewById(R.id.et_pass);
        editPhone = findViewById(R.id.et_phone);
        updateBtn = findViewById(R.id.updateBtn);

        loadData();
        UpdateUserProfile();
    }

    private void loadData() {
        Intent intent = getIntent();
        String UserID = intent.getStringExtra("EMAIL");
        Cursor result = databaseHelper.getUser(UserID);
        StringBuilder stringBuilder0 = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        StringBuilder stringBuilder4 = new StringBuilder();

        while (result.moveToNext()) {
            stringBuilder0.append(result.getString(0));
            stringBuilder1.append(result.getString(1));
            stringBuilder2.append(result.getString(2));
            stringBuilder3.append(result.getString(3));
            stringBuilder4.append(result.getString(4));
        }
        editEmail.setText(stringBuilder0);
        editFirstName.setText(stringBuilder2);
        editLastName.setText(stringBuilder3);
        editPass.setText(stringBuilder1);
        editPhone.setText(stringBuilder4);
    }

    public void UpdateUserProfile() {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FirstName = editFirstName.getText().toString();
                String LastName = editLastName.getText().toString();
                String Email = editEmail.getText().toString();
                String Pass = editPass.getText().toString();
                String Phone = editPhone.getText().toString();
                boolean isUpdated = databaseHelper.updateUserProfile(Email, FirstName, LastName, Pass, Phone);

                if (isUpdated == true) {
                    Toast.makeText(EditProfileActivity.this, "Profile Data Updated Successfully", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(EditProfileActivity.this, "Profile Data Not Updated!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}