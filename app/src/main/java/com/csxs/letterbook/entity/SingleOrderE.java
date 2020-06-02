package com.csxs.letterbook.entity;

import com.csxs.viewlib.cartlayout.bean.GroupItemBean;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/28
 * description:
 */
public class SingleOrderE  {


    /**
     * totalSum : 184
     * subtotals : 184
     * shipping : null
     * commList : [{"amount":1,"originalPrice":87,"marchantName":"酷毙","subtotal":87,"commodityName":"固定商品","itemText":"黑色"},{"amount":1,"originalPrice":85,"marchantName":"酷毙","subtotal":85,"commodityName":"豌豆虎","itemText":"白色;m"},{"amount":1,"originalPrice":12,"marchantName":"酷毙","subtotal":12,"commodityName":"李宁","itemText":"白色"}]
     */

    private double totalSum;
    private double subtotals;
    private ShippingE shipping;
    private String marchantName;
    private int marchantId;
    private int commSum;
    private List<CommListBean> commList;


    public int getCommSum() {
        return commSum;
    }

    public void setCommSum(int commSum) {
        this.commSum = commSum;
    }

    public String getMarchantName() {
        return marchantName;
    }

    public void setMarchantName(String marchantName) {
        this.marchantName = marchantName;
    }

    public int getMarchantId() {
        return marchantId;
    }

    public void setMarchantId(int marchantId) {
        this.marchantId = marchantId;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public double getSubtotals() {
        return subtotals;
    }

    public void setSubtotals(double subtotals) {
        this.subtotals = subtotals;
    }

    public void setSubtotals(int subtotals) {
        this.subtotals = subtotals;
    }

    public ShippingE getShipping() {
        return shipping;
    }

    public void setShipping(ShippingE shipping) {
        this.shipping = shipping;
    }

    public List<CommListBean> getCommList() {
        return commList;
    }

    public void setCommList(List<CommListBean> commList) {
        this.commList = commList;
    }


}
