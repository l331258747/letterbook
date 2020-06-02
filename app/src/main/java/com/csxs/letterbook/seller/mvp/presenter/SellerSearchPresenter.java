package com.csxs.letterbook.seller.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.letterbook.seller.mvp.contract.SellerSearchContract;
import com.csxs.letterbook.seller.mvp.model.SellerSearchModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/17
 * description:商家搜索P
 */
public class SellerSearchPresenter extends BasePresenter<SellerSearchModel, SellerSearchContract.ISellerSearchView> {

    @Inject
    public SellerSearchPresenter() {

    }

}
