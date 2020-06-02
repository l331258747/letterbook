package com.csxs.letterbook.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: yeliu
 * created on 2020/5/8
 * description:
 */
public class MapSellerDetailsInfoE{


    /**
     * brief : 点点滴滴
     * address : 长沙市
     * distance : 0
     * nickName : 零食小家
     * latitude : 28.24042
     * openingTime : 10:00
     * businessName : 美食
     * contactsMobile : 18745624785
     * fans : 1
     * closingTime : 21:30
     * accessNumber : 118
     * personalDetails : 个人信息
     * thepointSinList : http://192.168.0.122/group2/M00/00/00/wKgAjF6zabyAJ8SxAAF9Jht7eLc843.jpg,http://192.168.0.122/group2/M00/00/00/wKgAjF6zabyAGbG3AABQd-59y4U276.jpg,http://192.168.0.122/group2/M00/00/00/wKgAjF6zab2ADRrGAAA5MmUHePo349.jpg,http://192.168.0.122/group2/M00/00/00/wKgAjF6zab2AEnd-AAC45DvVQ3U538.jpg,http://192.168.0.122/group2/M00/00/00/wKgAjF6zab2AZv_xAACcs8ywZhM228.jpg
     * id : 14
     * longitude : 112.9882
     */

    private String brief;
    private String address;
    private double distance;
    private String nickName;
    private double latitude;
    private String openingTime;
    private String businessName;
    private String contactsMobile;
    private int fans;
    private String closingTime;
    private int accessNumber;
    private String personalDetails;
    private String thepointSinList;
    private int id;
    private double longitude;
    private String logoPic;
    private List<MapSellerPictureE> thepointSins;
    private int isfans;


    public int getIsfans() {
        return isfans;
    }

    public void setIsfans(int isfans) {
        this.isfans = isfans;
    }

    public List<MapSellerPictureE> getThepointSins() {
        if(thepointSins == null){
            return new ArrayList<>();
        }

        return thepointSins;
    }

    public void setThepointSins(List<MapSellerPictureE> thepointSins) {
        this.thepointSins = thepointSins;
    }

    public String getLogoPic() {
        return logoPic;
    }

    public void setLogoPic(String logoPic) {
        this.logoPic = logoPic;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public int getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(int accessNumber) {
        this.accessNumber = accessNumber;
    }

    public String getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(String personalDetails) {
        this.personalDetails = personalDetails;
    }

    public String getThepointSinList() {
        return thepointSinList;
    }

    public void setThepointSinList(String thepointSinList) {
        this.thepointSinList = thepointSinList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
