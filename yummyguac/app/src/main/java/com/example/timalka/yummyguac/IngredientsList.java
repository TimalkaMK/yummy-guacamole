package com.example.timalka.yummyguac;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by timalka on 25/11/15.
 */
public class IngredientsList {

    private long _id;

    private String _ingredient;

    private String _associatemeal;

    public IngredientsList(){
    }

    public IngredientsList(String _ingredient, String _associatemeal) {
        this._ingredient = _ingredient;
        this._associatemeal = _associatemeal;
    }

    public long get_id() {
        return _id;
    }

    public String get_ingredient() {
        return _ingredient;
    }

    public String get_associatemeal() {
        return _associatemeal;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void set_ingredient(String _ingredient) {
        this._ingredient = _ingredient;
    }

    public void set_associatemeal(String _associatemeal) {
        this._associatemeal = _associatemeal;
    }
}
