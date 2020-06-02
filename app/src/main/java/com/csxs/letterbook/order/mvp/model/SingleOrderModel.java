package com.csxs.letterbook.order.mvp.model;

import android.util.Log;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.QSubmitSellerE;
import com.csxs.letterbook.entity.SingleOrderE;
import com.csxs.letterbook.order.mvp.contract.SingleOrderContract;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/5/28
 * description:
 */
public class SingleOrderModel extends BaseModel implements SingleOrderContract.ISingleOrderModel{

    @Inject
    LetterApiService apiService;

    @Inject
    Gson gson;

    @Inject
    public SingleOrderModel() {
    }
    @Override
    public Observable<Result<SingleOrderE>> querySingleOrderInfo(String order) {

       // String json= gson.toJson(qSubmitSeller);
        //Log.e("getMapSellerInfo",json);
        return apiService.onekeyAboutOrder(toRequestBody(order));
    }
}
