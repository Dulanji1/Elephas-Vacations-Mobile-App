package com.sliit.project_elephas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sliit.project_elephas.R;
import com.sliit.project_elephas.model.Payments;
import com.sliit.project_elephas.model.User;
import com.sliit.project_elephas.sql.DBHelper;

public class Payment extends AppCompatActivity {

    DBHelper db;
    TextInputEditText name,address,NIC,amount,cardNo, email;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //Define components
        name = (TextInputEditText) findViewById(R.id.namePay);
        email = (TextInputEditText) findViewById(R.id.emailPay);
        address = (TextInputEditText) findViewById(R.id.addressPay);
        NIC = (TextInputEditText) findViewById(R.id.payNic);
        amount = (TextInputEditText) findViewById(R.id.payAmounts);
        cardNo = (TextInputEditText) findViewById(R.id.pay_status);

        db = new DBHelper(this);

        addDetail();

    }

    public void addDetail() {
        save = (Button) findViewById(R.id.save);

        //set button colors
        save.setBackgroundColor(Color.parseColor("#03A9F4"));

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (name.getText().toString().equals("")) {
                            name.setError("Enter the name");
                            Toast.makeText(getApplicationContext(), "Payment not successfull", Toast.LENGTH_LONG).show();

                        } else if(email.getText().toString().equals("")){
                            email.setError("Enter your Email");
                            Toast.makeText(getApplicationContext(), "Enter your Email", Toast.LENGTH_LONG).show();
                        }else if (address.getText().toString().equals("")) {
                            address.setError("Enter your address");
                            Toast.makeText(getApplicationContext(), "Enter your address", Toast.LENGTH_LONG).show();
                        } else if (NIC.getText().toString().length() != 10) {
                            NIC.setError("Enter the NIC number");
                            Toast.makeText(getApplicationContext(), "payment not successful(NIC should have 10 charachters) ", Toast.LENGTH_LONG).show();
                        } else if (amount.getText().toString().equals("")) {
                            amount.setError("Enter your Amount");
                            Toast.makeText(getApplicationContext(), "Enter your address", Toast.LENGTH_LONG).show();
                        } else if (cardNo.getText().toString().length() != 12) {
                            cardNo.setError("Enter a valid card number");
                            Toast.makeText(getApplicationContext(), "Payment not successful", Toast.LENGTH_LONG).show();


                        } else {

                            Payments payment = new Payments();

                            payment.setUser_email(email.getText().toString());
                            payment.setName(name.getText().toString());
                            payment.setAddress(address.getText().toString());
                            payment.setNic(NIC.getText().toString());
                            payment.setAmount(Double.parseDouble(amount.getText().toString()));
                            payment.setCard(cardNo.getText().toString());
                            //boolean isInserted = db.insert_payment(payment);

                            boolean isInserted = db.addCustomerpayments(payment);

                            if (isInserted) {
                                Toast.makeText(Payment.this, "Payment successful", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(Payment.this, all_customer_payments.class);
                                startActivity(intent);

                                finish();

                            } else {
                                Toast.makeText(Payment.this, "Data is not inserted", Toast.LENGTH_LONG).show();

                            }

                        }
                    }
                });


            }

        });
    }

}
