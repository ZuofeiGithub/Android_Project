package com.example.administrator.nativeproject;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

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
                .textSize(50)
                .style(Typeface.BOLD | Typeface.ITALIC)
                .build());
        imageTextView.addSlice(new Slice.Builder("\u3000\u3000\u3000\u3000").build());
        imageTextView.addSlice(new Slice.Builder("  3.5/10  ")
                .textColor(Color.WHITE)
                .setImageResource(R.drawable.add)
                .build());
        imageTextView.display();
    }

}
