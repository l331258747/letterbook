package com.csxs.letterbook.entity;

/**
 * @author: yeliu
 * created on 2020/5/19
 * description:
 */
public class MapSocialInfoE {

    /**
     * birthDay : 2020-05-11T06:50:33.000+0000
     * distance : 0.460
     * sex : 1
     * nickname : test
     * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/pWGeFALOY3JicmdOTiaq2euXcnlFPmzQ1t9suVAOtSKzaQznp5rMOZLCfwRA0A06ekuia8IYKrEKkhQEMac1xibOGQ/132
     * id : 29887
     */

    private String birthDay;
    private String distance;
    private String sex;
    private String nickname;
    private String headimgurl;
    private int id;
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
