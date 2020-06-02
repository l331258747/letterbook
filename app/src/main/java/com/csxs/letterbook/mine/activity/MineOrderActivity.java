package com.csxs.letterbook.mine.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.csxs.core.base.BaseDiffActivity;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.GlobalConstants;
import com.csxs.letterbook.R;
import com.csxs.letterbook.dynamic.fragment.AttentionUserFragment;
import com.csxs.letterbook.dynamic.fragment.NearbyUserFragment;
import com.csxs.letterbook.mine.adapter.MineAllOrderAdapter;
import com.csxs.letterbook.mine.fragment.MineOrderFragment;
import com.csxs.letterbook.mine.mvp.presenter.MineOrderPresenter;
import com.csxs.letterbook.seller.adapter.SellerPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @author: yeliu
 * created on 2020/4/26
 * description:我的订单
 */
public class MineOrderActivity extends BaseDiffActivity<MineOrderPresenter> {

    @BindView(R.id.magic_tab_mine_order)
    MagicIndicator mineOrderTab;

    @BindView(R.id.vp_mine_order)
    ViewPager mineOrderVp;


    int position = 0;

    private static final String[] CHANNELS = new String[]{"全部", "待付款", "待发货","待收货","待使用","退货"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

    private ArrayList<Fragment> fragments;

    @Override
    public void initParam() {
        super.initParam();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_order;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        setCenterMainTitle("我的订单");
        initFragment();

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setBackgroundColor(Color.WHITE);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(14);
                simplePagerTitleView.setPadding(0,0,0,0);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.black));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.color_red_EC1919));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mineOrderVp.setCurrentItem(index);
                    }
                });

                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(getResources().getColor(R.color.color_red_EC1919));
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setRoundRadius(ScreenUtil.dip2px(mContext,2));
                indicator.setLineHeight(ScreenUtil.dip2px(mContext,2));

                return indicator;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                return 2;
            }
        });

        mineOrderTab.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
      //  titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
       // titleContainer.setDividerPadding(ScreenUtil.dip2px(this, 2));
       // titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));
        ViewPagerHelper.bind(mineOrderTab,mineOrderVp);

        mineOrderVp.setCurrentItem(position);


    }

    private void initFragment() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }

        fragments.add(MineOrderFragment.newInstance(0));
        fragments.add(MineOrderFragment.newInstance(1));
        fragments.add(MineOrderFragment.newInstance(2));
        fragments.add(MineOrderFragment.newInstance(3));
        fragments.add(MineOrderFragment.newInstance(4));
        fragments.add(MineOrderFragment.newInstance(5));


        SellerPagerAdapter adapter = new SellerPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, mDataList);

        mineOrderVp.setAdapter(adapter);

        mineOrderVp.setCurrentItem(position);
    }

    @Override
    protected void onInitData() {

    }
}
