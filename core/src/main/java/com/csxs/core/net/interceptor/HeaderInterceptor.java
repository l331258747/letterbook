package com.csxs.core.net.interceptor;

import android.os.Build;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;


import com.csxs.core.utils.MmkvUtlis;
import com.csxs.core.utils.SystemUtil;
import com.tencent.mmkv.MMKV;

import java.io.IOException;

import javax.inject.Inject;

import dagger.Lazy;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SenYe on 2018/1/26.
 */
public class HeaderInterceptor implements Interceptor {


    private MMKV mmkv;

    private ArrayMap<String, String> headers;

    public HeaderInterceptor() {
        mmkv=MmkvUtlis.getInstance().getMmkv();
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        try {
            if (headers != null) {
                for (String key : headers.keySet()) {
                    requestBuilder.addHeader(key, headers.get(key));
                }
            }

            if(mmkv!=null){
                requestBuilder.addHeader("token",mmkv.getString("token",""));
            }

            requestBuilder.addHeader("app_version", SystemUtil.getVersionName());
            requestBuilder.addHeader("app_build", String.valueOf(SystemUtil.getVersionCode()));
            requestBuilder.addHeader("device_name", Build.MODEL);
            requestBuilder.addHeader("device_platform", "Android");
            Request request = requestBuilder.build();
            Response.Builder responseBuilder = chain.proceed(request).newBuilder();
            return responseBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chain.proceed(chain.request());
    }

    public ArrayMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(ArrayMap<String, String> headers) {
        this.headers = headers;
    }
}
