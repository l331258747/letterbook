package com.csxs.letterbook.seller.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.SellerGoodsE;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;
import com.csxs.letterbook.seller.mvp.model.SellerStoreDynamicModel;
import com.csxs.letterbook.seller.mvp.model.SellerStoreOrderModel;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商家主页 --> 商家动态P
 */
public class SellerStoreOrderPresenter extends BasePresenter<SellerStoreOrderModel, SellerStoreContract.ISellerStoreOrderView> implements SellerStoreContract.ISellerStoreOrderPresenter{

    @Inject
    public SellerStoreOrderPresenter() {

    }

    @Override
    public void queryCommodity(int marchantId) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.queryCommodity(marchantId), new ResultErrorObserver<List<SellerGoodsE>>() {
            @Override
            public void handlerResult(List<SellerGoodsE> list) {
                getView().queryCommoditySuccess(list);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().queryCommodityFailure(code,msg);

            }
        });
    }
}
