package com.example.timalka.yummyguac;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MenuTabActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText input;
    MyDBHandler dbHandler;
    Spinner storage;
    Spinner percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tab);

        scrolling();
        //input,output and interacting with database handler
        input = (EditText) findViewById(R.id.input);
       // storage = (Spinner) findViewById(R.id.storage);
       // percentage = (Spinner) findViewById(R.id.percentage);
        dbHandler = new MyDBHandler(this,null,null,1);

        storage = (Spinner) findViewById(R.id.storage);

        showList();

        listItemLongClick();

        createstorageSpinner();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.produce) {

           // Intent openProduce = new Intent(this, KitchenActivity.class);
           // startActivity(openProduce);

        } else if (id == R.id.meals) {

            Intent openMeals = new Intent(this, MealsActivity.class);
            startActivity(openMeals);

        } else if (id == R.id.shoppinglist) {

            Intent openShoppingList = new Intent(this, ShoppingListActivity.class);
            startActivity(openShoppingList);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void scrolling(){

       View scrollview = (View) findViewById(R.id.scrolling);

        SlidingUpPanelLayout layout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);

        layout.setDragView(scrollview);
   }

    //saving the produce to the database when clicking save
    public void saveButtonClicked(View view){
        ProduceList item = new ProduceList(input.getText().toString(), storage.getSelectedItem().toString());
        dbHandler.addProduce(item);
        showList();
    }

    // print the list on the scrollview
    public void showList(){
        Cursor items = dbHandler.showProduceList();

        //map the data to the textview
        String[] fromProduceName = new String[]{MyDBHandler.KEY_PRODUCENAME , MyDBHandler.KEY_STORAGETYPE};
        int[] toViewItem = new int[] {R.id.producelist,R.id.storagelist};

        //adapting the data to the listview
        final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.list1,items,fromProduceName,toViewItem,0);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(cursorAdapter);

        //clearing the user input
        input.setText("");
    }

    public void createstorageSpinner(){

        ArrayAdapter<CharSequence> storageArrayAdapter = ArrayAdapter.createFromResource(this,R.array.storage_types, android.R.layout.simple_spinner_item);
        storageArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        storage.setAdapter(storageArrayAdapter);

        storage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String storagetype = storage.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void listItemLongClick(){

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                dbHandler.deleteRow(id);
                showList();
                return false;
            }
        });

    }

    public void shoppingButtonClicked(View view){

        Intent intent = new Intent(this,ShoppingListActivity.class);
        startActivity(intent);

    }
    public void mealsButtonClicked (View view){

        Intent intent = new Intent(this,MealsListActivity.class);
        startActivity(intent);
    }

}
