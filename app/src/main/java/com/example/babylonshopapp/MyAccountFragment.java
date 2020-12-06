package com.example.babylonshopapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MyAccountFragment extends Fragment {

    DatabaseHelper databaseHelper;
    Button editProfileBtn;

    public MyAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        databaseHelper = new DatabaseHelper(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_my_account, container, false);
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView username = getActivity().findViewById(R.id.username);
        TextView email = getActivity().findViewById(R.id.et_email);
        TextView firstName = getActivity().findViewById(R.id.et_first_name);
        TextView lastName = getActivity().findViewById(R.id.et_last_name);
        TextView phone = getActivity().findViewById(R.id.et_phone);

        Intent intent = getActivity().getIntent();
        String Username = intent.getStringExtra("USERNAME");

        Cursor result = databaseHelper.retrieveAllData(Username);
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
        username.setText(stringBuilder0);
        email.setText(stringBuilder0);
        firstName.setText(stringBuilder2);
        lastName.setText(stringBuilder3);
        phone.setText(stringBuilder4);

        String getEmail = username.getText().toString();

        editProfileBtn = getActivity().findViewById(R.id.editUserBtn);
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), EditProfileActivity.class);
                intent.putExtra("EMAIL", getEmail);
                startActivity(intent);
            }
        });
    }
}

