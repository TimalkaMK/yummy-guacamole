package com.example.timalka.yummyguac;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by timalka on 25/11/15.
 */
public class ProduceList {

    private long _id;

    private String _producename;
    private String _storagetype;

    public ProduceList(){
    }

    public ProduceList(String _producename, String _storagetype) {
        this._producename = _producename;
        this._storagetype = _storagetype;
    }

    public long get_id() {
        return _id;
    }

    public String get_producename() {
        return _producename;
    }

    public String get_storagetype() { return _storagetype; }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void set_producename(String _producename) {
        this._producename = _producename;
    }

    public void set_storagetype(String _storagetype) { this._storagetype = _storagetype; }
}
