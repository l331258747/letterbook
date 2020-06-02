package com.csxs.letterbook.login.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.csxs.core.base.BaseDiffActivity;
import com.csxs.letterbook.LetterApp;
import com.csxs.letterbook.R;
import com.csxs.letterbook.login.mvp.presenter.LoginTypePresenter;
import com.gyf.immersionbar.ImmersionBar;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: yeliu
 * created on 2020/4/9
 * description:选择登录方式 微信或者手机
 */
public class LoginTypeActivity extends BaseDiffActivity<LoginTypePresenter> {


    @BindView(R.id.iv_logo_login)
    ImageView ivLogoLogin;
    @BindView(R.id.iv_text)
    ImageView ivText;
    @BindView(R.id.login_wx)
    Button loginWx;
    @BindView(R.id.login_phone)
    Button loginPhone;
    @BindView(R.id.rl_mask)
    RelativeLayout rlMask;

    @Override
    public void initParam() {
        super.initParam();
        hideTitleBar = false;
        isStateView = false;
        topBarView = false;
    }

    @Override
    public void initStatusBar() {
        ImmersionBar.with(this).statusBarDarkFont(false).init();
    }

    @Override
    public void setWindowBackGround() {
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.bg_login_type);
        this.getWindow().setBackgroundDrawable(drawable);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_type;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitData() {

    }


    @OnClick({R.id.login_wx, R.id.login_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_wx:
                boolean isPaySupported = LetterApp.iwxapi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
                if(isPaySupported){
                    final SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo,snsapi_friend,snsapi_message,snsapi_contact";
                    req.state = "none";
                    LetterApp.iwxapi.sendReq(req);
                }else{
                    Toast.makeText(this, "没有安装微信或微信版本过低", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.login_phone:
                SmsCodeLoginActivity.start(this,SmsCodeLoginActivity.class,null);
                finish();
                break;
        }
    }
}
