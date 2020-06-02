package com.csxs.letterbook.login.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.SmsLoginE;
import com.csxs.letterbook.login.mvp.contract.LoginContract;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/10
 * description: 短信登录 model
 */
public class SmsCodeLoginModel extends BaseModel implements LoginContract.ISmsCodeLoginModel {
    @Inject
    LetterApiService apiService;



    @Inject
    public SmsCodeLoginModel(){}


    @Override
    public Observable<Result<String>> sendSmsCode(int nationCode,String phoneNumber) {
        return apiService.sendSmsCode(nationCode,phoneNumber);
    }

    @Override
    public Observable<Result<SmsLoginE>> smsCodeLogin(String nationCode, String phoneNumber, String code) {
        return apiService.loginBySmsCode(String.valueOf(nationCode),phoneNumber,code);
    }
}
