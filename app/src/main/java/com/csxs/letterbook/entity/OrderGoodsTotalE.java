package com.csxs.letterbook.entity;

import com.csxs.viewlib.cartlayout.bean.CartItemBean;

public class OrderGoodsTotalE extends CartItemBean {
    private int goods_count=1;
    private double goods_total_price;

    public int getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(int goods_count) {
        this.goods_count = goods_count;
    }

    public double getGoods_total_price() {
        return goods_total_price;
    }

    public void setGoods_total_price(double goods_total_price) {
        this.goods_total_price = goods_total_price;
    }
}
