package com.example.babylonshopapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    public String email;
    DatabaseHelper db = new DatabaseHelper(this);
    Button checkoutbtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        email = i.getStringExtra("email");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();



    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_category:
                            selectedFragment = new CategoryFragment();
                            break;
                        case R.id.nav_myaccount:
                            selectedFragment = new MyAccountFragment();
                            break;
                        case R.id.nav_cart:
                            selectedFragment = new CartFragment();
                            break;
                        case R.id.nav_history:
                            selectedFragment = new HistoryFragment(email);
                            createHistoryView();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    public void createHistoryView(){
        db = new DatabaseHelper(this);
        Cursor res = db.getOrders(email);
        int d = 2;
        int id = 0;
        if(res.getCount() > 0){
            res.moveToNext();
            TextView orderNum = findViewById(R.id.order1);
            TextView date = findViewById(R.id.date1);
            date.setText(res.getString(d));
            orderNum.setText("Order #"+res.getInt(id));
        } else {
            CardView cont = findViewById(R.id.cont1);
            cont.setVisibility(View.GONE);
        }
        if(res.getCount() > 1){
            res.moveToNext();
            TextView orderNum = findViewById(R.id.order2);
            TextView date = findViewById(R.id.date2);
            date.setText(res.getString(d));
            orderNum.setText("Order #"+res.getInt(id));
        } else {
            CardView cont = findViewById(R.id.cont2);
            cont.setVisibility(View.GONE);
        }
        if(res.getCount() > 2){
            res.moveToNext();
            TextView orderNum = findViewById(R.id.order3);
            TextView date = findViewById(R.id.date3);
            date.setText(res.getString(d));
            orderNum.setText("Order #"+res.getInt(id));
        } else {
            CardView cont = findViewById(R.id.cont3);
            cont.setVisibility(View.GONE);
        }


    }
}