package com.example.sightseeen.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.sightseeen.Database.SightseenMaster.Sightseen.TABLE_NAME_SightseenTabele;

public class DBHelperForSightSeen  extends SQLiteOpenHelper {

    //Data base  for sight seen details
    public static final String DATABASE_NAME_Sight_Seen = "SightSeenDetails.db";



    public DBHelperForSightSeen(@Nullable Context context) {
        super(context, DATABASE_NAME_Sight_Seen, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_ENTRIES2 = "CREATE TABLE " + TABLE_NAME_SightseenTabele + " (" +
                SightseenMaster.Sightseen.COLUMN_NAME_SightNo + " INTEGER PRIMARY KEY, " +
                SightseenMaster.Sightseen.COLUMN_NAME_SightName + " Text," +
                SightseenMaster.Sightseen.COLUMN_NAME_Child_Ticket_Price + " Text," +
                SightseenMaster.Sightseen.COLUMN_NAME_Adult_Ticket_Price +" Text)";

        // Execute the SQL
        db.execSQL(SQL_CREATE_ENTRIES2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SightseenTabele);
        onCreate(db);

    }


    // Method for add info to sight seen
    public boolean insertDartaSight( String sightNo,String sightName,String childTicPrice,String adultTicPrice) {

        // Get the data repository in write mode
        SQLiteDatabase  db = this.getWritableDatabase();

        // Create new map of values where column names are the key
        ContentValues values = new ContentValues();
        values.put(SightseenMaster.Sightseen.COLUMN_NAME_SightNo,sightNo);
        values.put(SightseenMaster.Sightseen.COLUMN_NAME_SightName,sightName);
        values.put(SightseenMaster.Sightseen.COLUMN_NAME_Child_Ticket_Price,childTicPrice);
        values.put(SightseenMaster.Sightseen.COLUMN_NAME_Adult_Ticket_Price,adultTicPrice);


        // Insert the new row returning the primary key value of new row
        long result = db.insert(TABLE_NAME_SightseenTabele, null, values);
        if (result == -1){
            return false;
        }else
            return true;

    }


    // Method for read info
    public Cursor readAllInfoSight() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res2 = db.rawQuery("select * from " + TABLE_NAME_SightseenTabele, null);
        return res2;
    }


    //update data
    public boolean updateDataSight(String sightNo,String sightName,String childTicPrice,String adultTicPrice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SightseenMaster.Sightseen.COLUMN_NAME_SightNo,sightNo);
        values.put(SightseenMaster.Sightseen.COLUMN_NAME_SightName,sightName);
        values.put(SightseenMaster.Sightseen.COLUMN_NAME_Child_Ticket_Price,childTicPrice);
        values.put(SightseenMaster.Sightseen.COLUMN_NAME_Adult_Ticket_Price,adultTicPrice);


        db.update(TABLE_NAME_SightseenTabele,values,"sightNo = ?",new String[]{sightNo});
        return true;

    }

    //delete data
    public Integer deleteDataSight (String sightNo){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_SightseenTabele,"sightNo = ?",new  String[] {sightNo});


    }


}
