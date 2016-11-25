package com.app.sampleandroidproject.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.service
 *
 * @Author: xie
 * @Time: 2016/11/25 15:45
 * @Description:
 */


public class IBaseService extends Service {
    private IBaseAidlInterface.Stub stub;
    private final int FOREGROUND_ID = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        if (stub == null) {
            init();
        }
    }

    private void init() {
        startForeground(FOREGROUND_ID, new Notification());
        stub = new IService(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }


    class IService extends IBaseAidlInterface.Stub {
        private Context context;

        public IService(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        public int add(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    }
}
