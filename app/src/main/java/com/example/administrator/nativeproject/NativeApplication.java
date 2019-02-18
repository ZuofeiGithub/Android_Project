package com.example.administrator.nativeproject;

import android.app.Application;

import com.example.administrator.nativeproject.utils.Logger;

public class NativeApplication extends Application {
    private static NativeApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(this);
        instance = this;
    }

    public static NativeApplication getInstance(){
        return instance;
    }
}
