package com.example.babylonshopapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "babylon.db";

    public static final String USER_TABLE = "users";

//    public static final String EMAIL = "email";
//    public static final String FIRST_NAME = "firstName";
//    public static final String LAST_NAME = "lastName";
//    public static final String PASS = "password";
//    public static final String PHONE = "phone";
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

    public Cursor retrieveAllData(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        return cursor;
    }

    public boolean updateUserProfile(String email, String firstName, String lastName, String pass, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("first_name", firstName);
        contentValues.put("last_name", lastName);
        contentValues.put("phone", phone);
        db.update("users", contentValues, "email = ?", new String[]{email});
        return true;
    }

    public Cursor getUser(String userID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE email = ?", new String[]{userID});
        return result;
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM users", null);
        return result;
    }
}
