package com.csxs.letterbook.seller.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.RetryWithDelay;
import com.csxs.core.net.exception.HttpResponseFunc;
import com.csxs.core.net.result.Result;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.core.net.result.ResultObserver;
import com.csxs.letterbook.LetterApp;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.MapSellerStoreE;
import com.csxs.letterbook.entity.WxLoginE;
import com.csxs.letterbook.seller.mvp.contract.MapSellerContract;
import com.csxs.letterbook.seller.mvp.model.MapSellerModel;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import android.util.Base64;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.uber.autodispose.AutoDispose.autoDisposable;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:主页地图商家P
 */
public class MapSellerPresenter extends BasePresenter<MapSellerModel, MapSellerContract.IMapsellerView> implements MapSellerContract.IMapSellerPresenter {

    private Base64 decoder;

    @Inject
    public MapSellerPresenter() {

    }


    /**
     * 查询商家地图的 商家
     *
     * @param marchantTypeIds
     * @param distance
     * @param discount
     * @param latitude
     * @param longitude
     * @param pageCurr
     * @param pageSize
     */

    @Override
    public void getMapSellerInfo(String marchantTypeIds, double distance, String discount, double latitude, double longitude, int pageCurr, int pageSize) {

        Observable<Result<List<MapSellerStoreE>>> observable = mModel.getMapSellerInfo(marchantTypeIds, distance, discount, latitude, longitude, pageCurr, pageSize);
        observable.flatMap(new Function<Result<List<MapSellerStoreE>>, ObservableSource<List<MapSellerStoreE>>>() {
            @Override
            public ObservableSource<List<MapSellerStoreE>> apply(Result<List<MapSellerStoreE>> mapSellerStoreEResult) throws Exception {
                if (mapSellerStoreEResult != null && mapSellerStoreEResult.getResults() != null) {
                    return Observable.just(mapSellerStoreEResult.getResults());
                }
                return Observable.just(new ArrayList<>());
            }
        }).onErrorResumeNext(new HttpResponseFunc<List<MapSellerStoreE>>()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(AndroidLifecycleScopeProvider.from(getView().getOwnerLifeCycle())))
                .subscribe(new ResultObserver<List<MapSellerStoreE>>() {
                    @Override
                    public void handlerResult(List<MapSellerStoreE> mapSellerStoreE) {
                        getView().mapSellerInfoSuccess(mapSellerStoreE);
                    }

                    @Override
                    public void handlerError(int code, String msg) {
                        getView().showError(msg);
                    }
                });

    }

    /**
     * 获取单个商铺详情
     * @param shopId
     * @param latitude
     * @param longitude
     */
    @Override
    public void querySingleSellerInfo(int shopId, double latitude, double longitude) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.queryMarchantInfoList(shopId, latitude, longitude), new ResultErrorObserver<MapSellerDetailsInfoE>() {
            @Override
            public void handlerResult(MapSellerDetailsInfoE mapSellerDetailsInfoE) {
                   getView().mapSellerDetailsInfoSuccess(mapSellerDetailsInfoE);
            }

            @Override
            public void handlerError(int code, String msg) {
//                getView().showError(msg);
                getView().mapSellerDetailsInfoFailure(code,msg);
            }
        });
    }


}
