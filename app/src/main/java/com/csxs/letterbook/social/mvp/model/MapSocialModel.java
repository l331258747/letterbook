package com.csxs.letterbook.social.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.MapSocialInfoE;
import com.csxs.letterbook.entity.MapSocialUserInfoE;
import com.csxs.letterbook.social.mvp.contract.MapSocialContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public class MapSocialModel extends BaseModel implements MapSocialContract.IMapSocialModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public MapSocialModel() {
    }

    @Override
    public Observable<Result<List<MapSocialInfoE>>> querySocialList(double distance, double latitude, double longitude, int pageCurr, int pageSize) {
        return apiService.querySocialList(distance,latitude,longitude,pageCurr,pageSize);
    }

    @Override
    public Observable<Result<MapSocialUserInfoE>> querySingleUserInfo(int userId) {
        return apiService.queryUserInfo(userId);
    }
}
