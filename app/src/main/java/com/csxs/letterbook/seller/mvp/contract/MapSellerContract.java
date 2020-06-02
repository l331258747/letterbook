package com.csxs.letterbook.seller.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.MapSellerStoreE;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public interface MapSellerContract {

    interface IMapsellerView extends IBaseView {

        void mapSellerInfoSuccess(List<MapSellerStoreE> mapSellerStoreE);

        void mapSellerDetailsInfoSuccess(MapSellerDetailsInfoE mapSellerDetailsInfo);

        void mapSellerDetailsInfoFailure(int code, String msg);

    }

    interface IMapSellerPresenter {
        void getMapSellerInfo(String marchantTypeIds, double distance,String discount,double latitude,double longitude,int pageCurr,int pageSize);
        void querySingleSellerInfo(int shopId,double latitude ,double longitude);
    }


    interface IMapsellerModel extends IModel {
        Observable<Result<List<MapSellerStoreE>>> getMapSellerInfo(String marchantTypeIds, double distance, String discount, double latitude, double longitude, int pageCurr, int pageSize);
        Observable<Result<MapSellerDetailsInfoE>> queryMarchantInfoList(int shopId, double latitude , double longitude);
    }


}
