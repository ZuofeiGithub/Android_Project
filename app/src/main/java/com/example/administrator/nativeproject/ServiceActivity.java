package com.example.administrator.nativeproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.nativeproject.service.LogService;
import com.example.administrator.nativeproject.service.MyService;

public class ServiceActivity extends AppCompatActivity {
    private MyServiceConn myServiceConn = new MyServiceConn();
    private MyService.MyServiceProxy serviceProxy = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 1);
            } else {
                //TODO do something you need
            }
        }
    }

    public void startService(View view) {
        startService(new Intent(this, LogService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(this, LogService.class));
    }

    /**
     *
     * @param view
     */
    public void bindService(View view) {
        /**
         * flag取值
         *  @see #BIND_AUTO_CREATE
         *  @see #BIND_DEBUG_UNBIND
         *  @see #BIND_NOT_FOREGROUND
         */
        bindService(new Intent(this, LogService.class),myServiceConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    /**
     * 因为在销毁的时候会执行此方法
     */
    protected void onDestroy() {
        super.onDestroy();
        if(myServiceConn != null){
            //解绑服务
            //unbindService(myServiceConn);
            myServiceConn = null;
        }
    }

    public void mp3Play(View view) {
        serviceProxy.mp3Play((String) view.getTag());
    }

    private class MyServiceConn implements ServiceConnection{

        @Override
        /**
         * 正确建立连接服务才会被调用
         */
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("zuofei",this.getClass().getName() + "---->onServiceConnected");
            Log.i("zuofei",this.getClass().getName() + "---->service:"+service);
            //通过此方法可以间接的调用Service中的方法
            serviceProxy  = (MyService.MyServiceProxy)service;

        }

        /**
         * 系统断开连接
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("zuofei",this.getClass().getName() + "---->onServiceDisconnected");
        }
    }
}
