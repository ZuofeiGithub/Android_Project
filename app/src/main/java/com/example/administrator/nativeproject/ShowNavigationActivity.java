package com.example.administrator.nativeproject;

import android.app.Activity;
import android.os.Bundle;

public class ShowNavigationActivity extends Activity {
    private WJNavigationBarActivity wjNavigationBarActivity;      // 调用自定义Navigation的Java类

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shownavigation);

    }
}
