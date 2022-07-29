package com.sliit.project_elephas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.sliit.project_elephas.R;
import com.sliit.project_elephas.model.Driver;
import com.sliit.project_elephas.sql.DBHelper;

import java.text.DecimalFormat;

public class Activity_man_cost extends AppCompatActivity {

    DBHelper db;
    MaterialEditText passport, bata, guide;
    TextView total_cost_in_usd;
    Button save;

    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_cost);

        passport = (MaterialEditText)findViewById(R.id.passport_driver_guide);
        bata = (MaterialEditText)findViewById(R.id.bata);
        guide = (MaterialEditText)findViewById(R.id.guide);
        total_cost_in_usd = (TextView)findViewById(R.id.usd);
        save = (Button)findViewById(R.id.save);

        db = new DBHelper(this);

        addDriverDetails();

    }

    public void addDriverDetails(){
        save = (Button)findViewById(R.id.save);

        //set button colors
        save.setBackgroundColor(Color.parseColor("#03A9F4"));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Driver driver = new Driver();

                try {


                    final int passport_number = Integer.parseInt(passport.getText().toString());
                    final double DriverBata = Double.parseDouble(bata.getText().toString());
                    final double guideCost = Double.parseDouble(guide.getText().toString());
                    final double totalCost = driver.getTotal_in_usd(DriverBata, guideCost);
                    total_cost_in_usd.setText(decimalFormat.format(totalCost));

                    //save data
                    driver.setPassport(passport_number);
                    driver.setDriver(DriverBata);
                    driver.setGuide(guideCost);
                    driver.setTotal_in_usd(totalCost);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } finally {

                }

                boolean isInserted = db.addDriver(driver);

                if (isInserted) {
                    Toast.makeText(Activity_man_cost.this, "Driver and Guide details successfully added", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Activity_man_cost.this, Activity_manage_cost.class);
                    startActivity(intent);

                    finish();

                } else {
                    Toast.makeText(Activity_man_cost.this, "Data is not inserted", Toast.LENGTH_LONG).show();

                }
            }

        });


    }

}
