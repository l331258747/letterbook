package com.csxs.letterbook.login.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.SmsLoginE;
import com.csxs.letterbook.entity.WxLoginE;
import com.csxs.letterbook.entity.WxUserInfoE;
import com.csxs.letterbook.login.mvp.contract.BindPhoneContract;
import com.csxs.letterbook.login.mvp.contract.LoginContract;
import com.csxs.letterbook.login.mvp.model.BindPhoneModel;
import com.csxs.letterbook.login.mvp.model.SmsCodeLoginModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/5/7
 * description: 绑定手机号码 控制器P
 */
public class BindPhonePresenter extends BasePresenter<BindPhoneModel, BindPhoneContract.IBindPhoneView> implements BindPhoneContract.IBindPhonePresenter {

    @Inject
    public BindPhonePresenter() {
    }


    /**
     * 获取验证码
     *
     * @param nationCode  区号
     * @param phoneNumber 手机号
     */
    @Override
    public void sendSmsCode(int nationCode, String phoneNumber) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.sendSmsCode(nationCode, phoneNumber), new ResultErrorObserver<String>() {
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
     * 绑定手机号码
     *
     * @param nationCode
     * @param phoneNumber
     * @param code
     */
    @Override
    public void bindPhone(String nationCode, String phoneNumber, String code) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.bindPhone(nationCode, phoneNumber, code), new ResultErrorObserver<WxLoginE>() {
            @Override
            public void handlerResult(WxLoginE wxLoginE) {
                getView().bindPhoneSuccess(wxLoginE);
            }

            @Override
            public void handlerError(int code, String msg) {
                views.showError(msg);
            }
        });
    }


}
