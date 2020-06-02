package com.csxs.letterbook.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author: yeliu
 * created on 2020/5/11
 * description:
 */
public class SellerHomeDynamicE implements MultiItemEntity {

    public static final int  SELLER_HOME_DYNAMIC_TEXT= 1;
    public static final int SELLER_HOME_DYNAMIC_IMAGE = 2;

    public int itemType;


    /**
     * 动态文本带图片
     */
    private SellerHomeDynamicImageE sellerHomeDynamicImage;

    /**
     * 动态纯文本
     */

    private SellerHomeDynamicTextE sellerHomeDynamicText;




    public SellerHomeDynamicImageE getSellerHomeDynamicImage() {
        return sellerHomeDynamicImage;
    }

    public void setSellerHomeDynamicImage(SellerHomeDynamicImageE sellerHomeDynamicImage) {
        this.sellerHomeDynamicImage = sellerHomeDynamicImage;
    }

    public SellerHomeDynamicTextE getSellerHomeDynamicText() {
        return sellerHomeDynamicText;
    }

    public void setSellerHomeDynamicText(SellerHomeDynamicTextE sellerHomeDynamicText) {
        this.sellerHomeDynamicText = sellerHomeDynamicText;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
