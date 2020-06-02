package com.csxs.letterbook.entity;

import com.csxs.viewlib.linkerv.bean.BaseGroupedItem;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/15
 * description:
 */
public class CommodityListE extends BaseGroupedItem.ItemInfo {
    private int id;
    private String thumbnail;
    private double originalPrice;
    private int isSpec;
    private String classifyName;
    private String commodityName;
//    private List<GoodsSpec> specList;
    private List<SpecResultListE> specResultList;
    private int count;
    private long selectCount;
    private SpecResultListE selectSpec;
    private int marchantId;

    public int getMarchantId() {
        return marchantId;
    }

    public void setMarchantId(int marchantId) {
        this.marchantId = marchantId;
    }

    public SpecResultListE getSelectSpec() {
        return selectSpec;
    }

    public void setSelectSpec(SpecResultListE selectSpec) {
        this.selectSpec = selectSpec;
    }

    public long getSelectCount() {
        return selectCount;
    }

    public void setSelectCount(long selectCount) {
        this.selectCount = selectCount;
    }

    public CommodityListE(String title, String group) {
        super(title, group);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(int isSpec) {
        this.isSpec = isSpec;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }





    public List<SpecResultListE> getSpecResultList() {
        return specResultList;
    }

    public void setSpecResultList(List<SpecResultListE> specResultList) {
        this.specResultList = specResultList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CommodityListE{" +
                "id=" + id +
                ", thumbnail='" + thumbnail + '\'' +
                ", originalPrice=" + originalPrice +
                ", isSpec=" + isSpec +
                ", classifyName='" + classifyName + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", specResultList=" + specResultList +
                '}';
    }
}
