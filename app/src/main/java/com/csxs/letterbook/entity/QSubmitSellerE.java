package com.csxs.letterbook.entity;

/**
 * @author: yeliu
 * created on 2020/5/8
 * description:去查询地图商家信息 提交实体类
 */
public class QSubmitSellerE {
    private String marchantTypeIds;
    private double distance;
    private String discount;
    private double latitude;
    private double longitude;
    private int pageCurr;
    private int pageSize;

    public QSubmitSellerE(String marchantTypeIds, double distance, String discount, double latitude, double longitude, int pageCurr, int pageSize) {
        this.marchantTypeIds = marchantTypeIds;
        this.distance = distance;
        this.discount = discount;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pageCurr = pageCurr;
        this.pageSize = pageSize;
    }

    public String getMarchantTypeIds() {
        return marchantTypeIds;
    }

    public void setMarchantTypeIds(String marchantTypeIds) {
        this.marchantTypeIds = marchantTypeIds;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

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

    public int getPageCurr() {
        return pageCurr;
    }

    public void setPageCurr(int pageCurr) {
        this.pageCurr = pageCurr;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
