package com.example.nerurkar.todoapp;

/**
 * Created by Nerurkar on 7/14/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;


public class DbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_MAIN = "main";
    private static final String KEY_SUB = "sub";
    private static final String KEY_DESC = "desc";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_PRIORITY = "priority";

    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_MAIN + " TEXT,"
                + KEY_SUB + " TEXT,"
                + KEY_DESC + " TEXT"
                + KEY_DATE + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_PRIORITY + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    void insertUserDetails(String main, String sub, String desc,String date, String time, String pri){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_MAIN, main);
        cValues.put(KEY_SUB, sub);
        cValues.put(KEY_DESC, desc);
        cValues.put(KEY_DATE, date);
        cValues.put(KEY_TIME, time);
        cValues.put(KEY_PRIORITY, pri);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Users,null, cValues);
        db.close();
    }
    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT main, sub, desc, date, time, priority FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("main",cursor.getString(cursor.getColumnIndex(KEY_MAIN)));
            user.put("sub",cursor.getString(cursor.getColumnIndex(KEY_SUB)));
            user.put("desc",cursor.getString(cursor.getColumnIndex(KEY_DESC)));
            user.put("date",cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            user.put("time",cursor.getString(cursor.getColumnIndex(KEY_TIME)));
            user.put("priority",cursor.getString(cursor.getColumnIndex(KEY_PRIORITY)));
            userList.add(user);
        }
        return  userList;
    }
    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT main, sub, desc, date, time, priority FROM "+ TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_MAIN, KEY_SUB, KEY_DESC, KEY_DATE, KEY_TIME, KEY_PRIORITY},
                KEY_ID+ "=?",new String[]{String.valueOf(userid)},
                null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("main",cursor.getString(cursor.getColumnIndex(KEY_MAIN)));
            user.put("sub",cursor.getString(cursor.getColumnIndex(KEY_SUB)));
            user.put("desc",cursor.getString(cursor.getColumnIndex(KEY_DESC)));
            user.put("date",cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            user.put("time",cursor.getString(cursor.getColumnIndex(KEY_TIME)));
            user.put("priority",cursor.getString(cursor.getColumnIndex(KEY_PRIORITY)));
            userList.add(user);
        }
        return  userList;
    }
    // Delete User Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    // Update User Details
    public int UpdateUserDetails(String main, String sub, String desc,String date, String time, String pri, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_MAIN, main);
        cVals.put(KEY_SUB, sub);
        cVals.put(KEY_DESC, desc);
        cVals.put(KEY_DATE, date);
        cVals.put(KEY_TIME, time);
        cVals.put(KEY_PRIORITY, pri);
        int count = db.update(TABLE_Users, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }

}
