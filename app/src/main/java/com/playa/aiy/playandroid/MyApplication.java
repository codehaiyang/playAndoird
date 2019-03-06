package com.playa.aiy.playandroid;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        //初始化讯飞
        SpeechUtility.createUtility(MyApplication.this, "appid=" + getString(R.string.app_id));
        super.onCreate();
        myApplication = this;
    }

    public static synchronized MyApplication getInstance(){
        return myApplication;
    }
}
