package com.csxs.letterbook.entity;

import android.net.Uri;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/22
 * description:附近人的动态
 */
public class NearbyUserDynamicE implements MultiItemEntity {

    public static final int NEARBY_DYNAMIC_TEXT = 1;
    public static final int NEARBY_DYNAMIC_IMAGE = 2;

    public int itemType;
    /**
     * address : 天心区
     * con : 1
     * addTime : 2020-05-11T07:51:06.000+0000
     * distance : 0.098
     * latitude : 28.24279
     * userId : 15
     * content : 我又又又发布了一个动态
     * isMarchant : 1
     * id : 4
     * marchantId : 15
     * imgUuid : c4ae033c1a9447f7803b5845c4efeb8c
     * userCommId : null
     * longitude : 112.98804
     */

    private String address;
    private int con;
    private String addTime;
    private String distance;
    private double latitude;
    private int userId;
    private String content;
    private int isMarchant;
    private int id;
    private int marchantId;
    private String imgUuid;
    private int userCommId;
    private double longitude;
    private int iscon;
    private int praiseCount;
    private int replyCount;
    private int status;
    private int age;
    private int isPraise;

    public int getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(int isPraise) {
        this.isPraise = isPraise;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIscon() {
        return iscon;
    }

    public void setIscon(int iscon) {
        this.iscon = iscon;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private DynamicUserinfoE userInfo;
    private List<DynamicPictureE> imgList;
    private DynamicRichText dynamicRichText;
    private String distanceSpace;
    private ArrayList<Uri> imageUrl;


    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public DynamicUserinfoE getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(DynamicUserinfoE userInfo) {
        this.userInfo = userInfo;
    }

    public List<DynamicPictureE> getImgList() {
        return imgList;
    }

    public void setImgList(List<DynamicPictureE> imgList) {
        this.imgList = imgList;
    }

    public DynamicRichText getDynamicRichText() {
        return dynamicRichText;
    }

    public void setDynamicRichText(DynamicRichText dynamicRichText) {
        this.dynamicRichText = dynamicRichText;
    }

    public String getDistanceSpace() {
        return distanceSpace;
    }

    public void setDistanceSpace(String distanceSpace) {
        this.distanceSpace = distanceSpace;
    }

    public ArrayList<Uri> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ArrayList<Uri> imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int getItemType() {
        return itemType;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    public int getIsMarchant() {
        return isMarchant;
    }

    public void setIsMarchant(int isMarchant) {
        this.isMarchant = isMarchant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMarchantId() {
        return marchantId;
    }

    public void setMarchantId(int marchantId) {
        this.marchantId = marchantId;
    }

    public String getImgUuid() {
        return imgUuid;
    }

    public void setImgUuid(String imgUuid) {
        this.imgUuid = imgUuid;
    }

    public int getUserCommId() {
        return userCommId;
    }

    public void setUserCommId(int userCommId) {
        this.userCommId = userCommId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
