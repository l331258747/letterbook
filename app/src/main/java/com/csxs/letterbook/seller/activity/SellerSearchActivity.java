package com.csxs.letterbook.seller.activity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.core.base.BaseDiffActivity;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.SearchHotE;
import com.csxs.letterbook.seller.adapter.SearchHotAdapter;
import com.csxs.letterbook.seller.adapter.SearchResultAdapter;
import com.csxs.letterbook.seller.mvp.presenter.SellerSearchPresenter;
import com.csxs.letterbook.seller.mvp.contract.SellerSearchContract;
import com.csxs.viewlib.LabelsView;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: yeliu
 * created on 2020/4/17
 * description: 商家搜索
 */
public class SellerSearchActivity extends BaseDiffActivity<SellerSearchPresenter> implements SellerSearchContract.ISellerSearchView{

    @BindView(R.id.lv_search_history)
    LabelsView labelsView;

    @BindView(R.id.lv_search_hot)
    LabelsView hotlabelsView;

    @BindView(R.id.rc_hot_seller)
    RecyclerView rcHotSeller;

    @BindView(R.id.rc_search_result)
    RecyclerView rcSearchResult;



    @Override
    public void initParam() {
        super.initParam();
        hideTitleBar = false;
        isStateView = false;
        topBarView = false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_seller;
    }

    @Override
    public void initStatusBar() {
        ImmersionBar.with(this).statusBarColor(R.color.black).fitsSystemWindows(true).statusBarDarkFont(false).init();
    }

    @Override
    public void setWindowBackGround() {
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.color.white);
        this.getWindow().setBackgroundDrawable(drawable);
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
         initLabelsView();
    }

    private void initLabelsView() {

        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                Log.e("onLabelSelectChange",position+"");
            }
        });


        List<String>  list=new ArrayList<>();

        list.add("鱼粉");
        list.add("炸鸡");
        labelsView.setLabels(list, new LabelsView.LabelTextProvider<String>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, String data) {
                return data;
            }
        });

        List<String>  hotlist=new ArrayList<>();

        hotlist.add("鱼粉");
        hotlist.add("炸鸡");
        hotlist.add("书亦烧仙草");
        hotlist.add("黄焖鸡米饭");

        hotlabelsView.setOnLabelClickListener((label, data, position) -> Log.e("onLabelSelectChange",position+""));

        hotlabelsView.setLabels(hotlist, (label, position, data) -> data);

        rcHotSeller.setLayoutManager(new GridLayoutManager(mContext,2));
        List<SearchHotE> searchHots=new ArrayList<>();
        searchHots.add(new SearchHotE("大兵家串串火锅as大立科技爱搜ID混砂机拍大师","开福区北辰三角洲凤凰店啊实打实的卡斯加代收",R.drawable.ic_hot_store_header));
        searchHots.add(new SearchHotE("大兵家串串火锅","开福区北辰三角洲凤凰店",R.drawable.ic_hot_store_header));
        searchHots.add(new SearchHotE("大兵家串串火锅","开福区北辰三角洲凤凰店",R.drawable.ic_hot_store_header));
        searchHots.add(new SearchHotE("大兵家串串火锅","开福区北辰三角洲凤凰店",R.drawable.ic_hot_store_header));
        SearchHotAdapter adapter=new SearchHotAdapter(R.layout.item_search_hot_recommend);
        rcHotSeller.setAdapter(adapter);
        adapter.setNewData(searchHots);


        rcSearchResult.setLayoutManager(new LinearLayoutManager(mContext));
        SearchResultAdapter searchResultAdapter=new SearchResultAdapter(R.layout.item_search_result);
        rcSearchResult.setAdapter(searchResultAdapter);
        searchResultAdapter.setNewData(searchHots);


    }

    @Override
    protected void onInitData() {

    }
}
