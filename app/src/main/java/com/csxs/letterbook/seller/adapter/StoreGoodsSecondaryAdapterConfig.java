package com.csxs.letterbook.seller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.CommodityListE;
import com.csxs.letterbook.entity.ElemeGroupedItem;
import com.csxs.letterbook.seller.listener.AddGoodsSpac;
import com.csxs.letterbook.seller.listener.ListenerGoodsCount;
import com.csxs.letterbook.widgets.numbtn.AddWidget;
import com.csxs.viewlib.linkerv.adapter.viewholder.LinkageSecondaryHeaderViewHolder;
import com.csxs.viewlib.linkerv.adapter.viewholder.LinkageSecondaryViewHolder;
import com.csxs.viewlib.linkerv.bean.BaseGroupedItem;
import com.csxs.viewlib.linkerv.contract.ILinkageSecondaryAdapterConfig;

/**
 * @author: yeliu
 * created on 2020/4/22
 * description:
 */
public class StoreGoodsSecondaryAdapterConfig implements ILinkageSecondaryAdapterConfig<CommodityListE> {
    private static final int SPAN_COUNT_FOR_GRID_MODE = 2;
    private Context mContext;
    private AddWidget.OnAddClick listenerGoodsCount;
    private AddGoodsSpac listenerSpac;

    public StoreGoodsSecondaryAdapterConfig(AddWidget.OnAddClick listenerGoodsCount, AddGoodsSpac addGoodsSpac) {
        this.listenerGoodsCount = listenerGoodsCount;
        this.listenerSpac = addGoodsSpac;
    }

    @Override
    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public int getGridLayoutId() {
        return 0;
    }

    @Override
    public int getLinearLayoutId() {
        return R.layout.item_seller_goods;
    }

    @Override
    public int getHeaderLayoutId() {
        return R.layout.adapter_goods_eleme_secondary_header;
    }

    @Override
    public int getHeaderTextViewId() {
        return R.id.goods_secondary_header;
    }

    @Override
    public int getSpanCountOfGridMode() {
        return SPAN_COUNT_FOR_GRID_MODE;
    }

    @Override
    public void onBindViewHolder(LinkageSecondaryViewHolder holder, BaseGroupedItem<CommodityListE> item) {
        ((TextView) holder.getView(R.id.iv_goods_name)).setText(item.info.getCommodityName());


        if (item.info.getIsSpec() == 1) {

        }

        ((TextView) holder.getView(R.id.iv_goods_price)).setText(mContext.getString(R.string.rmb_single, item.info.getOriginalPrice()));


//        Glide.with(mContext).load(item.info.getThumbnail()).into((ImageView) holder.getView(R.id.iv_goods_img));
        ImageLoaderV4.getInstance().displayRoundImage(mContext, item.info.getThumbnail(), holder.getView(R.id.iv_goods_img), R.drawable.default_picture, ScreenUtil.dip2px(mContext, 3));


        holder.getView(R.id.iv_goods_item).setOnClickListener(v -> {
            //点击跳转详情


        });


        if (item.info.getIsSpec() == 1) {
            holder.getView(R.id.iv_goods_add_spac).setOnClickListener(listener);
            holder.getView(R.id.iv_goods_add_spac).setTag(item.info);

            holder.getView(R.id.iv_goods_add).setVisibility(View.GONE);
            holder.getView(R.id.iv_goods_add_spac).setVisibility(View.VISIBLE);
        } else {
            ((AddWidget) holder.getView(R.id.iv_goods_add)).setData(listenerGoodsCount, item.info);
            holder.getView(R.id.iv_goods_add).setVisibility(View.VISIBLE);
            holder.getView(R.id.iv_goods_add_spac).setVisibility(View.GONE);
        }


    }

    @Override
    public void onBindHeaderViewHolder(LinkageSecondaryHeaderViewHolder holder, BaseGroupedItem<CommodityListE> item) {
        ((TextView) holder.getView(R.id.goods_secondary_header)).setText(item.header);
    }

//    new AddWidget.OnAddClick() {
//        @Override
//        public void onAddClick(View view, CommodityListE item) {
//            // item.info.setCount(count);
//            if(listenerGoodsCount!=null){
//                listenerGoodsCount.add();
//            }
//        }
//
//        @Override
//        public void onSubClick(CommodityListE commodityListE) {
//            //  item.info.setCount(fb);
//            if(listenerGoodsCount!=null){
//                listenerGoodsCount.reduce();
//            }
//        }
//    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.iv_goods_add_spac) {
                if (listenerSpac != null) {

                    listenerSpac.onSpacDerails((CommodityListE) v.getTag());
                }
            }

        }
    };

}
