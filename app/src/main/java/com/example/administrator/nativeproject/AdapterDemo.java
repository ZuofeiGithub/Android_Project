package com.example.administrator.nativeproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.administrator.nativeproject.adapter.MyAdapter;
import com.example.administrator.nativeproject.bean.ContextBean;

import java.util.ArrayList;
import java.util.List;

public class AdapterDemo extends AppCompatActivity {
    private ListView mListView;
    private List<ContextBean> mDatas;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_demo);

        initDatas();

        initView();
    }

    private void initView(){
        mListView = findViewById(R.id.id_listview);
        mListView.setAdapter(mAdapter);
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for(int i = 1;i < 11;i++)
        {
            ContextBean bean = new ContextBean("Android新技能Get"+i,"Android打造万年的ListView和GridView适配器"+i,"2019-02-17","10086");
            mDatas.add(bean);
        }

        mAdapter = new MyAdapter(this,mDatas);
    }
}
