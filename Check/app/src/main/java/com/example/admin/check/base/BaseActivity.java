package com.example.admin.check.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.admin.check.view.dialog.ProgressDialog;


/**
 * 通用Activity
 * Created by yunfei on 2017/11/22.
 */

public abstract class BaseActivity extends AppCompatActivity implements
        IBaseView, View.OnClickListener {

    protected View view;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        AppManager.getAppManager().addActivity(this);
        loadPresenter();
        initView();
        initListener();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void loadPresenter();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void otherViewClick(View view);

    /**
     * @return 显示的内容
     */
    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
    }

    /**
     * 点击的事件的统一的处理
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                otherViewClick(view);
                break;
        }
    }

    /**
     * 初始化进度对话框
     */
    public void setLoadingCancelable(boolean isCancelable) {
        progressDialog.setCancelable(isCancelable);
    }

    /**
     * 显示进度对话框
     */
    @Override
    public void showLoading() {

        if (progressDialog == null) {
            progressDialog = ProgressDialog.createDialog(this);
        }

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    /**
     * 隐藏进度对话框
     */
    @Override
    public void hideLoading() {

        if (progressDialog == null) {
            progressDialog = ProgressDialog.createDialog(this);
        }

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }

    @Override
    public void showError(int errorId) {
        switch (errorId) {
            case HttpErrorType.NETWORK_ERROR:
                toast("网络不给力...");
                break;
            case HttpErrorType.HTTP_ERROR:
                toast("服务器出错了(＞﹏＜)");
                break;
            default:
                toast("软件出错了...");
        }
    }

    /**
     * @param str 日志的处理
     */
    public void logE(String str) {
        MyLog.e(getClass().getSimpleName(), str);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {

        if (KeyEvent.ACTION_DOWN == event.getAction()
                && KeyEvent.KEYCODE_BACK == event.getKeyCode()) {

            AppManager.getAppManager().finishActivity();

            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
