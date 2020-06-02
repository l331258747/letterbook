package com.csxs.letterbook.seller.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.MarchantCommentReplyE;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/5/12
 * description:
 */
public class SellerDynamicCommentModel extends BaseModel implements SellerStoreContract.ISellerStoreDynamicCommentModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public SellerDynamicCommentModel() {
    }

    @Override
    public Observable<Result<MarchantCommentReplyE>> queryMarchantCommentReply(int commentId,double latitude,double longitude) {
        return apiService.queryMarchantCommentReply(commentId,latitude,longitude);
    }

    @Override
    public Observable<Result<String>> sendDynamicComment(int commentId, String content, int isMarchant, int parentId) {
        return apiService.queryMarchantCommentReply(commentId,content,isMarchant,parentId);
    }

    @Override
    public Observable<Result<String>> thumbs(int commentId) {
        return apiService.thumbs(commentId);
    }


}
