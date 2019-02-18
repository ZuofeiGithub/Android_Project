package com.example.administrator.nativeproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 服务在后台执行，Activity习奥会服务不会终止
 * 应用场景:1.播放音乐 2.下载网络数据(在Android3.x之后只支持子线程下载，应该在子线程中启动服务) 3.访问文件，访问数据库，业务逻辑可以让Service来实现
 * 要调用service中的方法需要先绑定该服务
 */
public class MyService extends Service{
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("zuofei",this.getClass().getName() + "---->onBind");
        Binder binder = new MyServiceProxy();
        Log.i("zuofei",this.getClass().getName() + "---->binder:"+binder);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("zuofei",this.getClass().getName() + "---->onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("zuofei",Thread.currentThread().getName() + "MyService --> onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("zuofei",Thread.currentThread().getName() + "MyService --> onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("zuofei",Thread.currentThread().getName() + "MyService --> onDestroy");
    }

    /**
     * 定义内部类，完成对MyService方法的封装调用
     */
     public class MyServiceProxy extends Binder{
        //调用真正的Service的方法
        public void mp3Play(String name){
            MyService.this.mp3Play(name);
        }
    }

    //业务功能
    private void mp3Play(String name){
        Log.i("zuofei",this.getClass().getName() +"正在播放的歌曲名称为"+name);
    }
}
