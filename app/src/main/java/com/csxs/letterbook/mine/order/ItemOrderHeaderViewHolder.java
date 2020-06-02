package com.csxs.letterbook.mine.order;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.letterbook.R;


final class ItemOrderHeaderViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout layoutItem;
    public ImageView shopLogo;
    public TextView tvOrderOrder;
    public TextView tvOrderShopName;

    ItemOrderHeaderViewHolder(@NonNull final View view) {
        super(view);
        shopLogo = itemView.findViewById(R.id.iv_shop_logo);
        layoutItem = itemView.findViewById(R.id.layout_order_info);
        tvOrderOrder = itemView.findViewById(R.id.tv_order_state);
        tvOrderShopName = itemView.findViewById(R.id.tv_shop_name);
    }
}
