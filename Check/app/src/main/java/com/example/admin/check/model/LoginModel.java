package com.example.admin.check.model;

import android.support.annotation.NonNull;


import com.example.admin.check.application.MyApplication;
import com.example.admin.check.base.mvp.BaseModel;
import com.example.admin.check.http.HttpRequest;
import com.example.admin.check.http.HttpUrl;
import com.example.admin.check.interfaces.IHttpCallBack;
import com.example.admin.check.model.bean.Base;
import com.example.admin.check.util.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by GaoSheng on 2016/11/26.
 * 20:53
 *
 * @VERSION V1.4
 * com.example.gs.mvpdemo.model
 * 主要做一些数据处理呀,网路请求呀
 */

public class LoginModel extends BaseModel {

    /**
     * 登录请求
     */
    public void login(@NonNull String username, @NonNull String password, @NonNull final InfoHint
            infoHint) {

        if (infoHint == null)
            throw new RuntimeException("InfoHint不能为空");

        String url = HttpUrl.loginUrl;

        Map<String, String> params = new LinkedHashMap<>();
        params.put(Constants.TOKEN, Constants.token);
        params.put("userCode", username);
        params.put("passWord", password);
        params.put(Constants.TYPE, "Login");

        HttpRequest httpRequest = new HttpRequest(MyApplication.getContext(), url, params, Base.class);
       // httpRequest.setIsDialog(true);
        httpRequest.setHttpCallBack(new IHttpCallBack() {
            @Override
            public void onResponse(Object response) {
                infoHint.successInfo(response);
            }

            @Override
            public void onError(int errorId) {

            }
        });
        httpRequest.request();
    }

    //通过接口产生信息回调
    public interface InfoHint {
        void successInfo(Object response);
    }
}
