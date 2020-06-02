package com.csxs.letterbook.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author: yeliu
 * created on 2020/5/15
 * description:
 */
public class SellerGoodsCategoryDetailsE implements MultiItemEntity {

    public static final int SELLERGOODSCATEGORY_HEADER = 1;
    public static final int SELLERGOODSCATEGORY_BODY = 2;

    private String titleName;
    private String tag;

    private CommodityListE commodityList;

    public CommodityListE getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(CommodityListE commodityList) {
        this.commodityList = commodityList;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
