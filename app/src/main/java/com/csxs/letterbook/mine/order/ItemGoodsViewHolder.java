package com.csxs.letterbook.mine.order;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.letterbook.R;


final class ItemGoodsViewHolder extends RecyclerView.ViewHolder {

    public TextView tvGoodsName;
    public TextView tvGoodsCurrentPrice;
    public TextView tvGoodsOriginalPrice;
    public TextView tvGoodsNum;
    public ImageView goodsImage;
    public TextView tvSpecifications;
    public TextView retrunMoney;
    public TextView tvOrderGoodsBuyWay;

    ItemGoodsViewHolder(@NonNull final View view) {
        super(view);

        goodsImage = itemView.findViewById(R.id.sv_order_goods_img);
        tvGoodsName = itemView.findViewById(R.id.tv_order_goods_name);
        tvSpecifications = itemView.findViewById(R.id.tv_order_type_sf);
        tvGoodsCurrentPrice = itemView.findViewById(R.id.tv_order_goods_current_price);
        tvGoodsNum = itemView.findViewById(R.id.tv_order_goods_num);
        retrunMoney = itemView.findViewById(R.id.tv_goods_return_money);
        tvGoodsOriginalPrice = itemView.findViewById(R.id.tv_order_goods_original_price);
        tvOrderGoodsBuyWay = itemView.findViewById(R.id.tv_order_goods_buy_way);
    }
}