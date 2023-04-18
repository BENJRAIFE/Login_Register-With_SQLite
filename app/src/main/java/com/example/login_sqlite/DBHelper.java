package com.example.login_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static String DBNAME="Login.db";

    public DBHelper( Context context) {
        super(context,"Login.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("create Table users(username TEXT primary key,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int i, int i1) {

        Mydb.execSQL("drop Table if exists users ");
    }
    public boolean insertData(String username,String password) {

     SQLiteDatabase MYdb=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result =MYdb.insert("users",null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean checkusername(String username) {
        SQLiteDatabase MyDb=this.getWritableDatabase();
        Cursor cursor=MyDb.rawQuery("select * from users where username = ? ",new String[]{username});
        if (cursor.getCount()>0){
            return true;
        }else  return false;

    }
    public boolean checkUsernamePassword(String username,String password) {
        SQLiteDatabase MyDb=this.getWritableDatabase();
        Cursor cursor=MyDb.rawQuery("select * from users where username=? and password = ? ",new String[]{username,password});
        if (cursor.getCount()>0){
            return true;
        }else  return false;

    }


}
