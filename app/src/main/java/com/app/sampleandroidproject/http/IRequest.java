package com.app.sampleandroidproject.http;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.http
 *
 * @Author: xie
 * @Time: 2016/8/26 14:22
 * @Description:
 */


public interface IRequest<T> {
    void onHttpStart();

    void onHttpSuccess(T result);

    void onHttpFinish();

    void onHttpError();
}