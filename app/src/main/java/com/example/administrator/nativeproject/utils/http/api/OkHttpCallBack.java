package com.example.administrator.nativeproject.utils.http.api;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class OkHttpCallBack implements Callback {
    public abstract void onSuccess(final Call call, JSONObject response);
    public Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(final Call call, Response response) throws IOException {
        if(response != null){
            if(response.isSuccessful()){
                try {
                    String jsonStr = response.body().string().trim();
                    final JSONObject object = (JSONObject) new JSONTokener(jsonStr).nextValue();
                    if(object != null){
                        if(isPostInMaininThread()){
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    onSuccess(call,object);
                                }
                            });
                        }else{
                            onSuccess(call,object);
                        }

                    }else{
                        onFailure(call,null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                onFailure(call,null);
            }
        }
    }

    protected boolean isPostInMaininThread(){
        return true;
    }
}
