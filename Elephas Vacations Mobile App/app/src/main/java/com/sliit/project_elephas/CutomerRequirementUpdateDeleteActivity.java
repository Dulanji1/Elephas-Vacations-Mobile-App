package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CutomerRequirementUpdateDeleteActivity extends AppCompatActivity {

    private static String PASSPORT_NO = null;

    /*  CUSTOMER REQUIREMENTS======================================================================================================================*/

    public static final String COL1 = "ID";
    public static final String COL2 = "PASSPORTNO";
    //public static final String COL3 = "NATIONALITY";
    public static final String COL3 = "NOOFPEOPLE";
    public static final String COL4 = "ARRIVALDATE";
    public static final String COL5 = "DEPARTUREDATE";
    public static final String COL6 = "NOOFDAYS";
    public static final String COL7 = "STARCATEGORY";
    public static final String COL8 = "REMARKS";

    /*  END - CUSTOMER REQUIREMENTS======================================================================================================================*/


    /*  CUSTOMER ======================================================================================================================*/

    public static final String CUSTOMER_TABLE_COL1 = "ID";
    public static final String CUSTOMER_TABLE_COL2 = "NAME";
    public static final String CUSTOMER_TABLE_COL3 = "NATIONALITY";
    public static final String CUSTOMER_TABLE_COL4 = "PASSPORTNO";
    public static final String CUSTOMER_TABLE_COL5 = "EMAIL";
    public static final String CUSTOMER_TABLE_COL6 = "PHONE";
    public static final String CUSTOMER_TABLE_COL7 = "PASSWORD";

    /*  END - CUSTOMER ======================================================================================================================*/



    Button btnUpdate, btnDelete;
    DatabaseHelper mDatabaseHelper;
    TextView txtViewCustomerReqID,txtViewPassport,txtViewNationality;
    EditText NoOfPeople,ArrivalDate,DepartureDate,NoOfDays,StarCategory,Remarks;
    //EditText Name,Nationality;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutomer_requirement_update_delete);

        PASSPORT_NO = getIntent().getExtras().getString("CUSTOMER_PASSPORT_NO");

        btnUpdate = (Button) findViewById(R.id.button16);
        btnDelete = (Button) findViewById(R.id.button17);

        txtViewCustomerReqID = (TextView) findViewById(R.id.textViewRequirementID);   // customerID
        txtViewPassport = (TextView) findViewById(R.id.textViewPassport);   // Passport
        txtViewNationality = (TextView) findViewById(R.id.textViewNationality);   // Nationality

        //Name = (EditText) findViewById(R.id.editText15);
        //Nationality = (EditText) findViewById(R.id.editText16);
        NoOfPeople = (EditText) findViewById(R.id.editTextNoPeople);
        ArrivalDate = (EditText) findViewById(R.id.editTextArrivalDate);
        DepartureDate = (EditText) findViewById(R.id.editTextDepartureDate);
        NoOfDays = (EditText) findViewById(R.id.editTextNoDays);
        StarCategory = (EditText) findViewById(R.id.editTextStarCategory);
        Remarks = (EditText) findViewById(R.id.editTextRemark);

        mDatabaseHelper = new DatabaseHelper(this);

        //display related customer requirement data in the EditText fields + ViewText
        // by retrieving data from the customer requirement table + customer table
        CustomerRequirement customerReq = retrieveCustomerRequirementDataFromDB();
        txtViewCustomerReqID.setText(Integer.toString(customerReq.getId()));
        txtViewNationality.setText(customerReq.getNationality());
        txtViewPassport.setText(customerReq.getPassPortNo());

        NoOfPeople.setText(customerReq.getNoOfPeople());
        ArrivalDate.setText(customerReq.getArrivalDate());
        DepartureDate.setText(customerReq.getDepartureDate());
        NoOfDays.setText(customerReq.getNoOfDays());
        StarCategory.setText(customerReq.getStarCategory());
        Remarks.setText(customerReq.getRemark());


        //button methods
        updateDataOnClick();    //methods - update button
        deleteDataOnClick();    //methods - delete button

    }


    public CustomerRequirement retrieveCustomerRequirementDataFromDB() {
        CustomerRequirement customerRequirement = new CustomerRequirement();

        Cursor cusReq = mDatabaseHelper.getCustomerRequirementData(PASSPORT_NO);
        String CustomerReqID = null;
        String PassportNo = null;
        String NoOfPeople = null;
        String NoOfDays = null;
        String ArrivalDate = null;
        String DepartureDate = null;
        String StarCategory = null;
        String Remark = null;

        if (cusReq.getCount()==0) {
            Toast.makeText(getApplicationContext(),"Customer Not Found : Customer Table",Toast.LENGTH_SHORT).show();
        } else  {

            while (cusReq.moveToNext()) {
                CustomerReqID = cusReq.getString(cusReq.getColumnIndex(COL1));
                PassportNo = cusReq.getString(cusReq.getColumnIndex(COL2));
                NoOfPeople = cusReq.getString(cusReq.getColumnIndex(COL3));
                NoOfDays = cusReq.getString(cusReq.getColumnIndex(COL6));
                ArrivalDate = cusReq.getString(cusReq.getColumnIndex(COL4));
                DepartureDate = cusReq.getString(cusReq.getColumnIndex(COL5));
                StarCategory = cusReq.getString(cusReq.getColumnIndex(COL7));
                Remark = cusReq.getString(cusReq.getColumnIndex(COL8));
            }
            cusReq.close();
        }


        //search the customer to find and get rest of the customer related details
        Customer customer = retrieveCustomerDataFromDB(PassportNo);


        customerRequirement.setId(Integer.parseInt(CustomerReqID));
        customerRequirement.setName(customer.getName());
        customerRequirement.setNationality(customer.getNationality());
        customerRequirement.setPassPortNo(PassportNo);
        customerRequirement.setNoOfPeople(NoOfPeople);
        customerRequirement.setNoOfDays(NoOfDays);
        customerRequirement.setArrivalDate(ArrivalDate);
        customerRequirement.setDepartureDate(DepartureDate);
        customerRequirement.setStarCategory(StarCategory);
        customerRequirement.setRemark(Remark);


        return customerRequirement;
    }


    public Customer retrieveCustomerDataFromDB(String passportnoDB) {
        Customer currentCustomer = new Customer();
        Cursor customer = mDatabaseHelper.getCustomerData(passportnoDB);
        String ID = null;
        String Name = null;
        String Nationality = null;
        //String PassportNo = null;


        if (customer.getCount()==0) {
            Toast.makeText(getApplicationContext(),"Customer Not Found : Customer Table",Toast.LENGTH_SHORT).show();
        } else  {

            while (customer.moveToNext()) {
                ID = customer.getString(customer.getColumnIndex(CUSTOMER_TABLE_COL1));
                Name = customer.getString(customer.getColumnIndex(CUSTOMER_TABLE_COL2));
                Nationality = customer.getString(customer.getColumnIndex(CUSTOMER_TABLE_COL3));
            }
            customer.close();
        }

        currentCustomer.setId(Integer.parseInt(ID));
        currentCustomer.setName(Name);
        currentCustomer.setNationality(Nationality);


        return currentCustomer;
    }


    //this method is for update button
    public void updateDataOnClick() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get values from the editText fields
                String id = txtViewCustomerReqID.getText().toString();
                //String name = Name.getText().toString();
                //String nationality = Nationality.getText().toString();
                String passportno=PASSPORT_NO;
                String noOfPeople = NoOfPeople.getText().toString();
                String arrivalDate = ArrivalDate.getText().toString();
                String departureDate = DepartureDate.getText().toString();
                String noOfDays = NoOfDays.getText().toString();
                String starCategory = StarCategory.getText().toString();
                String remarks = Remarks.getText().toString();


                boolean isUpdate = mDatabaseHelper.updateData(id,passportno,noOfPeople,arrivalDate,departureDate,noOfDays,starCategory,remarks);

                if (isUpdate) {
                    //data insertion successful
                    Toast.makeText(getApplicationContext(),"Data Updated Successfully!",Toast.LENGTH_SHORT).show(); //display message to display successful data updating
                    //redirect to view data activity
                    //code
                } else {
                    //something went wrong when data is updating
                    Toast.makeText(getApplicationContext(),"Error: Data Not Updated!",Toast.LENGTH_SHORT).show(); //display message
                }

            }
        });
    }


    //this method is to delete the record
    public void deleteDataOnClick() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtViewCustomerReqID.getText().toString();
                Integer deletedRows = mDatabaseHelper.deleteData(id);
                if (deletedRows > 0) {
                    Toast.makeText(getApplicationContext(),"Data Deleted Successfully!",Toast.LENGTH_SHORT).show(); //display message to display successful data deleting
                } else {
                    Toast.makeText(getApplicationContext(),"Data Not Deleted!",Toast.LENGTH_SHORT).show(); //display message
                }
            }
        });
    }
}
