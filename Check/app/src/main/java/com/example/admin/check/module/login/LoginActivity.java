package com.example.admin.check.module.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


import com.example.admin.check.MainActivity;
import com.example.admin.check.R;
import com.example.admin.check.application.AppManager;
import com.example.admin.check.base.BaseActivity;
import com.example.admin.check.util.MyImageUtils;
import com.example.admin.check.view.MyToast;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 登录界面
 * 作者：柏云飞 on 2015/7/14 10:16
 * 邮箱：306200335@qq.com
 */
public class LoginActivity extends BaseActivity implements LoginContract.LoginView {
    @BindView(R.id.name_et_login_a)
    EditText nameEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.get_password_cb)
    CheckBox getPasswordCb;
    @BindView(R.id.login_bt_login_a)
    Button loginBt;
    @BindView(R.id.cancel_bt_login_a)
    Button cancelBt;

    private LoginPresenter mPresenter;

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        getPasswordCb.setVisibility(View.GONE);
    }


    @Override
    protected void loadPresenter() {
        mPresenter=new LoginPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initListener() {
      //  mPresenter.setAddressType();//type_local_rb
        loginBt.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        //取得设置好的drawable对象
        //      Drawable drawable = this.getResources().getDrawable(R.drawable.login_select_checkbox);
        //设置drawable对象的大小
        //     drawable.setBounds(0,0,40,40);
        //设置CheckBox对象的位置，对应为左、上、右、下
        //     getPasswordCb.setCompoundDrawables(drawable,null,null,null);
        //保证存储图片的文件夹存在
        File myFile = new File(MyImageUtils.ADD_PHOTO_LOCATION);
        if (!myFile.exists()) {
            myFile.mkdirs();
        }
        myFile = new File(MyImageUtils.DOWNLOAD_LOCATION);
        if (!myFile.exists()) {
            myFile.mkdirs();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
        //推出栈中的所有Activity，防止返回闪退
        AppManager.getAppManager().finishAllActivity();
        mPresenter.getNameAndPass();
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.login_bt_login_a:// 登录按钮
                mPresenter.login(getUserName(), getPwd(), getPasswordCb.isChecked());
                break;
        }
    }

    /**
     * 设置记住的用户名和密码
     */
    @Override
    public void setNameAndPass(String username, String password, boolean isGetPassword) {
        nameEt.setText(username);
        passwordEt.setText(password);
        getPasswordCb.setChecked(isGetPassword);
    }

    /**
     * 获取用户名
     */
    @Override
    public String getUserName() {
        return nameEt.getText().toString().trim();
    }

    /**
     * 获取密码
     */
    @Override
    public String getPwd() {
        return passwordEt.getText().toString().trim();
    }

    /**
     * 登录成功跳转到选择项目界面
     */
    @Override
    public void loginSuccess(String str) {
        toast(str);
        //跳转到选择项目界面
        Intent intent = new Intent(LoginActivity.this,
                MainActivity.class);
        startActivity(intent);
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 登录失败提示
     */
    @Override
    public void loginError(String str) {
        toast(str);
    }

    /**
     * 用户名和密码是否为空判断
     */
    public boolean checkNull() {
        boolean isNull = false;
        if (TextUtils.isEmpty(getUserName())) {
            MyToast.showToast(LoginActivity.this, "请输入用户名!");
            isNull = true;
        } else if (TextUtils.isEmpty(getPwd())) {
            MyToast.showToast(LoginActivity.this, "请输入密码!");
            isNull = true;
        }
        return isNull;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //6.0运行时权限设置提醒
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //保证存储图片的文件夹存在
                File myFile = new File(MyImageUtils.ADD_PHOTO_LOCATION);
                if (!myFile.exists()) {
                    myFile.mkdirs();
                }
                myFile = new File(MyImageUtils.DOWNLOAD_LOCATION);
                if (!myFile.exists()) {
                    myFile.mkdirs();
                }
            } else {
                MyToast.showToast(this, "禁止了权限，无法使用内存卡...");
            }
        }
    }


    @Override
    public void toast(String msg) {

    }

    @Override
    public void showError(int errorId) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
