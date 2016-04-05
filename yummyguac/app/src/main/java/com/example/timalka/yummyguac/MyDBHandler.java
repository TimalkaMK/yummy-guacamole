package com.example.timalka.yummyguac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by timalka on 30/03/16.
 */
public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "yummyguac.db" ;
    public static final String TABLE_PRODUCE = "produce";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCENAME = "producename";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_PRODUCE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCENAME + " TEXT " + ");" ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCE);
        onCreate(db);
    }

    // add a produce to the database
    public void addProduce(ProduceList producelist){

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_PRODUCENAME, producelist.get_producename());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PRODUCE, null, contentValues);

        db.close();
    }

    // print out the database as string
    public Cursor showProduceList(){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCE + " WHERE 1";

        //POINTER
        Cursor cursor = db.rawQuery(query, null);

        //extract the product name from table and puts into produces string with new line
        if (cursor != null){
                cursor.moveToFirst();
        }
        db.close();

        return cursor;
    }
}
