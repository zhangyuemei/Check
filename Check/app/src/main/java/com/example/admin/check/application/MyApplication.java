package com.example.admin.check.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.example.admin.check.http.HttpAddress;
import com.example.admin.check.util.Constants;
import com.example.admin.check.util.MyUtils;
import com.example.admin.check.util.SPUtils;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/**
 * Created by yunfei on 2016/12/12.
 * 应用,主要用来做一下初始化的操作
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        //初始化全局log日志
        Logger.init(TAG);


        // 获取网络和推送的设置
        MyUtils.isMobileNetworkCopyPhoto = (boolean) SPUtils.get(mContext, Constants.MOBILE_NETWORK, true);
        MyUtils.isCloudChannel = (boolean) SPUtils.get(mContext, Constants.CLOUD_CHANNEL, true);

        int addressType = (int) SPUtils.get(mContext, Constants.ADDRESS_TYPE, HttpAddress.ADDRESS_CLOUD);
        HttpAddress.initAddress(addressType);


        //网络请求配置
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);


    }

    /**
     * @return 全局的上下文
     */
    public static Context getContext() {
        return mContext;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

}
