package com.csxs.letterbook.social.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.PersonalDynamicE;
import com.csxs.letterbook.social.mvp.contract.PersonalDynamicContract;
import com.csxs.letterbook.social.mvp.model.PersonDynamicModel;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/6/2
 * description:
 */
public class PersonalDynamicPresenter extends BasePresenter<PersonDynamicModel, PersonalDynamicContract.IPersonalDynamicView> implements PersonalDynamicContract.IPersonalDynamicPresenter {

    @Inject
    public PersonalDynamicPresenter() {
    }

    @Override
    public void getDynamicList() {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.getDynamicList(), new ResultErrorObserver<List<PersonalDynamicE>>() {

            @Override
            public void handlerResult(List<PersonalDynamicE> personalDynamicES) {
                getView().getDynamicListSuccess(personalDynamicES);
            }

            @Override
            public void handlerError(int code, String msg) {
                views.showError(msg);
            }
        });
    }
}
