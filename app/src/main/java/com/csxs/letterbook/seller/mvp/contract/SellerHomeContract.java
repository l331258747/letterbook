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
 * created on 2020/4/18
 * description:
 */
public interface SellerHomeContract {

    interface ISellerHomeView extends IBaseView {

        void attentionUserSuccess();
        void cancleAttentionUserSuccess();
        void attentionUserFailure(int code, String msg);
        void cancleAttentionUserFailure(int code, String msg);

        void sellerDetailsInfoSuccess(MapSellerDetailsInfoE mapSellerDetailsInfo);

        void sellerDetailsInfoFailure(int code, String msg);
    }

    interface ISellerHomePresenter {
        void attentionUser(int marchantId);
        void cancleAttentionUser(int marchantId);

        void querySingleSellerInfo(int shopId,double latitude ,double longitude);
    }

    interface ISellerHomeModel extends IModel {



        Observable<Result<String>> attentionUser(int marchantId);
        Observable<Result<String>> cancleAttentionUser(int marchantId);

        Observable<Result<MapSellerDetailsInfoE>> querySellerInfo(int shopId, double latitude , double longitude);

    }
}
