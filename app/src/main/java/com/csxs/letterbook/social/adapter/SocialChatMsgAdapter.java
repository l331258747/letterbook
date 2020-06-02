package com.csxs.letterbook.social.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.letterbook.R;

/**
 * @author: yeliu
 * created on 2020/5/19
 * description:
 */
public class SocialChatMsgAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public SocialChatMsgAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
      // helper.setText(R.id.tv_user_chat_msg,item);
    }


}
