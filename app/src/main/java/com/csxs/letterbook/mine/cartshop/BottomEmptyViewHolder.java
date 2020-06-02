package com.csxs.letterbook.mine.cartshop;

import android.view.View;
import android.widget.LinearLayout;

import com.csxs.letterbook.R;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;


public class BottomEmptyViewHolder extends CartViewHolder {


    public LinearLayout view;

    public BottomEmptyViewHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        view = itemView.findViewById(R.id.view_empty_cart);

    }
}
