package com.csxs.letterbook.dynamic.mvp;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.dynamic.mvp.contract.DynamicContract;
import com.csxs.letterbook.dynamic.mvp.model.DynamicModel;
import com.csxs.letterbook.entity.NearbyUserDynamicE;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/13
 * description:
 */
public class DynamicPresenter extends BasePresenter<DynamicModel, DynamicContract.IDynamicView> implements DynamicContract.IDynamicPresenter {

    @Inject
    public DynamicPresenter() {
    }


    @Override
    public void queryNearbyUserDynamic(int distance, double latitude, double longitude, int pageCurr, int pageSize) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.queryNearbyUserDynamic(distance, latitude, longitude, pageCurr, pageSize), new ResultErrorObserver<List<NearbyUserDynamicE>>() {
            @Override
            public void handlerResult(List<NearbyUserDynamicE> list) {
                getView().nearbyUserDynamicSuccess(list);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().nearbyUserDynamicFailure(code, msg);

            }
        });
    }

    @Override
    public void thumbs(int commentId) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.thumbs(commentId), new ResultErrorObserver<String>() {
            @Override
            public void handlerResult(String list) {
                getView().thumbsDynamicSuccess();
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().thumbsDynamicFailure(code, msg);

            }
        });
    }

    @Override
    public void addOrDeleteAttention( int userIsMar,int conUserIsMar,int userId,int conUserId) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.addOrDeleteAttention(userIsMar,conUserIsMar,userId,conUserId), new ResultErrorObserver<String>() {
            @Override
            public void handlerResult(String list) {
                getView().addOrDeleteAttentionSuccess();
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().addOrDeleteAttentionFailure(code, msg);

            }
        });
    }
}
