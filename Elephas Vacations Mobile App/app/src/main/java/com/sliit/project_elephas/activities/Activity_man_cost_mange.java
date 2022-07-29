package com.sliit.project_elephas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.sliit.project_elephas.R;
import com.sliit.project_elephas.model.Driver;
import com.sliit.project_elephas.sql.DBHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Activity_man_cost_mange extends AppCompatActivity {

    MaterialEditText pass, bata, guide;
    TextView totalUsd;
    DBHelper db;
    Button update, delete;
    Spinner spinner;
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_cost_mange);

        pass = (MaterialEditText)findViewById(R.id.passport_driver_guide);
        bata = (MaterialEditText)findViewById(R.id.bata);
        guide = (MaterialEditText)findViewById(R.id.guide);
        totalUsd = (TextView)findViewById(R.id.usd);
        update = (Button)findViewById(R.id.update);
        delete = (Button)findViewById(R.id.delete);

        spinner = (Spinner) findViewById(R.id.spinnerDriver);

        db = new DBHelper(this);

        spinnerClass();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Driver> allModels = db.selectedDrivers(spinner.getSelectedItem().toString());

                for(int x=0;allModels.size()>x;x++) {


                    final int passport_number = allModels.get(x).getPassport();
                    final double driver_bata = allModels.get(x).getDriver();
                    final double guide_cost = allModels.get(x).getGuide();
                    final double total_charge = allModels.get(x).getTotal_in_usd(driver_bata,guide_cost);

                    pass.setText(Integer.toString(passport_number));
                    bata.setText(decimalFormat.format(driver_bata));
                    guide.setText(decimalFormat.format(guide_cost));
                    totalUsd.setText(decimalFormat.format(total_charge));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("")){

                    Driver driver = new Driver();

                    //Transport transport = new Transport();

                    String passport = pass.getText().toString();
                    final int passport_number = Integer.parseInt(passport);
                    final double driver_bata = Double.parseDouble(bata.getText().toString());
                    final double guide_cost = Double.parseDouble(guide.getText().toString());
                    final double total_charge = driver.getTotal_in_usd(driver_bata,guide_cost);
                    totalUsd.setText(decimalFormat.format(total_charge));


                    if(db.updateDriver(spinner.getSelectedItem().toString(),passport_number,driver_bata,guide_cost,total_charge)){
                        Toast.makeText(getApplicationContext(),"Successfully updated !",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("")){


                    if(db.deleteDriver(spinner.getSelectedItem().toString())){
                        Toast.makeText(getApplicationContext(),"Successfully updated !",Toast.LENGTH_SHORT).show();
                        spinnerClass();
                    }else {
                        Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select !",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    /**
     * Function to load the spinner data from SQLite database
     * */
    private void spinnerClass(){
        ArrayList<Driver> drivers = db.readAllDrivers();

        final List<String> listDrivers = new ArrayList<String>();

        for(int i=0;drivers.size()>i;i++){

            listDrivers.add(Integer.toString(drivers.get(i).getID()));

        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listDrivers);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);

    }

}
