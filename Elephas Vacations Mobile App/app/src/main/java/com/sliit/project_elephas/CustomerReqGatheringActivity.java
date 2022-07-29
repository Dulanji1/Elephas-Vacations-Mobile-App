package com.sliit.project_elephas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerReqGatheringActivity extends AppCompatActivity {

    private static final String TAG = "CustomerReqGatheringActivity";
    private static String PASSPORT_NO = null;
    DatabaseHelper mDatabaseHelper;
    Button btnSave;
    Button btnCancel;
    EditText Name,Nationality,NoOfPeople,ArrivalDate,DepartureDate,NoOfDays,StarCategory,Remarks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_req_gathering);

        PASSPORT_NO = getIntent().getExtras().getString("CUSTOMER_PASSPORT_NO");

        NoOfPeople = (EditText) findViewById(R.id.editText5);
        ArrivalDate = (EditText) findViewById(R.id.editText11);
        DepartureDate = (EditText) findViewById(R.id.editText12);
        NoOfDays = (EditText) findViewById(R.id.editText6);
        StarCategory = (EditText) findViewById(R.id.editText7);
        Remarks = (EditText) findViewById(R.id.editText13);
        btnSave = (Button) findViewById(R.id.btnCustomerReqSave);
        mDatabaseHelper = new DatabaseHelper(this);

        AddData();

    }


    public void AddData () {

        btnSave = (Button) findViewById(R.id.btnCustomerReqSave);

        //when save button triggers this method will run
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting values form the form fields (editTexts) as String values put those into a String variable for each
                //String name = Name.getText().toString();
                //String nationality = Nationality.getText().toString();
                String passportno= PASSPORT_NO;
                String noOfPeople = NoOfPeople.getText().toString();
                String arrivalDate = ArrivalDate.getText().toString();
                String departureDate = DepartureDate.getText().toString();
                String noOfDays = NoOfDays.getText().toString();
                String starCategory = StarCategory.getText().toString();
                String remarks = Remarks.getText().toString();

                //check all the fields are not empty
                if (passportno.length() != 0 && noOfPeople.length() != 0 && arrivalDate.length() != 0 && departureDate.length() != 0 && noOfDays.length() != 0 && starCategory.length() != 0 && remarks.length() != 0) {
                    boolean isInserted = mDatabaseHelper.insertData(passportno, noOfPeople, arrivalDate, departureDate, noOfDays, starCategory, remarks);

                    if (isInserted == true) {
                        //data insertion successful
                        Toast.makeText(getApplicationContext(),"Data Inserted Successfully!",Toast.LENGTH_SHORT).show(); //display message to display successful data insertion
                        resetForm();    //after successful data insertion all form fields should be empty
                    } else {
                        //something went wrong when data is inserted
                        Toast.makeText(getApplicationContext(),"Error: Data Not Inserted!",Toast.LENGTH_SHORT).show(); //display message
                    }
                } else {
                    //if some fields are empty then display all are required
                    Toast.makeText(getApplicationContext(),"All fields are required!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetForm() {
        //form fields are set as null / empty
        NoOfPeople.setText("");
        ArrivalDate.setText("");
        DepartureDate.setText("");
        NoOfDays.setText("");
        StarCategory.setText("");
        Remarks.setText("");

    }

}
