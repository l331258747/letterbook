package com.csxs.letterbook.social.adapter;

import android.app.Activity;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.MapSellerPictureE;
import com.csxs.letterbook.entity.UserPhotoE;
import com.csxs.letterbook.seller.adapter.ImageHolder;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * 自定义布局，图片
 */
public class PersonalBannerAdapter extends BannerAdapter<UserPhotoE, ImageHolder> {

    private Activity context;

    public PersonalBannerAdapter(Activity context, List<UserPhotoE> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
        this.context = context;
    }

    //更新数据
    public void updateData(List<UserPhotoE> data) {
        //这里的代码自己发挥，比如如下的写法等等
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }


    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new ImageHolder(imageView);
    }


    @Override
    public void onBindView(ImageHolder holder, UserPhotoE data, int position, int size) {
        //holder.imageView.setImageResource(data.getHttpAddress());
       if(!isDestroy(context)){
           ImageLoaderV4.getInstance().displayImage(context,data.getHttpAddress(),holder.imageView, R.drawable.default_picture);
       }

    }


    //判断Activity是否Destroy
    public static boolean isDestroy(Activity activity) {
        if (activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed())) {
            return true;
        } else {
            return false;
        }
    }

}
