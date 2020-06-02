package com.csxs.letterbook.seller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.SellerGoodsCategoryE;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/15
 * description: 左边 商品类别
 */
public class SellerGoodsCategoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int mSelectedPosition = -1;

    private Context mContext;

    public SellerGoodsCategoryAdapter(Context context,int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.mContext=context;
    }

    public void setSelectedPosition(int selectedPosition) {
        mSelectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        TextView tvGoodsCategoryName = helper.getView(R.id.tv_goods_category_name);
        View vGoodsCategoryCheck = helper.getView(R.id.v_check);
        tvGoodsCategoryName.setText(item);


       if(helper.getAdapterPosition()==mSelectedPosition){

           vGoodsCategoryCheck.setVisibility(View.VISIBLE);
           tvGoodsCategoryName.setTextColor(mContext.getResources().getColor(R.color.color_0D0D0D));
       }else{

           vGoodsCategoryCheck.setVisibility(View.GONE);
           tvGoodsCategoryName.setTextColor(mContext.getResources().getColor(R.color.black_999999));
       }
    }
}
