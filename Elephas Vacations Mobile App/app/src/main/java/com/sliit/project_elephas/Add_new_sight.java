<<<<<<< HEAD
package com.example.sightseeen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
=======
package com.sliit.project_elephas;

>>>>>>> origin/master
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
<<<<<<< HEAD
import android.text.TextUtils;
=======
>>>>>>> origin/master
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

<<<<<<< HEAD
import com.example.sightseeen.Database.DBHelperForSightSeen;
import com.example.sightseeen.Database.SightseenMaster;

=======
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.sliit.project_elephas.Database.DBHelperForSightSeen;
>>>>>>> origin/master

public class Add_new_sight extends AppCompatActivity {

    Button select_all, add, delete, update,details,back;
    EditText sightNo,sightName,childTicPrice,adultTicPrice;
    DBHelperForSightSeen sightDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sight);


        sightDB = new DBHelperForSightSeen(this);
        SQLiteDatabase db = sightDB.getReadableDatabase();

        // Hooks
        details = findViewById(R.id.s_DetailsButton);
        select_all = findViewById(R.id.s_showButton);
        add = findViewById(R.id.s_saveButton);
        update = findViewById(R.id.s_updateButton);
        delete = findViewById(R.id.s_deleteButton);
        back=findViewById(R.id.back_btn);

        sightNo = findViewById(R.id.sno1);
        sightName = findViewById(R.id.sname1);
        childTicPrice = findViewById(R.id.childprice);
        adultTicPrice = findViewById(R.id.adultprice);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });


        AddDataSight();
        detailsForAdminSight();
        viewAllSight();
        UpdateDataSight();
        DeleteDataSight();


    }

public void onBackPressed(){
    Intent intent2 = new Intent(this, sightseen.class);
    startActivity(intent2);
    super.onBackPressed();
    finish();
}
    public void AddDataSight(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(sightNo.getText().toString().equals("")||sightName.getText().toString().equals("") || childTicPrice.getText().toString().equals("") || adultTicPrice.getText().toString().equals(""))){
                    boolean val = sightDB.insertDartaSight(sightNo.getText().toString(),sightName.getText().toString(), childTicPrice.getText().toString(), adultTicPrice.getText().toString());
                    if(val == true)
                        Toast.makeText(Add_new_sight.this, "New sight seen details added successfully ", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Add_new_sight.this, "Data already exists. ", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(Add_new_sight.this, "All fields are mandetory. Data  not inserted ", Toast.LENGTH_SHORT).show();
            }


        });


    }


    //details for new admin
    public void detailsForAdminSight(){
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = 0;

                StringBuffer buffer3 = new StringBuffer();
                while (x<1){

                    buffer3.append("\n INSERT DATA \nYou can only give interger number for sight seen no.\nYou can add data  as you wish.\nBut Sight Seen No is primary key.\nSo you can not add different data to same primary key.");
                    buffer3.append("\n \n \n UPDATE DATA \nWhen you want to update data you can update data according to relevant primary key.\nBut in here you have the feilds details.");
                    buffer3.append("\n \n \n DELETE DATA \nYou can delete data by given only primary key(Sight Seen No).\nThen relevant details according to primary key will be deleted from Data Base.");
                    buffer3.append("\n  \n \n  VIEW DATA \nYou can view details which you add before.");
                    buffer3.append("\n");
                    buffer3.append("");
                    buffer3.append("");
                    x++;

                }
                //show all data
                showMessage2("Details for new admin",buffer3.toString());

            }
        });


    }
    public void showMessage2(String title,String Message){
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setCancelable(true);
        builder2.setTitle(title);
        builder2.setMessage(Message);
        builder2.show();
    }



    //View details
    public void viewAllSight(){
        select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = sightDB.readAllInfoSight();
                if (res.getCount() == 0){
                    //show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){

                    buffer.append("Sight Seen No :"+res.getString(0)+"\n");
                    buffer.append("Sight Seen Name :"+res.getString(1)+"\n");
                    buffer.append("Ticket Price For Child :"+res.getString(2)+"\n");
                    buffer.append("Ticket Price For Adult  :"+res.getString(3)+"\n \n");

                }
                //show all data
                showMessage("Data",buffer.toString());
            }
        });
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    public void UpdateDataSight(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((sightNo.getText().toString().equals("")||sightName.getText().toString().equals("") || childTicPrice.getText().toString().equals("") || adultTicPrice.getText().toString().equals(""))) {
                    Toast.makeText(Add_new_sight.this, "No data to update.Select data first. ", Toast.LENGTH_SHORT).show();
                }else{
                    boolean isUpdated= sightDB.updateDataSight(sightNo.getText().toString(),sightName.getText().toString(),childTicPrice.getText().toString(),adultTicPrice.getText().toString());
                if (isUpdated == true){
                    Toast.makeText(Add_new_sight.this, "Data updated successfully ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Add_new_sight.this, "Data Not Updated ", Toast.LENGTH_SHORT).show();
                }
            }}
        });

    }

    //delete data
    public void DeleteDataSight(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = sightDB.deleteDataSight(sightNo.getText().toString());
                if (deletedRows > 0){
                    Toast.makeText(Add_new_sight.this, "Data Deleted ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Add_new_sight.this, "No Data To Deleted.Select Data First ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void openActivity2(){
        Intent intent2 = new Intent(this, sightseen.class);
        startActivity(intent2);
    }


}
