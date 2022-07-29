package com.sliit.project_elephas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sliit.project_elephas.activities.all_customer_payments;

import org.w3c.dom.Text;

public class AfterLoginCustomerActivity extends AppCompatActivity {

    private static String CUSTOMER_NAME = null;
    private static String PASSPORT_NO = null;
    DatabaseHelper mDatabaseHelper;
    Button btnViewAll;
    Button payment, requirements;
    TextView txtViewCustomerNameDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_customer);

        txtViewCustomerNameDisplay = (TextView) findViewById(R.id.textView26);

        try {
            PASSPORT_NO = getIntent().getExtras().getString("CUSTOMER_PASSPORT_NO");
            CUSTOMER_NAME = getIntent().getExtras().getString("CUSTOMER_NAME");
        } catch (NullPointerException e){
            e.printStackTrace();
        }


        if (CUSTOMER_NAME.length()!=0){
            txtViewCustomerNameDisplay.setText(CUSTOMER_NAME);
        } else  {
            txtViewCustomerNameDisplay.setText("User");
        }

        btnViewAll = (Button) findViewById(R.id.button3);
        mDatabaseHelper = new DatabaseHelper(this);

        payment = (Button) findViewById(R.id.payment);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLoginCustomerActivity.this, all_customer_payments.class);
                startActivity(intent);
            }
        });

        requirements = (Button) findViewById(R.id.button2);
        requirements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLoginCustomerActivity.this,CustomerReqGatheringActivity.class);

                //intent.putExtra("CUSTOMER_ID",);
                intent.putExtra("CUSTOMER_PASSPORT_NO",PASSPORT_NO);
                //intent.putExtra("CUSTOMER_EMAIL", );
                //intent.putExtra("CUSTOMER_NAME",);

                // EditText editTextForUsername = findViewById(R.id.editText);
                //   EditText editTextForPassword = findViewById(R.id.editText2);
                //String username = editTextForUsername.getText().toString();
                // String password = editTextForPassword.getText().toString();
                // intent.putExtra("UserName", username);
                // intent.putExtra("Password", password);
                startActivity(intent);
            }
        });


    }


    // redirection code using Intent
    public void redirectToCustomerReqGatheringActivity(View view){
        Intent intent = new Intent(this,CustomerReqGatheringActivity.class);

        //intent.putExtra("CUSTOMER_ID",);
        intent.putExtra("CUSTOMER_PASSPORT_NO",PASSPORT_NO);
        //intent.putExtra("CUSTOMER_EMAIL", );
        //intent.putExtra("CUSTOMER_NAME",);

        // EditText editTextForUsername = findViewById(R.id.editText);
        //   EditText editTextForPassword = findViewById(R.id.editText2);
        //String username = editTextForUsername.getText().toString();
        // String password = editTextForPassword.getText().toString();
        // intent.putExtra("UserName", username);
        // intent.putExtra("Password", password);
        startActivity(intent);

    }

    public void redirectToCustomerViewListOfReqActivity(View view){
        Intent intent = new Intent(this,CustomerViewListOfReqActivity.class);
        intent.putExtra("CUSTOMER_PASSPORT_NO",PASSPORT_NO);
        startActivity(intent);

    }
}
