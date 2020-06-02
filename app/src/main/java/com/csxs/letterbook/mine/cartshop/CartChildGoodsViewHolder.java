package com.csxs.letterbook.mine.cartshop;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csxs.letterbook.R;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;


public abstract class CartChildGoodsViewHolder extends CartViewHolder implements View.OnClickListener {
    public TextView tvGoodsName;
    public TextView tvGoodsCurrentPrice;
    public TextView tvGoodsNum;
    public ImageView goodsImage;
    public TextView tvSpecifications;
    public ImageView ivGoodsReduce;
    public ImageView ivGoodsAdd;
    public TextView tvGoodsBuyWay;
    public TextView tvGoodsOriginalPrice;

    public CartChildGoodsViewHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        goodsImage = itemView.findViewById(R.id.iv_cart_goods);
        tvGoodsName = itemView.findViewById(R.id.tv_cart_goods_name);
        tvSpecifications = itemView.findViewById(R.id.tv_cart_goods_sf);
        tvGoodsCurrentPrice = itemView.findViewById(R.id.tv_cart_goods_current_price);
        tvGoodsNum = itemView.findViewById(R.id.tv_cart_goods_num);
        ivGoodsReduce = itemView.findViewById(R.id.iv_reduce);
        ivGoodsAdd = itemView.findViewById(R.id.iv_add);
        tvGoodsBuyWay = itemView.findViewById(R.id.tv_cart_goods_buy_way);
        tvGoodsOriginalPrice = itemView.findViewById(R.id.tv_cart_goods_original_price);



        itemView.setOnClickListener(this);
        ivGoodsReduce.setOnClickListener(this);
        ivGoodsAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.item) {
            // Toast.makeText(v.getContext(), ((GoodsBean) mICartItem).getGoods_name(), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.iv_reduce) {
            int intValue = Integer.valueOf(tvGoodsNum.getText().toString()).intValue();
            if (intValue > 1) {
                intValue--;
                tvGoodsNum.setText(String.valueOf(intValue));
               // ((CartGoodsE) mICartItem).getGoods().setAmount(intValue);
                onNeedCalculate();
            }
        } else if (id == R.id.iv_add) {
            int intValue2 = Integer.valueOf(tvGoodsNum.getText().toString()).intValue();
            intValue2++;
            tvGoodsNum.setText(String.valueOf(intValue2));
           // ((CartGoodsE) mICartItem).getGoods().setAmount(intValue2);
            onNeedCalculate();
        }
    }

    /**
     * 这里因为把 ViewHolder 没有写到 adapter 中作为内部类，所以对事件写了一个回调的抽象方法。
     * 如果不想这样写，你可以在以下方式中选其一：
     * 1. 将 ViewHolder 写到 Adapter 中作为内部类，这样你就可以访问 Adapter 中的一些方法属性了；
     * 2. 或者，你把 ItemView & ItemChildView 的事件放到 Adapter 中的 onBindViewHolder() 方法中设置。
     */
    public abstract void onNeedCalculate();
}
