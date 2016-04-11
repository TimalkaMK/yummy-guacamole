package com.example.timalka.yummyguac;

/**
 * Created by timalka on 11/04/16.
 */
public class MealList {

    private long _id;

    private String _mealName;
    private String _mealType;

    public MealList(){
    }

    public MealList(String _mealName, String _mealType) {
        this._mealName = _mealName;
        this._mealType = _mealType;
    }

    public long get_id() {
        return _id;
    }

    public String get_mealName() {
        return _mealName;
    }

    public String get_mealType() { return _mealType; }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void set_mealName(String _mealName) {
        this._mealName = _mealName;
    }

    public void set_mealType(String _mealType) { this._mealType = _mealType; }
}
