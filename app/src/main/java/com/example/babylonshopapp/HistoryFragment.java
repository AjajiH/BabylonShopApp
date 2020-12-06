package com.example.babylonshopapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HistoryFragment extends Fragment {
    DatabaseHelper db;
    public String email;
    TextView orderNum,date,date2,date3,orderNum2,orderNum3;
    CardView cont1,cont2,cont3;


    public HistoryFragment(String email){
        this.email = email;
        System.out.println("Email:" + this.email);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DatabaseHelper(getActivity().getApplicationContext());
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        orderNum = v.findViewById(R.id.order1);
        date =  v.findViewById(R.id.date1);
        orderNum2 =  v.findViewById(R.id.order2);
        date2 =  v.findViewById(R.id.date2);
        orderNum3 =  v.findViewById(R.id.order3);
        date3 =  v.findViewById(R.id.date3);

        cont1 =  v.findViewById(R.id.cont1);
        cont2 =  v.findViewById(R.id.cont2);
        cont3 = v.findViewById(R.id.cont3);
        createHistoryView();
        return v;
    }

    public void createHistoryView(){
//        db = new DatabaseHelper(getActivity().getApplicationContext());
        Cursor res = db.getOrders(email);
        int d = 2;
        int id = 0;
//        res.moveToFirst();
        if(res.getCount() > 0){
            res.moveToNext();
            System.out.println("@#@#@#@#@#@#"+res.getString(d));
            date.setText(res.getString(d));
            orderNum.setText("Order #"+res.getInt(id));
        } else {
            cont1.setVisibility(View.GONE);
        }
        if(res.getCount() > 1){
            res.moveToNext();
            date2.setText(res.getString(d));
            orderNum2.setText("Order #"+res.getInt(id));
        } else {
            cont2.setVisibility(View.GONE);
        }
                if(res.getCount() > 2){
            res.moveToNext();

            date3.setText(""+res.getString(d));
            orderNum3.setText("Order #"+res.getInt(id));
        } else {

            cont3.setVisibility(View.GONE);
        }




    }
}