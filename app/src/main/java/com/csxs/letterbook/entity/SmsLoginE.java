package com.csxs.letterbook.entity;

/**
 * @author: yeliu
 * created on 2020/4/20
 * description:
 */
public class SmsLoginE {


    /**
     * sex : null
     * autonym : 0
     * binding : 0
     * userId : 37
     * token : 6cc98d37653d76252cf9d34babd6dea1
     * phoneNumber : 17726102825
     * audit :
     * identity : null
     * isEdit : 0
     * inviteCode : null
     * nickname : null
     * openId1 : null
     * headimgurl : null
     */

    private int sex;
    private String autonym;
    private int binding;
    private int userId;
    private String token;
    private String phoneNumber;
    private String audit;
    private String identity;
    private int isEdit;
    private String inviteCode;
    private String nickname;
    private String openId1;
    private String headimgurl;
    private int infoisok;

    public int getInfoisok() {
        return infoisok;
    }

    public void setInfoisok(int infoisok) {
        this.infoisok = infoisok;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAutonym() {
        return autonym;
    }

    public void setAutonym(String autonym) {
        this.autonym = autonym;
    }

    public int getBinding() {
        return binding;
    }

    public void setBinding(int binding) {
        this.binding = binding;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(int isEdit) {
        this.isEdit = isEdit;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenId1() {
        return openId1;
    }

    public void setOpenId1(String openId1) {
        this.openId1 = openId1;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}
