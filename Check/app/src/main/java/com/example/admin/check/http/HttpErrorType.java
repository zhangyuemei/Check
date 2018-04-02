package com.example.admin.check.http;

/**
 * 网络请求错误类型
 * Created by 30620 on 2017/11/22.
 */

public class HttpErrorType {

    /**
     * 未知错误
     */
    public static final int UNKONW = 1000;

    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1001;

    /**
     * 服务器出错
     */
    public static final int HTTP_ERROR = 1002;

    /**
     * 解析对象为空
     */
    public static final int EMPTY_BEAN = 1003;

    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1004;
}
