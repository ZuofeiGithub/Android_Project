package com.example.administrator.nativeproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity {
    private static int REQ_1 = 1;
    private static int REQ_2 = 2;
    private ImageView iv;
    private String mFilePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        iv = findViewById(R.id.iv);
        mFilePath = Environment.getExternalStorageDirectory().getPath();//获取SDK路径
        mFilePath = mFilePath + "/"+"temp.png";
    }

       public void startCamera1(View view){
        Toast.makeText(this,"打开相机",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivity(intent); //启动无返回数据
        startActivityForResult(intent,REQ_1);
    }
    public void startCamera2(View view){
        Toast.makeText(this,"打开相机",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoUri = Uri.fromFile(new File(mFilePath));
        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);//系统拍照之后存储的数据路径进行更改成自己的路径
        //startActivity(intent); //启动无返回数据
        startActivityForResult(intent,REQ_2);
    }

    public void customCamera(View view){
        startActivity(new Intent(this,CustomCamera.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQ_1){
                Bundle bundle = data.getExtras();//封装的所有返回的数据
                Bitmap bitmap = (Bitmap) bundle.get("data");
                iv.setImageBitmap(bitmap);//缩略图
            }else if(requestCode == REQ_2){
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(mFilePath);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    iv.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
