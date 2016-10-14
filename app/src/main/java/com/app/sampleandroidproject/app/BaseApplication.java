package com.app.sampleandroidproject.app;

import android.support.multidex.MultiDexApplication;

import com.app.sampleandroidproject.app.dagger.AppComponent;
import com.app.sampleandroidproject.app.dagger.AppModule;
import com.app.sampleandroidproject.app.dagger.DaggerAppComponent;
import com.app.sampleandroidproject.ui.login.dagger.LoginComponent;
import com.app.sampleandroidproject.ui.login.dagger.LoginModule;
import com.app.sampleandroidproject.utils.BusProvider;
import com.app.sampleandroidproject.utils.ImagePipelineConfigFactory;
import com.app.sampleandroidproject.view.LoginView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.otto.Subscribe;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.app
 *
 * @Author: xie
 * @Time: 2016/9/2 9:58
 * @Description:
 */


public class BaseApplication extends MultiDexApplication {

    private static BaseApplication application;
    private AppComponent appComponent;

    public static BaseApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        LeakCanary.install(this);
        BusProvider.register(this);
        AppManagers.getAppManagers(this);
        Fresco.initialize(this, ImagePipelineConfigFactory.getOkHttpImagePipelineConfig(this));
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public LoginComponent creatLoginComponent(LoginView loginView) {
        return appComponent.getLoginComponent(new LoginModule(loginView));
    }

    @Subscribe
    public void callback(String error) {
        if (error != null) {
            AppManagers.getToastor().showSingletonToast(error);
        }
    }
}
