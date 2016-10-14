package com.app.sampleandroidproject.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;

import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.app.Constants;
import com.app.sampleandroidproject.beans.ModleBean;
import com.app.sampleandroidproject.utils.BusProvider;
import com.app.sampleandroidproject.utils.MaitianErrorHandler;
import com.app.sampleandroidproject.utils.fastjson.FastJsonConverterFactory;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.JsonSyntaxException;

import java.io.EOFException;
import java.io.File;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.http
 *
 * @Author: xie
 * @Time: 2016/8/19 11:10
 * @Description:
 */

public class BaseHttp {
    protected final static Map<String, String> keys = new HashMap<>();
    protected final String TOKEN = "token";
    protected HttpApi api;
    private Cache cache;

    private OkHttpClient _createClient(Context mContext, Map<String, String> headers, boolean isCach) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Constants.HTTP_CONNECTTIME, TimeUnit.SECONDS);
        builder.readTimeout(Constants.HTTP_CONNECTTIME, TimeUnit.SECONDS);
        builder.writeTimeout(Constants.HTTP_CONNECTTIME, TimeUnit.SECONDS);
        if (isCach) {
            builder.addInterceptor(new HttpCachInterceptor(mContext));
            if (cache == null) {
                File cacheFile = new File(mContext.getCacheDir(), Constants.HTTP_CACHFILENAME);
                cache = new Cache(cacheFile, Constants.HTTP_CACHSIZE);
            }
            builder.cache(cache);
        } else {
            builder.addInterceptor(new HttpInterceptor(headers));
        }

        if (Constants.HTTP_DEBUG) {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.networkInterceptors().add(new StethoInterceptor());
            builder.addInterceptor(logInterceptor);
        }

        return builder.build();
    }

    private <T> Subscription httpResult(Observable<ModleBean<T>> observable, final Subscriber<T> subscriber) {

        return observable
                .observeOn(Schedulers.io())
                .map(new Func1<ModleBean<T>, T>() {
                    @Override
                    public T call(ModleBean<T> modleBean) {
                        if (!modleBean.success && !TextUtils.isEmpty(modleBean.msg)) {
                            subscriber.onError(new Error(modleBean.msg));
                        }
                        return modleBean.pager;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    protected <T> T createService(Context mContext, Class<T> clazz, Map<String, String> headers, String baseURL, boolean isCach) {

        if (TextUtils.isEmpty(baseURL)) {
            throw new IllegalArgumentException("HttpManager-->createService >>> baseURL can not be empty");
        }

        if (!URLUtil.isHttpUrl(baseURL)) {
            throw new IllegalArgumentException("HttpManager-->createService >>> baseURL is invalid");
        }

        if (clazz == null) {
            throw new IllegalArgumentException("HttpManager-->createService >>> clazz can not pass null");
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .baseUrl(baseURL);

        builder.client(_createClient(mContext, headers, isCach));

        return builder.build().create(clazz);
    }

    private void ThrowErrorInfo(Throwable e) {
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int statusCode = httpException.code();
            switch (statusCode) {
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    BusProvider.post(MaitianErrorHandler.EMS.get("6"));
                    break;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                case HttpURLConnection.HTTP_FORBIDDEN:
                    BusProvider.post(MaitianErrorHandler.EMS.get("12"));
                    break;
                case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
                    BusProvider.post(MaitianErrorHandler.EMS.get("13"));
                    break;
                case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                    BusProvider.post(MaitianErrorHandler.EMS.get("14"));
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                case HttpURLConnection.HTTP_BAD_GATEWAY:
                case HttpURLConnection.HTTP_UNAVAILABLE:
                    BusProvider.post(MaitianErrorHandler.EMS.get("3"));
                    break;
                default:
                    if (statusCode >= 300 && statusCode <= 499) {
                        BusProvider.post(MaitianErrorHandler.EMS.get("2"));
                    } else if (statusCode >= 500 && statusCode <= 599) {
                        BusProvider.post(MaitianErrorHandler.EMS.get("3"));
                    } else {
                        BusProvider.post(MaitianErrorHandler.EMS.get("4"));
                    }
                    break;
            }
        } else if (e instanceof JsonSyntaxException) {
            BusProvider.post(MaitianErrorHandler.EMS.get("10"));
        } else if (e instanceof UnknownHostException) {
            BusProvider.post(MaitianErrorHandler.EMS.get("8"));
        } else if (e instanceof EOFException) {
            BusProvider.post(MaitianErrorHandler.EMS.get("5"));
        } else if (e instanceof SocketTimeoutException) {
            BusProvider.post(MaitianErrorHandler.EMS.get("9"));
        } else if (e instanceof InterruptedIOException) {
            BusProvider.post(MaitianErrorHandler.EMS.get("7"));
        } else if (e instanceof ConnectException) {
            BusProvider.post(MaitianErrorHandler.EMS.get("11"));
        } else if (e instanceof Error) {
            BusProvider.post(e.getMessage());
        } else {
            BusProvider.post(MaitianErrorHandler.EMS.get("1"));
        }
    }

    /**
     * @return com.isoftstone.rxjavademo.http.HttpManager
     * @Title: httpRequest
     * @Description: (isCach是否缓存，firstCach请求首选缓存)
     * @params [context, isCach, firstCach]
     */
    protected BaseHttp httpRequest(Context context, boolean isCach) {
        api = createService(context, HttpApi.class, null, Constants.BASEURL, isCach);
        if (!TextUtils.isEmpty(AppManagers.getTokenUtil().getToken()) && keys.size() == 0)
            keys.put(TOKEN, AppManagers.getTokenUtil().getToken());
        return this;
    }

    protected <T> Subscription dispachHttp(Observable<ModleBean<T>> observable,
                                           final HttpRequest<T> httpRequest) {
        return httpResult(observable, new Subscriber<T>() {
            @Override
            public void onStart() {
                super.onStart();
                httpRequest.onHttpStart();
            }

            @Override
            public void onCompleted() {
                unsubscribe();
                httpRequest.onHttpFinish();
            }

            @Override
            public void onError(Throwable e) {
                unsubscribe();
                ThrowErrorInfo(e);
                httpRequest.onHttpError();
                Log.e("tag", "------>onError=" + e.getMessage());

            }

            @Override
            public void onNext(T t) {
                httpRequest.onHttpSuccess(t);
            }
        });
    }
}

