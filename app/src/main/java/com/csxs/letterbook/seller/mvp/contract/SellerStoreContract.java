package com.csxs.letterbook.seller.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.MarchantCommentE;
import com.csxs.letterbook.entity.MarchantCommentReplyE;
import com.csxs.letterbook.entity.SellerGoodsE;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:店铺契约
 */
public interface SellerStoreContract {

    /**
     * 店铺信息view
     */
    interface ISellerStoreInfoView extends IBaseView {

    }

    /**
     * 店铺信息Model
     */
    interface ISellerStoreInfoModel extends IModel {

    }


    /**
     * 店铺动态view
     */
    interface ISellerStoreDynamicView extends IBaseView {

        void sellerHomeDynamicSuccess(List<MarchantCommentE> mapSellerDetailsInfo);

        void sellerHomeDynamicFailure(int code, String msg);

    }

    /**
     * 店铺动态 Presenter
     */
    interface ISellerStoreDynamicPresenter {
        void querySellerHomeDynamic(int id, double latitude, double longitude);
    }

    /**
     * 店铺动态Model
     */
    interface ISellerStoreDynamicModel extends IModel {
        Observable<Result<List<MarchantCommentE>>> queryMarchantComment(int id, double latitude, double longitude);
    }

    /**
     * 商铺动态评论
     */
    interface ISellerStoreDynamicCommentView extends IBaseView {
        void sellerHomeDynamicDetailsSuccess(MarchantCommentReplyE marchantCommentReply);

        void sellerHomeDynamicDetailsFailure(int code, String msg);

        void sendDynamicSuccess(String s);

        void sendDynamicFailure(int code,String msg);

        void thumbsDynamicSuccess();

        void thumbsDynamicFailure(int code, String msg);
    }

    /**
     * 店铺动态评论 Presenter
     */
    interface ISellerStoreDynamicCommentPresenter {
        //  void querySellerHomeDynamic(int id,double latitude ,double longitude);
        void querySellerHomeDynamicDetails(int commentId, double latitude, double longitude);

        void sendDynamicComment(int commentId, String content,int isMarchant, int parentId);

        void thumbs(int commentId);
    }

    /**
     * 店铺动态评论 Model
     */
    interface ISellerStoreDynamicCommentModel extends IModel {
        Observable<Result<MarchantCommentReplyE>> queryMarchantCommentReply(int commentId, double latitude, double longitude);

        Observable<Result<String>> sendDynamicComment(int commentId, String content,int isMarchant, int parentId);

        Observable<Result<String>> thumbs(int commentId);
    }


    /**
     * 店铺活动view
     */
    interface ISellerStorePromotionView extends IBaseView {

    }

    /**
     * 店铺活动Model
     */
    interface ISellerStorePromotionModel extends IModel {

    }

    /**
     * 店铺订单view
     */
    interface ISellerStoreOrderView extends IBaseView {
        void queryCommoditySuccess(List<SellerGoodsE> list);

        void queryCommodityFailure(int code,String msg);
    }

    /**
     * 店铺订单 Presenter
     */
    interface ISellerStoreOrderPresenter {
        void queryCommodity(int marchantId);
    }

    /**
     * 店铺订单Model
     */
    interface ISellerStoreOrderModel extends IModel {
        Observable<Result<List<SellerGoodsE>>> queryCommodity(int marchantId);
    }

}
