package com.csxs.letterbook.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.GoodsTotalBean;
import com.csxs.letterbook.entity.OrderGoodsE;
import com.csxs.letterbook.entity.OrderShopE;
import com.csxs.letterbook.entity.TopReciveAddressE;
import com.csxs.letterbook.order.SingleOrderActivity;
import com.csxs.letterbook.order.adapter.holder.GoodsTotalViewHolder;
import com.csxs.letterbook.order.adapter.holder.NormalViewHolder;
import com.csxs.letterbook.order.adapter.holder.OrderGoodsHolder;
import com.csxs.letterbook.order.adapter.holder.ShopHolder;
import com.csxs.viewlib.cartlayout.CartAdapter;
import com.csxs.viewlib.cartlayout.bean.ICartItem;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/28
 * description:
 */
public class SingleOrderAdapter extends CartAdapter<CartViewHolder> {
    private final RelativeLayout.LayoutParams lp;

    public SingleOrderAdapter(Context context, List<ICartItem> datas) {
        super(context, datas);
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ScreenUtil.dip2px(context, 10));
    }

    @Override
    protected CartViewHolder getNormalViewHolder(View itemView) {
        return new NormalViewHolder(itemView, -1);
    }

    @Override
    protected CartViewHolder getGroupViewHolder(View itemView) {
        return new ShopHolder(itemView, -1);
    }

    @Override
    protected CartViewHolder getChildViewHolder(View itemView) {
        return new OrderGoodsHolder(itemView, -1) {
            @Override
            public void onNeedCalculate() {
                if (onCheckChangeListener != null) {
                    onCheckChangeListener.onCalculateChanged(null);
                }
            }
        };
    }

    @Override
    protected CartViewHolder getShopItemTotalLayout(View itemView) {
        return new GoodsTotalViewHolder(itemView, -1);
    }

    @Override
    protected CartViewHolder getEmptyViewHolder(View itemView) {
        return null;
    }

    @Override
    protected int getChildItemLayout() {
        return R.layout.item_order_goods;
    }

    @Override
    protected int getGroupItemLayout() {
        return R.layout.item_order_shop;
    }

    @Override
    protected int getNormalItemLayout() {
        return R.layout.item_order_top_address;
    }

    @Override
    protected int getShopItemTotalLayout() {
        return R.layout.item_order_total;
    }

    @Override
    protected int getBottomEmptyLayout() {
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof NormalViewHolder) {
            //地址
            TopReciveAddressE normalBean = (TopReciveAddressE) mDatas.get(position);
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            normalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "选择地址", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(mContext, AddrListActivity.class);
                    //  intent.putExtra("select", true);
                    // ((SingleOrderActivity) mContext).startActivityForResult(intent, 10010);
                }
            });
            if (normalBean.getAddress() != null && normalBean.getAddress().getAddressDetais() != null && normalBean.getAddress().getAddressDetais().length() > 0) {
                normalViewHolder.tvAddress.setText(normalBean.getAddress().getAddressDetais());
                normalViewHolder.tvUserName.setText(normalBean.getAddress().getContacts());
                normalViewHolder.tvUserPhoneNum.setText(normalBean.getAddress().getContactWay());
                normalViewHolder.tvUserName.setVisibility(View.VISIBLE);
                normalViewHolder.tvUserPhoneNum.setVisibility(View.VISIBLE);

            } else {
                normalViewHolder.tvUserName.setVisibility(View.GONE);
                normalViewHolder.tvUserPhoneNum.setVisibility(View.GONE);
                normalViewHolder.tvAddress.setText(normalBean.getAddressTip());
            }
        } else if (holder instanceof ShopHolder) {
            //商铺
            ShopHolder groupViewHolder = (ShopHolder) holder;
            groupViewHolder.tvShopName.setText(((OrderShopE) mDatas.get(position)).getShopName());

        } else if (holder instanceof OrderGoodsHolder) {
            //商品
            OrderGoodsE goodsBean = ((OrderGoodsE) mDatas.get(position));
            OrderGoodsHolder childViewHolder = (OrderGoodsHolder) holder;
            childViewHolder.tvGoodsName.setText(goodsBean.getCommListBean().getCommodityName());
            if (goodsBean.getCommListBean().getImageUrl() != null && !"".equals(goodsBean.getCommListBean().getImageUrl())) {

                ImageLoaderV4.getInstance().displayRoundImage(mContext, goodsBean.getCommListBean().getImageUrl(), childViewHolder.goodsImage, R.drawable.default_picture, ScreenUtil.dip2px(mContext, 3));
            }else{
                childViewHolder.goodsImage.setImageResource( R.drawable.default_picture);
            }


            childViewHolder.tvSpecifications.setText(goodsBean.getCommListBean().getItemText());
            childViewHolder.tvGoodsPrice.setText(mContext.getString(R.string.rmb_single, goodsBean.getCommListBean().getOriginalPrice()));
            childViewHolder.tvGoodsNum.setText(mContext.getString(R.string.goods_num_placeholder, goodsBean.getCommListBean().getAmount()));

        } else if (holder instanceof GoodsTotalViewHolder) {
            //总计
            GoodsTotalBean goodsTotalBean = (GoodsTotalBean) mDatas.get(position);
            GoodsTotalViewHolder normalViewHolder = (GoodsTotalViewHolder) holder;
            normalViewHolder.count.setText(mContext.getString(R.string.rmb_all_count, goodsTotalBean.getGoodsCount()));
            normalViewHolder.money.setText(mContext.getString(R.string.rmb_total, goodsTotalBean.getGoodsTotalPrice()));
            if (position == mDatas.size() - 1) {
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(normalViewHolder.lastLayout.getLayoutParams());
                lp.setMargins(0, 0, 0, ScreenUtil.dp2px(mContext, 20));
                normalViewHolder.lastLayout.setLayoutParams(lp);
            } else {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) normalViewHolder.lastLayout.getLayoutParams();
                lp.setMargins(0, 0, 0, 0);
                normalViewHolder.lastLayout.setLayoutParams(lp);
            }
        }
    }
}
