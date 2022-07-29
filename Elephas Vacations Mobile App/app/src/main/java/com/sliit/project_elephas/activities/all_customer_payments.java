package com.sliit.project_elephas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sliit.project_elephas.AfterLoginCustomerActivity;
import com.sliit.project_elephas.LoginActivity;
import com.sliit.project_elephas.R;
import com.sliit.project_elephas.model.Invoice;
import com.sliit.project_elephas.sql.DBHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class all_customer_payments extends AppCompatActivity {

    Button pay_invoice;
    DBHelper db;
    TextView transportation_cost,diver_guide_cost,total_cost;

    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customer_payments);

        pay_invoice = (Button) findViewById(R.id.pay_inv);
        transportation_cost = (TextView) findViewById(R.id.transportation_cost);
        diver_guide_cost = (TextView) findViewById(R.id.diver_guide_cost);
        total_cost = (TextView) findViewById(R.id.total_cost);

        db = new DBHelper(this);

        String passport = "1234567891";

        viewData(passport);

        pay_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_customer_payments.this, Payment.class);
                startActivity(intent);
            }
        });



    }

    public void viewData(String passport){

        List<Invoice> invoice = db.selectedInvoice(passport);

        for (Invoice in : invoice) {

            Double transport = in.getTransport();
            Double guide = in.getDriver();
            Double total = in.getTotoalCost(transport,guide);

            transportation_cost.setText(decimalFormat.format(transport));
            diver_guide_cost.setText(decimalFormat.format(guide));
            total_cost.setText(decimalFormat.format(total));


        }

    }

}
