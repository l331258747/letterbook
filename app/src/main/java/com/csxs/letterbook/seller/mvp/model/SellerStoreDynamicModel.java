package com.csxs.letterbook.seller.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.MarchantCommentE;
import com.csxs.letterbook.seller.mvp.contract.SellerSearchContract;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商铺动态model
 */
public class SellerStoreDynamicModel extends BaseModel implements SellerStoreContract.ISellerStoreDynamicModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public SellerStoreDynamicModel() {
    }



    @Override
    public Observable<Result<List<MarchantCommentE>>> queryMarchantComment(int id, double latitude, double longitude) {
           return apiService.queryMarchantComment(id,latitude,longitude,1,20);
    }
}
