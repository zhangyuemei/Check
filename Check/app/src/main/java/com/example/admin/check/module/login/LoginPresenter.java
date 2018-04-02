package com.example.admin.check.module.login;

import com.example.admin.check.application.MyApplication;
import com.example.admin.check.base.BasePresenter;
import com.example.admin.check.http.HttpAddress;
import com.example.admin.check.model.bean.Base;
import com.example.admin.check.util.Constants;
import com.example.admin.check.util.MyUtils;
import com.example.admin.check.util.SPUtils;


/**
 * @author admin
 * @desc  登录presenter
 * @time 2016/12/12 17:18
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {

    /**
     * 获取保存的用户名和密码
     */
    @Override
    public void getNameAndPass() {
        // 获取保存的用户名和密码
        String username = (String) SPUtils.get(MyApplication.getmContext(), Constants.USER_USERNAME, "");
        String password = (String) SPUtils.get(MyApplication.getmContext(), Constants.USER_PASSWORD, "");
        boolean isGetPassword = (boolean) SPUtils.get(MyApplication.getmContext(), Constants.GET_PASSWORD, true);

        // 设置输入框的用户名和密码,已经是否记住密码
        getIView().setNameAndPass(username, password, isGetPassword);
    }

    /**
     * 设置请求的地址类型
     */
    @Override
    public void setAddressType() {
       int addressType = HttpAddress.ADDRESS_CLOUD;
        addressType = HttpAddress.ADDRESS_COMPANY;//ADDRESS_CLOUD

      //  HttpAddress.initAddress(addressType);
      //  HttpAddress.setAddress(addressType);
      //  SPUtils.put(MyApplication.getmContext(), Constants.ADDRESS_TYPE, addressType);
    }

    /**
     * 登录
     */
    @Override
    public void login(final String name, String pwd, boolean isGetPassword) {
        SPUtils.put(MyApplication.getmContext(), Constants.LOGIN, "true");
        SPUtils.put(MyApplication.getmContext(), Constants.USER_USERNAME, name);
        SPUtils.put(MyApplication.getmContext(), Constants.GET_PASSWORD, isGetPassword);

        //如果记住密码，保存密码，否则清空存储的密码
        if (isGetPassword) {
            SPUtils.put(MyApplication.getmContext(), Constants.USER_PASSWORD, pwd);
        } else {
            SPUtils.put(MyApplication.getmContext(), Constants.USER_PASSWORD, "");
        }

        if (!getIView().checkNull()) {
            ((LoginModel) getModelMap().get("login")).login(name, MyUtils.hashKeyForDisk(pwd), new LoginModel
                    .InfoHint() {
                @Override
                public void successInfo(Object response) {
                    Base iBase= (IBase) response;
                    if (iBase.IsSuccess.equals("true")) {
                        getIView().loginSuccess("登录成功!");  //成功
                        SPUtils.put(MyApplication.getmContext(), Constants.USER_NAME,name);
                        SPUtils.put(MyApplication.getmContext(), Constants.USER_NAME,name);
                      /*  SPUtils.put(MyApplication.getmContext(), Constants.USER_ID, login.getUserId());
                        SPUtils.put(MyApplication.getmContext(), Constants.PROJECT_ID, login.getProjectId());
                        SPUtils.put(MyApplication.getmContext(), Constants.SELECT_PROJECT_USER_ID, login.getSelectProjectUserId());
                        SPUtils.put(MyApplication.getmContext(), Constants.PROJECT_USER_ID, login.getProjectUserId());*/
                    }else {
                        getIView().loginError("登录失败，用户名或密码不正确...");
                    }
                }
            });
        }
    }
}
