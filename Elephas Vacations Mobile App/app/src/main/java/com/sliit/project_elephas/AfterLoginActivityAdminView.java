package com.sliit.project_elephas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sliit.project_elephas.activities.Activity_manage_cost;
import com.sliit.project_elephas.hasinthi.HotelMangement;

public class AfterLoginActivityAdminView extends AppCompatActivity {

    private static String ADMIN_NAME = null;
    private static String ADMIN_ID = null;
    private static String ADMIN_PASSPORT_NO = null;
    private static String ADMIN_EMAIL = null;
    Button btnViewData;
    Button btnManageInfoForTours;
    Button payments_manage;
    Button btnHotelmange;
    TextView textViewAdminNameDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_admin_view);

        payments_manage = (Button)findViewById(R.id.button7);
        btnManageInfoForTours = (Button)findViewById(R.id.button5); //MANAGE INFORMATION FOR TOURS BUTTON

        //MANAGE INFORMATION FOR TOURS BUTTON
        //We should redirect to ManageInformationForToursActivity.class
        btnManageInfoForTours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ManageInformationForToursActivity.class);
                intent.putExtra("ADMIN_ID",ADMIN_ID);
                intent.putExtra("ADMIN_PASSPORT_NO",ADMIN_PASSPORT_NO);
                intent.putExtra("ADMIN_EMAIL",ADMIN_EMAIL);
                intent.putExtra("ADMIN_NAME",ADMIN_NAME);
                startActivity(intent);
            }
        });


        // I commented this(below) code segment - Dhanusha
        // Because we do not want to redirect to Hashinthi's part.. first we redirect to ManageInformationForToursActivity.class
/*        btnHotelmange = (Button)findViewById(R.id.button5);

        btnHotelmange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hotelintent = new Intent(AfterLoginActivityAdminView.this, HotelMangement.class);
                startActivity(hotelintent);
            }
        });*/

        payments_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLoginActivityAdminView.this, Activity_manage_cost.class);
                startActivity(intent);
            }
        });

        // get intent values here
        ADMIN_NAME = getIntent().getExtras().getString("ADMIN_NAME");
        ADMIN_ID = getIntent().getExtras().getString("ADMIN_ID");
        ADMIN_PASSPORT_NO = getIntent().getExtras().getString("ADMIN_PASSPORT_NO");
        ADMIN_EMAIL = getIntent().getExtras().getString("ADMIN_EMAIL");

        btnViewData = (Button) findViewById(R.id.button4);
        textViewAdminNameDisplay = (TextView) findViewById(R.id.textViewAdminNameDisplay);
        textViewAdminNameDisplay.setText(ADMIN_NAME);

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminViewListOfCustomerRequirementActivity.class);
                intent.putExtra("ADMIN_ID",ADMIN_ID);
                intent.putExtra("ADMIN_PASSPORT_NO",ADMIN_PASSPORT_NO);
                intent.putExtra("ADMIN_EMAIL",ADMIN_EMAIL);
                intent.putExtra("ADMIN_NAME",ADMIN_NAME);
                startActivity(intent);
            }
        });
    }


    // This method, do not touch it.. it will be useless for right now but I have used it before
    public void redirectWithDataToManageInformationForTours(View view){
        Intent intent = new Intent(this,ManageInformationForToursActivity.class);
        intent.putExtra("ADMIN_ID",ADMIN_ID);
        intent.putExtra("ADMIN_PASSPORT_NO",ADMIN_PASSPORT_NO);
        intent.putExtra("ADMIN_EMAIL",ADMIN_EMAIL);
        intent.putExtra("ADMIN_NAME",ADMIN_NAME);
        // EditText editTextForUsername = findViewById(R.id.editText);
        //   EditText editTextForPassword = findViewById(R.id.editText2);
        //String username = editTextForUsername.getText().toString();
        // String password = editTextForPassword.getText().toString();
        // intent.putExtra("UserName", username);
        // intent.putExtra("Password", password);
        startActivity(intent);

    }



}
