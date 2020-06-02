package com.csxs.letterbook.login.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.letterbook.example.mvp.contract.MvpContract;
import com.csxs.letterbook.example.mvp.model.MvpModel;
import com.csxs.letterbook.login.mvp.contract.LoginContract;
import com.csxs.letterbook.login.mvp.model.SplashModel;

import javax.inject.Inject;

/**
 * 闪屏页 Presenter
 */
public class SplashPresenter extends BasePresenter<SplashModel, LoginContract.ISplashView> {

    @Inject
    public SplashPresenter() {}

}
