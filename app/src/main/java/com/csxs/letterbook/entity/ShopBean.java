package com.csxs.letterbook.entity;


import com.csxs.viewlib.cartlayout.bean.GroupItemBean;

public class ShopBean extends GroupItemBean {

    String shop_name;

    String shopId;



    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
