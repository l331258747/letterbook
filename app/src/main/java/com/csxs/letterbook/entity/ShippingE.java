package com.csxs.letterbook.entity;

public class ShippingE {
    /**
     * id : null
     * userId : null
     * address : null
     * contacts : cathy
     * contactWay : 224343
     * addTime : 2020-03-09T14:10:19.745+0000
     * provincesName : 河北省
     * cityName : 石家庄市
     * areaName : 长安区
     * isDefault : null
     * shippingId : 14
     */

    private long id;
    private Object userId;
    private String address;
    private String contacts;
    private String contactWay;
    private String addTime;
    private String provincesName;
    private String cityName;
    private String areaName;
    private Object isDefault;
    private long shippingId;
    private String addressDetais;

    public String getAddressDetais() {
        return addressDetais;
    }

    public void setAddressDetais(String addressDetais) {
        this.addressDetais = addressDetais;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getProvincesName() {
        return provincesName;
    }

    public void setProvincesName(String provincesName) {
        this.provincesName = provincesName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Object getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Object isDefault) {
        this.isDefault = isDefault;
    }

    public long getShippingId() {
        return shippingId;
    }

    public void setShippingId(long shippingId) {
        this.shippingId = shippingId;
    }
}
