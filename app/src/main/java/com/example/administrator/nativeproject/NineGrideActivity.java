package com.example.administrator.nativeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NineGrideActivity extends AppCompatActivity {
    private NineGridImageView nineGridImageView;
    private List<String> viewList;
    private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {

        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String photo) {
            Picasso.get()
                    .load(photo)
                    .into(imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
            super.onItemImageClick(context, imageView, index, list);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_gride);
        nineGridImageView = findViewById(R.id.grid_view);
        viewList = new ArrayList<>();
        viewList.add("http://i.imgur.com/DvpvklR.png");
        viewList.add("https://ps.ssl.qhmsg.com/bdr/576__/t01ca18554f214b9bb8.jpg");
        viewList.add("http://tu.maomaogougou.cn/picture/2015/05/d1ae13a28f8ef2dc2e6258ef2c513821.jpg");
        viewList.add("http://www.ixiupet.com/uploads/allimg/170729/110U2AU_0.jpg");
        nineGridImageView.setAdapter(mAdapter);
        nineGridImageView.setImagesData(viewList);
    }
}
