package com.csxs.letterbook.mine.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.letterbook.entity.OrderE;

/**
 * @author: yeliu
 * created on 2020/4/27
 * description:
 */
public class GoodsAdapter extends BaseQuickAdapter<OrderE, BaseViewHolder> {

    public GoodsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderE item) {

    }
}
