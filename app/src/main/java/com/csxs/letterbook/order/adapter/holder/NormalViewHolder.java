package com.csxs.letterbook.order.adapter.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csxs.letterbook.R;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;


public class NormalViewHolder extends CartViewHolder {
    public TextView tvAddress;
    public TextView tvUserName;
    public TextView tvUserPhoneNum;
    private LinearLayout layoutAddress;

    public NormalViewHolder(final View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        tvAddress = itemView.findViewById(R.id.tv_address);
        tvUserName = itemView.findViewById(R.id.tv_user_info);
        tvUserPhoneNum = itemView.findViewById(R.id.user_phone_num);
       layoutAddress = itemView.findViewById(R.id.item_addtess);
//        layoutAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(itemView.getContext(), AddrListActivity.class);
//                itemView.getContext().startActivity(intent);
//            }
//        });
     //  itemView.setOnClickListener(this);

        //imgViewClose = itemView.findViewById(R.id.img_close);
    }

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.item_addtess) {
//            Toast.makeText(v.getContext(), "112", Toast.LENGTH_SHORT).show();
//        }
//    }
}
