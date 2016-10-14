package com.app.sampleandroidproject.ui.login.dagger;

import com.app.sampleandroidproject.ui.login.mvp.LoginPresenter;
import com.app.sampleandroidproject.view.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.login.dagger
 *
 * @Author: xie
 * @Time: 2016/9/6 14:01
 * @Description:
 */
@Module
public class LoginModule {

    private LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides
    LoginView provideLoginView() {
        return loginView;
    }

    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter(loginView);
    }
}
