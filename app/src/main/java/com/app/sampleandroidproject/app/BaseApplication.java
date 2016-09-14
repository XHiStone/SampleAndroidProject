package com.app.sampleandroidproject.app;

import android.support.multidex.MultiDexApplication;

import com.app.sampleandroidproject.app.dagger.AppComponent;
import com.app.sampleandroidproject.app.dagger.AppModule;
import com.app.sampleandroidproject.app.dagger.DaggerAppComponent;
import com.app.sampleandroidproject.beans.result.DaoMaster;
import com.app.sampleandroidproject.beans.result.DaoSession;
import com.app.sampleandroidproject.ui.login.dagger.LoginComponent;
import com.app.sampleandroidproject.ui.login.dagger.LoginModule;
import com.app.sampleandroidproject.utils.BusProvider;
import com.app.sampleandroidproject.view.LoginView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.otto.Subscribe;

import org.greenrobot.greendao.database.Database;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.app
 *
 * @Author: xie
 * @Time: 2016/9/2 9:58
 * @Description:
 */


public class BaseApplication extends MultiDexApplication {
    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;
    private static BaseApplication application;
    private AppComponent appComponent;

    public static BaseApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        Fresco.initialize(this);
        LeakCanary.install(this);
        BusProvider.register(this);
        AppManagers.getAppManagers(this);
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"notes-db-encrypted",null);
        Database db = helper.getWritableDb();
//        Database db = helper.getEncryptedWritableDb("pwd");
        daoSession = new DaoMaster(db).newSession();
    }

    public LoginComponent creatLoginComponent(LoginView loginView) {
        return appComponent.getLoginComponent(new LoginModule(loginView));
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    @Subscribe
    public void callback(String error) {
        if (error != null) {
            AppManagers.getToastor().showSingletonToast(error);
        }
    }
}
