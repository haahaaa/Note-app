package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtable = "CREATE TABLE " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "ITEM1 TEXT)";
        db.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
    }
    public boolean addData(String item1){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);
        long result = db.insert(TABLE_NAME,null,contentValues);

        return result != -1;
    }
    public Cursor getList(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }
    public Cursor getID(String note){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL1 + " FROM " +  TABLE_NAME +
                " WHERE " + COL2 + " = '" + note + "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }
    public void updateNote(String newNote, int id, String oldNote) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + newNote + "' WHERE " + COL1 + " = '" + id + "'" + " AND " + COL2 + " = '" + oldNote + "'";
        db.execSQL(query);


    }

    public void deleteNote( int id, String note){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "+ COL1 + " = '" + id + "'"+" AND " + COL2 + " = '" + note + "'";
        db.execSQL(query);
    }
}
