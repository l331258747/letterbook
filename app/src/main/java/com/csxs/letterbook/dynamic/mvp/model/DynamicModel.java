package com.csxs.letterbook.dynamic.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.dynamic.mvp.contract.DynamicContract;
import com.csxs.letterbook.entity.NearbyUserDynamicE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/13
 * description:
 */
public class DynamicModel extends BaseModel implements DynamicContract.IDynamicModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public DynamicModel() {
    }

    @Override
    public Observable<Result<List<NearbyUserDynamicE>>> queryNearbyUserDynamic(int distance, double latitude, double longitude, int pageCurr, int pageSize) {
        Map<String,Object> param=new HashMap<>(5);
        param.put("distance",distance);
        param.put("latitude",latitude);
        param.put("longitude",longitude);
        param.put("pageCurr",pageCurr);
        param.put("pageSize",pageSize);
        return apiService.queryNearbyComm(param);
    }

    @Override
    public Observable<Result<String>> thumbs(int commentId) {
        return apiService.thumbs(commentId);
    }

    @Override
    public Observable<Result<String>> addOrDeleteAttention(int userIsMar,int conUserIsMar,int userId,int conUserId) {

        return apiService.addDelConcerns(userIsMar,conUserIsMar,userId,conUserId);
    }
}
