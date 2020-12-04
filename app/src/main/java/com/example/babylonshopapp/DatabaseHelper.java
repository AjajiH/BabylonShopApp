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
        db.execSQL("CREATE TABLE users (email TEXT PRIMARY KEY, password TEXT, first_name TEXT, last_name TEXT, phone TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

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

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM users", null);
        return result;
    }
}
