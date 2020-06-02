package com.csxs.letterbook.entity;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/19
 * description:
 */
public class MapSocialUserInfoE {


    /**
     * birthDay : 2020-05-19T02:10:15.000+0000
     * address : 湖南长沙
     * con : 0
     * addTime : 2020-05-13T06:07:13.000+0000
     * signature : sdsdsd
     * sex : 1
     * accessNumber : 6
     * albumUuid : e785ede3f97745cf990a87101dfd8fdd
     * emotionId : 1
     * nickname : sss
     * headimgurl : http://192.168.0.122/group1/M00/00/4C/wKgAjF7DV7SAM0L7AABWw4LMQWc006.jpg
     * id : 29920
     */

    private String birthDay;
    private String address;
    private int con;
    private String addTime;
    private String signature;
    private String sex;
    private int accessNumber;
    private String albumUuid;
    private int emotionId;
    private String nickname;
    private String headimgurl;
    private int id;



    private List<UserPhotoE> albumlist;

    private List<UserLabelE> label;


    public List<UserPhotoE> getAlbumlist() {
        return albumlist;
    }

    public void setAlbumlist(List<UserPhotoE> albumlist) {
        this.albumlist = albumlist;
    }

    public List<UserLabelE> getLabel() {
        return label;
    }

    public void setLabel(List<UserLabelE> label) {
        this.label = label;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(int accessNumber) {
        this.accessNumber = accessNumber;
    }

    public String getAlbumUuid() {
        return albumUuid;
    }

    public void setAlbumUuid(String albumUuid) {
        this.albumUuid = albumUuid;
    }

    public int getEmotionId() {
        return emotionId;
    }

    public void setEmotionId(int emotionId) {
        this.emotionId = emotionId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
