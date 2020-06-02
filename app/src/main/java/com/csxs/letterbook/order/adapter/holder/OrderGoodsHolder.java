package com.csxs.letterbook.order.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.csxs.letterbook.R;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;


public abstract class OrderGoodsHolder extends CartViewHolder implements View.OnClickListener {
    public TextView tvGoodsName;
    public TextView tvGoodsPrice;
    public TextView tvGoodsNum;
    public ImageView goodsImage;
    public  TextView tvSpecifications;

    public OrderGoodsHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        goodsImage= itemView.findViewById(R.id.sv_goods_img);
        tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
        tvSpecifications = itemView.findViewById(R.id.tv_type_sf);
        tvGoodsPrice = itemView.findViewById(R.id.tv_goods_price);
        tvGoodsNum = itemView.findViewById(R.id.tv_goods_num);
       // textViewReduce = ((TextView) itemView.findViewById(R.id.tv_reduce));

      //  textViewAdd = itemView.findViewById(R.id.tv_add);

        itemView.setOnClickListener(this);
       // textViewReduce.setOnClickListener(this);
      //  textViewAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.item) {
           // Toast.makeText(v.getContext(), ((GoodsBean) mICartItem).getGoods_name(), Toast.LENGTH_SHORT).show();
        }
//        else if (id == R.id.tv_reduce) {
//            int intValue = Integer.valueOf(textViewNum.getText().toString()).intValue();
//            if (intValue > 1) {
//                intValue--;
//                textViewNum.setText(String.valueOf(intValue));
//                ((GoodsBean) mICartItem).setGoods_amount(intValue);
//                onNeedCalculate();
//            }
//        } else if (id == R.id.tv_add) {
//            int intValue2 = Integer.valueOf(textViewNum.getText().toString()).intValue();
//            intValue2++;
//            textViewNum.setText(String.valueOf(intValue2));
//            ((GoodsBean) mICartItem).setGoods_amount(intValue2);
//            onNeedCalculate();
//        }
    }

    /**
     * 这里因为把 ViewHolder 没有写到 adapter 中作为内部类，所以对事件写了一个回调的抽象方法。
     * 如果不想这样写，你可以在以下方式中选其一：
     * 1. 将 ViewHolder 写到 Adapter 中作为内部类，这样你就可以访问 Adapter 中的一些方法属性了；
     * 2. 或者，你把 ItemView & ItemChildView 的事件放到 Adapter 中的 onBindViewHolder() 方法中设置。
     */
    public abstract void onNeedCalculate();
}
