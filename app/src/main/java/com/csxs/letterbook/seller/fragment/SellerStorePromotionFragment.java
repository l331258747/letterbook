package com.csxs.letterbook.seller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.core.base.LazyDiffFragment;
import com.csxs.letterbook.R;
import com.csxs.letterbook.seller.adapter.SellerStoreDynamicAdapter;
import com.csxs.letterbook.seller.adapter.SellerStoreInfoAdapter;
import com.csxs.letterbook.seller.mvp.presenter.SellerStoreOrderPresenter;
import com.csxs.letterbook.seller.mvp.presenter.SellerStorePromotionPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商铺活动
 */
public class SellerStorePromotionFragment extends LazyDiffFragment<SellerStorePromotionPresenter> {


    @BindView(R.id.seller_promotion_recycler)
    RecyclerView sellerPromotionRv;
    private SellerStoreDynamicAdapter sellerStoreInfoAdapter;

    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_promotion_store_seller;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        sellerStoreInfoAdapter = new SellerStoreDynamicAdapter(R.layout.item_seller_store_dynamic,null);
        sellerPromotionRv.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        sellerPromotionRv.setAdapter(sellerStoreInfoAdapter);
    }

    @Override
    protected void lazyInitData(boolean isFirstVisible) {
        if(isFirstVisible){
            List<String> list=new ArrayList<>();
            for (int i=0;i<20;i++){
                list.add("测试数据"+ i);
            }

            sellerStoreInfoAdapter.setNewData(list);
        }
    }

    @Override
    protected void fragmentHidden() {

    }


    public static Fragment newInstance(int status) {
        Bundle args = new Bundle();
        args.putInt("status", status);
        SellerStorePromotionFragment fragment = new SellerStorePromotionFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
