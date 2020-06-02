package com.csxs.letterbook.seller.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.MapSellerPictureE;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/20
 * description:
 */
public class StoreImageAdapter extends BaseQuickAdapter<MapSellerPictureE, BaseViewHolder> {



    public StoreImageAdapter(int layoutResId,List<MapSellerPictureE> data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MapSellerPictureE item) {

        ImageView imageView= helper.getView(R.id.iv_store_img);
        View view =helper.getView(R.id.view_sp);
        if(helper.getLayoutPosition()==0){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
        ImageLoaderV4.getInstance().displayRoundImage(imageView.getContext(),item.getHttpAddress(),imageView,R.drawable.default_picture, ScreenUtil.dip2px(imageView.getContext(),10));
//        Log.e("adapter",ImageLoaderV4.getInstance().toString());



    }
}
