package com.csxs.letterbook.entity;

import com.csxs.viewlib.cartlayout.bean.ChildItemBean;

public class GoodsTotalBean extends ChildItemBean {
    private int goodsCount=1;
    private double goodsTotalPrice;

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public double getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(double goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }
}
