package com.csxs.letterbook.social.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.SellerInfoE;
import com.csxs.letterbook.seller.adapter.StoreImageAdapter;
import com.csxs.viewlib.DividerItemDecoration;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

public class PersonalInfoAdapter extends BaseMultiItemQuickAdapter<SellerInfoE, BaseViewHolder> {


    private Context mContext;
    private LinearLayoutManager linearLayoutManager;
    private  DividerItemDecoration dividerItemDecoration;


    public PersonalInfoAdapter(Context context, List<SellerInfoE> data) {
        super(data);
        this.mContext=context;
        addItemType(SellerInfoE.STORE_TEXT_DESC, R.layout.item_personal_desc_text);
        addItemType(SellerInfoE.STORE_IMAGE, R.layout.item_store_image);
        addItemType(SellerInfoE.STORE_BASE_INFO, R.layout.item_store_basic_info);
        linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        dividerItemDecoration = new DividerItemDecoration(mContext,LinearLayoutManager.HORIZONTAL, ScreenUtil.dip2px(mContext,15),0);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SellerInfoE item) {
        switch (helper.getItemViewType()) {
            case SellerInfoE.STORE_TEXT_DESC:
                ExpandableTextView expandableTextView=   helper.getView(R.id.expand_text_view);
                expandableTextView.setText(item.getSellerStoreDesc().getStoreDesc());
                break;

            case SellerInfoE.STORE_IMAGE:

                RecyclerView rvimage=  helper.getView(R.id.rc_img);
                rvimage.setLayoutManager(linearLayoutManager);
                rvimage.addItemDecoration(dividerItemDecoration);
                StoreImageAdapter storeImageAdapter = new StoreImageAdapter(R.layout.item_seller_store_image,item.getSellerStoreImage().getImages());
                rvimage.setAdapter(storeImageAdapter);
                rvimage.setNestedScrollingEnabled(false);

                break;
            case SellerInfoE.STORE_BASE_INFO:

                helper.setText(R.id.tv_store_business_time,mContext.getResources().getString(R.string.str_yingye_placeholder,item.getSellerBasicInfo().getOpeningTime(),item.getSellerBasicInfo().getClosingTime()));
                helper.setText(R.id.tv_store_address,item.getSellerBasicInfo().getAddress());
                helper.setText(R.id.tv_store_phone,item.getSellerBasicInfo().getContactsMobile());

                break;
        }


    }
}
