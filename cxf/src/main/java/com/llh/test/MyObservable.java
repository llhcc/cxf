package com.llh.test;

import java.util.Observable;

public class MyObservable extends Observable {
    private String data;
    public void changeValue(String fValue) {
        data = fValue;
        setChanged();
    }
}
