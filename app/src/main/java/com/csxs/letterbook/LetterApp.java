package com.csxs.letterbook;

import android.app.Service;
import android.os.Vibrator;
import android.util.Log;


import com.baidu.mapapi.SDKInitializer;
import com.csxs.core.BaseCoreApp;
import com.csxs.core.utils.SystemUtil;
import com.csxs.letterbook.di.component.DaggerLetterAppComponent;
import com.csxs.letterbook.mapservice.LocationService;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class LetterApp extends BaseCoreApp implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;
    public LocationService locationService;
    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        String processName = SystemUtil.getProcessName(getApplicationContext(), android.os.Process.myPid());
        if(!getPackageName().equals(processName)){//非主进程不执行onCreate里面操作
            return;
        }
        super.onCreate();
        DaggerLetterAppComponent.factory().create(this).inject(this);
        initMap();
        RxjavaError();
    }

    private void initMap() {

        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        Log.e("initMap","地图初始化");

       // SDKInitializer.setCoordType(CoordType.BD09LL);
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    /**
     * rxjava2 全局异常
     */
    public void RxjavaError(){
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("ErrorHandler",throwable.toString());
            }
        });
    }

}
