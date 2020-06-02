package com.csxs.letterbook.login.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.login.mvp.contract.LoginContract;

import javax.inject.Inject;

/**
 * 闪屏页Model
 */
public class LoginTypeModel extends BaseModel implements LoginContract.ISplashModel {

    @Inject
    LetterApiService apiService;



    @Inject
    public LoginTypeModel(){}


}
