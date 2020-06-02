package com.csxs.letterbook.seller.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.SearchHotE;

/**
 * @author: yeliu
 * created on 2020/4/17
 * description:
 */
public class SearchResultAdapter extends BaseQuickAdapter<SearchHotE, BaseViewHolder> {

    public SearchResultAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SearchHotE item) {
        helper.setText(R.id.tv_search_store_name,item.getStoreName());
        helper.setText(R.id.tv_search_address,item.getStoreAddress());

    }
}
