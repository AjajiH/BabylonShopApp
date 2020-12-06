package com.example.babylonshopapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class CartFragment extends Fragment {

    TextView itemprice1 , itemprice2, totalprice;
    String email;

    public CartFragment(String email){
        this.email = email;

        System.out.println("Email:" + this.email);
    }
    public CartFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        itemprice1 = v.findViewById(R.id.itemprice1);
        itemprice2 = v.findViewById(R.id.itemprice2);
        totalprice = v.findViewById(R.id.totalPrice);

        Double TotalPrice = Double.parseDouble(itemprice1.getText().toString()) + Double.parseDouble(itemprice2.getText().toString()) ;

        totalprice.setText(""+ TotalPrice);

        Button checkoutbtn = v.findViewById(R.id.checkoutbtn);
        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(),CheckoutActivity.class);
                i.putExtra("Total_Price", "" + TotalPrice);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

        return v;
    }


}