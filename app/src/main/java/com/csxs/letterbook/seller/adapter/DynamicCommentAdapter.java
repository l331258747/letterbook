package com.csxs.letterbook.seller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.BitmapUtils;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.CommentReplyE;
import com.csxs.letterbook.entity.MarchantCommentE;
import com.csxs.letterbook.entity.NearbyUserDynamicE;
import com.csxs.letterbook.entity.ReplytoreplyE;
import com.csxs.letterbook.widgets.comment.CommentListView;
import com.csxs.letterbook.widgets.imagewatcher.MessagePicturesLayout;
import com.csxs.viewlib.CircleImageView;
import com.csxs.viewlib.dynamictext.DynamicTextView;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/12
 * description:
 */
public class DynamicCommentAdapter extends BaseMultiItemQuickAdapter<CommentReplyE, BaseViewHolder> {

    private Context context;

    private final Drawable dynamicSexMan;
    private final Drawable dynamicSexWoman;

    private final Drawable dynamicZanNormal;
    private final Drawable dynamicLike;

    private MessagePicturesLayout.Callback mCallback;


    public DynamicCommentAdapter(Context context, List<CommentReplyE> data) {
        super(data);

        addItemType(CommentReplyE.SELLER_DYNAMIC_COMMENT_TOP, R.layout.item_dynamic_comment);

        addItemType(CommentReplyE.SELLER_DYNAMIC_COMMENT_NORMAL, R.layout.item_comment);

        addItemType(CommentReplyE.SELLER_DYNAMIC_COMMENT_EMPTY, R.layout.empty_dynamic_comment);

        mContext = context;

        dynamicSexMan = mContext.getResources().getDrawable(R.drawable.ic_dynamic_sex_man);
        dynamicSexMan.setBounds(0, 0, dynamicSexMan.getMinimumWidth(), dynamicSexMan.getMinimumHeight());

        dynamicSexWoman = mContext.getResources().getDrawable(R.drawable.ic_dynamic_sex_woman);
        dynamicSexWoman.setBounds(0, 0, dynamicSexWoman.getMinimumWidth(), dynamicSexWoman.getMinimumHeight());

        dynamicZanNormal = mContext.getResources().getDrawable(R.drawable.ic_dynamic_zan_normal);
        dynamicZanNormal.setBounds(0, 0, dynamicZanNormal.getMinimumWidth(), dynamicZanNormal.getMinimumHeight());

        dynamicLike = mContext.getResources().getDrawable(R.drawable.ic_zan_dynamic);
        dynamicLike.setBounds(0, 0, dynamicLike.getMinimumWidth(), dynamicLike.getMinimumHeight());

    }

    public DynamicCommentAdapter setPictureClickCallback(MessagePicturesLayout.Callback callback) {
        mCallback = callback;
        return this;
    }

