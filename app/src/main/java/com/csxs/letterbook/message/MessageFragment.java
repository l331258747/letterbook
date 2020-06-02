package com.csxs.letterbook.message;

import android.os.Bundle;

import com.csxs.core.BaseConstants;
import com.csxs.core.base.LazyDiffFragment;
import com.csxs.letterbook.R;
import com.csxs.letterbook.message.mvp.MessagePresenter;
import com.csxs.letterbook.social.MapSocialUserFragment;
import com.csxs.letterbook.social.mvp.MapSociaUserPresenter;

/**
 * @author: yeliu
 * created on 2020/4/13
 * description:
 */
public class MessageFragment extends LazyDiffFragment<MessagePresenter> {


    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void lazyInitData(boolean isFirstVisible) {

    }

    @Override
    protected void fragmentHidden() {

    }

    public static MessageFragment newInstance(String type) {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        args.putString("type", type);
        return fragment;
    }
}
