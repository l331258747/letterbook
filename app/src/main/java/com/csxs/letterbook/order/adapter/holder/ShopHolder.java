package com.csxs.letterbook.order.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.csxs.letterbook.R;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;


public class ShopHolder extends CartViewHolder {
    public TextView tvShopName;

    public ShopHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        tvShopName = itemView.findViewById(R.id.tv_shop_name);
    }
}
