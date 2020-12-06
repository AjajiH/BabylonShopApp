package com.example.babylonshopapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "TAG";

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("" + intent.getAction());
        String log = stringBuilder.toString();
        Log.d(TAG, log);
        Toast.makeText(context, "Welcome to Babylon \n" + log, Toast.LENGTH_LONG).show();
    }
}
