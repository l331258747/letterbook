package com.csxs.letterbook.social.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.MapSocialUserInfoE;
import com.csxs.letterbook.social.mvp.contract.MapSocialContract;
import com.csxs.letterbook.social.mvp.contract.PersonalHomeContract;
import com.csxs.letterbook.social.mvp.model.PersonalHomeModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/5/23
 * description:
 */
public class PersonalHomePresenter extends BasePresenter<PersonalHomeModel, PersonalHomeContract.IPersonalHomeView> implements PersonalHomeContract.IPersonalHomePresenter {

    @Inject
    public PersonalHomePresenter() {
    }

    @Override
    public void querySingleUserInfo(int userId) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.querySingleUserInfo(userId), new ResultErrorObserver<MapSocialUserInfoE>() {
            @Override
            public void handlerResult(MapSocialUserInfoE mapSocialInfoE) {
                getView().singleUserInfoSuccess(mapSocialInfoE);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().singleUserInfoFailure(code,msg);
            }
        });
    }
}
