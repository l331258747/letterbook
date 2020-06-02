package com.csxs.letterbook.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/12
 * description:商铺动态 评论详情实体类
 */
public class MarchantCommentReplyE{





    private List<CommentReplyE> commentReply;

    private MarchantCommentE comment;


    public List<CommentReplyE> getCommentReply() {
        return commentReply;
    }

    public void setCommentReply(List<CommentReplyE> commentReply) {
        this.commentReply = commentReply;
    }

    public MarchantCommentE getComment() {
        return comment;
    }

    public void setComment(MarchantCommentE comment) {
        this.comment = comment;
    }


}
