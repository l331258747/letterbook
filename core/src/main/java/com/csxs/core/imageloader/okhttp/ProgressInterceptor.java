package com.csxs.core.imageloader.okhttp;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: SenYe
 * @CreateDate: 2020/1/19 21:54
 * @Description: 类作用描述
 */
public class ProgressInterceptor implements Interceptor {

    OnProgressListener listener;
    public ProgressInterceptor(OnProgressListener listener){
       this.listener=listener;
    }


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return response.newBuilder()
                .body(new ProgressResponseBody(request.url().toString(), response.body(), listener))
                .build();
    }
}
