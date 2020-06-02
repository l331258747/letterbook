package com.csxs.letterbook.mine.order;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csxs.letterbook.R;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;


public class OrderTitleGroupViewHolder extends CartViewHolder {

    private RelativeLayout layoutItem;
    private ImageView shopLogo;
    private TextView tvOrderOrder;
    private TextView tvOrderShopName;


    public OrderTitleGroupViewHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        shopLogo = itemView.findViewById(R.id.iv_shop_logo);
        layoutItem = itemView.findViewById(R.id.layout_order_info);
        tvOrderOrder = itemView.findViewById(R.id.tv_order_state);
        tvOrderShopName = itemView.findViewById(R.id.tv_shop_name);


    }

}
