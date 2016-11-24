package com.app.sampleandroidproject.http;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;

import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.app.BaseApplication;
import com.app.sampleandroidproject.app.Constants;
import com.app.sampleandroidproject.beans.ModleBean;
import com.app.sampleandroidproject.event.FEvent;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.app.sampleandroidproject.app.Constants.MULTIPART;
import static com.app.sampleandroidproject.app.Constants.PLAIN;
import static com.app.sampleandroidproject.app.Constants.PULL_IMG_PO;
import static com.app.sampleandroidproject.app.Constants.PULL_IMG_PR;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.http
 *
 * @Author: xie
 * @Time: 2016/8/19 11:10
 * @Description:
 */

public class BaseHttp {
    protected HttpApi api;

    protected final static Map<String, String> keys = new HashMap<>();
    protected final String TOKEN = "token";
    private Cache cache;
    private OkHttpClient.Builder builder;
    private Retrofit.Builder rBuilder;
    private HttpInterceptor httpInterceptor;
    private StethoInterceptor stethoInterceptor;
    private HttpLoggingInterceptor logInterceptor;
    private HttpCachInterceptor httpCachInterceptor;

    private void postEvent(Object o) {
        BusProvider.post(o);
    }

    private RequestBody getBody(String body) {
        return RequestBody.create(MediaType.parse(PLAIN), body);
    }

    private RequestBody getBody(File body) {
        return RequestBody.create(MediaType.parse(MULTIPART), body);
    }

    private OkHttpClient _createClient(Map<String, String> headers, boolean isCach) {

        if (builder == null) {
            builder = new OkHttpClient.Builder();
            if (Constants.HTTP_DEBUG) {
                stethoInterceptor = new StethoInterceptor();
                logInterceptor = new HttpLoggingInterceptor();
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.networkInterceptors().add(stethoInterceptor);
                builder.addInterceptor(logInterceptor);
            }
        }
        builder.connectTimeout(Constants.HTTP_CONNECTTIME, TimeUnit.SECONDS);
        builder.readTimeout(Constants.HTTP_CONNECTTIME, TimeUnit.SECONDS);
        builder.writeTimeout(Constants.HTTP_CONNECTTIME, TimeUnit.SECONDS);
        if (isCach) {
            if (cache == null) {
                httpCachInterceptor = new HttpCachInterceptor(BaseApplication.getInstance());
                builder.addInterceptor(httpCachInterceptor);
                File cacheFile = new File(BaseApplication.getInstance().getCacheDir(), Constants.HTTP_CACHFILENAME);
                cache = new Cache(cacheFile, Constants.HTTP_CACHSIZE);
            }
            builder.cache(cache);
        } else {
            httpInterceptor = new HttpInterceptor(headers);
            builder.addInterceptor(httpInterceptor);
        }
        return builder.build();
    }

    private <T> Subscription httpResult(Observable<ModleBean<T>> observable, final Subscriber<T> subscriber) {

        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(modleBean -> {
                    if (!modleBean.success && !TextUtils.isEmpty(modleBean.msg)) {
                        subscriber.onError(new Error(modleBean.msg));
                    }
                    return modleBean.pager;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private void ThrowErrorInfo(Throwable e) {
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int statusCode = httpException.code();
            switch (statusCode) {
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    postEvent(new FEvent(MaitianErrorHandler.EMS.get("6")));
                    break;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                case HttpURLConnection.HTTP_FORBIDDEN:
                    postEvent(new FEvent(MaitianErrorHandler.EMS.get("12")));
                    break;
                case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
                    postEvent(new FEvent(MaitianErrorHandler.EMS.get("13")));
                    break;
                case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                    postEvent(new FEvent(MaitianErrorHandler.EMS.get("14")));
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                case HttpURLConnection.HTTP_BAD_GATEWAY:
                case HttpURLConnection.HTTP_UNAVAILABLE:
                    postEvent(new FEvent(MaitianErrorHandler.EMS.get("3")));
                    break;
                default:
                    if (statusCode >= 300 && statusCode <= 499) {
                        postEvent(new FEvent(MaitianErrorHandler.EMS.get("2")));
                    } else if (statusCode >= 500 && statusCode <= 599) {
                        postEvent(new FEvent(MaitianErrorHandler.EMS.get("3")));
                    } else {
                        postEvent(new FEvent(MaitianErrorHandler.EMS.get("4")));
                    }
                    break;
            }
        } else if (e instanceof JsonSyntaxException) {
            postEvent(new FEvent(MaitianErrorHandler.EMS.get("10")));
        } else if (e instanceof UnknownHostException) {
            postEvent(new FEvent(MaitianErrorHandler.EMS.get("8")));
        } else if (e instanceof EOFException) {
            postEvent(new FEvent(MaitianErrorHandler.EMS.get("5")));
        } else if (e instanceof SocketTimeoutException) {
            postEvent(new FEvent(MaitianErrorHandler.EMS.get("9")));
        } else if (e instanceof InterruptedIOException) {
            postEvent(new FEvent(MaitianErrorHandler.EMS.get("7")));
        } else if (e instanceof ConnectException) {
            postEvent(new FEvent(MaitianErrorHandler.EMS.get("11")));
        } else if (e instanceof Error) {
            postEvent(new FEvent(e.getMessage()));
        } else {
            postEvent(new FEvent(MaitianErrorHandler.EMS.get("1")));
        }
    }


    protected <T> T createService(Class<T> clazz, Map<String, String> headers, String baseURL, boolean isCach) {

        if (TextUtils.isEmpty(baseURL)) {
            throw new IllegalArgumentException("HttpManager-->createService >>> baseURL can not be empty");
        }

        if (!URLUtil.isHttpUrl(baseURL)) {
            throw new IllegalArgumentException("HttpManager-->createService >>> baseURL is invalid");
        }

        if (clazz == null) {
            throw new IllegalArgumentException("HttpManager-->createService >>> clazz can not pass null");
        }

        if (rBuilder == null)
            rBuilder = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .baseUrl(baseURL);

        rBuilder.client(_createClient(headers, isCach));

        return rBuilder.build().create(clazz);
    }


    /**
     * @return com.isoftstone.rxjavademo.http.HttpManager
     * @Title: httpRequest
     * @Description: (isCach是否缓存，firstCach请求首选缓存)
     * @params [context, isCach, firstCach]
     */
    protected BaseHttp httpRequest(boolean isCach) {
        api = createService(HttpApi.class, null, Constants.BASEURL, isCach);
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
                Log.e("tag", "OkHttp--->onError=" + e.getMessage());

            }

            @Override
            public void onNext(T t) {
                httpRequest.onHttpSuccess(t);
            }
        });
    }

    protected Observable pull(String userId, List<File> photoList) {
        Map<String, RequestBody> photos = new LinkedHashMap<>();
        RequestBody mUserId = getBody(userId);
        List<File> files = photoList;
        if (files != null && files.size() > 0) {
            for (File file : files) {
                RequestBody photo = getBody(file);
                photos.put(PULL_IMG_PR + files.indexOf(photo) + PULL_IMG_PO + file.getName(), photo);
            }
        }
        return api.pullImg(mUserId, photos);
    }
}

