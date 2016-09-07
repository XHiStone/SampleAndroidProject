package com.app.sampleandroidproject.ui.login.dagger;

import com.app.sampleandroidproject.beans.request.LoginRequest;
import com.app.sampleandroidproject.ui.DaggerActivity;
import com.app.sampleandroidproject.ui.MVPActivity;

import dagger.Subcomponent;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.login.dagger
 *
 * @Author: xie
 * @Time: 2016/9/6 14:10
 * @Description:
 */
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {
    LoginRequest login();

    void inject(DaggerActivity activity);

    void inject(MVPActivity activity);
}
