package com.csxs.letterbook.login.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.SmsLoginE;
import com.csxs.letterbook.entity.fuli;

import java.util.List;

import io.reactivex.Observable;

/**
 * 登录模块 mvp 契约
 */
public interface LoginContract {

    /**
     * 闪屏页 View回调接口
     */
    interface ISplashView extends IBaseView {

    }

    /**
     * 闪屏页 Model 接口
     */
    interface ISplashModel extends IModel {

    }


    /**
     * 选择登录方式View
     */
    interface ILoginTypeView extends IBaseView {

    }

    /**
     * 选择登录方式Model
     */
    interface ILoginTypeModel extends IModel {

    }

    /**
     * 短信登录登录View
     */
    interface ISmsCodeLoginView extends IBaseView {

        void sendSmsCodeSuccess(String s);
        void loginBySmsCodeSuccess(SmsLoginE smsLogin);
    }


    /**
     * 短信登录登录P接口
     */
    interface ISmsCodeLoginPresenter{
        void sendSmsCode(int nationCode,String phoneNumber);
        void smsCodeLogin(String nationCode,String phoneNumber,String code);
    }


    /**
     * 短信登录登录Model 接口
     */
    interface ISmsCodeLoginModel extends IModel {
        Observable<Result<String>> sendSmsCode(int nationCode,String phoneNumber);
        Observable<Result<SmsLoginE>> smsCodeLogin(String nationCode, String phoneNumber, String code);
    }




}
