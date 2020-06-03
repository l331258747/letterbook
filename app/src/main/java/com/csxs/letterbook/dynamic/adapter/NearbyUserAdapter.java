package com.csxs.letterbook.dynamic.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.BitmapUtils;
import com.csxs.core.utils.SpannableStringUtils;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.NearbyUserDynamicE;
import com.csxs.letterbook.widgets.imagewatcher.MessagePicturesLayout;
import com.csxs.viewlib.CircleImageView;
import com.csxs.viewlib.dynamictext.DynamicTextView;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/4/24
 * description:附近的人
 */
public class NearbyUserAdapter extends BaseMultiItemQuickAdapter<NearbyUserDynamicE, BaseViewHolder> {

    private MessagePicturesLayout.Callback mCallback;

    private final Drawable dynamicSexMan;

    private final Drawable dynamicSexWoman;

    private final Drawable dynamicZanNormal;

    private final Drawable dynamicLike;


    private Context mContext;


    public NearbyUserAdapter(Context mContext, List<NearbyUserDynamicE> data) {
        super(data);

        this.mContext = mContext;

        addItemType(NearbyUserDynamicE.NEARBY_DYNAMIC_TEXT, R.layout.item_nearby_text);
        addItemType(NearbyUserDynamicE.NEARBY_DYNAMIC_IMAGE, R.layout.item_nearby_image);

        dynamicSexMan = mContext.getResources().getDrawable(R.drawable.ic_dynamic_sex_man);
        dynamicSexMan.setBounds(0, 0, dynamicSexMan.getMinimumWidth(), dynamicSexMan.getMinimumHeight());

        dynamicSexWoman = mContext.getResources().getDrawable(R.drawable.ic_dynamic_sex_woman);
        dynamicSexWoman.setBounds(0, 0, dynamicSexWoman.getMinimumWidth(), dynamicSexWoman.getMinimumHeight());

        dynamicZanNormal = mContext.getResources().getDrawable(R.drawable.ic_dynamic_zan_normal);
        dynamicZanNormal.setBounds(0, 0, dynamicZanNormal.getMinimumWidth(), dynamicZanNormal.getMinimumHeight());

        dynamicLike = mContext.getResources().getDrawable(R.drawable.ic_zan_dynamic);
        dynamicLike.setBounds(0, 0, dynamicLike.getMinimumWidth(), dynamicLike.getMinimumHeight());


    }


    public NearbyUserAdapter setPictureClickCallback(MessagePicturesLayout.Callback callback) {
        mCallback = callback;
        return this;
    }


    @Override
    protected void convertPayloads(@NonNull BaseViewHolder helper, NearbyUserDynamicE item, @NonNull List<Object> payloads) {
        super.convertPayloads(helper, item, payloads);

        for (int i = 0; i < payloads.size(); i++) {
            if ((int) payloads.get(i) == 12) {
                TextView dynamicZan = helper.getView(R.id.tv_nearby_user_dynamic_zan);
                updateThumbs(dynamicZan, item);
            } else if ((int) payloads.get(i) == 13) {
                TextView attention = helper.getView(R.id.tv_nearby_user_attention);
                updateAttention(attention, item);
            }
        }


    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, NearbyUserDynamicE item) {

        //
        TextView dynamicZan = helper.getView(R.id.tv_nearby_user_dynamic_zan);
        helper.addOnClickListener(R.id.tv_nearby_user_dynamic_zan);

        TextView nickName = helper.getView(R.id.tv_nearby_user_nickname);


        CircleImageView civ = helper.getView(R.id.civ_nearby_user_header);
        helper.addOnClickListener(R.id.civ_nearby_user_header);

        TextView attention = helper.getView(R.id.tv_nearby_user_attention);
        helper.addOnClickListener(R.id.tv_nearby_user_attention);


        if (NearbyUserDynamicE.NEARBY_DYNAMIC_TEXT == helper.getItemViewType()) {

            ImageView sellerMarchant = helper.getView(R.id.tv_nearby_user_identity_no);

            DynamicTextView textView = helper.getView(R.id.tv_nearby_user_content_text_no);

            ImageView vip = helper.getView(R.id.iv_user_vip_no);

            TextView address = helper.getView(R.id.tv_nearby_user_address_no);

            TextView sexTag = helper.getView(R.id.tv_nearby_user_sex_tag_no);


            TextView dynamicCommentNum = helper.getView(R.id.tv_nearby_user_dynamic_comment_no);

            TextView dynamicSendTime = helper.getView(R.id.tv_nearby_user_send_time_no);


            fillDynamicData(item, nickName, sellerMarchant, civ, sexTag, address, textView, null, dynamicSendTime, dynamicZan, dynamicCommentNum, vip, attention);

        } else if (NearbyUserDynamicE.NEARBY_DYNAMIC_IMAGE == helper.getItemViewType()) {


            ImageView vip = helper.getView(R.id.iv_user_vip);

            TextView sexTag = helper.getView(R.id.tv_nearby_user_sex_tag);

            TextView address = helper.getView(R.id.tv_nearby_user_address);

            ImageView sellerMarchant = helper.getView(R.id.tv_nearby_user_identity);

            DynamicTextView textView = helper.getView(R.id.tv_nearby_user_content_text);

            MessagePicturesLayout messagePicturesLayout = helper.getView(R.id.iv_message_pictures);

            TextView dynamicSendTime = helper.getView(R.id.tv_nearby_user_send_time);

            TextView dynamicCommentNum = helper.getView(R.id.tv_nearby_user_dynamic_comment);

            fillDynamicData(item, nickName, sellerMarchant, civ, sexTag, address, textView, messagePicturesLayout, dynamicSendTime, dynamicZan, dynamicCommentNum, vip, attention);
        }
    }

