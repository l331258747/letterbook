package com.csxs.letterbook.mine.order;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.letterbook.R;

final class ItemOrderFooterViewHolder extends RecyclerView.ViewHolder {

    public TextView count;
    public TextView money;
    public RelativeLayout footerRoot;

    ItemOrderFooterViewHolder(@NonNull final View view) {
        super(view);
        footerRoot =itemView.findViewById(R.id.item_footer_root);
        money = itemView.findViewById(R.id.tv_order_rmb_money);
        count = itemView.findViewById(R.id.tv_order_count_goods);
    }
}
