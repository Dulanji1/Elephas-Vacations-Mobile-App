package com.sliit.project_elephas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sliit.project_elephas.AfterLoginCustomerActivity;
import com.sliit.project_elephas.LoginActivity;
import com.sliit.project_elephas.R;

public class Activity_manage_cost extends AppCompatActivity {

    Button pay_man, transportBtn, transport_manage, driverBtn, driverManageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cost);

        pay_man = (Button)findViewById(R.id.manage_pay);
        transportBtn = (Button)findViewById(R.id.transportBtn);
        transport_manage = (Button)findViewById(R.id.transport_manage);
        driverBtn = (Button)findViewById(R.id.driverBtn);
        driverManageBtn = (Button)findViewById(R.id.driverManageBtn);


        driverManageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driverIntent = new Intent(Activity_manage_cost.this, Activity_man_cost_mange.class);
                startActivity(driverIntent);
            }
        });


        driverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driverIntent = new Intent(Activity_manage_cost.this, Activity_man_cost.class);
                startActivity(driverIntent);
            }
        });

        transport_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transManIntent = new Intent(Activity_manage_cost.this, Activity_transport_manage.class);
                startActivity(transManIntent);
            }
        });

        transportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transIntent = new Intent(Activity_manage_cost.this, Activity_transport.class);
                startActivity(transIntent);
            }
        });



        pay_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_manage_cost.this, Payment_manage.class);
                startActivity(intent);
            }
        });

    }
}
