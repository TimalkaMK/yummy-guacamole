package com.example.timalka.yummyguac;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

/**
 * Created by timalka on 13/04/16.
 */
public class PlanningActivity extends Activity {


    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_planning);

        dbHandler = new MyDBHandler(this,null,null,1);

        createBreakfastSpinner();
        createLunchSpinner();
        createDinnerSpinner();
    }

    public void createBreakfastSpinner(){

        Cursor items = dbHandler.showBreakfastMeals();

        //map the data to the textview
        String[] fromBreakfastMeals = new String[]{MyDBHandler.KEY_MEALNAME};
        int[] toViewItem = new int[] {android.R.id.text1};

        //adapting the data to the spinner
        final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_dropdown_item,items,fromBreakfastMeals,toViewItem,0);
        Spinner breakfast = (Spinner) findViewById(R.id.Breakfast);
        breakfast.setAdapter(cursorAdapter);

    }

    public void createLunchSpinner(){

        Cursor items = dbHandler.showLunchMeals();

        //map the data to the textview
        String[] fromLunchMeals = new String[]{MyDBHandler.KEY_MEALNAME};
        int[] toViewItem = new int[] {android.R.id.text1};

        //adapting the data to the spinner
        final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_dropdown_item,items,fromLunchMeals,toViewItem,0);
        Spinner lunch = (Spinner) findViewById(R.id.Lunch);
        lunch.setAdapter(cursorAdapter);

    }

    public void createDinnerSpinner(){

        Cursor items = dbHandler.showDinnerMeals();

        //map the data to the textview
        String[] fromDinnerMeals = new String[]{MyDBHandler.KEY_MEALNAME};
        int[] toViewItem = new int[] {android.R.id.text1};

        //adapting the data to the spinner
        final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_dropdown_item,items,fromDinnerMeals,toViewItem,0);
        Spinner dinner = (Spinner) findViewById(R.id.Dinner);
        dinner.setAdapter(cursorAdapter);
    }

    public void cancelButtonClicked(View view){

        // go back

    }

    public void saveplanButtonClicked(View view){


    }


}
