package com.csxs.letterbook.entity;

/**
 * @author: yeliu
 * created on 2020/4/20
 * description:
 */
public class SellerBasicInfoE {

    private String openingTime;
    private String closingTime;
    private String address;
    private String contactsMobile;

    public SellerBasicInfoE(String openingTime, String closingTime, String address, String contactsMobile) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.address = address;
        this.contactsMobile = contactsMobile;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }
}
