package com.example.timalka.yummyguac;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by timalka on 25/11/15.
 */
public class IngredientsListActivity extends Activity{

    EditText addIngredients;
    MyDBHandler dbHandler;
    String mealname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredientslist);

        addIngredients = (EditText) findViewById(R.id.addingredients);
        dbHandler = new MyDBHandler(this,null,null,9);

        //associated meal name
        Intent intent = getIntent();
        mealname = intent.getExtras().getString("mealname");

        showList(mealname);

        listItemLongClick();
    }

    public void  ingredientsAddButtonClicked(View view){
        //Toast.makeText(IngredientsListActivity.this, "The meal name is " + mealname,Toast.LENGTH_LONG).show();
        IngredientsList item = new IngredientsList(addIngredients.getText().toString(),mealname);
        dbHandler.addIngredients(item);
        showList(mealname);
    }

    public void showList(String mealname){
        Cursor items = dbHandler.showIngredientsList(mealname);

        //map the data to the textview
        String[] fromIngredientName = new String[]{MyDBHandler.KEY_INGREDIENTNAME};
        int[] toViewItem = new int[] {R.id.ingredientname};

        //adapting the data to the listview
        final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.list3,items,fromIngredientName,toViewItem,0);
        ListView listView3 = (ListView) findViewById(R.id.ingredientslist);
        listView3.setAdapter(cursorAdapter);

        //clearing the user input
        addIngredients.setText("");
    }

    //delete an item in the listview
    private void listItemLongClick(){

        ListView listView = (ListView) findViewById(R.id.ingredientslist);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // remove the item from database and listview
                dbHandler.deleteRow3(id);
                showList(mealname);
                return false;
            }
        });

    }


}
