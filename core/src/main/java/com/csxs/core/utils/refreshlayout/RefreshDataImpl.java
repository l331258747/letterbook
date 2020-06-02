package com.csxs.core.utils.refreshlayout;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SenYe
 * Date : 2020/4/7
 * Desc :刷新数据实现
 */
public class RefreshDataImpl<T> implements OnRefreshListener, OnLoadMoreListener, BaseQuickAdapter.RequestLoadMoreListener {

    private int PAGE_SIZE = 20;//分页大小
    private int PAGE_NUMBER = 1;//分页数量
    private RecyclerView mRecyclerView;
    private BaseQuickAdapter<T, com.chad.library.adapter.base.BaseViewHolder> mAdapter;
    private SmartRefreshLayout mRefreshLayout;
    private List<T> mList = new ArrayList<>(); // 数据
    private View mView;
    private Context mContext;
    private OnRefreshDataListener listener;


    public RefreshDataImpl(Context context, RecyclerView recyclerView, BaseQuickAdapter adapter, SmartRefreshLayout refreshLayout) {
        this(context, recyclerView, adapter, refreshLayout, null);
    }


    public RefreshDataImpl(Context context, RecyclerView recyclerView, BaseQuickAdapter adapter, SmartRefreshLayout refreshLayout, OnRefreshDataListener onDataRefreshListener) {
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        this.mAdapter = adapter;
        this.mRefreshLayout = refreshLayout;
        this.listener = onDataRefreshListener;
        onInit();
    }

    private void onInit() {
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(this);
        mAdapter.setEnableLoadMore(false); //禁用BaseQuickAdapter的 上拉加载更多
        //adapter.setOnLoadMoreListener(this,recyclerView);//必需指定ry 要不会闪退
    }


    public void setRefreshData(List<T> data, boolean isRefresh) {

        //下拉刷新 或 上拉加载 没有数据
        if (data == null || data.size() <= 0) {
            if (isRefresh) {
                //下拉刷新完毕 开启上拉加载更多
                // mRefreshLayout.setEnableLoadMore(true);

                //没有更多数据（上拉加载功能将显示没有更多数据）
                mRefreshLayout.finishRefreshWithNoMoreData();
            } else {
                //上拉加载更多完毕 开启下拉刷新
                //mRefreshLayout.setEnableRefresh(true);

                //将不会再次触发加载更多事件
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            }
            return;
        }

        //下拉刷新
        if (isRefresh) {
            mList.clear();
            mList = data;
            mAdapter.setNewData(mList);
            //下拉刷新时 禁止上拉加载更多
            //mRefreshLayout.setEnableLoadMore(true);
            //判断请求数据的条数小于规定的条数,就是没有更多数据
            if (mList.size() < PAGE_SIZE) {
                //当下拉刷新的数据小于规定的条数时  禁止上拉加载更多
               // mRefreshLayout.setEnableLoadMore(false);
                mRefreshLayout.finishRefreshWithNoMoreData();
            }else{
               // mRefreshLayout.setEnableLoadMore(false);
                mRefreshLayout.finishRefresh();//结束刷新,还有多的数据
            }

        } else {
            //上拉加载更多 请求数据小于规定的条数,没有更多的数据了

            if (data.size() < PAGE_SIZE) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据
            } else {
                mRefreshLayout.finishLoadMore();
            }
            mList.addAll(data);
            mAdapter.addData(mList);
        }
    }

    /**
     * 下拉刷新完毕
     */
    public void finishRefresh(){
        mRefreshLayout.finishRefresh();
    }

    /**
     * 上拉加载更多完毕
     */
    public void finishLoadMore(){
        mRefreshLayout.finishLoadMore();
    }


    //SmartRefreshLayout 的下拉刷新
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        //下拉刷新时禁止上拉加载更多
       // refreshLayout.setEnableLoadMore(false);
        if (listener != null) {
            listener.onRefresh();
        }
    }

    //SmartRefreshLayout 上拉加载更多
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        //上拉加载更多时禁止下拉刷新
       // refreshLayout.setEnableRefresh(false);
        if (listener != null) {
            listener.onLoadMore();
        }

    }

    //BaseQuickAdapter 上拉加载更多  默认禁止
    @Override
    public void onLoadMoreRequested() {

    }

    public OnRefreshDataListener getRefreshListener() {
        return listener;
    }

    public void setOnRefreshListener(OnRefreshDataListener listener) {
        this.listener = listener;
    }

    public int getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(int size) {
        this.PAGE_SIZE = size;
    }

    public int getPageNumber() {
        return PAGE_NUMBER;
    }

    public void setPageNumber(int number) {
        this.PAGE_NUMBER = number;
    }
}
