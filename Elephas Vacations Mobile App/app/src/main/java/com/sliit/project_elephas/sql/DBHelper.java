package com.sliit.project_elephas.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sliit.project_elephas.hasinthi.Database_hotel;
import com.sliit.project_elephas.hasinthi.Hotel;
import com.sliit.project_elephas.model.Driver;
import com.sliit.project_elephas.model.Invoice;
import com.sliit.project_elephas.model.Payments;
import com.sliit.project_elephas.model.Transport;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "projectElephas.db";

    //Payment Table create
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Database_payment.paymentEntry.TABLE_NAME + " (" +
                    Database_payment.paymentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Database_payment.paymentEntry.COL_1 + " TEXT," +
                    Database_payment.paymentEntry.COL_2 + " TEXT," +
                    Database_payment.paymentEntry.COL_3 + " TEXT," +
                    Database_payment.paymentEntry.COL_4 + " TEXT," +
                    Database_payment.paymentEntry.COL_5 + " TEXT," +
                    Database_payment.paymentEntry.COL_6 + " TEXT," +
                    Database_payment.paymentEntry.COL_7 + " DOUBLE)";

    // drop table sql query
    private String DROP_PAYMENT_TABLE = "DROP TABLE IF EXISTS " + Database_payment.paymentEntry.TABLE_NAME;

    //Transport table
    private static final String SQL_CREATE_TRANSPORT_TABLE =
            "CREATE TABLE " + Database_transport.transportEntry.TABLE_TRANSPORT + " (" +
                    Database_transport.transportEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Database_transport.transportEntry.COL_1 + " INTEGER," +
                    Database_transport.transportEntry.COL_2 + " DOUBLE," +
                    Database_transport.transportEntry.COL_3 + " DOUBLE," +
                    Database_transport.transportEntry.COL_4 + " DOUBLE," +
                    Database_transport.transportEntry.COL_5 + " DOUBLE," +
                    Database_transport.transportEntry.COL_6 + " DOUBLE)";

    // drop table sql query
    private String DROP_TRANSPORT_TABLE = "DROP TABLE IF EXISTS " + Database_transport.transportEntry.TABLE_TRANSPORT;

    //Driver table
    private static final String SQL_CREATE_DRIVER_TABLE =
            "CREATE TABLE " + Database_driver.driverEntry.TABLE_DRIVER + " (" +
                    Database_driver.driverEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Database_driver.driverEntry.COL_1 + " INTEGER," +
                    Database_driver.driverEntry.COL_2 + " DOUBLE," +
                    Database_driver.driverEntry.COL_3 + " DOUBLE," +
                    Database_driver.driverEntry.COL_4 + " DOUBLE)";

    // drop table sql query
    private String DROP_TABLE_DRIVER = "DROP TABLE IF EXISTS " + Database_driver.driverEntry.TABLE_DRIVER;

    private static final String SQL_CREATE_HOTEL_TABLE =
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


    private String DROP_TABLE_HOTEL = "DROP TABLE IF EXISTS " + Database_hotel.hotelEntry.TABLE_HOTEL;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_TRANSPORT_TABLE);
        db.execSQL(SQL_CREATE_DRIVER_TABLE);
        db.execSQL(SQL_CREATE_HOTEL_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DROP_PAYMENT_TABLE);
        db.execSQL(DROP_TABLE_HOTEL);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /*
    Author : Isara
    payment function start from here !
    */

    //add payments
    public boolean addCustomerpayments(Payments payments) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Database_payment.paymentEntry.COL_1, payments.getUser_email());
        values.put(Database_payment.paymentEntry.COL_2, payments.getName());
        values.put(Database_payment.paymentEntry.COL_3, payments.getAddress());
        values.put(Database_payment.paymentEntry.COL_4, payments.getNic());
        values.put(Database_payment.paymentEntry.COL_5, payments.getCard());
        values.put(Database_payment.paymentEntry.COL_7, payments.getAmount());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Database_payment.paymentEntry.TABLE_NAME, null, values);

        db.close();

        return true;

    }

    //read payment ID to spinner
    public ArrayList<Payments> readAllPayments(){

        ArrayList<Payments> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();

        Cursor results = scoredb.rawQuery("select * from "+ Database_payment.paymentEntry.TABLE_NAME,null);

        results.moveToFirst();

        while (results.isAfterLast()==false){

            Payments smodel= new Payments();

            smodel.setID(results.getInt(results.getColumnIndex(Database_payment.paymentEntry._ID)));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    //read data where id equals in spinner
    public ArrayList<Payments> selectedPayment(String id){

        ArrayList<Payments> models= new ArrayList<>();
        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_payment.paymentEntry.TABLE_NAME +" where "+Database_payment.paymentEntry._ID +" = '"+id+"'",null);
        results.moveToFirst();

        while (results.isAfterLast()==false){

            Payments smodel= new Payments();

            smodel.setUser_email(results.getString(1));
            smodel.setName(results.getString(2));
            smodel.setAddress(results.getString(3));
            smodel.setNic(results.getString(4));
            smodel.setCard(results.getString(5));
            smodel.setAmount(Double.parseDouble(results.getString(7)));
            smodel.setStatus(results.getString(6));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    //if status is verified
    public boolean updatePayment(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("status","Verified");

        long res=db.update(Database_payment.paymentEntry.TABLE_NAME,contentValues,Database_payment.paymentEntry._ID+" = '"+id+"'",null);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    //delete payment
    public boolean refundPayment(String id) {

        SQLiteDatabase db = getReadableDatabase();

        long res = db.delete(Database_payment.paymentEntry.TABLE_NAME, Database_payment.paymentEntry._ID + " = '" + id + "'", null);

        db.close();

        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }

    //Transport DB helper start here
    //add payments
    public boolean addtransport(Transport transport) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(Database_transport.transportEntry.COL_1, transport.getPassport());
        values.put(Database_transport.transportEntry.COL_2, transport.getGrossMileage());
        values.put(Database_transport.transportEntry.COL_3, transport.getExtraMileage());
        values.put(Database_transport.transportEntry.COL_4, transport.getTotalMileage(transport.getGrossMileage(),transport.getExtraMileage()));
        values.put(Database_transport.transportEntry.COL_5, transport.getChargePerKm());


        //values.put(Database_transport.transportEntry.COL_6, transport.getTotalUsd(transport.getTotalMileage(),transport.getExtraMileage()));

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Database_transport.transportEntry.TABLE_TRANSPORT, null, values);

        db.close();

        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }

    }

    //read payment ID to spinner
    public ArrayList<Transport> readAllTransports(){

        ArrayList<Transport> models= new ArrayList<>();

        SQLiteDatabase score = this.getReadableDatabase();

        Cursor results = score.rawQuery("select * from "+ Database_transport.transportEntry.TABLE_TRANSPORT,null);

        results.moveToFirst();

        while (results.isAfterLast()==false){

            Transport transmodel= new Transport();

            transmodel.setID(results.getInt(results.getColumnIndex(Database_transport.transportEntry._ID)));
            models.add(transmodel);
            results.moveToNext();
        }

        return models;

    }

    //read data where id equals in spinner
    public ArrayList<Transport> selectedTransports(String id){

        ArrayList<Transport> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_transport.transportEntry.TABLE_TRANSPORT +" where "+Database_transport.transportEntry._ID +" = '"+id+"'",null);
        results.moveToFirst();

        while (results.isAfterLast()==false){

            Transport smodel= new Transport();
            smodel.setPassport(results.getInt(1));
            smodel.setGrossMileage(results.getDouble(2));
            smodel.setExtraMileage(results.getDouble(3));
            smodel.setTotalMileage(results.getDouble(4));
            smodel.setChargePerKm(results.getDouble(5));
            smodel.setTotalUsd(results.getDouble(6));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    //Update transport
    public boolean updateTransport(String id,int passport, double gross, double extra, double chargeKm, double totalDistance, double totalUsd){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();

        contentValues.put(Database_transport.transportEntry.COL_1, passport);
        contentValues.put(Database_transport.transportEntry.COL_2, gross);
        contentValues.put(Database_transport.transportEntry.COL_3, extra);
        contentValues.put(Database_transport.transportEntry.COL_4, totalDistance);
        contentValues.put(Database_transport.transportEntry.COL_5, chargeKm);
        contentValues.put(Database_transport.transportEntry.COL_6, totalUsd);

        //contentValues.put("status","Verified");

        long res=db.update(Database_transport.transportEntry.TABLE_TRANSPORT,contentValues,Database_transport.transportEntry._ID+" = '"+id+"'",null);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    //delete transport
    public boolean deletetransport(String id) {

        SQLiteDatabase db = getReadableDatabase();

        long res = db.delete(Database_transport.transportEntry.TABLE_TRANSPORT, Database_transport.transportEntry._ID + " = '" + id + "'", null);

        db.close();

        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }

    //Add driver cost
    public boolean addDriver(Driver driver) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(Database_driver.driverEntry.COL_1, driver.getPassport());
        values.put(Database_driver.driverEntry.COL_2, driver.getDriver());
        values.put(Database_driver.driverEntry.COL_3, driver.getGuide());
        values.put(Database_driver.driverEntry.COL_4, driver.getTotal_in_usd(driver.getDriver(),driver.getGuide()));

        // Insert the new row, returning the primary key value of the new row
        //long newRowId = db.insert(Database_Driver_Guide.DriverEntry.TABLE_DRIVER, null, values);
        long newRowId = db.insert(Database_driver.driverEntry.TABLE_DRIVER, null, values);

        //db.close();

        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }

    }

    //read payment ID to spinner
    public ArrayList<Driver> readAllDrivers(){

        ArrayList<Driver> models= new ArrayList<>();

        SQLiteDatabase score = this.getReadableDatabase();

        Cursor results = score.rawQuery("select * from "+ Database_driver.driverEntry.TABLE_DRIVER,null);

        results.moveToFirst();

        while (!results.isAfterLast()){

            Driver driver = new Driver();

            driver.setID(results.getInt(results.getColumnIndex(Database_driver.driverEntry._ID)));

            models.add(driver);
            results.moveToNext();
        }

        return models;

    }

    //read data where id equals in spinner
    public ArrayList<Driver> selectedDrivers(String id){

        ArrayList<Driver> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_driver.driverEntry.TABLE_DRIVER +" where "+Database_driver.driverEntry._ID +" = '"+id+"'",null);
        results.moveToFirst();

        while (!results.isAfterLast()){

            Driver smodel= new Driver();
            smodel.setPassport(results.getInt(1));
            smodel.setDriver(results.getDouble(2));
            smodel.setGuide(results.getDouble(3));
            smodel.setTotal_in_usd(results.getDouble(4));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    //Update transport
    public boolean updateDriver(String id,int passport, double driver, double guide, double totalUsd){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();

        contentValues.put(Database_driver.driverEntry.COL_1, passport);
        contentValues.put(Database_driver.driverEntry.COL_2, driver);
        contentValues.put(Database_driver.driverEntry.COL_3, guide);
        contentValues.put(Database_driver.driverEntry.COL_4, totalUsd);

        //contentValues.put("status","Verified");

        long res=db.update(Database_driver.driverEntry.TABLE_DRIVER,contentValues,Database_driver.driverEntry._ID+" = '"+id+"'",null);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    //delete transport
    public boolean deleteDriver(String id) {

        SQLiteDatabase db = getReadableDatabase();

        long res = db.delete(Database_driver.driverEntry.TABLE_DRIVER, Database_driver.driverEntry._ID + " = '" + id + "'", null);

        db.close();

        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }


    /*public ArrayList<Driver> selectedInvoiceDriver(String passport){

        ArrayList<Driver> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_driver.driverEntry.TABLE_DRIVER +" where "+Database_driver.driverEntry.COL_1 +" = '"+passport+"'",null);
        results.moveToFirst();

        while (!results.isAfterLast()){

            Driver smodel= new Driver();

            smodel.setTotal_in_usd(results.getDouble(4));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    public ArrayList<Transport> selectedInvoiceTransports(String passport){

        ArrayList<Transport> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_transport.transportEntry.TABLE_TRANSPORT +" where "+Database_transport.transportEntry.COL_1 +" = '"+passport+"'",null);
        results.moveToFirst();

        while (results.isAfterLast()==false){

            Transport smodel= new Transport();
            smodel.setTotalUsd(results.getDouble(6));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }

    public ArrayList<Invoice> selectedInvoice(String passport){
        ArrayList<Invoice> models= new ArrayList<>();

        selectedInvoiceDriver(passport);



        return  models;


    }*/


    public List<Invoice> selectedInvoice(String passport){

        List<Invoice> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();

        /*Cursor results = scoredb.rawQuery("select * from "+ Database_transport.transportEntry.TABLE_TRANSPORT +" where "+Database_transport.transportEntry.COL_1 +" = '"+passport+"'",null);
*/
        Cursor results = scoredb.rawQuery("select  t.passport, t.total_usd, d.total_usd from transport_cost t,driver_guide d where t."+Database_transport.transportEntry.COL_1 +" = '"+passport+"' and t.passport == d.passport",null);

        results.moveToFirst();

        while (results.isAfterLast()==false){

            Invoice smodel= new Invoice();
            smodel.setPassport(results.getInt(results.getColumnIndex("passport")));
            smodel.setTransport(results.getDouble(results.getColumnIndex("total_USD")));
            smodel.setDriver(results.getDouble(results.getColumnIndex("total_usd")));

            //smodel.setTotoalCost(results.getDouble(6));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }


    //add payments
    public boolean addHotelDetails(Hotel hotel) {

        try {
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

            long newRowId = db.insert(Database_hotel.hotelEntry.TABLE_HOTEL, null, values);

            return newRowId != -1;

        }catch (SQLException e){
            e.printStackTrace();
        }

        // Insert the new row, returning the primary key value of the new row
        return true;
    }

    //read payment ID to spinner
    public ArrayList<Hotel> readAllHotels(){

        ArrayList<Hotel> models= new ArrayList<>();

        SQLiteDatabase score = this.getReadableDatabase();

        Cursor results = score.rawQuery("select * from "+ Database_hotel.hotelEntry.TABLE_HOTEL,null);

        results.moveToFirst();

        while (!results.isAfterLast()){

            Hotel hotel = new Hotel();

            hotel.setName(results.getString(results.getColumnIndex(Database_hotel.hotelEntry._ID)));

            //driver.setID(results.getInt(results.getColumnIndex(Database_driver.driverEntry._ID)));

            models.add(hotel);
            results.moveToNext();
        }

        return models;

    }

    //read data where id equals in spinner
    public ArrayList<Hotel> selectedHotels(String id){

        ArrayList<Hotel> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_hotel.hotelEntry.TABLE_HOTEL +" where "+Database_hotel.hotelEntry._ID +" = '"+id+"'",null);
        results.moveToFirst();

        while (!results.isAfterLast()){

            Hotel smodel= new Hotel();

            smodel.setName(results.getString(1));
            smodel.setAddress(results.getString(2));
            smodel.setEmail(results.getString(3));
            smodel.setPhone(results.getString(4));
            smodel.setStarclass(results.getString(5));
            smodel.setSingle(results.getString(6));
            smodel.setDouble(results.getString(7));
            smodel.setTriple(results.getString(8));
            smodel.setKing(results.getString(9));
            smodel.setQuard(results.getString(10));
            smodel.setQueen(results.getString(11));
            smodel.setRoomonly(results.getString(12));
            smodel.setBedandbreackfast(results.getString(13));
            smodel.setFullboard(results.getString(14));
            smodel.setHalfboard(results.getString(15));

        }

        return models;

    }

   /* //read data where id equals in spinner
    public ArrayList<Driver> selectedDrivers(String id){

        ArrayList<Driver> models= new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from "+ Database_driver.driverEntry.TABLE_DRIVER +" where "+Database_driver.driverEntry._ID +" = '"+id+"'",null);
        results.moveToFirst();

        while (!results.isAfterLast()){

            Driver smodel= new Driver();
            smodel.setPassport(results.getInt(1));
            smodel.setDriver(results.getDouble(2));
            smodel.setGuide(results.getDouble(3));
            smodel.setTotal_in_usd(results.getDouble(4));
            models.add(smodel);
            results.moveToNext();

        }

        return models;

    }*/

    //Update transport
    public boolean updateHotel(String id,String name, String address, String email, String phone, String starclass, String single,
                               String aDouble, String triple, String king, String quard, String queen, String roomonly, String bedandbreackfast,
                               String fullboard, String halfboard){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();

        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_NAME, name);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_Address, address);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_EMAIL, email);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_PHONE, phone);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_STARCLASS, starclass);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_SINGLE, single);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_DOUBLE, aDouble);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_TRIPLE, triple);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_KING, king);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_QUARD, quard);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_QUEEN, queen);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_ROOMONLY, roomonly);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_BEDANDBREACKFAST, bedandbreackfast);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_FULLBOARD, fullboard);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_HALFBOARD, halfboard);

        //contentValues.put("status","Verified");

        long res=db.update(Database_hotel.hotelEntry.TABLE_HOTEL,contentValues,Database_hotel.hotelEntry._ID+" = '"+id+"'",null);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    //delete transport
    public boolean deleteHotel(String id) {

        SQLiteDatabase db = getReadableDatabase();

        long res = db.delete(Database_hotel.hotelEntry.TABLE_HOTEL, Database_hotel.hotelEntry._ID + " = '" + id + "'", null);

        db.close();

        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }


}
