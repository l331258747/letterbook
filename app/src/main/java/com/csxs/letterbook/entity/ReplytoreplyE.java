package com.csxs.letterbook.entity;

/**
 * @author: yeliu
 * created on 2020/5/12
 * description:
 */
public class ReplytoreplyE {

    private int id;
    private int commentId;
    private int userId;
    private String content;
    private int praise;
    private int isMarchant;
    private int parentId;
    private DynamicUserinfoE userInfo;
    private DynamicUserinfoE parentInfo;



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

    public DynamicUserinfoE getParentInfo() {
        return parentInfo;
    }

    public void setParentInfo(DynamicUserinfoE parentInfo) {
        this.parentInfo = parentInfo;
    }
}
