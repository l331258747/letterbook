package com.csxs.letterbook.seller.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.csxs.letterbook.R;
import com.csxs.viewlib.linkerv.adapter.viewholder.LinkagePrimaryViewHolder;
import com.csxs.viewlib.linkerv.contract.ILinkagePrimaryAdapterConfig;

/**
 * @author: yeliu
 * created on 2020/4/22
 * description:
 */
public class StoreGoodsPrimaryAdapterConfig implements ILinkagePrimaryAdapterConfig {
    private static final int MARQUEE_REPEAT_LOOP_MODE = -1;
    private static final int MARQUEE_REPEAT_NONE_MODE = 0;

    private Context mContext;
    @Override
    public void setContext(Context context) {
        this.mContext = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.store_goods_adapter_linkage_primary;
    }

    @Override
    public int getGroupTitleViewId() {
        return R.id.tv_primary_group;
    }

    @Override
    public int getRootViewId() {
        return R.id.layout_primary_group;
    }

    @Override
    public void onBindViewHolder(LinkagePrimaryViewHolder holder, boolean selected, String title) {
        TextView tvTitle = ((TextView) holder.mGroupTitle);
        tvTitle.setText(title);
        holder.mGroupFlag.setBackgroundColor(mContext.getResources().getColor(selected ? R.color.color_E7A124 : R.color.transparent));

        tvTitle.setBackgroundColor(mContext.getResources().getColor(selected ? R.color.colorWhite : R.color.white_f4f5f7));
        tvTitle.setTextColor(ContextCompat.getColor(mContext, selected ? R.color.black : R.color.black_666666));


      //  tvTitle.setEllipsize(selected ? TextUtils.TruncateAt.MARQUEE : TextUtils.TruncateAt.END);
    //    tvTitle.setFocusable(selected);
    //    tvTitle.setFocusableInTouchMode(selected);
     //   tvTitle.setMarqueeRepeatLimit(selected ? MARQUEE_REPEAT_LOOP_MODE : MARQUEE_REPEAT_NONE_MODE);
    }

    @Override
    public void onItemClick(LinkagePrimaryViewHolder holder, View view, String title) {

    }
}
