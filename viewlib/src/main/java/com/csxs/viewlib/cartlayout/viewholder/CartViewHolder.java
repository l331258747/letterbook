package com.csxs.viewlib.cartlayout.viewholder;


import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.viewlib.cartlayout.bean.ICartItem;


public class CartViewHolder extends RecyclerView.ViewHolder {
    public CheckBox mCheckBox;
    protected ICartItem mICartItem;

    public CartViewHolder(View itemView) {
        this(itemView, -1);
    }

    public CartViewHolder(View itemView, @IdRes int chekbox_id) {
        super(itemView);
        if (chekbox_id != -1) {
            mCheckBox = itemView.findViewById(chekbox_id);
        }
    }

    public void bindData(ICartItem ICartItem) {
        mICartItem = ICartItem;
    }
}
