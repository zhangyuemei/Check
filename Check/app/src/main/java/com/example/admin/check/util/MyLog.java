package com.example.admin.check.util;


import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * 自定义Log(来自网络)
 * 作者：柏云飞 on 2015/7/14 10:16
 * 邮箱：306200335@qq.com
 */
public class MyLog {

    public static final int VERBOSE = 1;

    public static final int DEBUG = 2;

    public static final int INFO = 3;

    public static final int WARN = 4;

    public static final int ERROR = 5;

    public static final int NOTHING = 6;

    public static final int LEVEL = VERBOSE;

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Logger.v(msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Logger.d(msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Logger.i(msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Logger.w(msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Logger.e(msg);
        }
    }

    public static void json(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Logger.json(msg);
        }
    }
}
