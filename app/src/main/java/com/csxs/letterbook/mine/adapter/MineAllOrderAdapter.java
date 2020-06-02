package com.csxs.letterbook.mine.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.csxs.letterbook.R;
import com.csxs.letterbook.mine.order.OrderBottomViewHolder;
import com.csxs.letterbook.mine.order.OrderGoodsTotalViewHolder;
import com.csxs.letterbook.mine.order.OrderGoodsViewHolder;
import com.csxs.letterbook.mine.order.OrderTitleGroupViewHolder;
import com.csxs.viewlib.cartlayout.CartAdapter;
import com.csxs.viewlib.cartlayout.bean.ICartItem;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/4/26
 * description:
 */
public class MineAllOrderAdapter  extends CartAdapter<CartViewHolder> {

    private final RelativeLayout.LayoutParams lp;

    public MineAllOrderAdapter(Context context, List<ICartItem> datas) {
        super(context, datas);
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected CartViewHolder getNormalViewHolder(View itemView) {
        return null;
    }

    @Override
    protected CartViewHolder getGroupViewHolder(View itemView) {
        return new OrderTitleGroupViewHolder(itemView,-1);
    }

    @Override
    protected CartViewHolder getChildViewHolder(View itemView) {
        return new OrderGoodsViewHolder(itemView,-1){

        };
    }

    @Override
    protected CartViewHolder getShopItemTotalLayout(View itemView) {
        return new OrderGoodsTotalViewHolder(itemView,-1);
    }

    @Override
    protected CartViewHolder getEmptyViewHolder(View itemView) {
        return new OrderBottomViewHolder(itemView, -1);
    }

    @Override
    protected int getChildItemLayout() {
        return R.layout.item_order_goods_item;
    }

    @Override
    protected int getGroupItemLayout() {
        return R.layout.item_order_title_group;
    }

    @Override
    protected int getNormalItemLayout() {
        return 0;
    }

    @Override
    protected int getShopItemTotalLayout() {
        return R.layout.item_order_goods_total_price;
    }

    @Override
    protected int getBottomEmptyLayout() {
        return R.layout.item_order_bottom;
    }
}
