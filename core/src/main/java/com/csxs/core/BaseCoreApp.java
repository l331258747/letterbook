package com.csxs.core;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import com.csxs.core.utils.DynamicTimeFormat;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import me.jessyan.autosize.AutoSizeConfig;


/**
 * 基类Application
 */
public class BaseCoreApp extends Application {

    public static BaseCoreApp appConext;

    public String sessiontoken="";

    public static IWXAPI iwxapi;
    @Override
    public void onCreate() {
        super.onCreate();
        appConext=this;
        initAutoSize();
//        refWatcher= setupLeakCanary();

        iwxapi = WXAPIFactory.createWXAPI(this, BaseConstants.WX_APPID, false);

    }

    private void initAutoSize() {
        AutoSizeConfig.getInstance().setExcludeFontScale(true);
      //  AutoSizeConfig.getInstance().getExternalAdaptManager();
    }



    /**
     * 下拉刷新
     * SmartRefreshLayout
     */
    static {
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            //全局设置（优先级最低）
            layout.setEnableAutoLoadMore(true);
            layout.setEnableOverScrollDrag(false);
            layout.setEnableOverScrollBounce(true);
            layout.setEnableLoadMoreWhenContentNotFull(true);
            layout.setEnableScrollContentWhenRefreshed(true);
            layout.setPrimaryColorsId(R.color.white_f1f1f1, android.R.color.background_dark);//白色背景，黑色文字
            layout.getLayout().setTag("close egg");
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
            return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
        });
    }

}
