package com.csxs.letterbook.seller.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.letterbook.R;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/4/20
 * description:
 */
public class SellerStoreDynamicAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SellerStoreDynamicAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.content,item);
    }
}
