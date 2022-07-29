package com.sliit.project_elephas.hasinthi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sliit.project_elephas.R;

public class HotelMangement extends AppCompatActivity {

    Button addHotel, viewHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_mangement);

        addHotel = (Button)findViewById(R.id.button3);
        viewHotel = (Button)findViewById(R.id.button4);

        addHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hotelintent = new Intent(HotelMangement.this, HotelManagementForm.class);
                startActivity(hotelintent);
            }
        });

        viewHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hotelintent2 = new Intent(HotelMangement.this, ViewHotelDetails.class);
                startActivity(hotelintent2);
            }
        });

    }


}
