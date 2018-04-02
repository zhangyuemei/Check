package com.example.admin.check.http;

/**
 * 接口地址管理类
 * 作者：柏云飞 on 2015/7/14 10:16
 * 邮箱：306200335@qq.com
 */
public class HttpUrl {

    public static final String TAG = "HttpUrl";

    public static String BASE_URL = "http://www.teratek.cn:9090/cpmi-api/";

    //apk下载地址
    public static String apkUrl = "http://www.teratek.cn:8088/file/app/漫拓云工程_微现场.apk";

    //登录
    public static String loginUrl ="http://192.168.11.142/interface/AppHandler.ashx";

    //获取项目
    public static String getProjectUrl = "http://www.teratek.cn:9090/cpmi-api/user/userInfoController?getProjectList";

    //获取版本号
    public static String getVersionUrl = "http://www.teratek.cn:9090/cpmi-api/user/userInfoController?getVersion";

    //修改密码
    public static final String updatePasswordUrl = "user/userInfoController?updatePassWord";

    //获取个人信息
    public static final String getUserInfoUrl = "user/userInfoController?getUserInfo";

}
