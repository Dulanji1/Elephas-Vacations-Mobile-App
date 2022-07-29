package com.sliit.project_elephas;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    /*  CUSTOMER REQUIREMENTS======================================================================================================================*/

    public static final String DATABASE_NAME = "ElephasVacations.db";
    public static final String TABLE_NAME = "customer_requirement_table";   //CUSTOMER REQUIREMENT TABLE
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

    public static final String TABLE_NAME_CUSTOMER = "customer_table";   //CUSTOMER TABLE
    public static final String CUSTOMER_TABLE_COL1 = "ID";
    public static final String CUSTOMER_TABLE_COL2 = "NAME";
    public static final String CUSTOMER_TABLE_COL3 = "NATIONALITY";
    public static final String CUSTOMER_TABLE_COL4 = "PASSPORTNO";
    public static final String CUSTOMER_TABLE_COL5 = "EMAIL";
    public static final String CUSTOMER_TABLE_COL6 = "PHONE";
    public static final String CUSTOMER_TABLE_COL7 = "PASSWORD";

    /*  END - CUSTOMER ======================================================================================================================*/


    /*  ADMIN ======================================================================================================================*/

    public static final String TABLE_NAME_ADMIN = "admin_table";   //ADMIN TABLE
    public static final String ADMIN_TABLE_COL1 = "ID";
    public static final String ADMIN_TABLE_COL2 = "NAME";
    public static final String ADMIN_TABLE_COL3 = "NIC";
    public static final String ADMIN_TABLE_COL4 = "PASSPORTNO";
    public static final String ADMIN_TABLE_COL5 = "EMAIL";
    public static final String ADMIN_TABLE_COL6 = "PHONE";
    public static final String ADMIN_TABLE_COL7 = "PASSWORD";

    /*  END - ADMIN ======================================================================================================================*/




    /*  TABLE CREATION SQL QUERIES ======================================================================================================================*/

    //  1) CUSTOMER REQUIREMENT TABLE CREATION QUERY    //SQL query to create a table to keep the CUSTOMER REQUIREMENT details...
    public static final String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+" ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CUSTOMER_TABLE_COL4+" TEXT NOT NULL,"+COL3+" TEXT NOT NULL,"+COL4+" TEXT NOT NULL,"+COL5+" TEXT NOT NULL,"+COL6+" TEXT NOT NULL,"+COL7+" TEXT NOT NULL,"+COL8+" TEXT NOT NULL, FOREIGN KEY ("+CUSTOMER_TABLE_COL4+") REFERENCES "+TABLE_NAME_CUSTOMER+" ("+CUSTOMER_TABLE_COL4+") ON UPDATE CASCADE ON DELETE CASCADE)";

    //  2) CUSTOMER TABLE CREATION QUERY    //SQL query to create a table to keep the CUSTOMER details...
    public static final String CREATE_CUSTOMER_TABLE ="CREATE TABLE "+TABLE_NAME_CUSTOMER+" ("+CUSTOMER_TABLE_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CUSTOMER_TABLE_COL2+" TEXT NOT NULL,"+CUSTOMER_TABLE_COL3+" TEXT NOT NULL,"+CUSTOMER_TABLE_COL4+" TEXT NOT NULL UNIQUE,"+CUSTOMER_TABLE_COL5+" TEXT NOT NULL UNIQUE,"+CUSTOMER_TABLE_COL6+" TEXT NOT NULL,"+CUSTOMER_TABLE_COL7+" TEXT NOT NULL)";

    //  3) ADMIN TABLE CREATION QUERY   //SQL query to create a table to keep the ADMIN details...
    public static final String CREATE_ADMIN_TABLE ="CREATE TABLE "+TABLE_NAME_ADMIN+" ("+ADMIN_TABLE_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ADMIN_TABLE_COL2+" TEXT NOT NULL,"+ADMIN_TABLE_COL3+" TEXT NOT NULL,"+ADMIN_TABLE_COL4+" TEXT UNIQUE NOT NULL,"+ADMIN_TABLE_COL5+" TEXT NOT NULL,"+ADMIN_TABLE_COL6+" TEXT NOT NULL,"+ADMIN_TABLE_COL7+" TEXT NOT NULL)";

    /*  END - TABLE CREATION SQL QUERIES ======================================================================================================================*/


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase(); //checking purpose
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_ADMIN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);   //table name of the CUSTOMER REQUIREMENTS TABLE
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CUSTOMER);  //TABLE NAME OF THE CUSTOMER TABLE
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ADMIN);  //TABLE NAME OF THE CUSTOMER TABLE
        onCreate(db);

    }


    /*  METHODS - (1)    CUSTOMER REQUIREMENTS======================================================================================================================*/

    // insert data
    public boolean insertData(String passportno,String noofpeople,String arrivaldata,String departuredate,String noofdays,String starcategory,String remarks) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,passportno);
        //contentValues.put(COL3,nationality);
        contentValues.put(COL3,noofpeople);
        contentValues.put(COL4,arrivaldata);
        contentValues.put(COL5,departuredate);
        contentValues.put(COL6,noofdays);
        contentValues.put(COL7,starcategory);
        contentValues.put(COL8,remarks);
        long result =  db.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            //db.close();
            return false;
        } else {
            //db.close();
            return true;
        }
    }


    // view data
    public Cursor getCustomerData(String passportno) {
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME_CUSTOMER+" WHERE "+CUSTOMER_TABLE_COL4+" ='"+passportno+"'",null);
        return res;
    }

    public Cursor getCustomerRequirementData(String passportno) {
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+CUSTOMER_TABLE_COL4+" ='"+passportno+"'",null);
        return res;
    }


    // view data
    public Cursor getAllRequirementsForACustomerData(String passportno) {
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }

    public Cursor getAllCustomerRequirementDBForAdminView () {

        //<CustomerRequirement> dbCustomerRequirement = new ArrayList<CustomerRequirement>();

        SQLiteDatabase db =  this.getReadableDatabase();
        //Cursor res = db.rawQuery("SELECT "+TABLE_NAME+"."+COL1+", "+CUSTOMER_TABLE_COL2+","+CUSTOMER_TABLE_COL3+","+CUSTOMER_TABLE_COL4+","+CUSTOMER_TABLE_COL5+","+CUSTOMER_TABLE_COL6+","+COL3+","+COL4+","+COL5+","+COL6+","+COL7+","+COL8+" FROM "+TABLE_NAME_CUSTOMER+" INNER JOIN "+TABLE_NAME+" ON "+TABLE_NAME_CUSTOMER+"."+CUSTOMER_TABLE_COL4+" = "+TABLE_NAME+"."+COL2+"",null);
        Cursor res = db.rawQuery("SELECT "+TABLE_NAME+"."+COL1+", "+CUSTOMER_TABLE_COL2+","+CUSTOMER_TABLE_COL3+","+TABLE_NAME_CUSTOMER+"."+CUSTOMER_TABLE_COL4+","+CUSTOMER_TABLE_COL5+","+CUSTOMER_TABLE_COL6+","+COL3+","+COL4+","+COL5+","+COL6+","+COL7+","+COL8+" FROM "+TABLE_NAME_CUSTOMER+" INNER JOIN "+TABLE_NAME+" ON "+TABLE_NAME_CUSTOMER+"."+CUSTOMER_TABLE_COL4+" = "+TABLE_NAME+"."+COL2+"",null);

        return res;
    }

    /*// insert data
        public boolean insertData(String name,String nationality,String noofpeople,String arrivaldata,String departuredate,String noofdays,String starcategory,String remarks) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,nationality);
        contentValues.put(COL4,noofpeople);
        contentValues.put(COL5,arrivaldata);
        contentValues.put(COL6,departuredate);
        contentValues.put(COL7,noofdays);
        contentValues.put(COL8,starcategory);
        contentValues.put(COL9,remarks);
        long result =  db.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }
    */


