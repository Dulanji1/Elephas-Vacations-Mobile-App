package com.sliit.project_elephas.hasinthi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "hasinthiDb.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Database_hotel.hotelEntry.TABLE_HOTEL + " (" +
                    Database_hotel.hotelEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_NAME + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_Address + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_EMAIL + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_PHONE + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_STARCLASS + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_SINGLE + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_DOUBLE + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_TRIPLE + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_KING + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_QUARD + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_QUEEN + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_ROOMONLY + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_BEDANDBREACKFAST + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_FULLBOARD + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_HALFBOARD + " TEXT)";


    private String DROP_HOTEL_TABLE = "DROP TABLE IF EXISTS " + Database_hotel.hotelEntry.TABLE_HOTEL;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_HOTEL_TABLE);
        onCreate(db);
    }



    //add payments
    public boolean addHotelDetails(Hotel hotel) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();


        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_NAME, hotel.getName());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_Address, hotel.getAddress());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_EMAIL, hotel.getEmail());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_PHONE, hotel.getPhone());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_STARCLASS, hotel.getStarclass());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_SINGLE, hotel.getSingle());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_DOUBLE, hotel.getDouble());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_TRIPLE, hotel.getTriple());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_KING, hotel.getKing());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_QUARD, hotel.getQuard());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_QUEEN, hotel.getQueen());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_ROOMONLY, hotel.getRoomonly());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_BEDANDBREACKFAST, hotel.getBedandbreackfast());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_FULLBOARD, hotel.getFullboard());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_HALFBOARD, hotel.getHalfboard());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Database_hotel.hotelEntry.TABLE_HOTEL, null, values);

        db.close();

        return true;

    }

}
