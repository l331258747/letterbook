package com.csxs.letterbook.widgets;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.MapSellerStoreE;
import com.csxs.letterbook.seller.adapter.MapSellerStoreAdapter;
import com.csxs.letterbook.seller.adapter.SellerStoreInfoAdapter;
import com.lxj.xpopup.core.DrawerPopupView;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description: 自定义抽屉弹窗
 * Create by dance, at 2018/12/20
 */
public class MapDrawerPopupView extends DrawerPopupView {
    private RecyclerView rvSellerStore;
    private Context mContext;
    private MapSellerStoreAdapter mapSellerStoreAdapter;
    private List<MapSellerStoreE> list;
    public MapDrawerPopupView(@NonNull Context context) {
        super(context);
        mContext=context;
    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.layout_map_drawer_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        rvSellerStore = findViewById(R.id.rv_seller_store);
        mapSellerStoreAdapter = new MapSellerStoreAdapter(mContext,R.layout.item_map_seller_store);
        rvSellerStore.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        rvSellerStore.setAdapter(mapSellerStoreAdapter);
        List<MapSellerStoreE> list=new ArrayList<>();
        list.add(new MapSellerStoreE());
        list.add(new MapSellerStoreE());
        list.add(new MapSellerStoreE());
        list.add(new MapSellerStoreE());
        list.add(new MapSellerStoreE());
        list.add(new MapSellerStoreE());
        list.add(new MapSellerStoreE());
        list.add(new MapSellerStoreE());
        list.add(new MapSellerStoreE());
        list.add(new MapSellerStoreE());
        mapSellerStoreAdapter.setNewData(list);
        //通过设置topMargin，可以让Drawer弹窗进行局部阴影展示
//        ViewGroup.MarginLayoutParams params = (MarginLayoutParams) getPopupContentView().getLayoutParams();
//        params.topMargin = 450;
    }





    public void setData(List<MapSellerStoreE> list){
        if(list!=null&&list.size()>0){
            mapSellerStoreAdapter.setNewData(list);
        }

    }



    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag", "CustomDrawerPopupView onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag", "CustomDrawerPopupView onDismiss");
    }
}