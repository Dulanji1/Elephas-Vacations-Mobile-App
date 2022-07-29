package com.sliit.project_elephas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.sliit.project_elephas.R;
import com.sliit.project_elephas.model.Transport;
import com.sliit.project_elephas.model.User;
import com.sliit.project_elephas.sql.DBHelper;

import java.sql.Driver;
import java.text.DecimalFormat;

public class Activity_transport extends AppCompatActivity {

    DBHelper db;
    MaterialEditText passport, gross, extraMileage, chargePerKm;
    TextView totalMileage, total_cost_in_usd;
    Button t_save;

    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        passport = (MaterialEditText)findViewById(R.id.passport);
        gross = (MaterialEditText)findViewById(R.id.gross);
        extraMileage = (MaterialEditText)findViewById(R.id.extraMileage);
        chargePerKm = (MaterialEditText)findViewById(R.id.chargePerKm);
        totalMileage = (TextView) findViewById(R.id.totalMileage);
        total_cost_in_usd = (TextView) findViewById(R.id.total_cost_in_usd);

        db = new DBHelper(this);

        addTransportDetails();




    }

    public void addTransportDetails(){
        t_save = (Button) findViewById(R.id.t_save);

        //set button colors
        t_save.setBackgroundColor(Color.parseColor("#03A9F4"));

        t_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Transport transport = new Transport();

                try {
                    String pass = passport.getText().toString();
                    final int passport_number = Integer.parseInt(pass);
                    final double gross_mileage = Double.parseDouble(gross.getText().toString());
                    final double extra_mileage = Double.parseDouble(extraMileage.getText().toString());
                    final double chargeKm = Double.parseDouble(chargePerKm.getText().toString());
                    final double totalDistance = transport.getTotalMileage(gross_mileage,extra_mileage);
                    final double total_charge = transport.getTotalUsd(totalDistance,chargeKm);
                    totalMileage.setText(decimalFormat.format(totalDistance));
                    total_cost_in_usd.setText(decimalFormat.format(total_charge));

                    //save data
                    transport.setPassport(passport_number);
                    transport.setGrossMileage(gross_mileage);
                    transport.setTotalMileage(totalDistance);
                    transport.setChargePerKm(chargeKm);
                    transport.setTotalUsd(total_charge);

                }catch (NumberFormatException e){
                    e.printStackTrace();
                }finally {

                }

                boolean isInserted = db.addtransport(transport);

                if (isInserted) {
                    Toast.makeText(Activity_transport.this, "Transport details successfully added", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Activity_transport.this, Activity_manage_cost.class);
                    startActivity(intent);

                    finish();

                } else {
                    Toast.makeText(Activity_transport.this, "Data is not inserted", Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}