    @Override
    protected void convertPayloads(@NonNull BaseViewHolder helper, CommentReplyE item, @NonNull List<Object> payloads) {
        super.convertPayloads(helper, item, payloads);
        TextView dynamicZan = helper.getView(R.id.tv_seller_home_user_dynamic_zan);
        updateIsPraise(dynamicZan,item);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommentReplyE item) {

        if (CommentReplyE.SELLER_DYNAMIC_COMMENT_TOP == helper.getItemViewType()) {

            TextView nickName = helper.getView(R.id.tv_seller_home_user_nickname);

            CircleImageView civ = helper.getView(R.id.civ_seller_home_user_header);

            TextView sexTag = helper.getView(R.id.tv_seller_home_user_sex_tag);

            TextView address = helper.getView(R.id.tv_seller_home_user_address);

            ImageView sellerMarchant = helper.getView(R.id.tv_seller_home_user_identity);

            DynamicTextView textView = helper.getView(R.id.tv_seller_home_user_content_text);

            MessagePicturesLayout messagePicturesLayout = helper.getView(R.id.iv_message_pictures);

            TextView dynamicSendTime = helper.getView(R.id.tv_seller_home_user_send_time);

            TextView dynamicZan = helper.getView(R.id.tv_seller_home_user_dynamic_zan);

            helper.addOnClickListener(R.id.tv_seller_home_user_dynamic_zan);

            TextView dynamicCommentNum = helper.getView(R.id.tv_seller_home_user_dynamic_comment);

            fillDynamicData(item, nickName, sellerMarchant, civ, sexTag, address, textView, messagePicturesLayout, dynamicSendTime, dynamicZan, dynamicCommentNum);

        } else if (CommentReplyE.SELLER_DYNAMIC_COMMENT_NORMAL == helper.getItemViewType()) {
            if (item != null && item.getUserInfo() != null) {
                CircleImageView civHeader = helper.getView(R.id.civ_comment_header);

                TextView commentNickname = helper.getView(R.id.tv_comment_nickname);

                TextView dynamicComment = helper.getView(R.id.dynamic_comment);

                CommentListView commentList = helper.getView(R.id.commentList);

                LinearLayout layoutCommentlist = helper.getView(R.id.layout_commentlist);


                //是否是商家
                if (item.getIsMarchant() == 1) {
                    //头像
                    Bitmap header = BitmapUtils.stringToBitmap(item.getUserInfo().getLogoPic());
                    if (header != null) {
                        civHeader.setImageBitmap(header);
                    } else {
                        civHeader.setImageResource(R.drawable.ic_default_circle_store_header);
                    }

                } else {

                    if (item.getUserInfo() != null && item.getUserInfo().getHeadimgurl() != null) {
                        ImageLoaderV4.getInstance().displayImage(mContext, item.getUserInfo().getHeadimgurl(), civHeader, R.drawable.ic_default_circle_store_header);
                    } else {
                        civHeader.setImageResource(R.drawable.ic_default_circle_store_header);
                    }


                }

                commentNickname.setText(item.getUserInfo().getNickname());

                dynamicComment.setText(item.getContent());

                if (item.getReplytoreply() != null && item.getReplytoreply().size() > 0) {
                    commentList.setParentId(item.getUserId());
                    commentList.setDatas(item.getReplytoreply());
                    commentList.setOnItemClickListener(onItemClickListener);
                    layoutCommentlist.setVisibility(View.VISIBLE);
                } else {
                    layoutCommentlist.setVisibility(View.GONE);
                }


            }


        } else if (CommentReplyE.SELLER_DYNAMIC_COMMENT_EMPTY == helper.getItemViewType()) {


        }

    }


