package com.llh.test;

import java.util.Observable;
import java.util.Observer;

public class ObserverTest {
	public static void main(String[] args) {
        MyObservable myOservable = new MyObservable();
        myOservable.addObserver(new Observer() {
            //注册匿名内部类Observer，当数据改变时将通知该类的update方法
            public void update(Observable o, Object arg) {
                System.out.println("This value has been changed to " + (String) arg);
            }
        });
        String sValue = "Hello Msg";
        myOservable.changeValue(sValue);
        myOservable.notifyObservers(sValue + "!");//数据的改变由observable主动通知给Observer。
    }
}
