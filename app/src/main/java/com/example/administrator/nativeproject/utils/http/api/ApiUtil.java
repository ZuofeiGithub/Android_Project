package com.example.administrator.nativeproject.utils.http.api;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.Call;

public abstract class ApiUtil {
    private ApiListener mApiListener = null;
    private String mStatus;
    private HashMap<String,String> mBodyMap = new HashMap<>();
    private OkHttpCallBack mSendListener = new OkHttpCallBack() {

        @Override
        protected boolean isPostInMaininThread() {
            return isBackInMainThread();
        }

        @Override
        public void onSuccess(Call call, JSONObject response) {
            mStatus = response.optString("status");
            if(isSuccess()){
                try {
                    parseData(response);
                    mApiListener.success(ApiUtil.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                mApiListener.failure(ApiUtil.this);
            }
        }
    };

    protected boolean isBackInMainThread(){
        return true;
    }

    public boolean isSuccess(){
        return "0".equals(mStatus) || "200".equals(mStatus);
    }

    protected abstract void parseData(JSONObject jsonObject) throws Exception;

    protected abstract String getUrl();


    /**
     * 发送get请求
     * @param listener 告诉客户端请求是否成功
     */
    public void get(ApiListener listener){
        OkHttpUtil.get(getUrl(),mSendListener);
    }

    public void addParams(String key,String value){
        mBodyMap.put(key,value);
    }

    public void post(ApiListener listener){
        OkHttpUtil.post(getUrl(),mSendListener,mBodyMap);
    }
}
