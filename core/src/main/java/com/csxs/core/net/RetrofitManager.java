package com.csxs.core.net;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;


import com.csxs.core.BaseConstants;
import com.csxs.core.BaseCoreApp;
import com.csxs.core.BuildConfig;
import com.csxs.core.net.gson.GsonAdapter;
import com.csxs.core.net.gson.gsonconverter.GsonDConverterFactory;
import com.csxs.core.net.interceptor.HeaderInterceptor;
import com.csxs.core.net.interceptor.HttpLogger;
import com.csxs.core.net.interceptor.NetCacheInterceptor;
import com.csxs.core.net.interceptor.OfflineCacheInterceptor;
import com.csxs.core.net.interceptor.ParamsInterceptor;
import com.csxs.core.utils.SystemUtil;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.ByteString;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitManager {

    private ArrayMap<String, String> header;
    private HeaderInterceptor headerInterceptor;
    private ParamsInterceptor paramsInterceptor;
    private Retrofit mRetrofit;
    private WebSocket mWebSocket;
    private String httpUrl;

    public RetrofitManager() {
        initRetrofit();
    }

    private static class Holder {
        private static RetrofitManager instance = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return Holder.instance;
    }



    /**
     * 配置网络请求缓存
     */
    private static Cache getCache() {
        File httpCacheDirectory = new File(BaseCoreApp.appConext.getCacheDir(), "httpCache");
        return new Cache(httpCacheDirectory, 1024 * 1024 * 50);
    }


    public void setRequestHeader(ArrayMap<String, String> header) {
        this.header = header;
    }

    /**
     * 公共参数
     */
    public static ArrayMap<String, String> getRequestParams() {
        ArrayMap<String, String> header = new ArrayMap<>();
        header.put("app_version", SystemUtil.getVersionName());
        header.put("app_build", "" + SystemUtil.getVersionCode());
        header.put("device_name", Build.MODEL);
        header.put("device_platform", "Android");
        return header;
    }

    /**
     * 获取Retrofit
     */
    public void initRetrofit() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //拦截器
        addInterceptor(okHttpBuilder);
        //设置缓存
        okHttpBuilder.cache(getCache());
        //设置连接超时
        okHttpBuilder.connectTimeout(10, TimeUnit.SECONDS);
        //设置写超时
        okHttpBuilder.writeTimeout(10, TimeUnit.SECONDS);
        //设置读超时
        okHttpBuilder.readTimeout(10, TimeUnit.SECONDS);

        okHttpBuilder.pingInterval(30, TimeUnit.SECONDS);

        OkHttpClient okHttpClient = okHttpBuilder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(getHttpUrl())
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonDConverterFactory.create(GsonAdapter.buildGson()))
                .build();
    }

    private void addInterceptor(OkHttpClient.Builder builder) {
        //debug模式添加log信息拦截
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLogger());
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            builder.addNetworkInterceptor(interceptor);
        }

        headerInterceptor = new HeaderInterceptor();

        paramsInterceptor = new ParamsInterceptor();


        builder.addNetworkInterceptor(new NetCacheInterceptor());
        builder.addInterceptor(new OfflineCacheInterceptor());

        builder.addInterceptor(headerInterceptor);

        builder.addInterceptor(paramsInterceptor);

    }

    //创建Service
    public <T> T create(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    public HeaderInterceptor getHeaderInterceptor() {
        return headerInterceptor;
    }

    public void setHeaderInterceptor(HeaderInterceptor headerInterceptor) {
        this.headerInterceptor = headerInterceptor;
    }

    public ParamsInterceptor getParamsInterceptor() {
        return paramsInterceptor;
    }

    public void setParamsInterceptor(ParamsInterceptor paramsInterceptor) {
        this.paramsInterceptor = paramsInterceptor;
    }

    public WebSocket getmWebSocket() {
        return mWebSocket;
    }

    public void setmWebSocket(WebSocket mWebSocket) {
        this.mWebSocket = mWebSocket;
    }

    public String getHttpUrl() {
        return BaseConstants.BASE_URL;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }
}
