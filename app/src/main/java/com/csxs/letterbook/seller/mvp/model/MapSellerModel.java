package com.csxs.letterbook.seller.mvp.model;

import android.util.Log;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.MapSellerStoreE;
import com.csxs.letterbook.entity.QSubmitSellerE;
import com.csxs.letterbook.seller.mvp.contract.MapSellerContract;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public class MapSellerModel extends BaseModel implements MapSellerContract.IMapsellerModel {

    @Inject
    LetterApiService apiService;

    @Inject
    Gson gson;

    @Inject
    public MapSellerModel() {
    }


    @Override
    public Observable<Result<List<MapSellerStoreE>>> getMapSellerInfo(String marchantTypeIds, double distance, String discount, double latitude, double longitude, int pageCurr, int pageSize) {
        QSubmitSellerE qSubmitSeller=new QSubmitSellerE(marchantTypeIds,distance,discount,latitude,longitude,1,100);
        String json= gson.toJson(qSubmitSeller);
        Log.e("getMapSellerInfo",json);
        return apiService.queryMarchantList(toRequestBody(json));
    }



    @Override
    public Observable<Result<MapSellerDetailsInfoE>> queryMarchantInfoList(int shopId, double latitude, double longitude) {
        return apiService.queryMarchantInfoList(shopId,latitude,longitude);

    }
}
