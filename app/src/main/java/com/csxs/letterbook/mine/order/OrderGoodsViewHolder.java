package com.csxs.letterbook.mine.order;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.csxs.letterbook.R;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;


public class OrderGoodsViewHolder extends CartViewHolder implements View.OnClickListener {
    public TextView tvGoodsName;
    public TextView tvGoodsCurrentPrice;
    public TextView tvGoodsOriginalPrice;
    public TextView tvGoodsNum;
    public ImageView goodsImage;
    public TextView tvSpecifications;
    public TextView retrunMoney;
    public TextView tvOrderGoodsBuyWay;

    public OrderGoodsViewHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        goodsImage = itemView.findViewById(R.id.sv_order_goods_img);
        tvGoodsName = itemView.findViewById(R.id.tv_order_goods_name);
        tvSpecifications = itemView.findViewById(R.id.tv_order_type_sf);
        tvGoodsCurrentPrice = itemView.findViewById(R.id.tv_order_goods_current_price);
        tvGoodsNum = itemView.findViewById(R.id.tv_order_goods_num);
        retrunMoney = itemView.findViewById(R.id.tv_goods_return_money);
        tvGoodsOriginalPrice = itemView.findViewById(R.id.tv_order_goods_original_price);
        tvOrderGoodsBuyWay = itemView.findViewById(R.id.tv_order_goods_buy_way);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_item_root) {
            //商品点击item
        }

    }

    /**
     * 这里因为把 ViewHolder 没有写到 adapter 中作为内部类，所以对事件写了一个回调的抽象方法。
     * 如果不想这样写，你可以在以下方式中选其一：
     * 1. 将 ViewHolder 写到 Adapter 中作为内部类，这样你就可以访问 Adapter 中的一些方法属性了；
     * 2. 或者，你把 ItemView & ItemChildView 的事件放到 Adapter 中的 onBindViewHolder() 方法中设置。
     */
    // public abstract void onNeedCalculate();
}
