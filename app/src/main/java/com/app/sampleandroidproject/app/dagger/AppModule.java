package com.app.sampleandroidproject.app.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.app.dagger
 *
 * @Author: xie
 * @Time: 2016/9/6 13:36
 * @Description:
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

}
