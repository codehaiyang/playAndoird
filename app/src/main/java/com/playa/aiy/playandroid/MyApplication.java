package com.playa.aiy.playandroid;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static synchronized MyApplication getInstance(){
        return myApplication;
    }
}
