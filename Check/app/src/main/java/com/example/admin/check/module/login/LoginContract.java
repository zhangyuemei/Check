package com.example.admin.check.module.login;

import com.example.admin.check.base.IBaseView;

/**
 * Created by GaoSheng on 2016/11/26.
 * 18:28
 *
 * @VERSION V1.4
 * com.example.gs.mvpdemo.contract
 * 契约类,定义登录用到的一些接口方法
 */

public class LoginContract {

    public interface LoginView extends IBaseView{

        void setNameAndPass(String username, String password, boolean isGetPassword);

        //void setAddressType(boolean isLocal, boolean isCompany, boolean isCloud);

        String getUserName();

        String getPwd();

        void loginSuccess(String str);

        void loginError(String str);
    }


    public interface LoginPresenter {
        void getNameAndPass();

        void setAddressType();

        void login(String name, String password, boolean isGetPassword);
    }
}
