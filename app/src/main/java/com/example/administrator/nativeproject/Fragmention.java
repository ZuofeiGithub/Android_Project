package com.example.administrator.nativeproject;

import android.os.Bundle;

import me.yokeyword.fragmentation.SupportActivity;

public class Fragmention extends SupportActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmention);
        if (findFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());  //load root Fragment
        }
    }
}
