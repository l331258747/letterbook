package com.csxs.letterbook.mine.order;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csxs.letterbook.R;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;


public class OrderBottomViewHolder extends CartViewHolder {


    // public TextView couponDiscount;
    public TextView tvOrderPay;
    public TextView tvOrderCancle;
    public TextView tvConfirmReceipt;
    public TextView tvOrderDelete;
    public TextView tvOrderRemind;
    public LinearLayout lastLayout;
    public View lineView;

    public OrderBottomViewHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        //  couponDiscount = itemView.findViewById(R.id.tv_order_coupon_money);
        lineView = itemView.findViewById(R.id.view_order_coupon_line);
        tvOrderPay = itemView.findViewById(R.id.tv_order_pay);
        tvOrderCancle = itemView.findViewById(R.id.tv_order_cancle);
        tvConfirmReceipt = itemView.findViewById(R.id.tv_confirm_receipt);
        tvOrderDelete = itemView.findViewById(R.id.tv_order_delete);
        tvOrderRemind = itemView.findViewById(R.id.tv_order_remind);
        lastLayout = itemView.findViewById(R.id.last_layout);
    }
}
