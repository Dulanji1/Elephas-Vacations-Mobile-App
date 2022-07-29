package Databasedula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static Databasedula.CostMaster.Costs.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {

    //Data base  for other cost
    public static final String DATABASE_NAME = "ElephasOtherCostDetails.db";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
                CostMaster.Costs.COLUMN_NAME_PackageNo + " INTEGER PRIMARY KEY, " +
                CostMaster.Costs.COLUMN_NAME_PackageName+ " Text," +
                CostMaster.Costs.COLUMN_NAME_PackageDiscription+ " Text," +
                CostMaster.Costs.COLUMN_NAME_PackagePrice+" Text)";

        // Execute the SQL
        db.execSQL(SQL_CREATE_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method for add info
    public boolean insertDarta( String packageNo,String packageName,String packageDis,String packagePrice) {

        // Get the data repository in write mode
        SQLiteDatabase  db = this.getWritableDatabase();

        // Create new map of values where column names are the key
        ContentValues values = new ContentValues();
        values.put(CostMaster.Costs.COLUMN_NAME_PackageNo,packageNo);
        values.put(CostMaster.Costs.COLUMN_NAME_PackageName,packageName);
        values.put(CostMaster.Costs.COLUMN_NAME_PackageDiscription,packageDis);
        values.put(CostMaster.Costs.COLUMN_NAME_PackagePrice,packagePrice);

        // Insert the new row returning the primary key value of new row
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1){
            return false;
        }else
            return true;

    }


    // Method for read info
    public Cursor readAllInfo() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }


    //update data
      public boolean updateData(String packageNo, String packageName,String packageDis,String packagePrice){
           SQLiteDatabase db = this.getWritableDatabase();
           ContentValues values = new ContentValues();

           values.put(CostMaster.Costs.COLUMN_NAME_PackageNo,packageNo);
           values.put(CostMaster.Costs.COLUMN_NAME_PackageName,packageName);
           values.put(CostMaster.Costs.COLUMN_NAME_PackageDiscription,packageDis);
           values.put(CostMaster.Costs.COLUMN_NAME_PackagePrice,packagePrice);

           db.update(TABLE_NAME,values,"packageNo = ?",new String[]{packageNo});
           return true;

       }

       //delete data
    public Integer deleteData (String packageNo){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"packageNo = ?",new  String[] {packageNo});


    }


}
