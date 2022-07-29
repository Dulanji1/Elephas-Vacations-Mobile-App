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
import com.sliit.project_elephas.model.Transport;
import com.sliit.project_elephas.sql.DBHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Activity_transport_manage extends AppCompatActivity {

    MaterialEditText pass, gross, extra, charge;
    TextView totalMileage, totalUsd;
    DBHelper db;
    Button update, delete;
    Spinner spinner;
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_manage);

        spinner = (Spinner) findViewById(R.id.spinnerTransport);

        pass = (MaterialEditText) findViewById(R.id.passport);
        gross = (MaterialEditText) findViewById(R.id.gross);
        extra = (MaterialEditText) findViewById(R.id.extraMileage);
        charge = (MaterialEditText) findViewById(R.id.chargePerKm);
        totalMileage = (TextView) findViewById(R.id.totalMileage);
        totalUsd = (TextView) findViewById(R.id.total_cost_in_usd);

        update = (Button)findViewById(R.id.transport_update);
        delete = (Button)findViewById(R.id.transport_delete);

        db = new DBHelper(this);

        spinnerClass();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Transport> allModels = db.selectedTransports(spinner.getSelectedItem().toString());

                for(int x=0;allModels.size()>x;x++) {


                    final int passport_number = allModels.get(x).getPassport();
                    final double gross_mileage = allModels.get(x).getGrossMileage();
                    final double extra_mileage = allModels.get(x).getExtraMileage();
                    final double chargeKm = allModels.get(x).getChargePerKm();
                    final double totalDistance = allModels.get(x).getTotalMileage(gross_mileage, extra_mileage);
                    final double total_charge = allModels.get(x).getTotalUsd(totalDistance, chargeKm);

                    pass.setText(Integer.toString(passport_number));
                    gross.setText(Double.toString(gross_mileage));
                    extra.setText(Double.toString(extra_mileage));
                    charge.setText(Double.toString(chargeKm));
                    totalMileage.setText(decimalFormat.format(totalDistance));
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

                    Transport transport = new Transport();

                    String passport = pass.getText().toString();
                    final int passport_number = Integer.parseInt(passport);
                    final double gross_mileage = Double.parseDouble(gross.getText().toString());
                    final double extra_mileage = Double.parseDouble(extra.getText().toString());
                    final double chargeKm = Double.parseDouble(charge.getText().toString());
                    final double totalDistance = transport.getTotalMileage(gross_mileage,extra_mileage);
                    final double total_charge = transport.getTotalUsd(totalDistance,chargeKm);
                    totalMileage.setText(decimalFormat.format(totalDistance));
                    totalUsd.setText(decimalFormat.format(total_charge));


                    if(db.updateTransport(spinner.getSelectedItem().toString(),passport_number,gross_mileage,extra_mileage,totalDistance,chargeKm,total_charge)){
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


                    if(db.deletetransport(spinner.getSelectedItem().toString())){
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
        ArrayList<Transport> transports = db.readAllTransports();

        final List<String> listTransport = new ArrayList<String>();

        for(int i=0;transports.size()>i;i++){

            listTransport.add(Integer.toString(transports.get(i).getID()));

        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listTransport);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);

    }

}
