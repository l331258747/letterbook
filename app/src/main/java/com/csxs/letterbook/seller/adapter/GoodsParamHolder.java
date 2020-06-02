package com.csxs.letterbook.seller.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.csxs.letterbook.R;
import com.csxs.viewlib.LabelsView;

/**
 * @author: yeliu
 * created on 2020/5/18
 * description:
 */
public class GoodsParamHolder extends RecyclerView.ViewHolder {
    public LabelsView labelsView;


    public GoodsParamHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        labelsView = itemView.findViewById(R.id.lv_goods_param);


    }
}
