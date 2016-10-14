package com.app.sampleandroidproject.http;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by IntelliJ IDEA in MaitianCommonLibrary
 * cn.maitian.app.library.network.http
 *
 * @Author: xie
 * @Time: 2016/4/14 15:58
 * @Description:
 */
final class HttpInterceptor implements Interceptor {

    private Map<String, String> _headers;

    public HttpInterceptor(Map<String, String> headers) {
        this._headers = headers;
    }

    @Override
    public Response intercept (Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        if (_headers != null && _headers.size() != 0) {
            Set<Map.Entry<String, String>> entrys = _headers.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Request request =
                builder
                        //.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("User-Agent", "Android")
                        .addHeader("Accept", "application/json")
                        .build();


        return chain.proceed(request);
    }
}
