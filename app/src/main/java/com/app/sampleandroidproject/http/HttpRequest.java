package com.app.sampleandroidproject.http;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.http
 *
 * @Author: xie
 * @Time: 2016/8/26 14:22
 * @Description:
 */


public interface HttpRequest<T> {
    void onHttpStart();

    void onHttpSuccess(T t);

    void onHttpFinish();

    void onHttpError();
}