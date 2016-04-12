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

    private static final int DATABASE_VERSION = 24;
    private static final String DATABASE_NAME = "yummyguac.db" ;

    public static final String TABLE_PRODUCE = "produce";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_PRODUCENAME = "producename";
    public static final String KEY_STORAGETYPE = "storagetype";

    public static final String TABLE_MEALS = "meals";
    public static final String KEY_ROWID2 = "_id";
    public static final String KEY_MEALNAME = "mealname";
    public static final String KEY_MEALTYPE = "mealtype";

    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String KEY_ROWID3 = "_id";
    public static final String KEY_ASSOCIATED_MEAL = "associatedmeal";
    public static final String KEY_INGREDIENTNAME = "ingredientname";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_PRODUCE + " (" +
                KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_PRODUCENAME + " TEXT NOT NULL, " +
                KEY_STORAGETYPE + " TEXT NOT NULL);";
        String query2 = "CREATE TABLE " + TABLE_MEALS + " (" +
                KEY_ROWID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_MEALNAME + " TEXT NOT NULL, " +
                KEY_MEALTYPE + " TEXT NOT NULL);";

        String query3 = "CREATE TABLE " + TABLE_INGREDIENTS + " (" +
                KEY_ROWID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_ASSOCIATED_MEAL + " TEXT NOT NULL, " +
                KEY_INGREDIENTNAME + " TEXT NOT NULL);";

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(db);
    }

    // add a produce to the database
    public void addProduce(ProduceList producelist){

        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_PRODUCENAME, producelist.get_producename());
        contentValues.put(KEY_STORAGETYPE, producelist.get_storagetype());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PRODUCE, null, contentValues);

        db.close();
    }

    public void  addMeal(MealList meallist){

        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_MEALNAME, meallist.get_mealName());
        contentValues.put(KEY_MEALTYPE, meallist.get_mealType());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_MEALS, null, contentValues);

        db.close();

    }

    public void addIngredients (IngredientsList ingredientsList){

        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_INGREDIENTNAME, ingredientsList.get_ingredient());
        contentValues.put(KEY_ASSOCIATED_MEAL, ingredientsList.get_associatemeal());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_INGREDIENTS, null, contentValues);

        db.close();
    }

    public boolean deleteRow(long id){

        SQLiteDatabase db = getWritableDatabase();

        String where =  KEY_ROWID + "=" + id;

        return db.delete(TABLE_PRODUCE,where,null) != 0;
    }

    // print out the database as string
    public Cursor showProduceList(){

        SQLiteDatabase db = getWritableDatabase();

        String[] columns = new String[]{KEY_ROWID,KEY_PRODUCENAME,KEY_STORAGETYPE};

        //POINTER
        Cursor cursor = db.query(TABLE_PRODUCE,columns,null,null,null,null,null);

        if (cursor != null){
                cursor.moveToFirst();
        }
        db.close();

        return cursor;
    }

    public Cursor showMealsList(){

        SQLiteDatabase db = getWritableDatabase();

        //POINTER
        Cursor cursor = db.rawQuery("SELECT " + KEY_ROWID2 + " AS _id," + KEY_MEALNAME + "," + KEY_MEALTYPE + " FROM " + TABLE_MEALS, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();

        return cursor;
    }

    public Cursor showIngredientsList(String mealname){

        SQLiteDatabase db = getWritableDatabase();
        //"SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";

        //retrieve ingredients associated with meals
        Cursor cursor = db.rawQuery("SELECT " + KEY_ROWID3 + " AS _id," + KEY_INGREDIENTNAME +"," + KEY_ASSOCIATED_MEAL + " FROM "
                + TABLE_INGREDIENTS + " WHERE " + KEY_ASSOCIATED_MEAL + "='" + mealname + "'", null );

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();

        return cursor;
    }

    public boolean deleteRow2 (long id){

        SQLiteDatabase db = getWritableDatabase();

        String where =  KEY_ROWID2 + "=" + id;

        return db.delete(TABLE_MEALS,where,null) != 0;
    }

    public boolean deleteRow3 (long id){

        SQLiteDatabase db = getWritableDatabase();

        String where =  KEY_ROWID3 + "=" + id;

        return db.delete(TABLE_INGREDIENTS,where,null) != 0;
    }

}
