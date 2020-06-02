package com.csxs.letterbook.mine.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.mine.mvp.contract.SendDynamicContract;
import com.csxs.letterbook.mine.mvp.model.SendDynamicModel;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/5/13
 * description:
 */
public class SendDynamicPresenter extends BasePresenter<SendDynamicModel, SendDynamicContract.ISendDynamicView> implements SendDynamicContract.ISendDynamicPresenter{

    @Inject
    public SendDynamicPresenter() {

    }


    @Override
    public void sendDynamic(int marchantId, String content, int isMarchant, double latitude, double longitude, String address, List<String> image) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.addMarchantComment(marchantId,content,isMarchant,latitude,longitude,address,image), new ResultErrorObserver<String>() {
            @Override
            public void handlerResult(String s) {
                getView().sendDynamicSuccess(s);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().sendDynamicFailure(code,msg);
            }
        });
    }
}
