package com.example.administrator.nativeproject.utils.http.api;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {
    private static OkHttpClient mOkHttpClient = null;
    public static void init(){
        if(mOkHttpClient == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(5000, TimeUnit.MILLISECONDS).readTimeout(5000,TimeUnit.MILLISECONDS).writeTimeout(5000,TimeUnit.MILLISECONDS);
            mOkHttpClient = builder.build();
        }
    }

    /**
     * get参数
     * @param url
     * @param params
     * @return
     */
    public static String getFinalString(String url,HashMap<String,String> params){
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for(HashMap.Entry<String,String> entry:params.entrySet()){
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
            }
            String paraString = stringBuilder.toString();
            if(url.contains("?")){
                url += ("&" + paraString);
            }else{
                url += ("?" + paraString);
            }
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void get(String url,OkHttpCallBack okHttpCallBack){
        Call call = null;
       try {
           HashMap<String,String> commonHashMap = new HashMap<>();
           url = getFinalString(url,commonHashMap);
           Request request = new Request.Builder().url(url).build();
           call = mOkHttpClient.newCall(request);
           call.enqueue(okHttpCallBack);
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public static void post(String url, OkHttpCallBack okHttpCallBack, HashMap<String,String>bodyMap){
        Call call = null;
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for(HashMap.Entry<String,String> entry:bodyMap.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
            RequestBody body = builder.build();
            Request request = new Request.Builder().post(body).url(url).build();
            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallBack);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
