package com.example.timalka.yummyguac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by timalka on 25/02/16.
 */

class CustomAdapter1 extends ArrayAdapter<String> {

    //Constructor - what will be used when converting array into list items
    CustomAdapter1(Context context, String [] list) {
        super(context,R.layout.list1,list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Get ready for rendering
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View customView = inflater.inflate(R.layout.list1, parent, false);

        //get each item in the list
        String singleItem = getItem(position);

        //Retrieve text view from list1.xml
        TextView text = (TextView) customView.findViewById(R.id.weekstextview);

        //set the text layout for each item
        text.setText(singleItem);

        //show the custom view
        return customView;


    }
}

