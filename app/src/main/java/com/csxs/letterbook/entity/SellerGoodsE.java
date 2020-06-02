package com.csxs.letterbook.entity;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/15
 * description:
 */
public class SellerGoodsE {

    private List<CommodityListE> commodityList;
    private String classifyName ;
    private int marchantId;



    public int getMarchantId() {
        return marchantId;
    }

    public void setMarchantId(int marchantId) {
        this.marchantId = marchantId;
    }

    public List<CommodityListE> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<CommodityListE> commodityList) {
        this.commodityList = commodityList;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    @Override
    public String toString() {
        return "SellerGoodsE{" +
                "commodityList=" + commodityList +
                ", classifyName='" + classifyName + '\'' +
                '}';
    }
}
