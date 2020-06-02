package com.csxs.letterbook.mine.order;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.OrderE;
import com.csxs.letterbook.widgets.sectionadapter.Section;
import com.csxs.letterbook.widgets.sectionadapter.SectionParameters;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/4/27
 * description:
 */
public class OrderSection extends Section {


    private List<OrderE> list;
    private OrderShopE orderShopE;

    private OrderTotalE orderTotalE;

    private Context context;

//    public OrderSection(@NonNull final String title, @NonNull final List<OrderE> list) {
//        super(SectionParameters.builder().itemResourceId(R.layout.item_order_goods_item).headerResourceId(R.layout.item_order_title_group).footerResourceId(R.layout.item_order_goods_total_price)
//                .build());
//        this.title = title;
//        this.list = list;
//    }

    public OrderSection(Context context) {
        super(SectionParameters.builder().itemResourceId(R.layout.item_order_goods_item).headerResourceId(R.layout.item_order_title_group).footerResourceId(R.layout.item_order_goods_total_price).build());
        this.context= context;
    }

    @Override
    public int getContentItemsTotal() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /**
     * 订单 商铺栏
     *
     * @param view View inflated by resource returned by getHeaderResourceId
     * @return
     */
    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(final View view) {
        return new ItemOrderHeaderViewHolder(view);
    }

    /**
     * 订单商品栏目
     *
     * @param view View created by getItemView or inflated resource returned by getItemResourceId
     * @return
     */
    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemGoodsViewHolder(view);
    }


    /**
     * 订单底部 栏
     * @param view View inflated by resource returned by getFooterResourceId
     * @return
     */
    @Override
    public RecyclerView.ViewHolder getFooterViewHolder(View view) {
        return  new ItemOrderFooterViewHolder(view);
    }





    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {

    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemGoodsViewHolder itemHolder = (ItemGoodsViewHolder) holder;
        OrderE orderE = list.get(position);


    }


    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        ItemOrderFooterViewHolder itemOrderFooterViewHolder= (ItemOrderFooterViewHolder) holder;
//        itemOrderFooterViewHolder.footerRoot.addView(textView);

    }

    public List<OrderE> getList() {
        return list;
    }

    public void setList(List<OrderE> list) {
        this.list = list;
    }

    public OrderShopE getOrderShopE() {
        return orderShopE;
    }

    public void setOrderShopE(OrderShopE orderShopE) {
        this.orderShopE = orderShopE;
    }

    public OrderTotalE getOrderTotalE() {
        return orderTotalE;
    }

    public void setOrderTotalE(OrderTotalE orderTotalE) {
        this.orderTotalE = orderTotalE;
    }
}
