package com.csxs.letterbook.widgets.refreshlayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/22
 * description:
 */
public class RefreshPageData<T> implements OnRefreshListener, OnLoadMoreListener {

    public int pageCurrent = 1;//当前页码

    public int pageSize = 10;//分页大小

    private RecyclerView mRecyclerView;

    //BaseQuickAdapter 里面有维护一个List集合 不用我们 自己再去维护一个集合了
    private BaseQuickAdapter<T, BaseViewHolder> mAdapter;

    private SmartRefreshLayout mRefreshLayout;

    private RefreshDataListener listener;

    private boolean enableRefresh = true; //默认开启下拉刷新

    private boolean enableLoadMore = true;//默认开启上拉加载更多

    private boolean isRefresh =true;

    public RefreshPageData(RecyclerView recyclerView, BaseQuickAdapter<T, BaseViewHolder> adapter, SmartRefreshLayout refreshLayout, RefreshDataListener refreshDataListener) {
        this.mRecyclerView = recyclerView;
        this.mAdapter = adapter;
        this.mRefreshLayout = refreshLayout;
        this.listener = refreshDataListener;
        onInit();
    }

    private void onInit() {
        mRefreshLayout.setOnRefreshListener(this);
        mAdapter.setEnableLoadMore(false); //禁用BaseQuickAdapter的 上拉加载更多
        //adapter.setOnLoadMoreListener(this,recyclerView);//必需指定ry 要不会闪退

    }


    /**
     * 设置是否开启下拉刷新
     *
     * @param enableRefresh
     */
    public void setEnableRefresh(boolean enableRefresh) {
        this.enableRefresh = enableRefresh;
        mRefreshLayout.setEnableRefresh(enableRefresh);
    }

    /**
     * 设置是否开启上拉加载更多
     *
     * @param enableLoadMore
     */
    public void setEnableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
        mRefreshLayout.setEnableRefresh(enableLoadMore);
    }

    /**
     * 设置新的数据
     *
     * @param data
     */
    public void setNewData(List<T> data) {
        if (mAdapter != null) {
            mAdapter.setNewData(data);
        }

    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(List<T> data) {
        if (data != null && data.size() > 0) {
            if (mAdapter != null) {
                mAdapter.addData(data);
            }

        }
    }

    public void setRefreshData(List<T> data) {
        //下拉刷新 或 上拉加载 没有数据
        if (data == null || data.size() <= 0) {
            if (isRefresh) {
                mRefreshLayout.finishRefresh();
            } else {
                mRefreshLayout.finishLoadMore();
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            }
        } else {
            //下拉刷新有数据
            if (isRefresh) {
                //添加数据
                mAdapter.addData(data);
                if (data.size() < pageSize) {
                    mRefreshLayout.finishRefreshWithNoMoreData();
                } else {
                    mRefreshLayout.finishRefresh();
                }
            } else {
                mRefreshLayout.finishLoadMore();
                if (data.size() < pageSize) {
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                }
            }

        }
    }


    /**
     * 获取adapter 中的所有数据
     *
     * @return
     */
    public List<T> getData() {
        if (mAdapter != null) {
            return mAdapter.getData();
        }
        return null;
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (listener != null) {
            listener.onRefresh();
            pageCurrent=1;
            isRefresh=true;
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (listener != null) {
            listener.onLoadMore();
            pageCurrent++;
            isRefresh=false;
        }
    }

    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
