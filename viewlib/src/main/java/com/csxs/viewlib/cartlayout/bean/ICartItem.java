package com.csxs.viewlib.cartlayout.bean;



public interface ICartItem {
    int TYPE_NORMAL = 0;//recylerView头部
    int TYPE_GROUP = 1;//item头部
    int TYPE_CHILD = 2;//内容
    int TYPE_TOTAL = 3;  //小计
    int TYPE_COUPON = 4;  //优惠券
    int TYPE_BOTTOM_EMPTY = 5;
    boolean isChecked();

    void setChecked(boolean checked);

    long getItemId();

    void setItemId(long itemId);

    int getItemType();

    void setItemType(int itemType);
}
