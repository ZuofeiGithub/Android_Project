package com.example.administrator.nativeproject.test;

import com.example.administrator.nativeproject.constant.Url;
import com.example.administrator.nativeproject.utils.http.api.ApiUtil;

import org.json.JSONObject;

public class TestGetApi extends ApiUtil {
    public String mResponse;
    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {
        mResponse = jsonObject.toString();
    }

    @Override
    protected String getUrl() {
        return Url.IP + "/api/";
    }
}
