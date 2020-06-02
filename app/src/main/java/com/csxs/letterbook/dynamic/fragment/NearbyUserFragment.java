package com.csxs.letterbook.dynamic.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.csxs.core.BaseConstants;
import com.csxs.core.base.LazyDiffFragment;
import com.csxs.core.utils.MmkvUtlis;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.core.utils.TimeStampUtils;
import com.csxs.letterbook.R;
import com.csxs.letterbook.dynamic.adapter.NearbyUserAdapter;
import com.csxs.letterbook.dynamic.mvp.DynamicPresenter;
import com.csxs.letterbook.dynamic.mvp.contract.DynamicContract;
import com.csxs.letterbook.entity.DynamicPictureE;
import com.csxs.letterbook.entity.DynamicRichText;
import com.csxs.letterbook.entity.MarchantCommentE;
import com.csxs.letterbook.entity.NearbyUserDynamicE;
import com.csxs.letterbook.event.ModifyUserInfoEvent;
import com.csxs.letterbook.event.UpdateIsPraiseEvent;
import com.csxs.letterbook.seller.activity.SellerDynamicCommentActivity;
import com.csxs.letterbook.seller.activity.SellerHomeActivity;
import com.csxs.letterbook.social.activity.PersonalHomeActivity;
import com.csxs.letterbook.widgets.imagewatcher.ImageWatcherHelper;
import com.csxs.letterbook.widgets.imagewatcher.MessagePicturesLayout;
import com.csxs.letterbook.widgets.refreshlayout.RefreshDataListener;
import com.csxs.letterbook.widgets.refreshlayout.RefreshPageData;
import com.csxs.viewlib.DividerItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author: yeliu
 * created on 2020/4/24
 * description:
 */
public class NearbyUserFragment extends LazyDiffFragment<DynamicPresenter> implements DynamicContract.IDynamicView, MessagePicturesLayout.Callback, RefreshDataListener {


    @BindView(R.id.rv_nearby)
    RecyclerView rv_nearby;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private NearbyUserAdapter nearbyUserAdapter;

    private ImageWatcherHelper iwHelper;

    private RefreshPageData<NearbyUserDynamicE> refreshPageData;

    @Inject
    MmkvUtlis mmkvUtlis;

    private double latitude;
    private double longitude;

    private int position;

    private NearbyUserDynamicE nearbyUserDynamicE;

    @Override
    public void initParam() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nearby_user;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        nearbyUserAdapter = new NearbyUserAdapter(mContext, null).setPictureClickCallback(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL, ScreenUtil.dip2px(mContext, 10f), Color.parseColor("#E30212"));

        dividerItemDecoration.setRightMargin(ScreenUtil.dip2px(mContext, 15));

        dividerItemDecoration.setLeftMargin(ScreenUtil.dip2px(mContext, 15));

        rv_nearby.addItemDecoration(dividerItemDecoration);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        rv_nearby.setLayoutManager(linearLayoutManager);

        rv_nearby.setAdapter(nearbyUserAdapter);

        rv_nearby.setHasFixedSize(true);

        nearbyUserAdapter.openLoadAnimation();

        refreshPageData = new RefreshPageData<>(rv_nearby, nearbyUserAdapter, smartRefreshLayout, this);

        nearbyUserAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NearbyUserDynamicE marchantComment = (NearbyUserDynamicE) adapter.getData().get(position);
                if (marchantComment != null && marchantComment.getId() != 0) {
                    Intent intent = new Intent();
                    intent.putExtra("type",1);
                    intent.putExtra("commentId", marchantComment.getId());
                    intent.putExtra("latitude", marchantComment.getLatitude());
                    intent.putExtra("longitude", marchantComment.getLongitude());
                    SellerDynamicCommentActivity.start(mContext, SellerDynamicCommentActivity.class, intent);
                }
            }
        });

        nearbyUserAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NearbyUserFragment.this.position = position;
                NearbyUserFragment.this.nearbyUserDynamicE = (NearbyUserDynamicE) adapter.getData().get(position);

                if (view.getId() == R.id.tv_nearby_user_dynamic_zan) {
                    //点赞
                    mPresenter.thumbs(nearbyUserDynamicE.getId());
                } else if (view.getId() == R.id.civ_nearby_user_header) {
                    if (nearbyUserDynamicE.getIsMarchant() == 1) {
                        //跳转商家主页
                        Intent intent = new Intent();
                        int shopId = nearbyUserDynamicE.getMarchantId();
                        intent.putExtra("type", 2);
                        intent.putExtra("shopId", shopId);
                        intent.putExtra("latitude", nearbyUserDynamicE.getLatitude());
                        intent.putExtra("longitude", nearbyUserDynamicE.getLongitude());
                        SellerHomeActivity.start(mContext, SellerHomeActivity.class, intent);

                    } else {
                        //跳转普通用户
                        Intent intent = new Intent();
                        int userid = nearbyUserDynamicE.getUserId();
                        intent.putExtra("UserId", userid);
                        PersonalHomeActivity.start(mContext, PersonalHomeActivity.class, intent);
                    }
                } else if (view.getId() == R.id.tv_nearby_user_attention) {
                    int conUserId = nearbyUserDynamicE.getUserId();
                    int userId = mmkvUtlis.getMmkv().getInt("userId", -1);
                    mPresenter.addOrDeleteAttention(BaseConstants.USER_IDENTITY_FLAG, nearbyUserDynamicE.getIsMarchant(), userId, conUserId);

                }
            }
        });


        if (getActivity() instanceof ImageWatcherHelper.Provider) {

            iwHelper = ((ImageWatcherHelper.Provider) getActivity()).iwHelper();

        }

    }

    @Override
    protected void lazyInitData(boolean isFirstVisible) {
        if (isFirstVisible) {

            latitude = Double.parseDouble(mmkvUtlis.getMmkv().getString("latitude", "0.00"));

            longitude = Double.parseDouble(mmkvUtlis.getMmkv().getString("longitude", "0.00"));

            if (latitude != 0.00 && longitude != 0.00) {
                requestNetData();
            } else {
                //定位
            }

        }

    }


    public void requestNetData() {
        mPresenter.queryNearbyUserDynamic(5, latitude, longitude, refreshPageData.getPageCurrent(), refreshPageData.getPageSize());
    }

    @Override
    protected void fragmentHidden() {

    }

    @Override
    public void onRefresh() {
        requestNetData();
    }

    @Override
    public void onLoadMore() {
        requestNetData();
    }


    public static Fragment newInstance(int status) {
        Bundle args = new Bundle();
        args.putInt("status", status);
        NearbyUserFragment fragment = new NearbyUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onThumbPictureClick(ImageView i, SparseArray<ImageView> imageGroupList, List<Uri> urlList) {
        iwHelper.show(i, imageGroupList, urlList);
    }

    @Override
    public void nearbyUserDynamicSuccess(List<NearbyUserDynamicE> dynamics) {
        if (dynamics != null && dynamics.size() > 0) {
            //动态数据 整合
            ArrayList<Uri> list;
            int dynamicSize = dynamics.size();
            for (int i = 0; i < dynamicSize; i++) {

                if (dynamics.get(i).getIsMarchant() != 1) {
                    if (dynamics.get(i).getUserInfo() != null && dynamics.get(i).getUserInfo().getBirthDay() != null) {

                        int age = TimeStampUtils.getAgeByBirth(TimeStampUtils.stringChangeToDate(dynamics.get(i).getAddTime(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
                        dynamics.get(i).setAge(age);
                    }
                }

                if (dynamics.get(i).getContent() != null && !"".equals(dynamics.get(i).getContent())) {
                    dynamics.get(i).setDynamicRichText(new DynamicRichText(dynamics.get(i).getContent()));
                }

                long time = TimeStampUtils.stringTolong(dynamics.get(i).getAddTime(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ");

                String time1 = TimeStampUtils.getShortTime(time);

                dynamics.get(i).setAddTime(time1);

                double distance = Double.parseDouble(dynamics.get(i).getDistance());

                if (distance > 1f) {
                    String result = String.format(Locale.CHINA, "%.1f", distance);
                    dynamics.get(i).setDistanceSpace(result + "km");
                } else {
                    String result = String.format(Locale.CHINA, "%.1f", distance * 1000);
                    dynamics.get(i).setDistanceSpace(result + "m");
                }

                if (dynamics.get(i).getImgList() != null && dynamics.get(i).getImgList().size() > 0) {
                    list = new ArrayList<Uri>();

                    for (DynamicPictureE url : dynamics.get(i).getImgList()) {
                        list.add(Uri.parse(url.getHttpAddress()));
                    }

                    dynamics.get(i).setImageUrl(list);

                    dynamics.get(i).setItemType(NearbyUserDynamicE.NEARBY_DYNAMIC_IMAGE);
                } else {
                    dynamics.get(i).setItemType(NearbyUserDynamicE.NEARBY_DYNAMIC_TEXT);
                }
            }

            refreshPageData.setRefreshData(dynamics);

        } else {
            showErrorDataStatus(BaseConstants.STATE_LAYOUT_EMPTY);
        }


    }

    @Override
    public void nearbyUserDynamicFailure(int code, String msg) {

    }

    @Override
    public void thumbsDynamicSuccess() {
        if (nearbyUserDynamicE != null) {
            if (nearbyUserDynamicE.getIsPraise() == 1) {
                nearbyUserDynamicE.setIsPraise(0);
                if (nearbyUserDynamicE.getPraiseCount() != 0) {
                    nearbyUserDynamicE.setPraiseCount(nearbyUserDynamicE.getPraiseCount() - 1);
                }
            } else if (nearbyUserDynamicE.getIsPraise() == 0) {
                nearbyUserDynamicE.setIsPraise(1);
                if (nearbyUserDynamicE.getPraiseCount() < 0) {
                    nearbyUserDynamicE.setPraiseCount(1);
                } else {
                    nearbyUserDynamicE.setPraiseCount(nearbyUserDynamicE.getPraiseCount() + 1);
                }
            } else {
                nearbyUserDynamicE.setIsPraise(0);
            }
        }

        nearbyUserAdapter.notifyItemChanged(position, 12);
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void UpdateIsPraiseEvent(UpdateIsPraiseEvent messageEvent) {
//
//
//
//    }

    @Override
    public void thumbsDynamicFailure(int code, String msg) {
        Toast.makeText(mContext, "点赞失败:" + msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 关注与取消关注 成功
     */
    @Override
    public void addOrDeleteAttentionSuccess() {
        if (nearbyUserDynamicE.getIscon() == 1) {
            nearbyUserDynamicE.setIscon(0);
        } else if (nearbyUserDynamicE.getIscon() == 0) {
            nearbyUserDynamicE.setIscon(1);
        }

        nearbyUserAdapter.notifyItemChanged(position, 13);

    }

    /**
     * 关注与取消关注 失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void addOrDeleteAttentionFailure(int code, String msg) {

    }


}