    public void fillDynamicData(NearbyUserDynamicE item, TextView nickName, ImageView sellerMarchant, CircleImageView civ, TextView sexTag, TextView address,
                                DynamicTextView textView, MessagePicturesLayout picturesLayout,
                                TextView dynamicSendTime, TextView dynamicZan, TextView dynamicCommentNum, ImageView ivVip, TextView tvAttention) {


        if (item.getUserInfo() != null) {
            nickName.setText(mContext.getString(R.string.str_placeholder, item.getUserInfo().getNickname() != null ? item.getUserInfo().getNickname() : String.valueOf(item.getUserId())));

            //是否是商家
            if (item.getIsMarchant() == 1) {

                //头像
                Bitmap header = BitmapUtils.stringToBitmap(item.getUserInfo().getLogoPic());
                if (header != null) {
                    civ.setImageBitmap(header);
                } else {
                    civ.setImageResource(R.drawable.ic_default_circle_store_header);
                }
                sellerMarchant.setVisibility(View.VISIBLE);

                sellerMarchant.setImageResource(R.drawable.ic_seller_store);


                sexTag.setVisibility(View.GONE);
            } else {
                if (item.getUserInfo().getHeadimgurl() != null && !"".equals(item.getUserInfo().getHeadimgurl())) {
                    ImageLoaderV4.getInstance().displayImage(mContext, item.getUserInfo().getHeadimgurl(), civ, R.drawable.ic_default_circle_store_header);
                } else {
                    civ.setImageResource(R.drawable.ic_default_circle_store_header);
                }

                if (item.getUserInfo().getGradeId() != 0 && item.getUserInfo().getGradeName() != null) {
                    ivVip.setVisibility(View.VISIBLE);
                } else {
                    ivVip.setVisibility(View.GONE);

                }

                if (item.getStatus() == 0) {
                    sellerMarchant.setImageResource(R.drawable.ic_user_non_authentication);
                } else {
                    sellerMarchant.setImageResource(R.drawable.ic_user_authentication);

                }


                if (item.getUserInfo().getSex() != null && item.getUserInfo().getSex().length() > 0) {
                    sexTag.setVisibility(View.VISIBLE);
                    sexTag.setText(mContext.getString(R.string.int_placeholder, item.getAge()));

                    if (item.getUserInfo().getSex().equals("1")) {

                        sexTag.setCompoundDrawables(dynamicSexMan, null, null, null);

                        sexTag.setBackgroundResource(R.drawable.bg_dynamic_sex_age_man);

                    } else if (item.getUserInfo().getSex().equals("2")) {

                        sexTag.setCompoundDrawables(dynamicSexWoman, null, null, null);

                        sexTag.setBackgroundResource(R.drawable.bg_dynamic_sex_age_woman);
                    }
                } else {
                    sexTag.setVisibility(View.GONE);
                }
            }

            //关注
            updateAttention( tvAttention, item);
            //点赞
            updateThumbs(dynamicZan, item);

            //地址
            address.setText(mContext.getString(R.string.str_placeholder, item.getAddress()));

            //性别 年龄
            // sexTag.setText();

//            //文本内容动态
//            if (item.getContent() != null && !"".equals(item.getContent())) {
//                textView.setVisibility(View.VISIBLE);
//                textView.bind(item.getDynamicRichText());
//                textView.setContent(item.getDynamicRichText().getContent());
//            } else {
//                textView.setVisibility(View.GONE);
//            }
//
//            //设置九宫格图片
//            if (picturesLayout != null) {
////                if(item.getImageUrl().size()==4){
////                    picturesLayout.setLayoutParams(new RelativeLayout.LayoutParams());
////                }
//                picturesLayout.set(item.getImageUrl(), item.getImageUrl());
//                picturesLayout.setCallback(mCallback);
//            }
//
//
//            //时间 距离
//            dynamicSendTime.setText((mContext.getString(R.string.str_dynamic_time_placeholder, item.getAddTime(), item.getDistanceSpace())));
//
//
//            //回复
//            dynamicCommentNum.setText(mContext.getString(R.string.int_placeholder, item.getReplyCount()));
        }

        //文本内容动态
        if (item.getContent() != null && !"".equals(item.getContent())) {
            textView.setVisibility(View.VISIBLE);
            textView.bind(item.getDynamicRichText());
            textView.setContent(item.getDynamicRichText().getContent());
        } else {
            textView.setVisibility(View.GONE);
        }

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


        //回复
        dynamicCommentNum.setText(mContext.getString(R.string.int_placeholder, item.getReplyCount()));
    }

    private void updateThumbs(TextView dynamicZan, NearbyUserDynamicE item) {

        if (item.getIsPraise() == 1) {
            dynamicZan.setCompoundDrawables(dynamicLike, null, null, null);
        } else {
            dynamicZan.setCompoundDrawables(dynamicZanNormal, null, null, null);
        }

        //点赞
        dynamicZan.setText(mContext.getString(R.string.int_placeholder, item.getPraiseCount()));
    }


    private void updateAttention(TextView attention, NearbyUserDynamicE item) {

        if (item.getIscon() == 0) {
            attention.setBackgroundResource(R.drawable.bg_button_radius_black);
            attention.setTextColor(mContext.getResources().getColor(R.color.white));
            attention.setText("关注");
        } else {
            attention.setBackgroundResource(R.drawable.bg_text_attention_radius);
            attention.setTextColor(mContext.getResources().getColor(R.color.black_999999));
            attention.setText("已关注");
        }
    }


}
