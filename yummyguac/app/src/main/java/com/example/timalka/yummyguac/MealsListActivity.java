package com.example.timalka.yummyguac;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by timalka on 25/11/15.
 */
public class MealsListActivity extends Activity {

    EditText addMeals;
    MyDBHandler dbHandler;
    Spinner mealType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mealslist);

        addMeals = (EditText) findViewById(R.id.addmeals);
        mealType = (Spinner) findViewById(R.id.mealtype);
        dbHandler = new MyDBHandler(this,null,null,9);

        showList();

        createMealTypeSpinner();

        listItemLongClick();
    }


    public void addButtonClicked(View view){
        MealList item = new MealList(addMeals.getText().toString(), mealType.getSelectedItem().toString());
        dbHandler.addMeal(item);
        showList();
    }

    public void createMealTypeSpinner(){

        ArrayAdapter<CharSequence> MealTypeArrayAdapter = ArrayAdapter.createFromResource(this,R.array.meal_types, android.R.layout.simple_spinner_item);
        MealTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealType.setAdapter(MealTypeArrayAdapter);

        mealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String mealtype = mealType.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void showList(){
        Cursor items = dbHandler.showMealsList();

        //map the data to the textview
        String[] fromMealName = new String[]{MyDBHandler.KEY_MEALNAME,MyDBHandler.KEY_MEALTYPE};
        int[] toViewItem = new int[] {R.id.mealname,R.id.mealType};

       //adapting the data to the listview
        final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.list2,items,fromMealName,toViewItem,0) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View mealItemView = super.getView(position, convertView, parent);
                final String mealName = ((TextView) mealItemView.findViewById(R.id.mealname)).getText().toString();
                //when mealname view is clicked open the ingredients list with populate listview
                mealItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onMealItemClick(mealName);
                    }
                });

                mealItemView.setLongClickable(true);

                return mealItemView;
            }
        };
        ListView listView2 = (ListView) findViewById(R.id.meallist);
        listView2.setAdapter(cursorAdapter);

        //clearing the user input
        addMeals.setText("");
    }

    //delete an item in the listview
    private void listItemLongClick(){

        ListView listView = (ListView) findViewById(R.id.meallist);

        listView.setLongClickable(true);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // remove the item from database and listview
                dbHandler.deleteRow2(id);
                showList();
                return false;
            }
        });

    }

    //open ingredients list activity
    public void onMealItemClick(String mealName) {
        Intent intent = new Intent(this,IngredientsListActivity.class);
        // pass the meal name to ingredients list activity
        intent.putExtra("mealname", mealName);
        startActivity(intent);


    }

}
