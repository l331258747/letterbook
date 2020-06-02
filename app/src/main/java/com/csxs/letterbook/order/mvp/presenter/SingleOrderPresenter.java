package com.csxs.letterbook.order.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.MapSocialUserInfoE;
import com.csxs.letterbook.entity.SingleOrderE;
import com.csxs.letterbook.order.mvp.contract.SingleOrderContract;
import com.csxs.letterbook.order.mvp.model.SingleOrderModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/5/28
 * description:
 */
public class SingleOrderPresenter extends BasePresenter<SingleOrderModel, SingleOrderContract.ISingleOrderView> implements SingleOrderContract.ISingleOrderPresenter {

    @Inject
    public SingleOrderPresenter() {
    }


    @Override
    public void querySingleOrderInfo(String order) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.querySingleOrderInfo(order), new ResultErrorObserver<SingleOrderE>() {
            @Override
            public void handlerResult(SingleOrderE singleOrderE) {
                getView().singleOrderInfoSuccess(singleOrderE);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().singleOrderInfoFailure(code,msg);
            }
        });
    }
}
