package com.example.administrator.nativeproject.test;

import com.example.administrator.nativeproject.utils.http.api.ApiUtil;

import org.json.JSONObject;

public class TestPostApi extends ApiUtil {
    public String mResponse;
    public TestPostApi(String name,String description){
        addParams("name",name);
        addParams("descript",description);
    }
    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {

    }

    @Override
    protected String getUrl() {
        return null;
    }
}
