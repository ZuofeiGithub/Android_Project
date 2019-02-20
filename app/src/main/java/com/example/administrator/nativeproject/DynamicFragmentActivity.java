package com.example.administrator.nativeproject;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.nativeproject.fragment.V4ImageFragment;

/**
 * 动态添加Fragment
 */
public class DynamicFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
         setContentView(R.layout.activity_dynamic_fragment);
//        //1.创建Fragment的管理器对象
//        FragmentManager manager = getFragmentManager();
//        //2.获取Fragment的事务对象并开启事务
//        FragmentTransaction transaction = manager.beginTransaction();
//        //3.调用事务添加fragment到指定位置
//        transaction.add(R.id.fragment_image,new ImageFragment());
//        //transaction.remove(arg0); remove(需要移除的fragment对象)
//        //transaction.replace(arg0,arg1) replace(表示替换fragment位置的资源id,要替换的fragment对象)
//        //4.提交事务
//        transaction.commit();
        /**
         * v4包下的fragment
         */
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_image,new V4ImageFragment());
        transaction.commit();
    }
}
