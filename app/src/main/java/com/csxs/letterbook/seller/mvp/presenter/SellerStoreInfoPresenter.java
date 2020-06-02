package com.csxs.letterbook.seller.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.letterbook.seller.mvp.contract.SellerSearchContract;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;
import com.csxs.letterbook.seller.mvp.model.SellerSearchModel;
import com.csxs.letterbook.seller.mvp.model.SellerStoreInfoModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商家主页 --> 商家信息P
 */
public class SellerStoreInfoPresenter extends BasePresenter<SellerStoreInfoModel, SellerStoreContract.ISellerStoreInfoView> {

    @Inject
    public SellerStoreInfoPresenter() {

    }
}
