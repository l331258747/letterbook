package com.csxs.letterbook.seller.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.MarchantCommentE;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;
import com.csxs.letterbook.seller.mvp.model.SellerStoreDynamicModel;
import com.csxs.letterbook.seller.mvp.model.SellerStoreInfoModel;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商家主页 --> 商家动态P
 */
public class SellerStoreDynamicPresenter extends BasePresenter<SellerStoreDynamicModel, SellerStoreContract.ISellerStoreDynamicView> implements SellerStoreContract.ISellerStoreDynamicPresenter {

    @Inject
    public SellerStoreDynamicPresenter() {

    }

    @Override
    public void querySellerHomeDynamic(int id, double latitude, double longitude) {

        requestHttpResult(getView().getOwnerLifeCycle(), mModel.queryMarchantComment(id, latitude, longitude), new ResultErrorObserver<List<MarchantCommentE>>() {
            @Override
            public void handlerResult(List<MarchantCommentE> mapSellerDetailsInfoE) {
                getView().sellerHomeDynamicSuccess(mapSellerDetailsInfoE);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().sellerHomeDynamicFailure(code, msg);

            }
        });
    }
}
