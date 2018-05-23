package com.example.zhangj0585.mycontactapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_Version =1;
    public static final String DATABASE_NAME ="Contact2018.db";
    public static final String TABLE_NAME ="Contact2018_table";
    public static final String ID="ID";
    public static final String COLUMN_NAME_CONTACT ="contact";
    public static final String COLUMN_NAME_ADDRESS ="address";
    public static final String COLUMN_NAME_PHONE ="phone";
    public static final String SQL_CREATE_ENTRIES ="CREATE TABLE " + TABLE_NAME + " ("+ ID+" INTEGER PRIMARY KEY AUTOINCREMENT," + " TEXT)";
   public static final String SQL_DELETE_ENTRIES= "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_Version);
       // SQLiteDatabase db=this.getWritableDatabase(); //for initial test of db creation
        Log.d("MyContactApp","DatabaseHelper: constructed DatabaseHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyContactApp","DatabaseHelper: creating database");
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyContactApp","DatabaseHelper: upgrading database");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean insertData(String name,String home,String phonum){
        Log.d("MyContactApp","DatabaseHelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        ContentValues contentValue1 = new ContentValues();
        ContentValues contentValue2 = new ContentValues();
        contentValue.put(COLUMN_NAME_CONTACT,name);
        contentValue.put(COLUMN_NAME_ADDRESS,home);
        contentValue.put(COLUMN_NAME_PHONE,phonum);

        long result = db.insert(TABLE_NAME, null, contentValue);
        if (result == -1){
            Log.d("My contact app","Databasehelper:Contact insert- Failed");
            return false;
        }
        else{
            Log.d("Mycontact app","Databasehelper: Contact insert-Passed");
            return true;
        }
    }
}