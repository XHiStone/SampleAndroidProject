package com.app.sampleandroidproject.http;

import android.content.Context;

import com.app.sampleandroidproject.beans.PagerBean;
import com.app.sampleandroidproject.beans.request.LoginRequest;
import com.app.sampleandroidproject.beans.result.SysUserBean;
import com.app.sampleandroidproject.beans.result.SysUserResponseVo;


/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.http
 *
 * @Author: xie
 * @Time: 2016/8/16 19:59
 * @Description:
 */

public class HttpManager extends BaseHttp {

  private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    public void getToken(Context context, boolean isCach,
                         String appId, String deviceId,
                         HttpRequest<SysUserBean> httpRequest) {
        httpRequest(context, isCach);
        dispachHttp(api.getToken(appId, deviceId), httpRequest);
    }

    public void login(Context context, boolean isCach,
                      LoginRequest loginRequest,
                      HttpRequest<PagerBean<SysUserResponseVo>> httpRequest) {
        httpRequest(context, isCach);
        dispachHttp(api.login(keys, loginRequest), httpRequest);

    }
}
