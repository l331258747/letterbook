package com.csxs.letterbook.login.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.Result;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.core.net.result.ResultObserver;
import com.csxs.letterbook.login.mvp.contract.PrefectUserInfoContract;
import com.csxs.letterbook.login.mvp.model.PrefectUserInfoModel;

import java.io.File;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/10
 * description:完善用户信息 控制器  P
 */
public class PerfectUserInfoPresenter extends BasePresenter<PrefectUserInfoModel, PrefectUserInfoContract.IPrefectUserInfoView> implements PrefectUserInfoContract.IPrefectUserInfoPresenter{

    @Inject
    public PerfectUserInfoPresenter(){}

    @Override
    public void submitUserInfo(String nackname, String phoneNumber, int sex, File file) {

        requestHttpResult(getView().getOwnerLifeCycle(), mModel.submitUserInfo11(nackname, "", sex, file), new ResultErrorObserver<String>() {


            @Override
            public void handlerResult(String s) {
                views.submitSuccess();
            }

            @Override
            public void handlerError(int code, String msg) {
                views.showError(msg);
            }
        });
    }
}
