package com.app.sampleandroidproject.app;

import android.app.Application;

import com.app.sampleandroidproject.utils.BusProvider;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.otto.Subscribe;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.app
 *
 * @Author: xie
 * @Time: 2016/9/2 9:58
 * @Description:
 */

public class BaseApplication extends Application {
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = BaseApplication.this;
        AppManagers.getAppManagers(this);
        Fresco.initialize(this);
        BusProvider.register(this);
    }

    public static BaseApplication getInstance() {
        return application;
    }


    @Subscribe
    public void callback(String error) {
        if (error != null) {
            AppManagers.getToastor().showSingletonToast(error);
        }
    }
}
