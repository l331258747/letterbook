package com.csxs.letterbook.seller.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.core.base.LazyDiffFragment;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.SellerBasicInfoE;
import com.csxs.letterbook.entity.SellerInfoE;
import com.csxs.letterbook.entity.SellerStoreDescE;
import com.csxs.letterbook.entity.SellerStoreImageE;
import com.csxs.letterbook.seller.adapter.SellerStoreInfoAdapter;
import com.csxs.letterbook.seller.mvp.presenter.SellerStoreDynamicPresenter;
import com.csxs.letterbook.seller.mvp.presenter.SellerStoreInfoPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:店铺信息
 */
public class SellerStoreInfoFragment extends LazyDiffFragment<SellerStoreInfoPresenter> {

    @BindView(R.id.seller_info_recycler)
    RecyclerView sellerinfoRv;
    private SellerStoreInfoAdapter sellerStoreInfoAdapter;
    @Inject
    ImageLoaderV4 imageLoaderV4;

    @Inject
    Gson gson;

    private MapSellerDetailsInfoE sellerDetailsInfo;

    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_store_seller;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if(getArguments()!=null){
            String json=  getArguments().getString("detailsinfo");
            sellerDetailsInfo = gson.fromJson(json, MapSellerDetailsInfoE.class);
        }
        sellerStoreInfoAdapter = new SellerStoreInfoAdapter(mContext,null);
        sellerinfoRv.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        sellerinfoRv.setAdapter(sellerStoreInfoAdapter);

        if(imageLoaderV4!=null){
            Log.e("imageloader",imageLoaderV4.toString());
        }

    }

    @Override
    protected void lazyInitData(boolean isFirstVisible) {
        if(isFirstVisible){


            List<SellerInfoE> list=new ArrayList<>();
            SellerInfoE sellerInfo1=new SellerInfoE();
            SellerStoreDescE sellerStoreDesc=new SellerStoreDescE();
            sellerStoreDesc.setStoreDesc(sellerDetailsInfo.getPersonalDetails());
            sellerInfo1.setItemType(1);
            sellerInfo1.setSellerStoreDesc(sellerStoreDesc);
            list.add(sellerInfo1);


            SellerInfoE sellerInfo2=new SellerInfoE();
            SellerStoreImageE sellerStoreImage=new SellerStoreImageE();
//            for (int i=0;i<sellerDetailsInfo.getThepointSins().size();i++){
//
//            }

            sellerStoreImage.setImages(sellerDetailsInfo.getThepointSins());
            sellerInfo2.setItemType(2);
            sellerInfo2.setSellerStoreImage(sellerStoreImage);
            list.add(sellerInfo2);

            SellerInfoE sellerInfo3=new SellerInfoE();
            SellerBasicInfoE sellerBasicInfo=new SellerBasicInfoE(sellerDetailsInfo.getOpeningTime(),sellerDetailsInfo.getClosingTime(),sellerDetailsInfo.getAddress(),sellerDetailsInfo.getContactsMobile());

            sellerInfo3.setItemType(3);
            sellerInfo3.setSellerBasicInfo(sellerBasicInfo);
            list.add(sellerInfo3);

            sellerStoreInfoAdapter.setNewData(list);

        }
    }

    @Override
    protected void fragmentHidden() {

    }


    public static Fragment newInstance(int status) {
        Bundle args = new Bundle();
        args.putInt("status", status);
        SellerStoreInfoFragment fragment = new SellerStoreInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
