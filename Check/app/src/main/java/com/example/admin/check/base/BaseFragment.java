package com.example.admin.check.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.check.http.HttpErrorType;
import com.example.admin.check.util.MyLog;
import com.example.admin.check.view.dialog.ProgressDialog;


/**
 * 通用Fragment
 * Created by 30620 on 2017/12/1.
 */

public abstract class BaseFragment extends Fragment implements
        IBaseView, View.OnClickListener {

    protected Context mContext;
    protected View view;
    protected ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
            loadPresenter();
            initView(view);
            initListener();
            initData();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void loadPresenter();

    protected abstract View initView(View view);

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void otherViewClick(View view);

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
    public void initLoading() {
        progressDialog = ProgressDialog.createDialog(mContext);
//        progressDialog.setCancelable(false);
    }

    /**
     * 显示进度对话框
     */
    @Override
    public void showLoading() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    /**
     * 隐藏进度对话框
     */
    @Override
    public void hideLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return mContext;
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
}
