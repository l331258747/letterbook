package com.csxs.letterbook.seller.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.SellerGoodsCategoryDetailsE;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/15
 * description:
 */
public class SellerGoodsCategoryDetailsAdapter extends BaseMultiItemQuickAdapter<SellerGoodsCategoryDetailsE, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SellerGoodsCategoryDetailsAdapter(List<SellerGoodsCategoryDetailsE> data) {
        super(data);

        addItemType(SellerGoodsCategoryDetailsE.SELLERGOODSCATEGORY_HEADER, R.layout.item_category_title);

        addItemType(SellerGoodsCategoryDetailsE.SELLERGOODSCATEGORY_BODY, R.layout.item_goods_catecory_detail);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SellerGoodsCategoryDetailsE item) {

        if (SellerGoodsCategoryDetailsE.SELLERGOODSCATEGORY_HEADER == helper.getItemViewType()) {

            TextView titleName = helper.getView(R.id.tv_title);
            titleName.setText(item.getTitleName());

        } else if (SellerGoodsCategoryDetailsE.SELLERGOODSCATEGORY_BODY == helper.getItemViewType()) {
            TextView categoryName = helper.getView(R.id.iv_goods_name);
            categoryName.setText(item.getCommodityList().getCommodityName());

        }

    }
}
