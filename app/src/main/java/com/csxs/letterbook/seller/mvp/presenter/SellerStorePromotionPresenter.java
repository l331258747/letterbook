package com.csxs.letterbook.seller.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;
import com.csxs.letterbook.seller.mvp.model.SellerStoreDynamicModel;
import com.csxs.letterbook.seller.mvp.model.SellerStorePromotionModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商家主页  --> 商家活动P
 */
public class SellerStorePromotionPresenter extends BasePresenter<SellerStorePromotionModel, SellerStoreContract.ISellerStorePromotionView> {

    @Inject
    public SellerStorePromotionPresenter() {

    }
}
