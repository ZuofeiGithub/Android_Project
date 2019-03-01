package com.example.administrator.nativeproject.adapter;

import android.content.Context;

import com.example.administrator.nativeproject.R;
import com.example.administrator.nativeproject.bean.ContextBean;
import com.example.administrator.nativeproject.utils.CommonAdapter;
import com.example.administrator.nativeproject.utils.ViewHolder;

import java.util.List;

public class MyAdapterWidthCommonView extends CommonAdapter<ContextBean> {
    public MyAdapterWidthCommonView(Context context, List<ContextBean> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ViewHolder holder, ContextBean contextBean) {
        holder.setText(R.id.id_title, contextBean.getTitle())
                .setText(R.id.id_desc, contextBean.getDesc())
                .setText(R.id.id_time, contextBean.getTime())
                .setText(R.id.id_phone, contextBean.getPhone());
    }
}
