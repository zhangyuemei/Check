package com.example.admin.check.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;


import com.example.admin.check.application.MyApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者：柏云飞 on 2015/9/18.
 * 邮箱：306200335@qq.com
 */
public class MyUtils {

    //微现场模块菜单
    public static boolean isSceneMenuFirst = true;

    //首次个人信息获取
    public static boolean isPersonInfoFirst = true;

    //个人信息是否已获取
    public static boolean isPersonInfo = false;

    //个人头像
    public static boolean isPersonImageFirst = true;

    //通知
    public static boolean isNoticeTFirst = true;

    //公告
    public static boolean isNoticeGFirst = true;

    //移动网络是否加载缩略图片
    public static boolean isMobileNetworkCopyPhoto = true;

    //是否推送
    public static boolean isCloudChannel = true;

    //是否有新的会话消息
    public static boolean isHaveChatMessage = false;

    //是否有新的一条会话消息
    public static boolean isHaveNewChatMessage = false;

    //我的关注测点
    public static boolean isMyPointFirst = false;

    //获取项目id
    public static String getProjectId() {
        return (String) SPUtils.get(MyApplication.getContext(), Constants.PROJECT_ID, "");
    }

    //获取用户id
    public static String getUserId() {
        return (String) SPUtils.get(MyApplication.getContext(), Constants.USER_ID, "");
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo packageInfo = null;

        try {
            PackageManager packageManager = context.getPackageManager();
            packageInfo = packageManager.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return packageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return packageInfo;
    }

    /**
     * 获取应用的版本号
     *
     * @param context 上下文
     * @return
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * MD5加密
     *
     * @param key 加密的key
     * @return
     */
    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    /**
     * @param bytes 字节数据
     * @return
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNumber(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][123456789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
}
