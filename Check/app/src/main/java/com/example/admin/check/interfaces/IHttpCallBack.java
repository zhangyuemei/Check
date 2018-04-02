package com.example.admin.check.interfaces;

/**
 * 请求接口回调
 * Created by 30620 on 2017/11/22.
 */

public interface IHttpCallBack {

    void onResponse(Object response);

    void onError(int errorId);
}
