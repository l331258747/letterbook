package com.csxs.letterbook.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/23
 * description:暂时没有用到 动态详情 直接跳转 SellerDynamicCommentActivity
 */
public class NearbyDynamicDetialsE implements MultiItemEntity {

    public static final int NEARBY_DYNAMIC_DETAILS_TEXT = 1;
    public static final int NEARBY_DYNAMIC_DETAILS_IMAGE = 2;

    public int itemType;

    private List<CommentReplyE> commentReply;

    private NearbyDynamicCommentE comment;


    public List<CommentReplyE> getCommentReply() {
        return commentReply;
    }

    public void setCommentReply(List<CommentReplyE> commentReply) {
        this.commentReply = commentReply;
    }

    public NearbyDynamicCommentE getComment() {
        return comment;
    }

    public void setComment(NearbyDynamicCommentE comment) {
        this.comment = comment;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
