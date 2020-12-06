package com.example.babylonshopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        BroadcastReceiver broadcastReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.abc.WELCOME_INTENT");
        registerReceiver(broadcastReceiver, filter);

        Intent welcomeIntent = getIntent();
        String InputData = welcomeIntent.getStringExtra("USERNAME");
        Log.i("TAG", "Login");
        Intent intent = new Intent();
        intent.setAction("com.abc.WELCOME_INTENT");
        sendBroadcast(new Intent(this, MyReceiver.class).setAction(InputData));
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
                            selectedFragment = new HistoryFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}