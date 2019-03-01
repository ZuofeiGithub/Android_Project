package com.example.administrator.nativeproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.nativeproject.R;
import com.example.administrator.nativeproject.bean.ContextBean;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ContextBean> mDatas;
    public MyAdapter(Context context, List<ContextBean> datas){
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.activity_adapter_demo_item,parent,false);
            holder = new ViewHolder(); //各种控件的引用
            holder.mTitle = convertView.findViewById(R.id.id_title);
            holder.mDesc = convertView.findViewById(R.id.id_desc);
            holder.mTime = convertView.findViewById(R.id.id_time);
            holder.mPhone = convertView.findViewById(R.id.id_phone);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ContextBean bean = mDatas.get(position);
        holder.mTitle.setText(bean.getTitle());
        holder.mDesc.setText(bean.getDesc());
        holder.mTime.setText(bean.getTime());
        holder.mPhone.setText(bean.getPhone());

        return convertView;
    }

    private class ViewHolder{
        TextView mTitle;
        TextView mDesc;
        TextView mTime;
        TextView mPhone;
    }
}
