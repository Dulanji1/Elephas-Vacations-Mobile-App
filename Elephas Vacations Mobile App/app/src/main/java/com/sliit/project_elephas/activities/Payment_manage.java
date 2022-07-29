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

import com.sliit.project_elephas.R;
import com.sliit.project_elephas.model.Payments;
import com.sliit.project_elephas.sql.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class Payment_manage extends AppCompatActivity {

    DBHelper db;

    Spinner spinner;

    TextView name,address,nic,amount,cardNo,status,pay_email;

    Button verify,refund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_manage);

        spinner = (Spinner) findViewById(R.id.spinnerPayments);

        name = (TextView) findViewById(R.id.pay_name);
        address = (TextView) findViewById(R.id.pay_address);
        nic = (TextView) findViewById(R.id.pay_Nic);
        amount = (TextView) findViewById(R.id.pay_amount);
        cardNo = (TextView) findViewById(R.id.pay_card);
        status = (TextView) findViewById(R.id.pay_status);
        pay_email = (TextView) findViewById(R.id.pay_email);

        verify = (Button) findViewById(R.id.pay_verify);
        refund = (Button) findViewById(R.id.pay_refund);

        db = new DBHelper(this);

        try {
            spinnerClass();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "spinnerClass() not working", Toast.LENGTH_SHORT).show();
        }

        //spinnerClass();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Payments> allModels = db.selectedPayment(spinner.getSelectedItem().toString());

                for(int x=0;allModels.size()>x;x++){

                    name.setText(allModels.get(x).getName());
                    pay_email.setText(allModels.get(x).getUser_email());
                    address.setText(allModels.get(x).getAddress());
                    nic.setText(allModels.get(x).getNic());
                    amount.setText(Double.toString(allModels.get(x).getAmount()));
                    cardNo.setText(allModels.get(x).getCard());
                    status.setText(allModels.get(x).getStatus());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("")){


                    if(db.updatePayment(spinner.getSelectedItem().toString())){
                        Toast.makeText(getApplicationContext(),"Success !",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("")){


                    if(db.refundPayment(spinner.getSelectedItem().toString())){
                        Toast.makeText(getApplicationContext(),"Success !",Toast.LENGTH_SHORT).show();
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
        ArrayList<Payments> allModels1 = db.readAllPayments();

        final List<String> list1 = new ArrayList<String>();

        for(int i=0;allModels1.size()>i;i++){

            list1.add(Integer.toString(allModels1.get(i).getID()));

        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
    }

}


