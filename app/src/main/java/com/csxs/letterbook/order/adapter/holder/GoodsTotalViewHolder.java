package com.csxs.letterbook.order.adapter.holder;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csxs.letterbook.R;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;


public class GoodsTotalViewHolder extends CartViewHolder {
    public TextView count;
    public TextView money;
    public EditText inputText;
    public LinearLayout lastLayout;

    public GoodsTotalViewHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        money = itemView.findViewById(R.id.tv_rmb_money);
        count = itemView.findViewById(R.id.tv_count_goods);
        inputText = itemView.findViewById(R.id.et_input_info);
        lastLayout= itemView.findViewById(R.id.bottomLayout);

    }
}
