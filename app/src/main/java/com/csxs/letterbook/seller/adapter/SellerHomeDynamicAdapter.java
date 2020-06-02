package com.csxs.letterbook.seller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.BitmapUtils;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.MarchantCommentE;
import com.csxs.letterbook.widgets.imagewatcher.MessagePicturesLayout;
import com.csxs.viewlib.CircleImageView;
import com.csxs.viewlib.dynamictext.DynamicTextView;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/11
 * description:商家主页动态
 */
public class SellerHomeDynamicAdapter extends BaseMultiItemQuickAdapter<MarchantCommentE, BaseViewHolder> {

    private MessagePicturesLayout.Callback mCallback;

    private Context mContext;

    private final Drawable dynamicSexMan;
    private final Drawable dynamicSexWoman;

    public SellerHomeDynamicAdapter(Context context, List<MarchantCommentE> data) {
        super(data);

        addItemType(MarchantCommentE.SELLER_HOME_DYNAMIC_TEXT, R.layout.item_seller_home_dynamic_text);

        addItemType(MarchantCommentE.SELLER_HOME_DYNAMIC_IMAGE, R.layout.item_seller_home_dynamic_image);

        mContext = context;

        dynamicSexMan = mContext.getResources().getDrawable(R.drawable.ic_dynamic_sex_man);
        dynamicSexMan.setBounds(0, 0, dynamicSexMan.getMinimumWidth(), dynamicSexMan.getMinimumHeight());

        dynamicSexWoman = mContext.getResources().getDrawable(R.drawable.ic_dynamic_sex_woman);
        dynamicSexWoman.setBounds(0, 0, dynamicSexWoman.getMinimumWidth(), dynamicSexWoman.getMinimumHeight());
    }


    public SellerHomeDynamicAdapter setPictureClickCallback(MessagePicturesLayout.Callback callback) {
        mCallback = callback;
        return this;
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, MarchantCommentE item) {
//        int  getLayoutPosition= helper.getLayoutPosition();
//        int  AdapterPosition=  helper.getAdapterPosition();
        if (MarchantCommentE.SELLER_HOME_DYNAMIC_TEXT == helper.getItemViewType()) {

            TextView nickName = helper.getView(R.id.tv_seller_home_user_nickname_no);

            ImageView sellerMarchant = helper.getView(R.id.tv_seller_home_user_identity_no);

            DynamicTextView textView = helper.getView(R.id.tv_seller_home_user_content_text_no);

            CircleImageView civ = helper.getView(R.id.civ_seller_home_user_header_no);

            TextView address = helper.getView(R.id.tv_seller_home_user_address_no);

            TextView sexTag = helper.getView(R.id.tv_seller_home_user_sex_tag_no);

            TextView dynamicZan = helper.getView(R.id.tv_seller_home_user_dynamic_zan_no);

            TextView dynamicCommentNum = helper.getView(R.id.tv_nearby_user_dynamic_comment_no);

            TextView dynamicSendTime = helper.getView(R.id.tv_seller_home_user_send_time_no);

            fillDynamicData(item, nickName, sellerMarchant, civ, sexTag, address, textView, null, dynamicSendTime, dynamicZan, dynamicCommentNum);

        } else if (MarchantCommentE.SELLER_HOME_DYNAMIC_IMAGE == helper.getItemViewType()) {

            TextView nickName = helper.getView(R.id.tv_seller_home_user_nickname);

            CircleImageView civ = helper.getView(R.id.civ_seller_home_user_header);

            TextView sexTag = helper.getView(R.id.tv_seller_home_user_sex_tag);

            TextView address = helper.getView(R.id.tv_seller_home_user_address);

            ImageView sellerMarchant = helper.getView(R.id.tv_seller_home_user_identity);

            DynamicTextView textView = helper.getView(R.id.tv_seller_home_user_content_text);

            MessagePicturesLayout messagePicturesLayout = helper.getView(R.id.iv_message_pictures);

            TextView dynamicSendTime = helper.getView(R.id.tv_seller_home_user_send_time);

            TextView dynamicZan = helper.getView(R.id.tv_seller_home_user_dynamic_zan);

            TextView dynamicCommentNum = helper.getView(R.id.tv_seller_home_user_dynamic_comment);

            fillDynamicData(item, nickName, sellerMarchant, civ, sexTag, address, textView, messagePicturesLayout, dynamicSendTime, dynamicZan, dynamicCommentNum);

        }
    }


    public void fillDynamicData(MarchantCommentE item, TextView nickName, ImageView sellerMarchant, CircleImageView civ, TextView sexTag, TextView address,
                                DynamicTextView textView, MessagePicturesLayout picturesLayout,
                                TextView dynamicSendTime, TextView dynamicZan, TextView dynamicCommentNum) {


        if (item.getUserinfo() != null) {
            nickName.setText(mContext.getString(R.string.str_placeholder, item.getUserinfo().getNickname() != null ? item.getUserinfo().getNickname() : ""));

            //是否是商家
            if (item.getIsMarchant() == 1) {
                //头像
                Bitmap header = BitmapUtils.stringToBitmap(item.getUserinfo().getLogoPic());
                if (header != null) {
                    civ.setImageBitmap(header);
                } else {
                    civ.setImageResource(R.drawable.ic_default_circle_store_header);
                }
                sellerMarchant.setVisibility(View.VISIBLE);

                sexTag.setVisibility(View.GONE);
            } else {
                ImageLoaderV4.getInstance().displayImage(mContext, item.getUserinfo().getHeadimgurl(), civ, R.drawable.ic_default_circle_store_header);

                sellerMarchant.setVisibility(View.GONE);

                sexTag.setVisibility(View.VISIBLE);

                if (item.getUserinfo().getSex() != null && item.getUserinfo().getSex().length() > 0) {

                    sexTag.setText(item.getUserinfo().getBirthDay());

                    if (item.getUserinfo().getSex().equals("1")) {

                        sexTag.setCompoundDrawables(dynamicSexMan, null, null, null);

                        sexTag.setBackgroundResource(R.drawable.bg_dynamic_sex_age_man);

                    } else if (item.getUserinfo().getSex().equals("2")) {

                        sexTag.setCompoundDrawables(dynamicSexWoman, null, null, null);

                        sexTag.setBackgroundResource(R.drawable.bg_dynamic_sex_age_woman);
                    }
                }
            }

            //地址
            address.setText(mContext.getString(R.string.str_placeholder, item.getAddress()));

            //性别 年龄
            // sexTag.setText();

            //文本内容动态
            textView.bind(item.getDynamicRichText());
            textView.setContent(item.getDynamicRichText().getContent());

            //设置九宫格图片
            if (picturesLayout != null) {
//                if(item.getImageUrl().size()==4){
//                    picturesLayout.setLayoutParams(new RelativeLayout.LayoutParams());
//                }
                picturesLayout.set(item.getImageUrl(), item.getImageUrl());
                picturesLayout.setCallback(mCallback);
            }


            //时间 距离
            dynamicSendTime.setText((mContext.getString(R.string.str_dynamic_time_placeholder, item.getAddTime(), item.getDistanceSpace())));

            //点赞
            dynamicZan.setText(mContext.getString(R.string.int_placeholder, item.getPraiseCount()));

            //回复
            dynamicCommentNum.setText(mContext.getString(R.string.int_placeholder, item.getReplyCount()));
        }


    }
}
