package com.csxs.letterbook.entity;

import com.csxs.viewlib.cartlayout.bean.GroupItemBean;

/**
 * @author: yeliu
 * created on 2020/5/28
 * description:
 */
public class OrderShopE extends GroupItemBean {

    public String shopName;

    public String shopId;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
