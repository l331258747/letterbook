package com.csxs.letterbook.seller.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.seller.mvp.contract.SellerHomeContract;
import com.csxs.letterbook.seller.mvp.model.SellerHomeModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商家主页P
 */
public class SellerHomePresenter extends BasePresenter<SellerHomeModel, SellerHomeContract.ISellerHomeView> implements SellerHomeContract.ISellerHomePresenter {

    @Inject
    public SellerHomePresenter() {
    }

    @Override
    public void attentionUser(int marchantId) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.attentionUser(marchantId), new ResultErrorObserver<String>() {
            @Override
            public void handlerResult(String mapSellerDetailsInfoE) {
                getView().attentionUserSuccess();
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().attentionUserFailure(code, msg);
            }
        });
    }

    @Override
    public void cancleAttentionUser(int marchantId) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.cancleAttentionUser(marchantId), new ResultErrorObserver<String>() {
            @Override
            public void handlerResult(String mapSellerDetailsInfoE) {
                getView().cancleAttentionUserSuccess();
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().cancleAttentionUserFailure(code, msg);
            }
        });
    }

    @Override
    public void querySingleSellerInfo(int shopId, double latitude, double longitude) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.querySellerInfo(shopId, latitude, longitude), new ResultErrorObserver<MapSellerDetailsInfoE>() {
            @Override
            public void handlerResult(MapSellerDetailsInfoE mapSellerDetailsInfoE) {
                getView().sellerDetailsInfoSuccess(mapSellerDetailsInfoE);
            }

            @Override
            public void handlerError(int code, String msg) {
//                getView().showError(msg);
                getView().sellerDetailsInfoFailure(code, msg);
            }
        });
    }
}
