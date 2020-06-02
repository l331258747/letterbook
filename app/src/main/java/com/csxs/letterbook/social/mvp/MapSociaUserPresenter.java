package com.csxs.letterbook.social.mvp;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.MapSocialInfoE;
import com.csxs.letterbook.entity.MapSocialUserInfoE;
import com.csxs.letterbook.social.mvp.contract.MapSocialContract;
import com.csxs.letterbook.social.mvp.model.MapSocialModel;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public class MapSociaUserPresenter extends BasePresenter<MapSocialModel, MapSocialContract.IMapSocialView> implements MapSocialContract.IMapSocialPresenter{

    @Inject
    public MapSociaUserPresenter() {
    }

    @Override
    public void getMapSocialInfo(double distance, double latitude, double longitude, int pageCurr, int pageSize) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.querySocialList(distance, latitude, longitude,pageCurr,pageSize), new ResultErrorObserver<List<MapSocialInfoE>>() {
            @Override
            public void handlerResult(List<MapSocialInfoE> mapSocialInfoE) {
                getView().mapSocialInfoSuccess(mapSocialInfoE);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().mapSocialInfoFailure(code,msg);
            }
        });
    }

    @Override
    public void querySingleUserInfo(int userId) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.querySingleUserInfo(userId), new ResultErrorObserver<MapSocialUserInfoE>() {
            @Override
            public void handlerResult(MapSocialUserInfoE mapSocialInfoE) {
                getView().mapSocialSingleUserInfoSuccess(mapSocialInfoE);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().mapSocialSingleUserInfoFailure(code,msg);
            }
        });
    }
}
