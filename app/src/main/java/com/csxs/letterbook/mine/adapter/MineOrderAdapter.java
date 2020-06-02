package com.csxs.letterbook.mine.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.OrderE;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/4/27
 * description:
 */
public class MineOrderAdapter extends BaseQuickAdapter<OrderE, BaseViewHolder> {
    Context mContext;
    private LinearLayoutManager linearLayoutManager;
    private final GoodsAdapter goodsAdapter;

    public MineOrderAdapter(Context context, int layoutResId) {
        super(layoutResId);
        this.mContext = context;
         linearLayoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        goodsAdapter = new GoodsAdapter(R.layout.item_order_goods_item);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderE item) {
        ImageView ivShopLogo = helper.getView(R.id.iv_shop_logo);
        RelativeLayout layoutItem = helper.getView(R.id.layout_order_info);
        TextView tvOrderOrder = helper.getView(R.id.tv_order_state);
        TextView tvOrderShopName = helper.getView(R.id.tv_shop_name);
        TextView money = helper.getView(R.id.tv_order_rmb_money);
        TextView count = helper.getView(R.id.tv_order_count_goods);
        RecyclerView recyclerView = helper.getView(R.id.rv_item_goods);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    //    recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(goodsAdapter);
        goodsAdapter.setNewData(getData());
    }
}
