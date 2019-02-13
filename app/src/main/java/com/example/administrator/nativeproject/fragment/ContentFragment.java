package com.example.administrator.nativeproject.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.nativeproject.CameraActivity;
import com.example.administrator.nativeproject.DynamicFragmentActivity;
import com.example.administrator.nativeproject.R;

public class ContentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content,null);
        Button addfragBtn = view.findViewById(R.id.add_fragment);
        Button startCamera = view.findViewById(R.id.start_camera);
        startCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"打开摄像机",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(),CameraActivity.class);
                startActivity(intent);
            }
        });
        addfragBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"动态添加fragment",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(), DynamicFragmentActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
