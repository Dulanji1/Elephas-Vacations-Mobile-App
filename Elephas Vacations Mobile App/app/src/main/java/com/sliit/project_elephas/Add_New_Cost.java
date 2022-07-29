<<<<<<< HEAD
package com.example.sightseeen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
=======
package com.sliit.project_elephas;
>>>>>>> origin/master

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

<<<<<<< HEAD
import com.example.sightseeen.Database.DBHelper;
=======
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.sliit.project_elephas.Database.DBHelper;

>>>>>>> origin/master

public class Add_New_Cost extends AppCompatActivity {
    Button select_all, add, delete, update,details;
    EditText packNo,packName,packDis,packPrice;
    DBHelper costDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__cost);


        costDb = new DBHelper(this);
        SQLiteDatabase db = costDb.getReadableDatabase();

        // Hooks
        details = findViewById(R.id.details_btn);
        select_all = findViewById(R.id.selectAll_btn);
        add = findViewById(R.id.add_btn);
        update = findViewById(R.id.update_btn);
        delete = findViewById(R.id.delete_btn);

        packNo = findViewById(R.id.pno);
        packName = findViewById(R.id.pName);
        packDis = findViewById(R.id.pDis);
        packPrice = findViewById(R.id.pPric);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();
        detailsForAdmin();

    }
    public void onBackPressed(){
        Intent intent2 = new Intent(this, Other_Coast_Details.class);
        startActivity(intent2);
        super.onBackPressed();
        finish();
    }

    public void AddData(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(packNo.getText().toString().equals("")||packName.getText().toString().equals("") || packDis.getText().toString().equals("") || packPrice.getText().toString().equals(""))){
                    boolean val = costDb.insertDarta(packNo.getText().toString(),packName.getText().toString(), packDis.getText().toString(), packPrice.getText().toString());
                    if(val == true)
                        Toast.makeText(Add_New_Cost.this, "New cost package added successfully ", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Add_New_Cost.this, "Data already exists. ", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(Add_New_Cost.this, "All fields are mandetory. Data  not inserted ", Toast.LENGTH_SHORT).show();
            }


        });


    }
    //details for new admin
    public void detailsForAdmin(){
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int x = 0;

                StringBuffer buffer2 = new StringBuffer();
                while (x<1){

                    buffer2.append("\n INSERT DATA \nYou can only give interger number for package no.\nYou can add data  as you wish.\nBut Package No is primary key.\nSo you can not add different data to same primary key.");
                    buffer2.append("\n \n \n UPDATE DATA \nWhen you want to update data you can update data according to relevant primary key.\nBut in here you have the feilds details.");
                    buffer2.append("\n \n \n DELETE DATA \nYou can delete data by given only primary key(Package No).\nThen relevant details according to primary key will be deleted from Data Base.");
                    buffer2.append("\n  \n \n  VIEW DATA \nYou can view details which you add before.");
                    buffer2.append("\n");
                    buffer2.append("");
                    buffer2.append("");
                    x++;

                }
                //show all data
                showMessage2("Details for new admin",buffer2.toString());

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
    public void viewAll(){
        select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Cursor res = costDb.readAllInfo();
                 if (res.getCount() == 0){
                     //show message
                     showMessage("Error","Nothing found");
                     return;
                 }
                 StringBuffer buffer = new StringBuffer();
                 while (res.moveToNext()){

                     buffer.append("packageNo :"+res.getString(0)+"\n");
                     buffer.append("packageName :"+res.getString(1)+"\n");
                     buffer.append("packageDis :"+res.getString(2)+"\n");
                     buffer.append("packagePrice :"+res.getString(3)+"\n \n");

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



    public void UpdateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((packNo.getText().toString().equals("")||packName.getText().toString().equals("") || packDis.getText().toString().equals("") || packPrice.getText().toString().equals(""))) {
                    Toast.makeText(Add_New_Cost.this, "No data to update.Select data first. ", Toast.LENGTH_SHORT).show();
                }else{
                    boolean isUpdated= costDb.updateData(packNo.getText().toString(),packName.getText().toString(),packDis.getText().toString(),packPrice.getText().toString());
                    if (isUpdated == true){
                        Toast.makeText(Add_New_Cost.this, "Data updated successfully ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Add_New_Cost.this, "Data Not Updated ", Toast.LENGTH_SHORT).show();
                    }
                }}
        });

    }



    //delete data
    public void DeleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = costDb.deleteData(packNo.getText().toString());
                if (deletedRows > 0){
                    Toast.makeText(Add_New_Cost.this, "Data Deleted ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Add_New_Cost.this, "Data Not Deleted ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
