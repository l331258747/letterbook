package com.csxs.letterbook.seller.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.SellerGoodsE;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商铺订单model
 */
public class SellerStoreOrderModel extends BaseModel implements SellerStoreContract.ISellerStoreOrderModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public SellerStoreOrderModel() {
    }

    @Override
    public Observable<Result<List<SellerGoodsE>>> queryCommodity(int marchantId) {
        return apiService.queryCommodity(marchantId);
    }
}
