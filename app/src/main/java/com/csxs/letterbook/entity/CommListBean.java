package com.csxs.letterbook.entity;

/**
 * @author: yeliu
 * created on 2020/5/28
 * description:
 */
public class CommListBean {

    private int amount;
    private double originalPrice;
    private String marchantName;
    private double subtotal;
    private String commodityName;
    private String itemText;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getMarchantName() {
        return marchantName;
    }

    public void setMarchantName(String marchantName) {
        this.marchantName = marchantName;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }
}
