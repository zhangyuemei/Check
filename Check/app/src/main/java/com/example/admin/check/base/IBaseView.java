package com.example.admin.check.base;

import android.content.Context;

/**
 * 通用view
 * Created by 30620 on 2017/11/22.
 */

public interface IBaseView {

    /**
     * 显示正在加载view
     */
    void showLoading();

    /**
     * 关闭正在加载view
     */
    void hideLoading();

    /**
     * 显示提示
     *
     * @param msg
     */
    void toast(String msg);

    /**
     * 显示请求错误提示
     */
    void showError(int errorId);

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    Context getContext();
}
