package com.csxs.letterbook.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author: yeliu
 * created on 2020/4/20
 * description:
 */
public class SellerInfoE implements MultiItemEntity {

    private int itemType;

    public static final int STORE_TEXT_DESC = 1;
    public static final int STORE_IMAGE = 2;
    public static final int STORE_BASE_INFO = 3;

    /**
     * 商铺文字描述
     */
    private SellerStoreDescE sellerStoreDesc;

    /**
     * 商铺图片展示
     */
    private SellerStoreImageE sellerStoreImage;

    /**
     * 商铺基本信息
     */
    private SellerBasicInfoE sellerBasicInfo;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public SellerStoreDescE getSellerStoreDesc() {
        return sellerStoreDesc;
    }

    public void setSellerStoreDesc(SellerStoreDescE sellerStoreDesc) {
        this.sellerStoreDesc = sellerStoreDesc;
    }

    public SellerStoreImageE getSellerStoreImage() {
        return sellerStoreImage;
    }

    public void setSellerStoreImage(SellerStoreImageE sellerStoreImage) {
        this.sellerStoreImage = sellerStoreImage;
    }

    public SellerBasicInfoE getSellerBasicInfo() {
        return sellerBasicInfo;
    }

    public void setSellerBasicInfo(SellerBasicInfoE sellerBasicInfo) {
        this.sellerBasicInfo = sellerBasicInfo;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