    public void fillDynamicData(CommentReplyE item, TextView nickName, ImageView sellerMarchant, CircleImageView civ, TextView sexTag, TextView address,
                                DynamicTextView textView, MessagePicturesLayout picturesLayout,
                                TextView dynamicSendTime, TextView dynamicZan, TextView dynamicCommentNum) {

        nickName.setText(mContext.getString(R.string.str_placeholder, item.getComment().getUserinfo().getNickname()));

        //是否是商家
        if (item.getIsMarchant() == 1) {
            //头像
            Bitmap header = BitmapUtils.stringToBitmap(item.getComment().getUserinfo().getLogoPic());
            if (header != null) {
                civ.setImageBitmap(header);
            } else {
                civ.setImageResource(R.drawable.ic_default_circle_store_header);
            }
            sellerMarchant.setVisibility(View.VISIBLE);

            sexTag.setVisibility(View.GONE);
        } else {
            ImageLoaderV4.getInstance().displayImage(mContext, item.getComment().getUserinfo().getHeadimgurl(), civ, R.drawable.ic_default_circle_store_header);

            sellerMarchant.setVisibility(View.GONE);

            sexTag.setVisibility(View.VISIBLE);

            if (item.getComment().getUserinfo().getSex() != null && item.getComment().getUserinfo().getSex().length() > 0) {

                sexTag.setText(item.getComment().getUserinfo().getBirthDay());

                if (item.getComment().getUserinfo().getSex().equals("1")) {

                    sexTag.setCompoundDrawables(dynamicSexMan, null, null, null);

                    sexTag.setBackgroundResource(R.drawable.bg_dynamic_sex_age_man);

                } else if (item.getComment().getUserinfo().getSex().equals("2")) {

                    sexTag.setCompoundDrawables(dynamicSexWoman, null, null, null);

                    sexTag.setBackgroundResource(R.drawable.bg_dynamic_sex_age_woman);
                }
            }
        }

        //地址
        address.setText(mContext.getString(R.string.str_placeholder, item.getComment().getAddress()));

        //性别 年龄
        // sexTag.setText();

        //文本内容动态


        if (item.getContent() != null && !"".equals(item.getContent())) {
            textView.setVisibility(View.VISIBLE);
            textView.bind(item.getComment().getDynamicRichText());
            textView.setContent(item.getComment().getDynamicRichText().getContent());
        }else{
            textView.setVisibility(View.GONE);
        }


        //设置九宫格图片
        if (item.getComment().getImageUrl() != null && item.getComment().getImageUrl().size() > 0) {
            picturesLayout.setVisibility(View.VISIBLE);
            picturesLayout.set(item.getComment().getImageUrl(), item.getComment().getImageUrl());
            picturesLayout.setCallback(mCallback);

        } else {
            picturesLayout.setVisibility(View.GONE);
        }


        //时间 距离
        dynamicSendTime.setText((mContext.getString(R.string.str_dynamic_time_placeholder, item.getComment().getAddTime(), item.getComment().getDistanceSpace())));



        //点赞
        updateIsPraise(dynamicZan,item);


        //回复
        dynamicCommentNum.setText(mContext.getString(R.string.int_placeholder, item.getComment().getReplyCount()));

    }


   private CommentListView.OnItemClickListener onItemClickListener = new CommentListView.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int commentPosition) {
            if(listViewItemListener!=null){
                ReplytoreplyE bean = (ReplytoreplyE) v.getTag();
                listViewItemListener.onItemClickListener(bean);
            }


//            if(presenter != null){
//                CommentConfig config = new CommentConfig();
//                config.circlePosition = circlePosition;
//                config.commentPosition = commentPosition;
//                config.commentType = CommentConfig.Type.REPLY;
//                config.replyUser = commentItem.getUser();
//                presenter.showEditTextBody(config);
//            }


//            ReplytoreplyE commentItem = item.getReplytoreply().get(commentPosition);

//                        if(DatasUtil.curUser.getId().equals(commentItem.getUser().getId())){//复制或者删除自己的评论
//
//                            CommentDialog dialog = new CommentDialog(context, presenter, commentItem, circlePosition);
//                            dialog.show();
//                        }else{//回复别人的评论
//                            if(presenter != null){
//                                CommentConfig config = new CommentConfig();
//                                config.circlePosition = circlePosition;
//                                config.commentPosition = commentPosition;
//                                config.commentType = CommentConfig.Type.REPLY;
//                                config.replyUser = commentItem.getUser();
//                                presenter.showEditTextBody(config);
//                            }
//                        }
        }
    };



    private void updateIsPraise(TextView dynamicZan, CommentReplyE item) {

        if (item.getComment().getIsPraise() == 1) {
            dynamicZan.setCompoundDrawables(dynamicLike, null, null, null);
        } else {
            dynamicZan.setCompoundDrawables(dynamicZanNormal, null, null, null);
        }

        //点赞
        dynamicZan.setText(mContext.getString(R.string.int_placeholder, item.getComment().getPraiseCount()));
    }


    public CommentListViewItemListener listViewItemListener;

    public interface CommentListViewItemListener {
        void onItemClickListener(ReplytoreplyE bean);
    }

    public CommentListViewItemListener getListViewItemListener() {
        return listViewItemListener;
    }

    public void setListViewItemListener(CommentListViewItemListener listViewItemListener) {
        this.listViewItemListener = listViewItemListener;
    }
}
