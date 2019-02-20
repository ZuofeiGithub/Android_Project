package com.example.administrator.nativeproject;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class NineGrideActivity extends AppCompatActivity {
    private NineGridImageView nineGridImageView;
    private List<String> viewList;
    private PhotoView photoView;
    private Info mInfo;
    private ImageView mImgView;
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
        photoView = findViewById(R.id.photoview);
        photoView.enable();
        viewList = new ArrayList<>();
        viewList.add("http://i.imgur.com/DvpvklR.png");
        viewList.add("https://ps.ssl.qhmsg.com/bdr/576__/t01ca18554f214b9bb8.jpg");
        viewList.add("http://tu.maomaogougou.cn/picture/2015/05/d1ae13a28f8ef2dc2e6258ef2c513821.jpg");
        viewList.add("http://www.ixiupet.com/uploads/allimg/170729/110U2AU_0.jpg");
        nineGridImageView.setAdapter(mAdapter);
        nineGridImageView.setImagesData(viewList);

        nineGridImageView.setItemImageClickListener(new ItemImageClickListener() {
            @Override
            public void onItemImageClick(Context context, final ImageView imageView, int index, List list) {
                mImgView = imageView;
                mInfo = PhotoView.getImageViewInfo(imageView);
                //imageView.setVisibility(View.GONE);
                photoView.setVisibility(View.VISIBLE);
                photoView.setMaxScale(100);
                Picasso.get().load(Uri.parse((String) list.get(index))).into(photoView);
                photoView.animaFrom(mInfo);
                Toast.makeText(context, "" + list.get(index), Toast.LENGTH_SHORT).show();
            }
        });

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoView.animaTo(mInfo, new Runnable() {
                    @Override
                    public void run() {
                        photoView.setVisibility(View.GONE);
                        mImgView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (photoView.getVisibility() == View.VISIBLE) {
            photoView.animaTo(mInfo, new Runnable() {
                @Override
                public void run() {
                    photoView.setVisibility(View.GONE);
                    mImgView.setVisibility(View.VISIBLE);
                }
            });
        } else {
            super.onBackPressed();
        }
    }
}
