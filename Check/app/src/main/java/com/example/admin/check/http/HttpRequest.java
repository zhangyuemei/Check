package com.example.admin.check.http;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.example.admin.check.application.MyApplication;
import com.example.admin.check.interfaces.IHttpCallBack;
import com.example.admin.check.util.Constants;
import com.example.admin.check.util.MyLog;
import com.example.admin.check.util.MyUtils;
import com.example.admin.check.util.NetUtils;
import com.example.admin.check.util.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 通用接口请求类
 * Created by 30620 on 2017/11/22.
 */

public class HttpRequest<T> {

    private IHttpCallBack iHttpCallBack;

    protected Context context;//上下文
    protected String url;//请求网址
    protected Map<String, String> params;//参数集
    protected List<String> name;//文件key集合
    private List<String> fileNameList;//文件名集合
    protected List<File> fileList;//文件集合
    protected Class<T> classOfT;//泛型类

    public HttpRequest(Context context, String url, Map<String, String> params, Class<T> classOfT) {
        this.context = context;
        this.url = url;
        this.classOfT = classOfT;
        initParam();
        this.params.putAll(params);
    }

    /**
     * 是否要传文件
     */
    public void setFileList(List<String> name, List<String> fileNameList, List<File> fileList) {
        this.name = name;
        this.fileNameList = fileNameList;
        this.fileList = fileList;
    }

    /**
     * 添加公共的参数
     */
    protected void initParam() {
        params = new LinkedHashMap<>();

        TelephonyManager telephonyManager = (TelephonyManager) MyApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (telephonyManager != null && telephonyManager.getDeviceId() != null) {
            IMEI = telephonyManager.getDeviceId();
        } else {//7.0权限问题
            //android.provider.Settings;
            IMEI = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        }

        if (IMEI == null || IMEI.equals("")) {
            IMEI = (String) SPUtils.get(context, Constants.USER_ID, "");
        }

        //公告参数
        params.put(Constants.TOKEN, Constants.token);
        params.put(Constants.USER_ID, (String) SPUtils.get(context, Constants.USER_ID, ""));
        params.put(Constants.USER_NAME, (String) SPUtils.get(context, Constants.USER_NAME, ""));
        params.put(Constants.PROJECT_ID, (String) SPUtils.get(context, Constants.PROJECT_ID, ""));
        params.put(Constants.PROJECT_USER_ID, (String) SPUtils.get(context, Constants.PROJECT_USER_ID, ""));
        params.put(Constants.PROJECT_GROUP_ID, (String) SPUtils.get(context, Constants.PROJECT_GROUP_ID, ""));
        params.put(Constants.USER_TAG, (String) SPUtils.get(context, Constants.USER_TAG, ""));
        params.put(Constants.MOBILE_TYPE, "android");
        params.put(Constants.VERSION, MyUtils.getVersionName(context));
        //手机型号+版本号
        params.put("phoneModel", android.os.Build.MODEL + "-" + android.os.Build.VERSION.RELEASE);
        params.put("IMEI", IMEI);
    }

    /**
     * 添加文件
     */
    protected void initFile(PostFormBuilder postFormBuilder) {

        if (fileList != null) {
            for (int i = 0; i < fileList.size(); i++) {
                postFormBuilder.addFile(name.get(i), fileNameList.get(i), fileList.get(i));
            }
        }
    }

    /**
     * 请求
     */
    @SuppressLint("HardwareIds")
    public void request() {

        //查看是否联网
        if (!NetUtils.isConnected(context)) {
            iHttpCallBack.onError(HttpErrorType.NETWORK_ERROR);
            return;
        }

        PostFormBuilder postFormBuilder = OkHttpUtils.post();

        initFile(postFormBuilder);

        MyLog.e("url", url);
        MyLog.e("httpRequest", params.toString());

        postFormBuilder.url(url)
                .params(params)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String string = response.body().string();
                        MyLog.json("Net_receive", string);
                        return new Gson().fromJson(string, classOfT);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();

                        iHttpCallBack.onError(HttpErrorType.HTTP_ERROR);
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        iHttpCallBack.onResponse(response);
                    }
                });
    }

    /**
     * 设置请求接口回调
     *
     * @param iHttpCallBack 请求接口回调
     */
    public void setHttpCallBack(IHttpCallBack iHttpCallBack) {
        this.iHttpCallBack = iHttpCallBack;
    }
}
