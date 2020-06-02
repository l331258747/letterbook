package com.csxs.letterbook.seller.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.seller.mvp.contract.SellerSearchContract;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/17
 * description:
 */
public class SellerSearchModel extends BaseModel implements SellerSearchContract.ISellerSearchModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public SellerSearchModel() {
    }
}
