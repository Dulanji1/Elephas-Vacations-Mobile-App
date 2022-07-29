package com.sliit.project_elephas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdminViewListOfCustomerRequirementActivity extends AppCompatActivity {

    private static final String TAG = "AdminViewListOfCustomerRequirementActivity";

    /* CUSTOMER REQUIREMENT =====================================================================*/
    public static final String COL1 = "ID";
    //public static final String COL2 = "NAME";
    public static final String COL2 = "PASSPORTNO";
    //public static final String COL3 = "NATIONALITY";
    public static final String COL3 = "NOOFPEOPLE";
    public static final String COL4 = "ARRIVALDATE";
    public static final String COL5 = "DEPARTUREDATE";
    public static final String COL6 = "NOOFDAYS";
    public static final String COL7 = "STARCATEGORY";
    public static final String COL8 = "REMARKS";
    /* CUSTOMER REQUIREMENT =====================================================================*/


    /* CUSTOMER =====================================================================*/
    public static final String CUSTOMER_TABLE_COL1 = "ID";
    public static final String CUSTOMER_TABLE_COL2 = "NAME";
    public static final String CUSTOMER_TABLE_COL3 = "NATIONALITY";
    /* CUSTOMER =====================================================================*/

    public  String PASSPORT_NO = null;

    DatabaseHelper mDatabaseHelper;

    private ListView lvCustomerReq;
    private CustomerRequirementListAdapter adapter;
    private List<CustomerRequirement> mCustomerRequirementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_list_of_req);

        PASSPORT_NO = getIntent().getExtras().getString("CUSTOMER_PASSPORT_NO");

        lvCustomerReq = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        mCustomerRequirementList = new ArrayList<>();

        //data should be retrieved from the database - using Cursor object
        // Cursor obj data should be mapped here
        viewData();


        lvCustomerReq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //When user clicks on a record, do something here
                Toast.makeText(getApplicationContext(),"Clicked! Customer Req.ID : "+view.getTag(),Toast.LENGTH_SHORT).show();

                //CustomeRequirement Obj data should be pass to next activity
                Intent intent = new Intent(getApplicationContext(),CutomerRequirementUpdateDeleteActivity.class);
                intent.putExtra("CUSTOMER_PASSPORT_NO", view.getTag().toString());
                startActivity(intent);
            }
        });


    }


    private void viewData() {

        Cursor cursor = mDatabaseHelper.getAllCustomerRequirementDBForAdminView();

        if (cursor.getCount()==0) {
            Toast.makeText(getApplicationContext(),"No data to show",Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(COL1));
                String name = cursor.getString(cursor.getColumnIndex(CUSTOMER_TABLE_COL2));
                String nationality = cursor.getString(cursor.getColumnIndex(CUSTOMER_TABLE_COL3));
                String passportNo = cursor.getString(cursor.getColumnIndex(COL2));
                String noOfPeople = cursor.getString(cursor.getColumnIndex(COL3));
                String noOfDays = cursor.getString(cursor.getColumnIndex(COL6));
                String arrivalDate = cursor.getString(cursor.getColumnIndex(COL4));
                String departureDate = cursor.getString(cursor.getColumnIndex(COL5));
                String starCategory = cursor.getString(cursor.getColumnIndex(COL7));
                String remark = cursor.getString(cursor.getColumnIndex(COL8));

                // retrieved data should be mapped here
                mCustomerRequirementList.add(new CustomerRequirement(Integer.parseInt(id),name,nationality,passportNo,noOfPeople,noOfDays,arrivalDate,departureDate,starCategory,remark));
            }

            cursor.close();

            adapter = new CustomerRequirementListAdapter(getApplicationContext(),mCustomerRequirementList);
            lvCustomerReq.setAdapter(adapter);
        }

    }

}
