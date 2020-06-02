package com.csxs.letterbook.mine.cartshop;

import android.view.View;
import android.widget.TextView;

import com.csxs.letterbook.R;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;

/**
 * @author: yeliu
 * created on 2020/4/25
 * description:购物车标题栏
 */
public class CartTitleGroupViewHolder extends CartViewHolder {
    public TextView titleText;
    public CartTitleGroupViewHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        titleText = itemView.findViewById(R.id.tv_store_name);
    }
}
