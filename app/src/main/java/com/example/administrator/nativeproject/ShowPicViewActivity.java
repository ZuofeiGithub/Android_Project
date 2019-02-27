package com.example.administrator.nativeproject;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;

import com.mpt.android.stv.Slice;
import com.mpt.android.stv.SpannableTextView;

public class ShowPicViewActivity extends Activity  {

    private SpannableTextView imageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_pic_view);

        imageTextView =  findViewById(R.id.image_text_view);
        imageTextView.addSlice(new Slice.Builder(" horrible! ")
                .textColor(Color.parseColor("#073680"))
                .style(Typeface.BOLD)
                .build());
        imageTextView.addSlice(new Slice.Builder("  3.5/10  ")
                .backgroundColor(Color.parseColor("#800736"))
                .textColor(Color.WHITE)
                .setCornerRadius(13)
                .build());
        imageTextView.display();
    }

}
