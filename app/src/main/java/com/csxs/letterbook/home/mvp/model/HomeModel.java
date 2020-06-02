package com.csxs.letterbook.home.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.home.mvp.contract.HomeContract;
import com.csxs.letterbook.login.mvp.contract.LoginContract;

import javax.inject.Inject;

/**
 * 闪屏页Model
 */
public class HomeModel extends BaseModel implements HomeContract.IHomeModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public HomeModel(){}


}
