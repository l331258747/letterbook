package com.csxs.letterbook.dynamic;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.csxs.core.base.BaseActivity;
import com.csxs.core.base.BaseDiffActivity;
import com.csxs.core.base.LazyDiffFragment;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.GlobalConstants;
import com.csxs.letterbook.R;
import com.csxs.letterbook.dynamic.fragment.AttentionUserFragment;
import com.csxs.letterbook.dynamic.fragment.NearbyUserFragment;
import com.csxs.letterbook.dynamic.mvp.DynamicPresenter;
import com.csxs.letterbook.home.HomeActivity;
import com.csxs.letterbook.seller.adapter.SellerPagerAdapter;
import com.csxs.letterbook.seller.fragment.SellerStoreDynamicFragment;
import com.csxs.letterbook.seller.fragment.SellerStoreInfoFragment;
import com.csxs.letterbook.seller.fragment.SellerStoreOrderFragment;
import com.csxs.letterbook.seller.fragment.SellerStorePromotionFragment;
import com.csxs.letterbook.widgets.ScaleTransitionPagerTitleView;
import com.csxs.letterbook.widgets.imagewatcher.Utils;
import com.gyf.immersionbar.ImmersionBar;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @author: yeliu
 * created on 2020/4/13
 * description:首页 动态 懒加载 fragment
 */
public class DynamicFragment extends LazyDiffFragment<DynamicPresenter> {

    @BindView(R.id.magic_indicator6)
    MagicIndicator magicIndicator;

    @BindView(R.id.vp_dymaic)
    ViewPager viewPager;

    @BindView(R.id.view_top)
    View viewTop;

    @BindView(R.id.ll_root)
    LinearLayout ll_root;


    private ArrayList<Fragment> fragments;

    private static final String[] CHANNELS = new String[]{"附近的人","关注"};
    private List<String> mDataList = Arrays.asList(CHANNELS);


    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        int statusBarHeight = ImmersionBar.getStatusBarHeight(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.height = statusBarHeight;
        viewTop.setLayoutParams(params);
        viewTop.setBackgroundColor(getResources().getColor(R.color.black));

        initFragment();

        magicIndicator.setBackgroundColor(Color.BLACK);
        CommonNavigator commonNavigator = new CommonNavigator(mContext);

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setIncludeFontPadding(false);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(25);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(getResources().getColor(R.color.white));
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setRoundRadius(ScreenUtil.dip2px(mContext, 2));
                indicator.setLineHeight(ScreenUtil.dip2px(mContext, 4));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setYOffset(ScreenUtil.dip2px(mContext, 4));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);


    }


    private void initFragment() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }

        fragments.add(NearbyUserFragment.newInstance(GlobalConstants.DYNAMIC_NEARBY_USER_FRAGMENT));
        fragments.add(AttentionUserFragment.newInstance(GlobalConstants.DYNAMIC_ATTENTION_USER_FRAGMENT));

        SellerPagerAdapter adapter = new SellerPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, mDataList);

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(0);


    }

    @Override
    protected void lazyInitData(boolean isFirstVisible) {

    }

    @Override
    protected void fragmentHidden() {

    }


    public static DynamicFragment newInstance(String type) {
        Bundle args = new Bundle();
        DynamicFragment fragment = new DynamicFragment();
        fragment.setArguments(args);
        args.putString("type", type);
        return fragment;
    }


}
