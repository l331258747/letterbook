package com.csxs.core.base;

import android.util.ArrayMap;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.csxs.core.BaseConstants;
import com.csxs.core.net.interceptor.HeaderInterceptor;
import com.csxs.core.net.interceptor.ParamsInterceptor;


import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author: yeliu
 * created on: 2019/12/31 15:49
 * description:
 */
public class BaseModel implements IModel, LifecycleObserver {

    @Inject
    HeaderInterceptor headerInterceptor;
    @Inject
    ParamsInterceptor paramsInterceptor;

    public BaseModel() {
    }

    @Override
    public void setHeader(ArrayMap<String, String> headers) {
        headerInterceptor.setHeaders(headers);
    }

    @Override
    public void setParam(ArrayMap<String, String> params) {
        paramsInterceptor.setParams(params);
    }

    @Override
    public void onDestroy() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }


    /**
     * json格式提交数据
     *
     * @param json
     * @return
     */
    public RequestBody toRequestBody(String json) {
        MediaType mediaType = MediaType.Companion.parse("application/json;charset=utf-8");
        return RequestBody.Companion.create(json, mediaType);
    }


    /**
     * 上传多图 带参数
     *
     * @param params
     * @param imageUrl
     * @return
     */
    public RequestBody toRequestBody(Map<String, String> params, List<String> imageUrl) {


        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        MediaType fileRQ = MediaType.Companion.parse("multipart/form-data");
        if (params != null) {
            for (String key : params.keySet()) {
                multipartBodyBuilder.addFormDataPart(key, Objects.requireNonNull(params.get(key)));
            }
        }

        if (imageUrl != null && imageUrl.size() > 0) {
            for (int i = 0; i < imageUrl.size(); i++) {
                File file = new File(imageUrl.get(i));
                RequestBody fileBody = RequestBody.Companion.create(file, fileRQ);
                multipartBodyBuilder.addFormDataPart("file", file.getName(), fileBody);
            }
        }

        RequestBody requestBody = multipartBodyBuilder.build();


        return requestBody;
    }
}
