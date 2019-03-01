package com.example.administrator.nativeproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.administrator.nativeproject.adapter.MyAdapter;
import com.example.administrator.nativeproject.adapter.MyAdapterWidthCommonView;
import com.example.administrator.nativeproject.bean.ContextBean;

import java.util.ArrayList;
import java.util.List;

public class AdapterDemo extends AppCompatActivity {
    private ListView mListView;
    private List<ContextBean> mDatas;
//    private MyAdapter mAdapter;
    private MyAdapterWidthCommonView mAdapter;
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
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               TextView phone =  view.findViewById(R.id.id_phone);

                LogUtils.i(phone.getText());
            }
        });
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for(int i = 1;i < 11;i++)
        {
            ContextBean bean = new ContextBean("Android新技能Get"+i,"Android打造万年的ListView和GridView适配器"+i,"2019-02-17","10086");
            mDatas.add(bean);
        }

        //mAdapter = new MyAdapter(this,mDatas);
        mAdapter = new MyAdapterWidthCommonView(this,mDatas);
    }
}
