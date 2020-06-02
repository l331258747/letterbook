package com.csxs.letterbook.example;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.csxs.core.base.BaseActivity;
import com.csxs.core.utils.refreshlayout.OnRefreshDataListener;
import com.csxs.core.utils.refreshlayout.RefreshDataImpl;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.fuli;
import com.csxs.letterbook.example.adapter.ExampleAdapter;
import com.csxs.letterbook.example.mvp.MvpPresenter;
import com.csxs.letterbook.example.mvp.contract.MvpContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.graphics.drawable.ClipDrawable.VERTICAL;

/**
 * 继承BaseActivity MVC 模式 下 用Presenter
 */
public class MvcActivity extends BaseActivity implements MvpContract.IHomeInfoView, OnRefreshDataListener {


    private boolean isRefresh=true;
    @BindView(R.id.rc_content)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @Inject
    MvpPresenter mvpPresenter;
    private ExampleAdapter adapter;
    private RefreshDataImpl<fuli> refreshData;

    /**
     * 重写initParam 可以在实例化控件之前做参数的初始化 比oncreate 先一步执行
     */
    @Override
    public void initParam() {
        super.initParam();
//        topBarView=false;
    }

    /**
     * 实例化Presenter
     */
    @Override
    protected void initPresenter() {
        super.initPresenter();
        //先关联上下文
        mvpPresenter.attachView(this);
        //生命周期监控
        getLifecycle().addObserver(mvpPresenter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_example;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
         setCenterMainTitle("测试");
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExampleAdapter(R.layout.example_item,null);
        refreshLayout.autoRefresh();
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshData = new RefreshDataImpl<>(mContext,recyclerView,adapter, (SmartRefreshLayout) refreshLayout,this);
    }

    @Override
    protected void onInitData() {
        requestinfo();
    }

    public void requestinfo(){
        mvpPresenter.getFuli();
    }


    @Override
    public void reusltSucessFuli(List<fuli> list) {
       refreshData.setRefreshData(list,true);


    }

    @Override
    public void reusltSucessFuli(String string) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext.getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        refreshData.finishRefresh();
    }

    @Override
    public void showError(int code, String message) {

    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public Lifecycle getOwnerLifeCycle() {
        return this.getLifecycle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mvpPresenter!=null){
            mvpPresenter=null;
        }
    }

    @Override
    public void onRefresh() {

        isRefresh=true;
        requestinfo();
    }

    @Override
    public void onLoadMore() {
        isRefresh=false;
        requestinfo();
    }
}
