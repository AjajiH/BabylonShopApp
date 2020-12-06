package com.example.babylonshopapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "babylon.db";

//    public static final String USER_TABLE = "user_table";
//
//    public static final String USER_COL_1 = "email";
//    public static final String USER_COL_2 = "password";
//    public static final String USER_COL_3 = "first_name";
//    public static final String USER_COL_4 = "last_name";
//    public static final String USER_COL_5 = "phone";
//
//
//    public static final String PRODUCT_TABLE = "product_table";
//    public static final String CART_TABLE = "cart_table";
//    public static final String PAYMENT_TABLE = "payment_table";


    
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (email TEXT PRIMARY KEY, password TEXT, first_name TEXT, last_name TEXT, phone TEXT )");// create users table
        db.execSQL("CREATE TABLE cart (email TEXT,p_id INTEGER,quantity INTEGER)"); //create cart table
        db.execSQL("CREATE TABLE orders (o_id INTEGER, ordered_items TEXT, date_placed INTEGER,Address TEXT, Status TEXT,cust_email TEXT,track TEXT)"); //create orders table
        db.execSQL("CREATE TABLE  payment (Owner TEXT,type TEXT,num TEXT,name TEXT,exp TEXT,cvv TEXT,billing TEXT)");
        db.execSQL("CREATE TABLE product (p_id INTEGER, name TEXT,price REAL, quantity INTEGER, category TEXT, image TEXT, added_by TEXT, description TEXT)");// create product table


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS cart");
        db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS payment");
        db.execSQL("DROP TABLE IF EXISTS product");
        onCreate(db);
    }

    //--------------------------User Related Functions Start---------------------------------
    public Boolean addUser(String email, String password, String first_name, String last_name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("phone", phone);
        long result = db.insert("users", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean validateUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        if (result.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean validateUserAndPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM users WHERE email = ? and password = ?", new String[]{email, password});
        if (result.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor getOrders(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM orders WHERE email = ?", new String[]{email});
        return result;
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM users", null);
        return result;
    }
    //--------------------------User Related Functions End------------------------------------

    //--------------------------Cart Related Functions Start---------------------------------
    public Boolean addCart(String email, int p_id, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("p_id", p_id);
        contentValues.put("quantity", quantity);
        long result = db.insert("cart", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM cart", null);
        return result;
    }

    //--------------------------Cart Related Functions End---------------------------------

    //--------------------------Orders Related Functions Start---------------------------------
    public Boolean addOrder(int o_id,String ordered_items, int date_placed,String Address, String Status,String cust_email,String track) {
        //track TEXT
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("o_id", o_id);
        contentValues.put("ordered_items", ordered_items);
        contentValues.put("date_placed", date_placed);
        contentValues.put("Address", Address);
        contentValues.put("Status", Status);
        contentValues.put("cust_email", cust_email);
        contentValues.put("track", track);
        long result = db.insert("orders", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllOrders(String cust_email) {
        // this function retrieve all orders related to customer email
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM orders WHERE cust_email="+cust_email, null);
        return result;
    }

    public Cursor getOrdersHistory(String cust_email) {
        // this function retrieve all shipped orders related to customer email (History)
        //note to make this function work when you proceed checkout adding an order the status should be assigned "confirmed" label
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM orders WHERE cust_email="+cust_email+" and Status=confirmed", null);
        return result;
    }

    //--------------------------Orders Related Functions End---------------------------------

    //--------------------------Payment Related Functions Start---------------------------------
    public Boolean addPayment(String Owner,String type, String num,String name, String exp,String cvv,String billing) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Owner", Owner);
        contentValues.put("type", type);
        contentValues.put("num", num);
        contentValues.put("name", name);
        contentValues.put("exp", exp);
        contentValues.put("cvv", cvv);
        contentValues.put("billing", billing);
        long result = db.insert("payment", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllPayment(String cust_email) {
        // this function retrieve all payments
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM payment", null);
        return result;
    }

    //--------------------------Payment Related Functions End---------------------------------

    //--------------------------Product Related Functions Start---------------------------------

    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM product", null);
        return result;
    }
    public Cursor getProductsCategory(String category) {
        //This function gets you products with specific category (bedroom,bathroom,kitchen,living room)
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM product where category="+category, null);
        return result;
    }


    //--------------------------Product Related Functions End---------------------------------




}
