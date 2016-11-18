package com.app.sampleandroidproject.app;

import android.content.Context;

import com.app.sampleandroidproject.dao.DbManagers;
import com.app.sampleandroidproject.http.HttpManager;
import com.app.sampleandroidproject.utils.CacheManager;
import com.app.sampleandroidproject.utils.Toastor;
import com.app.sampleandroidproject.utils.TokenUtil;

import java.io.File;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.app
 *
 * @Author: xie
 * @Time: 2016/8/18 10:16
 * @Description:
 */

public class AppManagers {

    private static AppManagers appManagers = new AppManagers();
    private static HttpManager httpManager;
    private static CacheManager cacheManager;
    private static TokenUtil tokenUtil;
    private static Toastor toastor;
    private static ActivitiesManager activities;
    private static DbManagers dbManagers;
    private static RequestManager requestManager;

    public static AppManagers getAppManagers(Context context) {

        if (cacheManager == null) {
            cacheManager = CacheManager.get(context.getExternalCacheDir().getAbsolutePath() +
                    File.separator + "DataCache");
        }

        if (tokenUtil == null) {
            tokenUtil = tokenUtil.getInstance();
            tokenUtil.getTocken(context);
        }

        if (toastor == null) {
            toastor = new Toastor(context);
        }

        if (dbManagers == null) {
            dbManagers = DbManagers.getDbManagers(context);
        }

        if (requestManager == null) {
            requestManager = RequestManager.get();
        }

        return appManagers;
    }

    public static HttpManager getHttpManager() {
        if (httpManager == null) {
            httpManager = HttpManager.getInstance();
        }
        return httpManager;
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    public static TokenUtil getTokenUtil() {
        return tokenUtil;
    }

    public static Toastor getToastor() {
        return toastor;
    }

    public static ActivitiesManager getActivitiesManager() {
        if (activities == null) {
            activities = ActivitiesManager.getInstance();
        }
        return activities;
    }

    public static DbManagers getDbManagers() {
        return dbManagers;
    }

    public static RequestManager getRequestManager() {
        return requestManager;
    }
}
