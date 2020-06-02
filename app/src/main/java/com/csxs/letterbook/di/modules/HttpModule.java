package com.csxs.letterbook.di.modules;




import com.csxs.core.net.RetrofitManager;
import com.csxs.core.net.interceptor.HeaderInterceptor;
import com.csxs.core.net.interceptor.ParamsInterceptor;
import com.csxs.core.utils.MmkvUtlis;
import com.csxs.letterbook.api.LetterApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class HttpModule {





    /**
     * 实例化 Retrofit
     *
     * @return
     */
    @Singleton
    @Provides
    RetrofitManager provideRetrofitManager() {
        return RetrofitManager.getInstance();
    }

    /**
     * 实例化 ApiService
     *
     * @param retrofitManager
     * @return
     */
    @Singleton
    @Provides
    LetterApiService provideApiService(RetrofitManager retrofitManager) {
        return retrofitManager.create(LetterApiService.class);
    }

    /**
     * 获取 okhttp 头部 拦截器
     *
     * @param retrofitManager
     * @return
     */
    @Singleton
    @Provides
    HeaderInterceptor provideHeaderInterceptor(RetrofitManager retrofitManager) {
        return retrofitManager.getHeaderInterceptor();
    }

    /**
     * 获取 okhttp 参数 拦截器
     *
     * @param retrofitManager
     * @return
     */
    @Singleton
    @Provides
    ParamsInterceptor provideParamsInterceptor(RetrofitManager retrofitManager) {
        return retrofitManager.getParamsInterceptor();
    }





//    @Singleton
//    @Provides
//    WebSocket provideWebSocket(RetrofitManager retrofitManager) {
//        return retrofitManager.getmWebSocket();
//    }
}
