package com.csxs.letterbook.seller.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商铺活动model
 */
public class SellerStorePromotionModel extends BaseModel implements SellerStoreContract.ISellerStorePromotionModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public SellerStorePromotionModel() {
    }
}
