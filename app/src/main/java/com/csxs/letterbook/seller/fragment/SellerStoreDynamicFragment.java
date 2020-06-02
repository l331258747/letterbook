package com.csxs.letterbook.seller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.csxs.core.BaseConstants;
import com.csxs.core.base.LazyDiffFragment;
import com.csxs.core.utils.TimeStampUtils;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.DynamicPictureE;
import com.csxs.letterbook.entity.DynamicRichText;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.MarchantCommentE;
import com.csxs.letterbook.entity.SellerHomeDynamicE;
import com.csxs.letterbook.entity.SellerHomeDynamicImageE;
import com.csxs.letterbook.seller.activity.SellerDynamicCommentActivity;
import com.csxs.letterbook.seller.adapter.SellerHomeDynamicAdapter;
import com.csxs.letterbook.seller.adapter.SellerStoreDynamicAdapter;
import com.csxs.letterbook.seller.adapter.SellerStoreInfoAdapter;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;
import com.csxs.letterbook.seller.mvp.presenter.SellerStoreDynamicPresenter;
import com.csxs.letterbook.widgets.imagewatcher.ImageWatcherHelper;
import com.csxs.letterbook.widgets.imagewatcher.MessagePicturesLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商家动态
 */
public class SellerStoreDynamicFragment extends LazyDiffFragment<SellerStoreDynamicPresenter> implements SellerStoreContract.ISellerStoreDynamicView, MessagePicturesLayout.Callback{


    @BindView(R.id.seller_store_recycler)
    RecyclerView sellerStoreRv;

    @Inject
    Gson gson;
    private SellerHomeDynamicAdapter sellerHomeDynamicAdapter;

    private MapSellerDetailsInfoE sellerDetailsInfo;

    private ImageWatcherHelper iwHelper;
    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic_store_seller;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if(getArguments()!=null){
            String json=  getArguments().getString("detailsinfo");
            sellerDetailsInfo = gson.fromJson(json, MapSellerDetailsInfoE.class);
        }
        sellerHomeDynamicAdapter = new SellerHomeDynamicAdapter(mContext,null).setPictureClickCallback(this);
        sellerStoreRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        sellerStoreRv.setAdapter(sellerHomeDynamicAdapter);
        sellerHomeDynamicAdapter.openLoadAnimation();

        sellerHomeDynamicAdapter.setOnItemClickListener((adapter, view, position) -> {
            MarchantCommentE marchantComment= (MarchantCommentE) adapter.getData().get(position);
            if(marchantComment!=null&&marchantComment.getId()!=0){
                Intent intent=new Intent();
                intent.putExtra("type",2);
                intent.putExtra("commentId",marchantComment.getId());
                intent.putExtra("latitude",marchantComment.getLatitude());
                intent.putExtra("longitude",marchantComment.getLongitude());
                SellerDynamicCommentActivity.start(mContext,SellerDynamicCommentActivity.class,intent);
            }
        });

        if (activity instanceof ImageWatcherHelper.Provider) {
            iwHelper = ((ImageWatcherHelper.Provider) getActivity()).iwHelper();
        }
    }

    @Override
    protected void lazyInitData(boolean isFirstVisible) {
        if (isFirstVisible) {
            if(sellerDetailsInfo!=null){
                mPresenter.querySellerHomeDynamic(sellerDetailsInfo.getId(),sellerDetailsInfo.getLatitude(),sellerDetailsInfo.getLongitude());
            }
            //sellerHomeDynamicAdapter.setNewData(list);
        }
    }

    @Override
    protected void fragmentHidden() {

    }


    public static Fragment newInstance(int status) {
        Bundle args = new Bundle();
        args.putInt("status", status);
        SellerStoreDynamicFragment fragment = new SellerStoreDynamicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void sellerHomeDynamicSuccess(List<MarchantCommentE> mapSellerDetailsInfo) {

        if (mapSellerDetailsInfo != null && mapSellerDetailsInfo.size() > 0) {
            //动态数据 整合
            ArrayList<Uri> list;
            int dynamicSize = mapSellerDetailsInfo.size();
            for (int i = 0; i < dynamicSize; i++) {

                mapSellerDetailsInfo.get(i).setDynamicRichText(new DynamicRichText(mapSellerDetailsInfo.get(i).getContent()));

                long time =TimeStampUtils.stringTolong(mapSellerDetailsInfo.get(i).getAddTime(),"yyyy-MM-dd'T'HH:mm:ss.SSSZ");

                String time1=TimeStampUtils.getShortTime(time);

                mapSellerDetailsInfo.get(i).setAddTime(time1);

                if (mapSellerDetailsInfo.get(i).getDistance() > 1f) {
                    String result = String.format(Locale.CHINA,"%.1f",mapSellerDetailsInfo.get(i).getDistance());
                    mapSellerDetailsInfo.get(i).setDistanceSpace(result + "km");
                } else {
                    String result = String.format(Locale.CHINA,"%.1f",mapSellerDetailsInfo.get(i).getDistance()*1000);
                    mapSellerDetailsInfo.get(i).setDistanceSpace(result+ "m");
                }

                if (mapSellerDetailsInfo.get(i).getPicture() != null && mapSellerDetailsInfo.get(i).getPicture().size() > 0) {
                    list = new ArrayList<Uri>();

                    for (DynamicPictureE url: mapSellerDetailsInfo.get(i).getPicture()) {
                        list.add(Uri.parse(url.getHttpAddress()));
                    }

                    mapSellerDetailsInfo.get(i).setImageUrl(list);

                    mapSellerDetailsInfo.get(i).setItemType(MarchantCommentE.SELLER_HOME_DYNAMIC_IMAGE);
                } else {
                    mapSellerDetailsInfo.get(i).setItemType(MarchantCommentE.SELLER_HOME_DYNAMIC_TEXT);
                }

            }

            sellerHomeDynamicAdapter.setNewData(mapSellerDetailsInfo);

        }else{
            showErrorDataStatus(BaseConstants.STATE_LAYOUT_EMPTY);
        }

        int dynamicSize = mapSellerDetailsInfo.size();
        for (int i = 0; i < dynamicSize; i++){
           Log.e("HomeDynamicSuccess",mapSellerDetailsInfo.get(i).getUserinfo().toString()) ;
        }


    }

    @Override
    public void sellerHomeDynamicFailure(int code, String msg) {
        showErrorDataStatus(code);
    }

    @Override
    public void onThumbPictureClick(ImageView i, SparseArray<ImageView> imageGroupList, List<Uri> urlList) {
        if (iwHelper!=null){
            iwHelper.show(i, imageGroupList, urlList);
        }

    }
}
