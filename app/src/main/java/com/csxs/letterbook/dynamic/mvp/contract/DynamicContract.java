package com.csxs.letterbook.dynamic.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.NearbyUserDynamicE;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/13
 * description:动态 MVP 契约
 */
public interface DynamicContract {


    interface IDynamicView extends IBaseView {

        void nearbyUserDynamicSuccess(List<NearbyUserDynamicE> dynamics);

        void nearbyUserDynamicFailure(int code, String msg);


        void thumbsDynamicSuccess();

        void thumbsDynamicFailure(int code, String msg);

        void addOrDeleteAttentionSuccess();

        void addOrDeleteAttentionFailure(int code, String msg);
    }


    /**
     * 店铺动态 Presenter
     */
    interface IDynamicPresenter {
        void queryNearbyUserDynamic(int distance, double latitude, double longitude, int pageCurr, int pageSize);

        void thumbs(int commentId);

        void addOrDeleteAttention(int userIsMar,int conUserIsMar,int userId,int conUserId);
    }

    interface IDynamicModel extends IModel {

        Observable<Result<List<NearbyUserDynamicE>>> queryNearbyUserDynamic(int distance, double latitude, double longitude, int pageCurr, int pageSize);

        Observable<Result<String>> thumbs(int commentId);

        Observable<Result<String>> addOrDeleteAttention(int userIsMar,int conUserIsMar,int userId,int conUserId);

    }

}
