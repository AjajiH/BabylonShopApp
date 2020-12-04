package com.example.babylonshopapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


public class CategoryFragment extends Fragment {

    private View v;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_category, container, false);
        configureImageButton();
        return v;
    }

    private void configureImageButton() {
        // TODO Auto-generated method stub
        ImageButton categoryBedroom = (ImageButton) v.findViewById(R.id.categoryBedroom);
        categoryBedroom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent bedrooms_products_intent = new Intent(getActivity(), BedroomsProductsActivity.class);
                startActivity(bedrooms_products_intent);
                Toast.makeText(getActivity(), "Bedroom Products!", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton categoryLivingRoom = (ImageButton) v.findViewById(R.id.categoryLivingroom);
        categoryLivingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent living_room_products_intent = new Intent(getActivity(), LivingRoomProductsActivity.class);
                startActivity(living_room_products_intent);
                Toast.makeText(getActivity(), "Living Room Products!", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton categoryKitchen = (ImageButton) v.findViewById(R.id.categoryKitchen);
        categoryKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kitchen_products_intent = new Intent(getActivity(), KitchenProductsActivity.class);
                startActivity(kitchen_products_intent);
                Toast.makeText(getActivity(), "Kitchen Products!", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton categoryBathroom = (ImageButton) v.findViewById(R.id.categoryBathroom);
        categoryBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bathroom_products_intent = new Intent(getActivity(), BathroomProductsActivity.class);
                startActivity(bathroom_products_intent);
                Toast.makeText(getActivity(), "Bathroom Products!", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton categoryTablesChairs = (ImageButton) v.findViewById(R.id.categoryTableChair);
        categoryTablesChairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tableChair_products_intent = new Intent(getActivity(), TableChairProductsActivity.class);
                startActivity(tableChair_products_intent);
                Toast.makeText(getActivity(), "Table and Chair Products!", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton categoryOutdoor = (ImageButton) v.findViewById(R.id.categoryOutdoor);
        categoryOutdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outdoor_products_intent = new Intent(getActivity(), OutdoorProductsActivity.class);
                startActivity(outdoor_products_intent);
                Toast.makeText(getActivity(), "Outdoor Products!", Toast.LENGTH_LONG).show();
            }
        });


    }
}