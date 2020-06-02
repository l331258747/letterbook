package com.csxs.letterbook.login.activity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;

import com.csxs.core.base.BaseDiffActivity;
import com.csxs.core.utils.DrawableUtils;
import com.csxs.core.utils.MmkvUtlis;
import com.csxs.letterbook.GlobalConstants;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.SmsLoginE;
import com.csxs.letterbook.entity.WxLoginE;
import com.csxs.letterbook.home.HomeActivity;
import com.csxs.letterbook.login.mvp.contract.BindPhoneContract;
import com.csxs.letterbook.login.mvp.contract.LoginContract;
import com.csxs.letterbook.login.mvp.presenter.BindPhonePresenter;
import com.csxs.letterbook.login.mvp.presenter.SmsCodeLoginPresenter;
import com.csxs.viewlib.ClearEditText;
import com.csxs.viewlib.DensityUtil;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.uber.autodispose.AutoDispose.autoDisposable;

/**
 * @author: yeliu
 * created on 2020/5/7
 * description: 绑定手机号码
 */
public class BindPhoneActivity extends BaseDiffActivity<BindPhonePresenter> implements BindPhoneContract.IBindPhoneView {


    @BindView(R.id.user_bind_account_phone)
    ClearEditText userAccountPhone;

    @BindView(R.id.et_login_verif_code)
    EditText etLoginVerifCode;

    @BindView(R.id.tv_send_code)
    TextView tvSendCode;

    @BindView(R.id.tv_confirm_bind_phone)
    TextView tvLoginSmsCode;

    @BindView(R.id.rb_agree)
    CheckBox cbAgree;

    private Drawable bgSmsCodeNormalDrawable;
    private Drawable bgSmsCodeDefaultDrawable;

    private Drawable bgSmsLoginNormalDrawable;
    private Drawable bgSmsLoginDefaultDrawable;

    private int textNormalColor;
    private int textDefaultColor;

    private boolean inputPhoneComplete = false;

    private boolean isInputSmsCodeComplete = false;


    @Inject
    MmkvUtlis mmkvUtlis;
    private int type;


    @Override
    public void initParam() {
        super.initParam();
        hideTitleBar = false;
        isStateView = false;
        topBarView = false;
    }

    @Override
    public void setWindowBackGround() {
        Resources res = getResources();
        Drawable drawable = res.getDrawable(com.csxs.core.R.color.white);
        this.getWindow().setBackgroundDrawable(drawable);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type",-1);
        tvLoginSmsCode.setEnabled(false);
        tvSendCode.setEnabled(false);
        initButtonDrawable();
        initButtonListener();

    }

    private void initButtonListener() {
        userAccountPhone.setOnTextChangedListener(new ClearEditText.OnTextChangedListener() {

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                if (str.length() - 2 >= 11) {
                    tvSendCode.setBackground(bgSmsCodeNormalDrawable);
                    tvSendCode.setTextColor(textNormalColor);
                    tvSendCode.setEnabled(true);
                    inputPhoneComplete = true;

                    if (isInputSmsCodeComplete) {
                        tvLoginSmsCode.setBackground(bgSmsLoginNormalDrawable);
                        tvLoginSmsCode.setTextColor(textNormalColor);
                        tvLoginSmsCode.setEnabled(true);
                    }
                } else {

                    tvSendCode.setBackground(bgSmsCodeDefaultDrawable);
                    tvSendCode.setTextColor(textDefaultColor);
                    tvSendCode.setEnabled(false);
                    inputPhoneComplete = false;

                    tvLoginSmsCode.setBackground(bgSmsLoginDefaultDrawable);
                    tvLoginSmsCode.setTextColor(textDefaultColor);
                    tvLoginSmsCode.setEnabled(false);

                }
            }
        });

        etLoginVerifCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                //密码长度检查
                if (str.length() >= 6) {
                    if (inputPhoneComplete) {
                        tvLoginSmsCode.setBackground(bgSmsLoginNormalDrawable);
                        tvLoginSmsCode.setTextColor(textNormalColor);
                        tvLoginSmsCode.setEnabled(true);
                    }
                    isInputSmsCodeComplete = true;
                } else {
                    tvLoginSmsCode.setBackground(bgSmsLoginDefaultDrawable);
                    tvLoginSmsCode.setTextColor(textDefaultColor);
                    tvLoginSmsCode.setEnabled(false);
                    isInputSmsCodeComplete = false;
                }
            }
        });

        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


    }


    private void initButtonDrawable() {
        textNormalColor = getResources().getColor(R.color.white);
        textDefaultColor = getResources().getColor(R.color.black_cccccc);

        // 验证码 有效 Drawable
        int bgNormalColor = getResources().getColor(R.color.black);
        bgSmsCodeNormalDrawable = DrawableUtils.getShapeDrawable(bgNormalColor, bgNormalColor, 1, 1, 0, DensityUtil.dip2px(mContext, 20));
        // 验证码 默认 Drawable 效果
        int bgDefaultColor = getResources().getColor(R.color.color_F3F3F8);
        bgSmsCodeDefaultDrawable = DrawableUtils.getShapeDrawable(bgDefaultColor, bgDefaultColor, 1, 1, 0, DensityUtil.dip2px(mContext, 20));

        //短信 登录 Drawable
        bgSmsLoginNormalDrawable = DrawableUtils.getShapeDrawable(bgNormalColor, bgNormalColor, 1, 1, 0, DensityUtil.dip2px(mContext, 50));
        bgSmsLoginDefaultDrawable = DrawableUtils.getShapeDrawable(bgDefaultColor, bgDefaultColor, 1, 1, 0, DensityUtil.dip2px(mContext, 50));
    }

    @Override
    protected void onInitData() {


    }


    @OnClick({R.id.tv_send_code, R.id.tv_confirm_bind_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_code:
                String phone = userAccountPhone.getPhoneText();
                if (phone.equals("")) {
                    Toast.makeText(mContext, "请填写正确手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.sendSmsCode(86, phone);

                break;
            case R.id.tv_confirm_bind_phone:
                if (!cbAgree.isChecked()) {
                    Toast.makeText(this, "请阅读:用户协议与隐私协议", Toast.LENGTH_SHORT).show();
                    return;
                }


             //   mPresenter.smsCodeLogin("86", userAccountPhone.getPhoneText(), etLoginVerifCode.getText().toString());
                mPresenter.bindPhone("86", userAccountPhone.getPhoneText(), etLoginVerifCode.getText().toString());
                break;
        }
    }

    /**
     * 验证码倒计时
     */
    private void smsCodeCountTimeDown() {
        Flowable.intervalRange(0, 61, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        long countTime = 60 - aLong;
                        tvSendCode.setText(String.valueOf(countTime));
                        tvSendCode.setEnabled(false);
                    }
                }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                tvSendCode.setText("获取验证码");
                tvSendCode.setEnabled(true);
            }
        }).as(autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))).subscribe();


    }


    @Override
    public void sendSmsCodeSuccess(String s) {
        smsCodeCountTimeDown();
    }

    @Override
    public void bindPhoneSuccess(WxLoginE wxLoginE) {

        Log.e("token", wxLoginE.getToken());
        mmkvUtlis.putUserInfo(type,wxLoginE.getToken(), wxLoginE.getPhoneNumber(), wxLoginE.getHeadimgurl(), wxLoginE.getNickname(), wxLoginE.getSex(), wxLoginE.getUserId());
        HomeActivity.start(this,HomeActivity.class,null);
        finish();
    }



}
