package com.csxs.core.net.interceptor;

import android.util.Log;

import com.csxs.core.BaseCoreApp;
import com.csxs.core.net.interceptor.netstate.NetWorkState;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 没有网时候的缓存
 */
public class OfflineCacheInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetWorkState.isNetworkAvailable(BaseCoreApp.appConext)) {
            int offlineCacheTime = 60*60;//离线的时候的缓存的过期时间
            request = request.newBuilder()
//                        .cacheControl(new CacheControl
//                                .Builder()
//                                .maxStale(60,TimeUnit.SECONDS)
//                                .onlyIfCached()
//                                .build()
//                        ) 两种方式结果是一样的，写法不同
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + offlineCacheTime)
                    .build();
            Log.e("OfflineCacheInterceptor","没有网时候的缓存");
        }
        return chain.proceed(request);
    }
}
