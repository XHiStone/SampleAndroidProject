package com.app.sampleandroidproject.http;

import com.app.sampleandroidproject.beans.PagerBean;
import com.app.sampleandroidproject.beans.request.LoginRequest;
import com.app.sampleandroidproject.beans.result.HttpResultCityAndSpace;
import com.app.sampleandroidproject.beans.result.PullImgResult;
import com.app.sampleandroidproject.beans.result.SysUserBean;
import com.app.sampleandroidproject.beans.result.SysUserResponseVo;
import com.app.sampleandroidproject.http.download.DownloadProgressListener;

import java.io.File;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;


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

    public Subscription getToken( boolean isCach,
                                 String appId, String deviceId,
                                 HttpRequest<SysUserBean> httpRequest) {
        return httpRequest(isCach).dispachHttp(api.getToken(appId, deviceId), httpRequest);
    }

    public Subscription login(boolean isCach,
                              LoginRequest loginRequest,
                              HttpRequest<PagerBean<SysUserResponseVo>> httpRequest) {
        return httpRequest(isCach).dispachHttp(api.login(keys, loginRequest), httpRequest);

    }

    public Subscription getCity( boolean isCach,
                                HttpRequest<HttpResultCityAndSpace> httpRequest) {
        return httpRequest(isCach).dispachHttp(api.getCity(), httpRequest);
    }

    public Subscription pullImg(String userId, List<File> photoList, boolean isCach, HttpRequest<PullImgResult> httpRequest) {
        return httpRequest(isCach).dispachHttp(pull(userId, photoList), httpRequest);
    }


    public Subscription downLoadApk( String url, File file, DownloadProgressListener listener, Subscriber observer) {
        return httpRequest(listener).dispathDownLoad(downLoadApi.downLoadApk(url),file,observer);
    }

}
