package com.csxs.letterbook.mine.order;

import android.view.View;
import android.widget.TextView;

import com.csxs.letterbook.R;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;


public class OrderGoodsTotalViewHolder extends CartViewHolder {
    public TextView count;
    public TextView money;

    public OrderGoodsTotalViewHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        money = itemView.findViewById(R.id.tv_order_rmb_money);
        count = itemView.findViewById(R.id.tv_order_count_goods);
    }
}
