package com.csxs.letterbook.example.mvp;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.fuli;
import com.csxs.letterbook.example.mvp.contract.MvpContract;
import com.csxs.letterbook.example.mvp.model.MvpModel;

import java.util.List;

import javax.inject.Inject;

public class MvpPresenter extends BasePresenter<MvpModel, MvpContract.IHomeInfoView> {

    @Inject
    public MvpPresenter() {}
    /**
     * Presenter 方法随便写
     */
    public void getFuli() {

        requestHttpResult(getView().getOwnerLifeCycle(), mModel.getFuli(), new ResultErrorObserver<List<fuli>>() {
            @Override
            public void handlerResult(List<fuli> list) {
                getView().reusltSucessFuli(list);
            }

            @Override
            public void handlerError(int code, String msg) {
                views.showError(msg);
            }
        });
    }

}
