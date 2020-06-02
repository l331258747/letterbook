package com.csxs.letterbook.login.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;

import java.io.File;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/10
 * description:
 */
public interface PrefectUserInfoContract {

    /**
     * 完善用户信息 View回调接口
     */
    interface IPrefectUserInfoView extends IBaseView {

        void submitSuccess();

    }

    /**
     * 完善用户信息 Model 接口
     */
    interface IPrefectUserInfoModel extends IModel {
        Observable<String> submitUserInfo(String nackname,String phoneNumber, int sex, File file);
    }


    interface IPrefectUserInfoPresenter{
        void submitUserInfo(String nackname,String phoneNumber, int sex, File file);

    }
}
