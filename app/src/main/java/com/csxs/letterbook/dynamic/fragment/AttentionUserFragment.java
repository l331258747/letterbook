package com.csxs.letterbook.dynamic.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.csxs.core.base.LazyDiffFragment;
import com.csxs.letterbook.R;
import com.csxs.letterbook.dynamic.mvp.DynamicPresenter;
import com.csxs.letterbook.seller.fragment.SellerStoreInfoFragment;

/**
 * @author: yeliu
 * created on 2020/4/24
 * description:我关注人的动态
 */
public class AttentionUserFragment extends LazyDiffFragment<DynamicPresenter> {

    @Override
    protected void lazyInitData(boolean isFirstVisible) {

    }

    @Override
    protected void fragmentHidden() {

    }

    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attention_user;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    public static Fragment newInstance(int status) {
        Bundle args = new Bundle();
        args.putInt("status", status);
        AttentionUserFragment fragment = new AttentionUserFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
