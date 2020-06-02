package com.csxs.core.net.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sins
 * Date : 2018/11/17
 * Desc :
 */
public class RequesHeadertInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request()

                .newBuilder()

                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")

                .addHeader("Accept-Encoding", "gzip, deflate")

                .addHeader("Connection", "keep-alive")

                .addHeader("Accept", "*/*")

                .addHeader("Cookie", "add cookies here")

                .build();

        return chain.proceed(request);
    }
}
