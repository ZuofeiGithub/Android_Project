package com.example.administrator.nativeproject.utils.http.api;

public interface ApiListener {
    void success(ApiUtil apiUtil);

    void failure(ApiUtil apiUtil);
}
