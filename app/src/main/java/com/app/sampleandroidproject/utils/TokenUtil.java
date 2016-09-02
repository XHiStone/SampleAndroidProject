package com.app.sampleandroidproject.utils;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.app.Constants;
import com.app.sampleandroidproject.beans.result.SysUserBean;
import com.app.sampleandroidproject.http.HttpRequest;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.http
 *
 * @Author: xie
 * @Time: 2016/8/16 18:23
 * @Description:
 */

public class TokenUtil {

    private static TokenUtil tokenUtil;
    private final String APP_ID = "e3225cc1-eba7-4993-93f9-63044d4ee540";
    private final String KEY_DEVICE_ID = "mm_device_id";

    private static String DEVICE_ID;
    private String mToken;


    public static TokenUtil getInstance() {
        if (tokenUtil == null) {
            tokenUtil = new TokenUtil();
        }
        return tokenUtil;
    }

    public void getTocken(Context context) {
        mToken = AppManagers.getCacheManager().getAsString(Constants.KEY_TOKEN);
        if (TextUtils.isEmpty(mToken)) {
            String deviceId = AppManagers.getCacheManager().getAsString(KEY_DEVICE_ID);
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = TelephoneUtil.getIMEI(context);
                if (TextUtils.isEmpty(deviceId)) {
                    deviceId = AndroidUtil.getMacAddress(context);
                    if (TextUtils.isEmpty(deviceId)) {
                        deviceId = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
                    }
                }
                AppManagers.getCacheManager().put(KEY_DEVICE_ID, deviceId);
                DEVICE_ID = deviceId;
            }

            AppManagers.getHttpManager()
                    .getToken(context, true, APP_ID, deviceId, new HttpRequest<SysUserBean>() {
                                @Override
                                public void onStart() {
//                                    Log.i("tag","----onStart");
                                }

                                @Override
                                public void onSuccess(SysUserBean sysUserBean) {
                                    mToken = sysUserBean.content.get(0) + "";
//                                    Log.i("tag","----onSuccess");
                                }

                                @Override
                                public void onFinish() {
                                    if (!TextUtils.isEmpty(mToken))
                                        AppManagers.getCacheManager().put(Constants.KEY_TOKEN, mToken);
//                                    Log.i("tag","----onFinish");
                                }

                                @Override
                                public void onError() {

                                }
                            }
                    );

        }
    }

    public String getToken() {
        if (TextUtils.isEmpty(mToken)) {
            return AppManagers.getCacheManager().getAsString(Constants.KEY_TOKEN);
        }
        return mToken;
    }

}


