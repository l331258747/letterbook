package com.csxs.letterbook.seller.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.MarchantCommentE;
import com.csxs.letterbook.entity.MarchantCommentReplyE;
import com.csxs.letterbook.seller.mvp.contract.SellerHomeContract;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;
import com.csxs.letterbook.seller.mvp.model.SellerDynamicCommentModel;
import com.csxs.letterbook.seller.mvp.model.SellerHomeModel;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/5/12
 * description:商家动态 评论
 */
public class SellerDynamicCommentPresenter extends BasePresenter<SellerDynamicCommentModel, SellerStoreContract.ISellerStoreDynamicCommentView> implements SellerStoreContract.ISellerStoreDynamicCommentPresenter {
    @Inject
    public SellerDynamicCommentPresenter() {
    }

    @Override
    public void querySellerHomeDynamicDetails(int commentId,double latitude,double longitude) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.queryMarchantCommentReply(commentId,latitude,longitude), new ResultErrorObserver<MarchantCommentReplyE>() {
            @Override
            public void handlerResult(MarchantCommentReplyE marchantCommentReply) {
                getView().sellerHomeDynamicDetailsSuccess(marchantCommentReply);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().sellerHomeDynamicDetailsFailure(code,msg);

            }
        });
    }

    @Override
    public void sendDynamicComment(int commentId, String content, int isMarchant, int parentId) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.sendDynamicComment(commentId,content,isMarchant,parentId), new ResultErrorObserver<String>() {
            @Override
            public void handlerResult(String s) {
                getView().sendDynamicSuccess(s);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().sendDynamicFailure(code,msg);

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


}
