package com.csxs.letterbook.seller.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.csxs.core.base.BaseDiffActivity;
import com.csxs.core.utils.MmkvUtlis;
import com.csxs.core.utils.TimeStampUtils;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.CommentReplyE;
import com.csxs.letterbook.entity.DynamicPictureE;
import com.csxs.letterbook.entity.DynamicRichText;
import com.csxs.letterbook.entity.MarchantCommentE;
import com.csxs.letterbook.entity.MarchantCommentReplyE;
import com.csxs.letterbook.entity.ReplytoreplyE;
import com.csxs.letterbook.event.UpdateIsPraiseEvent;
import com.csxs.letterbook.seller.adapter.DynamicCommentAdapter;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;
import com.csxs.letterbook.seller.mvp.presenter.SellerDynamicCommentPresenter;
import com.csxs.letterbook.widgets.imagewatcher.ImageWatcherUtils;
import com.csxs.letterbook.widgets.imagewatcher.MessagePicturesLayout;
import com.csxs.letterbook.widgets.reply.CommentDialogFragment;
import com.csxs.letterbook.widgets.reply.DialogFragmentDataCallback;
import com.csxs.letterbook.widgets.reply.InputTextMsgDialog;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author: yeliu
 * created on 2020/5/12
 * description:商家主页动态的评论activity
 * update：所有的动态的详情 直接跳着此Activity
 */
