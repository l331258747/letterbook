package com.csxs.letterbook.login.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.SmsLoginE;
import com.csxs.letterbook.login.mvp.contract.LoginContract;
import com.csxs.letterbook.login.mvp.model.SmsCodeLoginModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/10
 * description: 短信登录 控制器P
 */
public class SmsCodeLoginPresenter extends BasePresenter<SmsCodeLoginModel, LoginContract.ISmsCodeLoginView> implements LoginContract.ISmsCodeLoginPresenter {
    @Inject
    public SmsCodeLoginPresenter() {}


    /**
     * 获取验证码
     * @param nationCode 区号
     * @param phoneNumber 手机号
     */
    @Override
    public void sendSmsCode(int nationCode,String phoneNumber) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.sendSmsCode(nationCode,phoneNumber), new ResultErrorObserver<String>() {
            @Override
            public void handlerResult(String s) {
                getView().sendSmsCodeSuccess(s);
            }

            @Override
            public void handlerError(int code, String msg) {
                views.showError(msg);
            }
        });

    }

    /**
     * 验证码登录
     */
    @Override
    public void smsCodeLogin(String nationCode,String phoneNumber,String code) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.smsCodeLogin(nationCode,phoneNumber,code), new ResultErrorObserver<SmsLoginE>() {
            @Override
            public void handlerResult(SmsLoginE smsLogin) {
                getView().loginBySmsCodeSuccess(smsLogin);
            }

            @Override
            public void handlerError(int code, String msg) {
                views.showError(msg);
            }
        });

    }

}
