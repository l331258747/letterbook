package com.csxs.letterbook.seller.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.csxs.letterbook.R;

/**
 * @author: yeliu
 * created on 2020/5/18
 * description:
 */
public class GoodsParamTitleHolder extends RecyclerView.ViewHolder{
    public TextView paramTitle;


    public GoodsParamTitleHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        paramTitle = itemView.findViewById(R.id.tv_goods_param_title);


    }
}
