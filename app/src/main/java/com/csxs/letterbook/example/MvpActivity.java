package com.csxs.letterbook.example;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.csxs.core.base.BaseDiffActivity;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.fuli;
import com.csxs.letterbook.example.mvp.MvpPresenter;
import com.csxs.letterbook.example.mvp.contract.MvpContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 示例 MVP
 */
public class MvpActivity extends BaseDiffActivity<MvpPresenter> implements MvpContract.IHomeInfoView {


    @Override
    public void initParam() {
        super.initParam();
        //需要展示titlebar
        hideTitleBar=true;
        //时候需要空布局 默认是需要true
        isStateView=false;//这里不需要
        ////是否需要 状态栏 占位 默认需要
        topBarView=true;

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_example;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
          setCenterMainTitle("信书");
    }

    @Override
    protected void onInitData() {
        mPresenter.getFuli();
    }

    @Override
    public void reusltSucessFuli(List<fuli> list) {
        Toast.makeText(mContext,list.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void reusltSucessFuli(String string) {
        Toast.makeText(mContext,string,Toast.LENGTH_SHORT).show();
    }


}
