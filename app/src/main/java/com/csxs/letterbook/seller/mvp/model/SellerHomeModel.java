package com.csxs.letterbook.seller.mvp.model;

import android.util.Log;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.MapSellerStoreE;
import com.csxs.letterbook.entity.QSubmitSellerE;
import com.csxs.letterbook.seller.mvp.contract.SellerHomeContract;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:
 */
public class SellerHomeModel extends BaseModel implements SellerHomeContract.ISellerHomeModel {

    @Inject
    LetterApiService apiService;

    @Inject
    Gson gson;

    @Inject
    public SellerHomeModel() {
    }

    @Override
    public Observable<Result<String>> attentionUser(int marchantId) {
        return apiService.addMarchantConcerns(marchantId);
    }

    @Override
    public Observable<Result<String>> cancleAttentionUser(int marchantId) {
        return apiService.delMarchantConcerns(marchantId);
    }




    @Override
    public Observable<Result<MapSellerDetailsInfoE>> querySellerInfo(int shopId, double latitude, double longitude) {
        return apiService.queryMarchantInfoList(shopId,latitude,longitude);

    }

}
