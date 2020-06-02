package com.csxs.letterbook.mine.adapter;

import android.content.Context;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.imageloader.tranform.RoundBitmapTranformation;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.UserPhotoWallE;
import com.csxs.viewlib.NiceImageView;
import com.csxs.viewlib.titlebar.ScreenUtils;
import com.luck.picture.lib.config.PictureMimeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sins
 * Date : 2020/5/19
 * Desc :
 */
public class PhotoWallAdapter extends RecyclerView.Adapter<PhotoWallAdapter.MyHolder>{
    private int mMaxNum=9;
    private Context mContext;
    private OnItemClickListener mItemClickListener;
    private OnCloseItemClick onCloseItemClick;
    private List<String> mDatas=new ArrayList<>();
    private final SparseArray<ImageView> mVisiblePictureList = new SparseArray<>();

    private final RequestOptions requestOptions;
    public PhotoWallAdapter(Context context){
        this.mContext = context;
      //  setHasStableIds(true);

        requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_image_pholder_dynamic)
                .error(R.drawable.ic_image_pholder_dynamic);
//        .transform(new CenterCrop(), new RoundBitmapTranformation(ScreenUtil.dip2px(context, 5)));
    }

    public void setData(List<String> datas){
        if(null!=datas){
            this.mDatas = datas;
        }

       // notifyDataSetChanged();
    }

    public void removeData(int postion){
        mDatas.remove(postion);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 填充布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.rc_photo_wall_item, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        if(mDatas.size()==0 || mDatas.size()==position){
            holder.imageView.setImageResource(R.drawable.ic_add_image);
            holder.closeview.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.VISIBLE);
        }else{
            holder.closeview.setVisibility(View.VISIBLE);
            holder.closeview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     if (null!=onCloseItemClick){
                         String url=  mDatas.get(position);
                         onCloseItemClick.onItemClickClose(url,position);
                     }
                }
            });

            Glide.with(mContext).load(mDatas.get(position)).apply(requestOptions).transition(DrawableTransitionOptions.withCrossFade()).into(holder.imageView);

            mVisiblePictureList.put(position, holder.imageView);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= holder.getAdapterPosition();
               // int pos=position;
                    int size=getItemCount();
                   if(mDatas.size()==pos){
                      // pos=position-1;
                       mItemClickListener.onItemClick(holder.imageView,null,null,pos);
                   }else{
                       if (mDatas.size()>0){
                       String url=  mDatas.get(pos);
                       mItemClickListener.onItemClick(holder.imageView,url,mVisiblePictureList,pos);
                   }
               }
            }
        });




    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size() < mMaxNum ? mDatas.size() + 1 : mDatas.size();
    }

    public List<String> getData(){
        return mDatas;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private NiceImageView imageView;
        private ImageView closeview;
        public MyHolder(View view) {
            super(view);
            closeview=(ImageView) view.findViewById(R.id.iv_del);
            imageView=(NiceImageView) view.findViewById(R.id.fiv);

        }
    }


    //item的回调接口
    public interface OnItemClickListener{
        void onItemClick(ImageView view, String url, SparseArray<ImageView> viewSparseArray, int Position);
    }
    //定义一个设置点击监听器的方法
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public interface OnCloseItemClick{
        void onItemClickClose(String url, int pos);
    }

    public void setOnCloseItemClick(OnCloseItemClick onCloseItemClick) {
        this.onCloseItemClick = onCloseItemClick;
    }



}
