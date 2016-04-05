package com.example.timalka.yummyguac;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by timalka on 25/11/15.
 */
public class ProduceList {

    private int _id;

    private String _producename;

    public ProduceList(){
    }

    public ProduceList(String _producename) {
        this._producename = _producename;
    }

    public int get_id() {
        return _id;
    }

    public String get_producename() {
        return _producename;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_producename(String _producename) {
        this._producename = _producename;
    }
}
