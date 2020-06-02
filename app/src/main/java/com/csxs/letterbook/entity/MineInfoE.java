package com.csxs.letterbook.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/19
 * description:
 */
public class MineInfoE {


    /**
     * birthDay : 2020-05-19T02:10:15.000+0000
     * emotion : 单身
     * address : 湖南长沙
     * signature : sdsdsd
     * albumUuid : e785ede3f97745cf990a87101dfd8fdd
     * sex : 1
     * headimgurl : http://192.168.0.122/group1/M00/00/4C/wKgAjF7DV7SAM0L7AABWw4LMQWc006.jpg
     * nickname : sss
     * id : 29920
     */

    private String birthDay;
    private String emotion;
    private String address;
    private String signature;
    private String albumUuid;
    private String sex;
    private String headimgurl;
    private String nickname;
    private int id;
    private int cityid;
    private int provinceid;
    private int areaid;

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public int getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(int provinceid) {
        this.provinceid = provinceid;
    }

    public int getAreaid() {
        return areaid;
    }

    public void setAreaid(int areaid) {
        this.areaid = areaid;
    }

    private List<MineInfoPhotoE> albumList;

    private List<MineInfoLabelE> labelList;

    public List<MineInfoLabelE> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<MineInfoLabelE> labelList) {
        this.labelList = labelList;
    }

    public List<MineInfoPhotoE> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<MineInfoPhotoE> albumList) {
        this.albumList = albumList;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAlbumUuid() {
        return albumUuid;
    }

    public void setAlbumUuid(String albumUuid) {
        this.albumUuid = albumUuid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
