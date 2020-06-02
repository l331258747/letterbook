package com.csxs.letterbook.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/12
 * description:
 */
public class CommentReplyE implements MultiItemEntity {


    public static final int SELLER_DYNAMIC_COMMENT_TOP = 1;
    public static final int SELLER_DYNAMIC_COMMENT_NORMAL = 0;
    public static final int SELLER_DYNAMIC_COMMENT_EMPTY = 2;
    public int itemType;

    private int id;
    private int commentId;
    private int userId;
    private String content;
    private int praise;
    private int isMarchant;
    private int parentId;
    private DynamicUserinfoE userInfo;
    private List<ReplytoreplyE> replytoreply;

    private MarchantCommentE comment;

    private int isMarchantCommentE=0;

    public int getIsMarchantCommentE() {
        return isMarchantCommentE;
    }

    public void setIsMarchantCommentE(int isMarchantCommentE) {
        this.isMarchantCommentE = isMarchantCommentE;
    }

    public MarchantCommentE getComment() {
        return comment;
    }

    public void setComment(MarchantCommentE comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getIsMarchant() {
        return isMarchant;
    }

    public void setIsMarchant(int isMarchant) {
        this.isMarchant = isMarchant;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public DynamicUserinfoE getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(DynamicUserinfoE userInfo) {
        this.userInfo = userInfo;
    }

    public List<ReplytoreplyE> getReplytoreply() {
        return replytoreply;
    }

    public void setReplytoreply(List<ReplytoreplyE> replytoreply) {
        this.replytoreply = replytoreply;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }



}
