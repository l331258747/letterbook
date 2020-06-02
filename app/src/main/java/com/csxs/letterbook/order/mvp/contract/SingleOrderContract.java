package com.csxs.letterbook.order.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.MapSocialUserInfoE;
import com.csxs.letterbook.entity.SingleOrderE;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/5/23
 * description:
 */
public interface SingleOrderContract {

    interface ISingleOrderView extends IBaseView {

        void singleOrderInfoSuccess(SingleOrderE singleOrderE);
        void singleOrderInfoFailure(int code, String msg);
    }

    interface ISingleOrderPresenter {


       // void querySingleUserInfo(int userId);

        void  querySingleOrderInfo(String order);
    }

    interface ISingleOrderModel extends IModel {

       // Observable<Result<MapSocialUserInfoE>> querySingleUserInfo(int userId);

        Observable<Result<SingleOrderE>> querySingleOrderInfo(String order);

    }





}
