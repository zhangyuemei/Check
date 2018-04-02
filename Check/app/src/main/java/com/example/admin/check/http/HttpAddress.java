package com.example.admin.check.http;

/**
 * 请求的服务器地址
 * Created by yunfei on 2016/10/10.
 */

public class HttpAddress {

    public static final int ADDRESS_LOCAL = 0;//本地调试地址
    public static final int ADDRESS_COMPANY = 1;//公司内部测试地址
    public static final int ADDRESS_CLOUD = 2;//云服务器发布地址

    private static int addressType = ADDRESS_CLOUD;

    /**
     * 初始化请求的服务器地址
     */
    public static void initAddress(int addressType) {

        HttpAddress.addressType = addressType;
        String baseUrl;

        switch (addressType) {
            case ADDRESS_LOCAL:
                baseUrl = "http://172.21.20.254:8080/cpmi-api/";
                //         baseUrl = "http://www.teratek.cn:9090/ttekpm-api-test/";//云端测试地址
                HttpUrl.BASE_URL = baseUrl;
                //登录
                HttpUrl.loginUrl = baseUrl + "user/userInfoController?userLogin";
                //获取项目
                HttpUrl.getProjectUrl = baseUrl + "user/userInfoController?getProjectList";
                //获取版本号
                HttpUrl.getVersionUrl = baseUrl + "user/userInfoController?getVersion";
                //apk下载地址
                HttpUrl.apkUrl = "http://www.teratek.cn:8088/file/app/漫拓云工程_微现场.apk";
                break;
            case ADDRESS_COMPANY:
                baseUrl = "http://www.kcpmit.com.cn:9090/cpmi-api/";
//                baseUrl = "http://www.kcpmit.com.cn:9090/demo-api/";//演示地址
                HttpUrl.BASE_URL = baseUrl;
                //登录
                HttpUrl.loginUrl = baseUrl + "user/userInfoController?userLogin";
                //获取项目
                HttpUrl.getProjectUrl = baseUrl + "user/userInfoController?getProjectList";
                //获取版本号
                HttpUrl.getVersionUrl = baseUrl + "user/userInfoController?getVersion";
                //apk下载地址
                HttpUrl.apkUrl = "http://www.teratek.cn:8088/file/app/漫拓云工程_微现场(内测版).apk";
                break;
            case ADDRESS_CLOUD:
                HttpUrl.loginUrl = "http://www.teratek.cn:9090/cpmi-api/user/userInfoController?userLogin";
                //获取项目
                HttpUrl.getProjectUrl = "http://www.teratek.cn:9090/cpmi-api/user/userInfoController?getProjectList";
                //获取版本号
                HttpUrl.getVersionUrl = "http://www.teratek.cn:9090/cpmi-api/user/userInfoController?getVersion";
                //apk下载地址
                HttpUrl.apkUrl = "http://www.teratek.cn:8088/file/app/漫拓云工程_微现场.apk";
                break;
        }
    }

    /**
     * @param url 项目对应的服务器地址
     */
    public static void setAddress(String url) {
        switch (addressType) {
            case ADDRESS_LOCAL:
                HttpUrl.BASE_URL = "http://172.21.20.254:8080/cpmi-api/";
                //      HttpUrl.BASE_URL = "http://www.teratek.cn:9090/ttekpm-api-test/";//云端测试地址
                break;
            case ADDRESS_COMPANY:
                HttpUrl.BASE_URL = "http://www.kcpmit.com.cn:9090/cpmi-api/";
//                HttpUrl.BASE_URL = "http://www.kcpmit.com.cn:9090/demo-api/";//演示地址
                break;
            case ADDRESS_CLOUD:
                HttpUrl.BASE_URL = url;
                break;
        }
    }
}
