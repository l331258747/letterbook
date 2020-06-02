package com.csxs.letterbook.entity;

/**
 * @author: yeliu
 * created on 2020/4/17
 * description:搜索商铺 热门店铺推荐
 */
public class SearchHotE {
    private String storeName;
    private String storeAddress;
    private int icon;


    public SearchHotE(String storeName, String storeAddress, int icon) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.icon = icon;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
