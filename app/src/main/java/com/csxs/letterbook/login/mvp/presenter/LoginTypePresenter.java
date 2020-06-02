package com.csxs.letterbook.login.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.letterbook.login.mvp.contract.LoginContract;
import com.csxs.letterbook.login.mvp.model.LoginTypeModel;
import com.csxs.letterbook.login.mvp.model.SplashModel;

import javax.inject.Inject;

/**
 * 登录方式 Presenter
 */
public class LoginTypePresenter extends BasePresenter<LoginTypeModel, LoginContract.ILoginTypeView> {

    @Inject
    public LoginTypePresenter() {}

}
