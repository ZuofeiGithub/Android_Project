package com.example.administrator.nativeproject.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.nativeproject.R;

/**
 * 日期:2019.1.29
 * 作者:左飞
 * 内容:1.创建自定义fagment
 * 2.重写onCreateView方法,该方法主要定义Fragment的布局,以view对象的形式返回fragment的视图
 * 3.将fragment引入到Activity中
 */
public class TitleFragment extends Fragment {
    /**
     * fragment第一次绘制用户界面时系统回调的方法
     *
     * @param inflater           布局填充器，xml文件
     * @param container          表示当前fragment插入activity的布局视图对象
     * @param savedInstanceState 存储上一个fragment的信息
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //将指定资源文件转换成具体的view对象 inflate(加载的内容,是否需要根布局);
        View view = inflater.inflate(R.layout.fragment_title, null);
        RelativeLayout relativeLayout = view.findViewById(R.id.top_title);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"我是标题栏",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
