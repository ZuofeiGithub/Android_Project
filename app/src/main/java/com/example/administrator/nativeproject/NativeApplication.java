package com.example.administrator.nativeproject;

import android.app.Application;

import com.blankj.utilcode.util.LogUtils;
import com.example.administrator.nativeproject.utils.Logger;

public class NativeApplication extends Application {
    private static NativeApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getConfig().setGlobalTag("左飞");
        Logger.init(this);
        instance = this;
    }

    public static NativeApplication getInstance(){
        return instance;
    }
}
