package com.app.sampleandroidproject.app;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.app
 *
 * @Author: xie
 * @Time: 2016/8/17 19:08
 * @Description:
 */

public class Constants {
//    public static final String HTTP_HOST = "http://192.168.12.69";
    //------网络请求---------
    public static final String HTTP_HOST = "http://172.16.10.101";
    public static final String HTTP_PORT = ":8080";
    public static final String HTTP_SERVER = HTTP_HOST + HTTP_PORT + "/";
    public static final String BASEURL = HTTP_SERVER + "mt/webservice/v1_1/";

    //-------debug和缓存---------
    public static final Boolean HTTP_DEBUG = true;
    public static final int HTTP_CACHSIZE = 10 * 1024 * 1024;  //设置缓存 10M
    public static final String HTTP_CACHFILENAME = "httpCache";
    public static final int HTTP_CONNECTTIME = 10; //秒
    public static final String KEY_TOKEN = "mm_token";

}