public class SellerDynamicCommentActivity extends BaseDiffActivity<SellerDynamicCommentPresenter> implements SellerStoreContract.ISellerStoreDynamicCommentView,
        MessagePicturesLayout.Callback, DialogFragmentDataCallback {


    @BindView(R.id.rc_seller_dynamic_comment)
    RecyclerView rcDynamicComment;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.layout_comment_bottom)
    RelativeLayout layoutCommentBottom;

    private ImageWatcherUtils imageWatcherUtils;

    private DynamicCommentAdapter dynamicCommentAdapter;

    private CommentDialogFragment commentDialogFragment;
    private ReplytoreplyE replytoreplyE;
    private int parentId;//动态评论Id
    private int id;//评论的评论id
    private int commentId;
    private double latitude;
    private double longitude;
    private CommentReplyE commentReplyE;

    private int position;

    @Inject
    MmkvUtlis mmkvUtlis;
    private int userId;
    private int type;

    @Override
    public void initParam() {
        super.initParam();
        topBarView = false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_seller_dynamic_comment;
    }


    @Override
    public void initStatusBar() {
        ImmersionBar.with(this).statusBarDarkFont(false).statusBarColor(R.color.black).fitsSystemWindows(true).init();
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        setCenterMainTitle("动态", R.color.white);
        setTitleBarBackgroundColor(R.color.black);
        setLeftImage(R.drawable.ic_back_white);
        dynamicCommentAdapter = new DynamicCommentAdapter(this, null).setPictureClickCallback(this);
        rcDynamicComment.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rcDynamicComment.setAdapter(dynamicCommentAdapter);
        dynamicCommentAdapter.openLoadAnimation();

        layoutCommentBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentDialog(null, 0);
            }
        });


        dynamicCommentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommentReplyE commentReplyE = (CommentReplyE) adapter.getData().get(position);
                int type = adapter.getItemViewType(position);
                if (type == 0 || type == 1) {
//                    if(userId!=-1&&userId!=commentReplyE.getUserId()){
//                        id = commentReplyE.getId();
//                        commentDialog(null,0);
//                    }else{
//                        Toast.makeText(mContext,"不能自己回复自己",Toast.LENGTH_SHORT).show();
//                    }

                    id = commentReplyE.getId();
                    commentDialog(null, 0);

                }
            }
        });

        dynamicCommentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                commentReplyE = (CommentReplyE) adapter.getData().get(position);
                SellerDynamicCommentActivity.this.position = position;
                if (view.getId() == R.id.tv_seller_home_user_dynamic_zan) {
                    mPresenter.thumbs(commentReplyE.getComment().getId());
                }
            }
        });


        dynamicCommentAdapter.setListViewItemListener(new DynamicCommentAdapter.CommentListViewItemListener() {
            @Override
            public void onItemClickListener(ReplytoreplyE bean) {
                if (userId != -1 && userId != bean.getUserId()) {
                    id = bean.getId();
                    commentDialog(null, 0);
                } else {
                    Toast.makeText(mContext, "不能自己回复自己", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void commentDialog(String userName, int id) {
        if (commentDialogFragment == null) {
            commentDialogFragment = new CommentDialogFragment();
        }

        if (userName != null) {
            Bundle bundle = new Bundle();
            bundle.putString("nickname", userName);
            bundle.putInt("id", id);
            commentDialogFragment.setArguments(bundle);
        } else {

            commentDialogFragment.setArguments(null);
        }


//        getSupportFragmentManager().executePendingTransactions();
//        if (!commentDialogFragment.isAdded()) {
//            commentDialogFragment.show(getSupportFragmentManager(), "CommentDialogFragment");
//        }


        if (!commentDialogFragment.isAdded() && !commentDialogFragment.isVisible() && !commentDialogFragment.isRemoving()) {
            commentDialogFragment.show(getSupportFragmentManager(), "CommentDialogFragment");
        }


    }

    @Override
    protected void onInitData() {
        userId = mmkvUtlis.getMmkv().getInt("userId", -1);

        type = getIntent().getIntExtra("type",-1);

        commentId = getIntent().getIntExtra("commentId", -1);

        latitude = getIntent().getDoubleExtra("latitude", -1);

        longitude = getIntent().getDoubleExtra("longitude", -1);

        if (mPresenter != null) {
            if (commentId != -1) {
                mPresenter.querySellerHomeDynamicDetails(commentId, latitude, longitude);
            }
        }

        imageWatcherUtils = new ImageWatcherUtils(this);
        imageWatcherUtils.init();
    }

    @Override
    public void sellerHomeDynamicDetailsSuccess(MarchantCommentReplyE marchantCommentReply) {

        List<CommentReplyE> list = marchantCommentReply.getCommentReply();
        CommentReplyE commentReplyE = new CommentReplyE();
        if (marchantCommentReply.getComment().getId() != 0) {
            MarchantCommentE marchantCommentE = marchantCommentReply.getComment();
            commentId = marchantCommentReply.getComment().getId();
            marchantCommentE.setDynamicRichText(new DynamicRichText(marchantCommentE.getContent()));

            long time = TimeStampUtils.stringTolong(marchantCommentE.getAddTime(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ");

            String time1 = TimeStampUtils.getShortTime(time);

            marchantCommentE.setAddTime(time1);

            if (marchantCommentE.getDistance() > 1f) {
                String result = String.format(Locale.CHINA, "%.1f", marchantCommentE.getDistance());
                marchantCommentE.setDistanceSpace(result + "km");
            } else {
                String result = String.format(Locale.CHINA, "%.1f", marchantCommentE.getDistance() * 1000);
                marchantCommentE.setDistanceSpace(result + "m");
            }

            if (marchantCommentE.getPicture() != null && marchantCommentE.getPicture().size() > 0) {

                if (marchantCommentE.getPicture() != null && marchantCommentE.getPicture().size() > 0) {

                    ArrayList imageUrl = new ArrayList<Uri>();

                    for (DynamicPictureE url : marchantCommentE.getPicture()) {

                        imageUrl.add(Uri.parse(url.getHttpAddress()));
                    }

                    marchantCommentE.setImageUrl(imageUrl);
                }

            }

            commentReplyE.setItemType(1);
            commentReplyE.setComment(marchantCommentE);

            list.add(0, commentReplyE);
            if (list.size() == 1) {
                CommentReplyE empty = new CommentReplyE();
                empty.setItemType(2);
                list.add(empty);
            }
        }


        if (list != null && list.size() > 0) {

            dynamicCommentAdapter.setNewData(list);
        }
    }

    @Override
    public void sellerHomeDynamicDetailsFailure(int code, String msg) {

    }

    @Override
    public void sendDynamicSuccess(String s) {
        if (mPresenter != null) {
            if (commentId != -1) {
                mPresenter.querySellerHomeDynamicDetails(commentId, latitude, longitude);
            }
        }
    }

    @Override
    public void sendDynamicFailure(int code, String msg) {

    }

    @Override
    public void thumbsDynamicSuccess() {

        if (commentReplyE != null) {
            if (commentReplyE.getComment().getIsPraise()== 1) {
                commentReplyE.getComment().setIsPraise(0);
                if (commentReplyE.getComment().getPraiseCount() != 0) {
                    commentReplyE.getComment().setPraiseCount(commentReplyE.getComment().getPraiseCount() - 1);
                }
            } else if (commentReplyE.getComment().getIsPraise() == 0) {
                commentReplyE.getComment().setIsPraise(1);
                if (commentReplyE.getComment().getPraiseCount() < 0) {
                    commentReplyE.getComment().setPraiseCount(1);
                } else {
                    commentReplyE.getComment().setPraiseCount(commentReplyE.getComment().getPraiseCount() + 1);
                }
            } else {
                commentReplyE.getComment().setIsPraise(0);
            }
        }

        dynamicCommentAdapter.notifyItemChanged(position, 12);

        //附近的人动态点赞
//        if(type==1){
//            EventBus.getDefault().post(new UpdateIsPraiseEvent(1));
//        }




    }

    @Override
    public void thumbsDynamicFailure(int code, String msg) {

    }


    @Override
    public void onBackPressed() {
        if (!imageWatcherUtils.getIwHelper().handleBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (imageWatcherUtils != null) {
            imageWatcherUtils.onDestroy();
        }
    }

    @Override
    public void onThumbPictureClick(ImageView i, SparseArray<ImageView> imageGroupList, List<Uri> urlList) {
        if (imageWatcherUtils.getIwHelper() != null) {
            imageWatcherUtils.getIwHelper().show(i, imageGroupList, urlList);
        }
    }

    @Override
    public String getCommentText() {
        return "";
    }

    @Override
    public String getHintText() {
        return "发布评论...";
    }

    @Override
    public void setCommentText(String commentTextTemp) {

    }

    @Override
    public void sendContentText(String content) {
        if (!content.equals("")) {
            if (commentId != 0) {
                mPresenter.sendDynamicComment(commentId, content, 0, id);
            }

        }
    }
}
