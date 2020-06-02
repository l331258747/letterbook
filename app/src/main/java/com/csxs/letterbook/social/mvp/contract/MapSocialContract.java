package com.csxs.letterbook.social.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.MapSellerStoreE;
import com.csxs.letterbook.entity.MapSocialInfoE;
import com.csxs.letterbook.entity.MapSocialUserInfoE;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public interface MapSocialContract {

    interface IMapSocialView extends IBaseView {
        void mapSocialInfoSuccess(List<MapSocialInfoE> mapSellerStoreE);
        void mapSocialInfoFailure(int code, String msg);

        void mapSocialSingleUserInfoSuccess(MapSocialUserInfoE mapSocialUserInfoE);
        void mapSocialSingleUserInfoFailure(int code, String msg);
    }


    interface IMapSocialPresenter {
        void getMapSocialInfo(double distance, double latitude, double longitude, int pageCurr, int pageSize);
        void querySingleUserInfo(int userId);
    }


    interface IMapSocialModel extends IModel {
        Observable<Result<List<MapSocialInfoE>>> querySocialList(double distance, double latitude, double longitude, int pageCurr, int pageSize);
        Observable<Result<MapSocialUserInfoE>> querySingleUserInfo(int userId);
    }
}
