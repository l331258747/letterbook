package com.csxs.letterbook.login.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.SmsLoginE;
import com.csxs.letterbook.entity.WxLoginE;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/5/7
 * description:
 */
public interface BindPhoneContract {


    interface IBindPhoneView extends IBaseView {
        void sendSmsCodeSuccess(String s);

        void bindPhoneSuccess(WxLoginE wxLoginE);
    }

    interface IBindPhoneModel extends IModel {
        Observable<Result<String>> sendSmsCode(int nationCode, String phoneNumber);
        Observable<Result<WxLoginE>> bindPhone(String nationCode, String phoneNumber, String code);
    }


    interface IBindPhonePresenter {
        void sendSmsCode(int nationCode, String phoneNumber);

        void bindPhone(String nationCode, String phoneNumber, String code);
    }

}
