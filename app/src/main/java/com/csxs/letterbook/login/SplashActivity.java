package com.csxs.letterbook.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.csxs.core.base.BaseDiffActivity;
import com.csxs.core.utils.MmkvUtlis;
import com.csxs.core.utils.permission.PermissionUtils;
import com.csxs.letterbook.GlobalConstants;
import com.csxs.letterbook.LetterApp;
import com.csxs.letterbook.R;
import com.csxs.letterbook.home.HomeActivity;
import com.csxs.letterbook.login.activity.LoginTypeActivity;
import com.csxs.letterbook.login.mvp.presenter.SplashPresenter;
import com.csxs.letterbook.mapservice.LocationService;
import com.csxs.letterbook.mapservice.listener.LocationListener;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.jessyan.autosize.internal.CancelAdapt;

import static com.uber.autodispose.AutoDispose.autoDisposable;

/**
 * 闪屏页 取消AutoSize适配  CancelAdapt美团适配方案
 */
public class SplashActivity extends BaseDiffActivity<SplashPresenter> implements CancelAdapt, LocationListener {

    private LocationService locationService;

    @Inject
    MmkvUtlis mmkvUtlis;

    @Inject
    PermissionUtils permissionUtils;
    private int login;

    @Override
    public void initParam() {
        super.initParam();
        hideTitleBar = false;
        isStateView = false;
        topBarView = false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    public void onInitView(Bundle savedInstanceState) {

        login = mmkvUtlis.getMmkv().getInt("loginway", 0);


        //申请定位权限
        AndPermission.with(mContext)
                .runtime()
                .permission(Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_FINE_LOCATION)
                // 准备方法，和 okhttp 的拦截器一样，在请求权限之前先运行改方法，已经拥有权限不会触发该方法
                .rationale((context, permissions, executor) -> {
                    // 此处可以选择显示提示弹窗
                    executor.execute();
                })
                // 用户给权限了
                .onGranted(permissions -> {
                    location();
                })
                // 用户拒绝权限，包括不再显示权限弹窗也在此列
                .onDenied(permissions -> {
                    // 判断用户是不是不再显示权限弹窗了，若不再显示的话进入权限设置页
                    if (AndPermission.hasAlwaysDeniedPermission(mContext, permissions)) {
                        return;
                    }
                })
                .start();

    }


    @Override
    protected void onInitData() {

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public void location() {
        locationService = ((LetterApp) activity.getApplicationContext()).locationService;
     //   getOwnerLifeCycle().addObserver(locationService);
        locationService.registerListener(SplashActivity.this);
        locationService.start();
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if(locationService!=null){
            locationService.onMapDestroy();
        }

        super.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    public void onLocationResult(BDLocation location) {
        if (location == null) {
            return;
        }

        if (location.getLocType() == BDLocation.TypeCriteriaException) {
            delay();
            return;
        }

        String strLatitude = String.valueOf(location.getLatitude());
        String strLongitude = String.valueOf(location.getLongitude());
        Log.e("strLatitude", strLatitude);
        Log.e("strLongitude", strLongitude);
        String token= mmkvUtlis.getMmkv().getString("token","");
        Log.e("strtoken", token);
        mmkvUtlis.putLoationInfo(strLatitude, strLongitude);
        delay();

    }

    @Override
    public void onLocationFailure(int code) {

    }


    public void delay() {

        Flowable.intervalRange(0, 4, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        long countTime = 4 - aLong;
                        Log.e("delay", countTime + "");
                    }
                }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                if (login != 0) {
                    if (login == GlobalConstants.LOGIN_WAY_SMS) {
                        //短信验证码登录

                    } else if (login == GlobalConstants.LOGIN_WAY_WEIXIN) {
                        //微信登录
                    }
                    finish();
                    HomeActivity.start(SplashActivity.this, HomeActivity.class, null);

                } else {
                    finish();
                    startActivity(new Intent(SplashActivity.this, LoginTypeActivity.class));

                }

            }
        }).as(autoDisposable(AndroidLifecycleScopeProvider.from(this))).subscribe();
    }

}
