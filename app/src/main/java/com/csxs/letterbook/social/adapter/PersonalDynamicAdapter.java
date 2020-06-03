package com.csxs.letterbook.social.adapter;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.core.utils.gallery.GlideEngine;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.PersonalDynamicE;
import com.csxs.letterbook.widgets.imagewatcher.MessagePicturesLayout;
import com.csxs.viewlib.CircleImageView;

/**
 * @author: yeliu
 * created on 2020/6/2
 * description:
 */
public class PersonalDynamicAdapter extends BaseQuickAdapter<PersonalDynamicE, BaseViewHolder> {

    Context context;

    public PersonalDynamicAdapter(Context context) {
        super(R.layout.item_personal_dynamic);
        this.context = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PersonalDynamicE item) {
        CircleImageView civ = helper.getView(R.id.iv_head);

        if(TextUtils.isEmpty(item.getHeadImg())){
            civ.setImageResource(R.drawable.ic_default_circle_store_header);
        }else {
            GlideEngine.createGlideEngine().loadImage(context, item.getHeadImg(), civ);
        }

        helper.setImageResource(R.id.iv_check,item.isCheck()?R.drawable.ic_user_authentication:R.drawable.ic_user_non_authentication);

        helper.setText(R.id.tv_name, item.getNickName());
        helper.setText(R.id.tv_age, item.getAge() + "");
        helper.setText(R.id.tv_location, item.getLocation());

        if (TextUtils.isEmpty(item.getContent())) {
            helper.setGone(R.id.tv_content, false);
        } else {
            helper.setGone(R.id.tv_content, true);
            helper.setText(R.id.tv_content, item.getContent());
        }

        MessagePicturesLayout picturesLayout = helper.getView(R.id.iv_message_pictures);
        if (item.getImgs() == null || item.getImgs().size() < 1) {
            helper.setGone(R.id.iv_message_pictures, false);
        } else {
            helper.setGone(R.id.iv_message_pictures, true);
            picturesLayout.set(item.getImgs(), item.getImgs());
        }

        helper.setText(R.id.tv_time,mContext.getString(R.string.str_dynamic_time_placeholder, item.getTime(), item.getRange()));

        helper.addOnClickListener(R.id.iv_head);
        helper.addOnClickListener(R.id.iv_more);
        helper.addOnClickListener(R.id.ll_share);
        helper.addOnClickListener(R.id.ll_common);
        helper.addOnClickListener(R.id.ll_zan);

    }
}
