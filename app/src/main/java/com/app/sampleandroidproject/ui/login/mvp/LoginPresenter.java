package com.app.sampleandroidproject.ui.login.mvp;

import android.content.Context;
import android.text.TextUtils;

import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.beans.PagerBean;
import com.app.sampleandroidproject.beans.request.LoginRequest;
import com.app.sampleandroidproject.beans.result.SysUserResponseVo;
import com.app.sampleandroidproject.http.HttpRequest;
import com.app.sampleandroidproject.view.LoginView;

import rx.Subscription;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.login.mvp
 *
 * @Author: xie
 * @Time: 2016/9/6 14:07
 * @Description:
 */

public class LoginPresenter implements HttpRequest<PagerBean<SysUserResponseVo>> {
    private final LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void checkInput(String username, String password) {
        loginView.canLogin(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password));
    }

    public Subscription login(Context context, LoginRequest login) {
        return AppManagers.getHttpManager().login(context, false, login, this);
    }

    public void saveLoginInfo(String username, String password) {

    }


    @Override
    public void onHttpStart() {
        loginView.showLoading();
    }

    @Override
    public void onHttpSuccess(PagerBean<SysUserResponseVo> loginResultPagerBean) {
        loginView.success(loginResultPagerBean.content.get(0));
    }

    @Override
    public void onHttpFinish() {
        loginView.hideLoading();
    }

    @Override
    public void onHttpError() {
        loginView.failture();
    }
}
