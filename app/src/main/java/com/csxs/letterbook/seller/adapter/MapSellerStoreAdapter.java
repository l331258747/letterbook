package com.csxs.letterbook.seller.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.letterbook.entity.MapSellerStoreE;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/4/21
 * description:
 */
public class MapSellerStoreAdapter extends BaseQuickAdapter<MapSellerStoreE, BaseViewHolder> {
    private Context mContext;
    public MapSellerStoreAdapter(Context context,int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MapSellerStoreE item) {

    }
}
