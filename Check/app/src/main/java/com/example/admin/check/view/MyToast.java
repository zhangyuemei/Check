package com.example.admin.check.view;

import android.content.Context;
import android.widget.Toast;

/**
 * 自定义Toast
 * 作者：柏云飞 on 2015/7/14 10:16
 * 邮箱：306200335@qq.com
 */
public class MyToast {

    public static void showToast(Context con, String text) {
        Toast.makeText(con, text, Toast.LENGTH_SHORT).show();
    }

    public static void showDebugToast(Context con, String text) {
//        Toast.makeText(con, text, Toast.LENGTH_SHORT).show();
    }

    public static void showToast_long(Context con, String text) {
        Toast.makeText(con, text, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context con, int id) {
        Toast.makeText(con, id, Toast.LENGTH_SHORT).show();
    }

    public static void showToast_long(Context con, int id) {
        Toast.makeText(con, id, Toast.LENGTH_LONG).show();
    }


}