/*    // view data
    public Cursor getAllData() {
        SQLiteDatabase db =  this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }*/

    // update data
    public boolean updateData(String id,String passportno,String noofpeople,String arrivaldata,String departuredate,String noofdays,String starcategory,String remarks) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL2,name);
        //contentValues.put(COL3,nationality);
        contentValues.put(CUSTOMER_TABLE_COL4,passportno);
        contentValues.put(COL3,noofpeople);
        contentValues.put(COL4,arrivaldata);
        contentValues.put(COL5,departuredate);
        contentValues.put(COL6,noofdays);
        contentValues.put(COL7,starcategory);
        contentValues.put(COL8,remarks);
        db.update(TABLE_NAME,contentValues,COL1 + "=?", new String[] { id });

        return true;
    }


    // delete data
    public Integer deleteData (String id) {
        SQLiteDatabase db =  this.getWritableDatabase();
        return db.delete(TABLE_NAME,COL1+"=?",new String[]{id});
    }

    /*  END OF THE SECTION - METHODS - CUSTOMER REQUIREMENTS======================================================================================================================*/


    /*  METHODS - (2)   CUSTOMER ======================================================================================================================*/

    // insert data
    public boolean insertCustomerData(String name,String nationality,String passportno,String email,String phone,String password) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_TABLE_COL2,name);
        contentValues.put(CUSTOMER_TABLE_COL3,nationality);
        contentValues.put(CUSTOMER_TABLE_COL4,passportno);
        contentValues.put(CUSTOMER_TABLE_COL5,email);
        contentValues.put(CUSTOMER_TABLE_COL6,phone);
        contentValues.put(CUSTOMER_TABLE_COL7,password);

        //data insertion happening here
        long result =  db.insert(TABLE_NAME_CUSTOMER,null,contentValues);

        if (result == -1){
            //if data insertion is unsuccessful this will return false
            db.close();
            return false;
        } else {
            //if data insertion is successful this will return true
            db.close();
            return true;
        }
    }


    // view data
    public Cursor getAllCustomerData() {
        SQLiteDatabase db =  this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME_CUSTOMER,null);
        return res;
    }

    // update data
    public boolean updateCustomerData(String id,String name,String nationality,String passportno,String email,String phone,String password) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_TABLE_COL2,name);
        contentValues.put(CUSTOMER_TABLE_COL3,nationality);
        contentValues.put(CUSTOMER_TABLE_COL4,passportno);
        contentValues.put(CUSTOMER_TABLE_COL5,email);
        contentValues.put(CUSTOMER_TABLE_COL6,phone);
        contentValues.put(CUSTOMER_TABLE_COL7,password);
        db.update(TABLE_NAME_CUSTOMER,contentValues,CUSTOMER_TABLE_COL1 + "=?", new String[] { id });

        return true;
    }

    // delete data
    public Integer deleteCustomerData (String id) {
        SQLiteDatabase db =  this.getWritableDatabase();
        return db.delete(TABLE_NAME_CUSTOMER,CUSTOMER_TABLE_COL1+"=?",new String[]{id});
    }

    /*  END OF THE SECTION - METHODS - CUSTOMER ======================================================================================================================*/




    /*  METHODS - (3)   ADMIN ======================================================================================================================*/

    // insert data      //this is used to insert a ADMIN
    public boolean insertAdminData(String name,String nic,String passportno,String email,String phone,String password) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ADMIN_TABLE_COL2,name);
        contentValues.put(ADMIN_TABLE_COL3,nic);
        contentValues.put(ADMIN_TABLE_COL4,passportno);
        contentValues.put(ADMIN_TABLE_COL5,email);
        contentValues.put(ADMIN_TABLE_COL6,phone);
        contentValues.put(ADMIN_TABLE_COL7,password);
        long result =  db.insert(TABLE_NAME_ADMIN,null,contentValues);

        if (result == -1){
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }


    // view data        //this is used to insert a Customer = User here
    public Cursor getAllAdminData() {
        SQLiteDatabase db =  this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME_ADMIN,null);
        return res;
    }

    // update data      //this is used to insert a Customer = User here
    public boolean updateAdminData(String id,String name,String nic,String passportno,String email,String phone,String password) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ADMIN_TABLE_COL2,name);
        contentValues.put(ADMIN_TABLE_COL3,nic);
        contentValues.put(ADMIN_TABLE_COL4,passportno);
        contentValues.put(ADMIN_TABLE_COL5,email);
        contentValues.put(ADMIN_TABLE_COL6,phone);
        contentValues.put(ADMIN_TABLE_COL7,password);

        db.update(TABLE_NAME_ADMIN,contentValues,ADMIN_TABLE_COL1 + "=?", new String[] { id });
        return true;
    }


    // delete data      //this is used to insert a Customer = User here
    public Integer deleteAdminData (String id) {
        SQLiteDatabase db =  this.getWritableDatabase();
        return db.delete(TABLE_NAME_ADMIN,ADMIN_TABLE_COL1+"=?",new String[]{id});
    }

    /*  END OF THE SECTION - METHODS - ADMIN ======================================================================================================================*/



    //Lognin

    public Customer getLoginCustomerCredentials(String userOradmin, String password) {
        SQLiteDatabase db =  this.getWritableDatabase();
        String QUERY_CUSTOMER_TABLE = "SELECT *" + "FROM " + TABLE_NAME_CUSTOMER;
        //String QUERY_ADMIN_TABLE = "SELECT " + ADMIN_TABLE_COL3 + "," + ADMIN_TABLE_COL7 + "FROM " + TABLE_NAME_ADMIN;

        Cursor res = db.rawQuery(QUERY_CUSTOMER_TABLE,null);
        String passportDB,emailDB,pwd=null;
        Customer newCustomer = new Customer();

        if (res.moveToFirst()) {
            do {
                passportDB = res.getString(3);
                emailDB = res.getString(4);

                //check database-username == user-given-username
                if (passportDB.equals(userOradmin) || emailDB.equals(userOradmin)) {
                    pwd = res.getString(6);

                    //if the password is matched then take the entire record details and put it to a customer object and return it
                    if (pwd.equals(password)) {
                        newCustomer.setId(Integer.parseInt(res.getString(0)));
                        newCustomer.setName(res.getString(1));
                        newCustomer.setNationality(res.getString(2));
                        newCustomer.setPassportno(res.getString(3));
                        newCustomer.setEmail(res.getString(4));
                        newCustomer.setPhone(res.getString(5));
                        newCustomer.setPassword(res.getString(6));
                        break;
                    }

                }

            } while (res.moveToNext());

        }

        return newCustomer;
    }

    public Admin getLoginAdminCredentials(String userOradmin, String password) {
        SQLiteDatabase db =  this.getWritableDatabase();
        //String QUERY_CUSTOMER_TABLE = "SELECT *" + "FROM " + TABLE_NAME_ADMIN;
        String QUERY_ADMIN_TABLE = "SELECT *"+ "FROM " + TABLE_NAME_ADMIN;

        Cursor res = db.rawQuery(QUERY_ADMIN_TABLE,null);
        String nicDB,emailDB,pwd=null;
        Admin newAdmin = new Admin();

        if (res.moveToFirst()) {
            do {
                nicDB = res.getString(2);
                emailDB = res.getString(4);

                //check database-username == user-given-username
                if (nicDB.equals(userOradmin) || emailDB.equals(userOradmin)) {
                    pwd = res.getString(6);

                    //if the password is matched then take the entire record details and put it to a customer object and return it
                    if (pwd.equals(password)) {
                        newAdmin.setId(Integer.parseInt(res.getString(0)));
                        newAdmin.setName(res.getString(1));
                        newAdmin.setNic(res.getString(2));
                        newAdmin.setPassportno(res.getString(3));
                        newAdmin.setEmail(res.getString(4));
                        newAdmin.setPhone(res.getString(5));
                        newAdmin.setPassword(res.getString(6));
                        break;
                    }

                }

            } while (res.moveToNext());

        }

        return newAdmin;
    }

}
