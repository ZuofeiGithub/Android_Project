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

//        initView();                               /// 初始化控件
    }

//    /*** * 初始化 */
//    public void initView() {
//        wjNavigationBarActivity = (WJNavigationBarActivity) super.findViewById(R.id.navi_main);
//        wjNavigationBarActivity.setTitle("自定义导航栏");
//        // Java中叫回调，iOS中叫Block
//        wjNavigationBarActivity.setClickCallback(new WJNavigationBarActivity.ClickCallback() {
//
//            /*** * 返回按钮 */
//            @Override
//            public void onBackClick() {
//                System.out.println("返回按钮");
//                System.out.println("写你的逻辑呗~");
//            }
//
//            /*** * 右侧按钮 */
//            @Override
//            public void onRightClick() {
//                System.out.println("右侧按钮");
//                System.out.println("写你的逻辑呗~");
//            }
//
//        }
//    }
}
