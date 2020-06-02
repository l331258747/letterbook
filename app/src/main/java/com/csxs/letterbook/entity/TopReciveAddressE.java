package com.csxs.letterbook.entity;


import com.csxs.viewlib.cartlayout.bean.CartItemBean;

public class TopReciveAddressE extends CartItemBean {
    int markdownNumber;
    public ShippingE address;
    public String addressTip;

    public int getMarkdownNumber() {
        return markdownNumber;
    }

    public void setMarkdownNumber(int markdownNumber) {
        this.markdownNumber = markdownNumber;
    }

    public ShippingE getAddress() {
        return address;
    }

    public void setAddress(ShippingE address) {
        this.address = address;
    }

    public String getAddressTip() {
        return addressTip;
    }

    public void setAddressTip(String addressTip) {
        this.addressTip = addressTip;
    }
}
