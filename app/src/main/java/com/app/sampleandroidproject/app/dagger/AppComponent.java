package com.app.sampleandroidproject.app.dagger;

import com.app.sampleandroidproject.ui.login.dagger.LoginComponent;
import com.app.sampleandroidproject.ui.login.dagger.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.app.dagger
 *
 * @Author: xie
 * @Time: 2016/9/6 13:40
 * @Description:
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    LoginComponent getLoginComponent(LoginModule loginModule);
}
