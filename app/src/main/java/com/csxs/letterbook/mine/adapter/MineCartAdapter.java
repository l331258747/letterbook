package com.csxs.letterbook.mine.adapter;

import android.content.Context;

import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;

import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.R;

import com.csxs.letterbook.entity.GoodsBean;
import com.csxs.letterbook.entity.ShopBean;
import com.csxs.letterbook.mine.cartshop.BottomEmptyViewHolder;
import com.csxs.letterbook.mine.cartshop.CartChildGoodsViewHolder;
import com.csxs.letterbook.mine.cartshop.CartTitleGroupViewHolder;
import com.csxs.viewlib.cartlayout.CartAdapter;
import com.csxs.viewlib.cartlayout.bean.ICartItem;
import com.csxs.viewlib.cartlayout.viewholder.CartViewHolder;

import java.util.List;

public class MineCartAdapter extends CartAdapter<CartViewHolder> {


    private final RelativeLayout.LayoutParams lp;

    public MineCartAdapter(Context context, List<ICartItem> datas) {
        super(context, datas);
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,ScreenUtil.dip2px(context,10));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof CartTitleGroupViewHolder) {
            //商铺
            ShopBean cartStoreE = ((ShopBean) mDatas.get(position));

            CartTitleGroupViewHolder titleGroupViewHolder= (CartTitleGroupViewHolder) holder;

            titleGroupViewHolder.titleText.setText(mContext.getString(R.string.str_placeholder,cartStoreE.getShop_name()));


        }else if(holder instanceof CartChildGoodsViewHolder){
            //商品
            GoodsBean goodsBean = ((GoodsBean) mDatas.get(position));

            CartChildGoodsViewHolder goodsViewHolder= (CartChildGoodsViewHolder) holder;
            //当前价格
            goodsViewHolder.tvGoodsCurrentPrice.setText(mContext.getString(R.string.rmb_single,goodsBean.getGoods_price()));
            //原价
            goodsViewHolder.tvGoodsOriginalPrice.setText(mContext.getString(R.string.rmb_single,goodsBean.getGoods_original_price()));

            goodsViewHolder.tvGoodsOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG );
            //商品原价
            goodsViewHolder.tvGoodsNum.setText(mContext.getString(R.string.str_placeholder,String.valueOf(goodsBean.getGoods_amount())));

            goodsViewHolder.tvGoodsName.setText(mContext.getString(R.string.str_placeholder,goodsBean.getGoods_name()));


        }else if(holder instanceof BottomEmptyViewHolder){
            BottomEmptyViewHolder emptyViewHolder = (BottomEmptyViewHolder) holder;
            if (position == mDatas.size() - 1) {
              //  RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(emptyViewHolder.view.getLayoutParams());
                lp.setMargins(0, 0, 0, ScreenUtil.dp2px(emptyViewHolder.view.getContext(), 20));
                emptyViewHolder.view.setLayoutParams(lp);
            }else{
//                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(emptyViewHolder.view.getLayoutParams());
                lp.setMargins(0, 0, 0, 0);
                emptyViewHolder.view.setLayoutParams(lp);
            }
        }
    }

    @Override
    protected CartViewHolder getNormalViewHolder(View itemView) {
        return null;
    }

    @Override
    protected CartViewHolder getGroupViewHolder(View itemView) {
        return new CartTitleGroupViewHolder(itemView,R.id.store_checkbox);
    }

    @Override
    protected CartViewHolder getChildViewHolder(View itemView) {
        return new CartChildGoodsViewHolder(itemView,R.id.goods_checkbox){
            @Override
            public void onNeedCalculate() {

            }
        };
    }

    @Override
    protected CartViewHolder getShopItemTotalLayout(View itemView) {
        return null;
    }

    @Override
    protected CartViewHolder getEmptyViewHolder(View itemView) {
        return new BottomEmptyViewHolder(itemView, R.id.bottm_checkbox_empty);
    }

    @Override
    protected int getChildItemLayout() {
        return R.layout.item_child_cart_goods;
    }

    @Override
    protected int getGroupItemLayout() {
        return R.layout.item_cart_title_group_shop;
    }

    @Override
    protected int getNormalItemLayout() {
        return 0;
    }

    @Override
    protected int getShopItemTotalLayout() {
        return 0;
    }

    @Override
    protected int getBottomEmptyLayout() {
        return R.layout.item_cart_bottom_empty;
    }

}
