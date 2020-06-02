package com.csxs.letterbook.social.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.csxs.core.base.BaseDiffActivity;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.BitmapUtils;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.core.utils.SpannableStringUtils;
import com.csxs.core.utils.TimeStampUtils;
import com.csxs.letterbook.GlobalConstants;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.DataBean;
import com.csxs.letterbook.entity.MapSocialUserInfoE;
import com.csxs.letterbook.seller.adapter.SellerBannerAdapter;
import com.csxs.letterbook.seller.adapter.SellerPagerAdapter;
import com.csxs.letterbook.seller.fragment.SellerStoreDynamicFragment;
import com.csxs.letterbook.seller.fragment.SellerStoreInfoFragment;
import com.csxs.letterbook.seller.fragment.SellerStorePromotionFragment;
import com.csxs.letterbook.social.adapter.PersonalBannerAdapter;
import com.csxs.letterbook.social.fragment.PersonalInfoFragment;
import com.csxs.letterbook.social.mvp.contract.PersonalHomeContract;
import com.csxs.letterbook.social.mvp.presenter.PersonalHomePresenter;
import com.csxs.viewlib.CircleImageView;
import com.csxs.viewlib.DrawableCenterTextView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnPageChangeListener;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: yeliu
 * created on 2020/5/23
 * description:个人主页
 */
public class PersonalHomeActivity extends BaseDiffActivity<PersonalHomePresenter> implements AppBarLayout.OnOffsetChangedListener, OnPageChangeListener, PersonalHomeContract.IPersonalHomeView {

    @BindView(R.id.banner_personal_top)
    Banner bannerPersonalTop;
    @BindView(R.id.banner_num_indicator)
    TextView bannerNumIndicator;
    @BindView(R.id.ci_img_personal)
    CircleImageView ciImgPersonal;
    @BindView(R.id.tv_attention_personal)
    DrawableCenterTextView tvAttentionPersonal;
    @BindView(R.id.rl_top_banner)
    RelativeLayout rlTopBanner;
    @BindView(R.id.tv_personal_name)
    TextView tvPersonalName;
    @BindView(R.id.tv_nearby_user_sex)
    TextView tvNearbyUserSex;
    @BindView(R.id.tv_nearby_user_location)
    TextView tvNearbyUserLocation;
    //    @BindView(R.id.tv_personal_emotion)
//    TextView tvPersonalEmotion;
    @BindView(R.id.iv_personal_authentication)
    ImageView ivPersonalAuthentication;
    @BindView(R.id.tv_personal_visitor)
    TextView tvPersonalVisitor;
    @BindView(R.id.tv_personal_attention)
    TextView tvPersonalAttention;
    @BindView(R.id.tv_personal_fans)
    TextView tvPersonalFans;
    @BindView(R.id.iv_ad_link)
    ImageView ivAdLink;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_magicIndicator)
    MagicIndicator tabMagicIndicator;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.ib_bnt)
    ImageButton ibBnt;
    @BindView(R.id.tv_common_title)
    TextView tvCommonTitle;
    @BindView(R.id.iv_share_store)
    ImageView ivShareStore;
    @BindView(R.id.iv_store_chat_msg)
    ImageView ivStoreChatMsg;
    @BindView(R.id.rl_bar)
    RelativeLayout rlBar;
    @BindView(R.id.layout_topbar)
    LinearLayout layoutTopbar;
    @BindView(R.id.tv_strat_chat)
    DrawableCenterTextView tvStratChat;
    private PersonalBannerAdapter adapter;

    private static final String[] CHANNELS = new String[]{"基本信息", "Ta的动态"};
    private List<String> titles = Arrays.asList(CHANNELS);
    private int userId;
    private PersonalInfoFragment personalInfoFragment;

    private ArrayList<Fragment> fragments;


    private Drawable dynamicSexMan;

    private Drawable dynamicSexWoman;

    int position = 0;
    private SellerPagerAdapter sellerPagerAdapter;

    @Override
    public void initParam() {
        super.initParam();
        hideTitleBar = false;
        isStateView = false;
        topBarView = false;
    }

    @Override
    public void initStatusBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true).init();
    }

    @Override
    public void setWindowBackGround() {
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.color.white_f2f2f2);
        this.getWindow().setBackgroundDrawable(drawable);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_personal;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {

        userId = getIntent().getIntExtra("UserId", -1);
        int statusBarHeight = ScreenUtil.getStatusBarHeight(this);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) viewTop.getLayoutParams();
        lp.height = lp.height + statusBarHeight;
        viewTop.setLayoutParams(lp);
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能

        adapter = new PersonalBannerAdapter(this, null);

        bannerPersonalTop.setAdapter(adapter);

        bannerPersonalTop.isAutoLoop(true);
        bannerPersonalTop.setIndicator(new CircleIndicator(this));
        bannerPersonalTop.setOnBannerListener((data, position) -> {
            Snackbar.make(bannerPersonalTop, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
        });
        //添加切换监听
        bannerPersonalTop.addOnPageChangeListener(this);

        bannerPersonalTop.setStartPosition(0);
        appbarLayout.addOnOffsetChangedListener(this);

        dynamicSexMan = mContext.getResources().getDrawable(R.drawable.ic_dynamic_sex_man);
        dynamicSexMan.setBounds(0, 0, dynamicSexMan.getMinimumWidth(), dynamicSexMan.getMinimumHeight());

        dynamicSexWoman = mContext.getResources().getDrawable(R.drawable.ic_dynamic_sex_woman);
        dynamicSexWoman.setBounds(0, 0, dynamicSexWoman.getMinimumWidth(), dynamicSexWoman.getMinimumHeight());

        initTab();

        initFragment();


    }


    private void initFragment() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }

        personalInfoFragment = (PersonalInfoFragment) PersonalInfoFragment.newInstance(1);
        SellerStorePromotionFragment sellerStorePromotionFragment = (SellerStorePromotionFragment) SellerStorePromotionFragment.newInstance(GlobalConstants.SELLER_STOREPROMOTION_FRAGMENT);

        fragments.add(personalInfoFragment);

        fragments.add(sellerStorePromotionFragment);

        sellerPagerAdapter = new SellerPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, null, null);

        viewPager.setAdapter(sellerPagerAdapter);

        viewPager.setCurrentItem(position);


    }

    private void initTab() {

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);

        commonNavigator.setBackgroundColor(Color.WHITE);
        //commonNavigator.setBackgroundResource(R.drawable.bg_radius_seller_navigator);
        commonNavigator.setScrollPivotX(0.65f);
        // commonNavigator.setBackground(getResources().getDrawable(R.drawable.bg_tab_radius));
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles == null ? 0 : titles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(titles.get(index));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.black_cccccc));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.black));
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
                indicator.setColors(getResources().getColor(R.color.black));
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setRoundRadius(ScreenUtil.dip2px(mContext, 2));
//                indicator.setLineWidth(UIUtil.dip2px(context, 18));
                indicator.setLineHeight(ScreenUtil.dip2px(mContext, 4));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setYOffset(ScreenUtil.dip2px(mContext, 2));
                return indicator;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                return 2;
            }
        });


        tabMagicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(ScreenUtil.dip2px(this, 15));
        ViewPagerHelper.bind(tabMagicIndicator, viewPager);


    }

    @Override
    protected void onInitData() {

        if (mPresenter != null) {
            mPresenter.querySingleUserInfo(userId);
        }

    }


    @OnClick({R.id.iv_share_store, R.id.iv_store_chat_msg, R.id.rl_bar, R.id.ib_bnt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_share_store:
                break;
            case R.id.iv_store_chat_msg:
                break;
            case R.id.rl_bar:
                break;
            case R.id.ib_bnt:
                finish();
                break;
        }
    }

    int color = Color.argb(0, 255, 255, 255);

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //垂直方向偏移量
        int offset = Math.abs(verticalOffset);
        //最大偏移距离
        int scrollRange = appBarLayout.getTotalScrollRange() - ScreenUtil.dip2px(this, 48);

        if (offset <= 0) {
            //顶部图处于最顶部，标题栏透明
            color = Color.argb(0, 255, 255, 255);
            viewTop.setBackgroundColor(color);
            //  topViewStatus.setBackgroundColor(color);
            ibBnt.setColorFilter(Color.WHITE);
            ivShareStore.setColorFilter(Color.WHITE);
            ivStoreChatMsg.setColorFilter(Color.WHITE);
        } else if (offset > 0 && offset < scrollRange) {
            //算出滑动距离比例
            float scale = (float) offset / scrollRange;
            //得到透明度
            float alpha = (255 * scale);
            color = Color.argb((int) alpha, 255, 255, 255);
            layoutTopbar.setBackgroundColor(color);
            //topViewStatus.setBackgroundColor(color);
            ibBnt.setColorFilter(Color.BLACK);
            ivShareStore.setColorFilter(Color.BLACK);
            ivStoreChatMsg.setColorFilter(Color.BLACK);

            tvCommonTitle.setTextColor(Color.argb((int) alpha, 0, 0, 0));

        } else {
            color = Color.argb(255, 255, 255, 255);
            layoutTopbar.setBackgroundColor(color);
            //  topViewStatus.setBackgroundColor(color);

            ibBnt.setColorFilter(Color.BLACK);
            ivShareStore.setColorFilter(Color.BLACK);
            ivStoreChatMsg.setColorFilter(Color.BLACK);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void singleUserInfoSuccess(MapSocialUserInfoE mapSocialUserInfoE) {
        if (mapSocialUserInfoE != null) {
           if(mapSocialUserInfoE.getAlbumlist()!=null&&mapSocialUserInfoE.getAlbumlist().size()>0){
               bannerNumIndicator.setText(getResources().getString(R.string.str_banner_num_placeholder, 1, mapSocialUserInfoE.getAlbumlist().size()));
           }

            if (mapSocialUserInfoE.getAlbumlist() != null && mapSocialUserInfoE.getAlbumlist().size() > 0) {
                adapter.updateData(mapSocialUserInfoE.getAlbumlist());
                //    adapter.notifyDataSetChanged();
            }


            String accessNumber = "访客 " + mapSocialUserInfoE.getAccessNumber();

            SpannableStringBuilder str = SpannableStringUtils.textFormat(accessNumber, getResources().getColor(R.color.color_E7A124), 2, accessNumber.length(), 1.4f, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            tvPersonalVisitor.setText(str);

            //    String fans = "粉丝 " + mapSocialUserInfoE.get;
            //   SpannableStringBuilder str1 = SpannableStringUtils.textFormat(fans, getResources().getColor(R.color.color_E7A124), 2, fans.length(), 1.4f, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            //   tvStoreFans.setText(str1);

            //昵称
            tvPersonalName.setText(getString(R.string.str_placeholder, mapSocialUserInfoE.getNickname().equals("") ? String.valueOf(mapSocialUserInfoE.getId()) : mapSocialUserInfoE.getId() + ""));

            //头像

            ImageLoaderV4.getInstance().displayImage(mContext, mapSocialUserInfoE.getHeadimgurl(), ciImgPersonal, R.drawable.ic_default_circle_store_header);

            int age = TimeStampUtils.getAgeByBirth(TimeStampUtils.stringChangeToDate(mapSocialUserInfoE.getBirthDay(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));


            tvNearbyUserSex.setText(mContext.getString(R.string.int_placeholder, age));

            if (mapSocialUserInfoE.getSex().equals("1")) {

                tvNearbyUserSex.setCompoundDrawables(dynamicSexMan, null, null, null);

                tvNearbyUserSex.setBackgroundResource(R.drawable.bg_personal_sex_age_man);

            } else if (mapSocialUserInfoE.getSex().equals("2")) {

                tvNearbyUserSex.setCompoundDrawables(dynamicSexWoman, null, null, null);

                tvNearbyUserSex.setBackgroundResource(R.drawable.bg_personal_sex_age_woman);
            }

            tvNearbyUserLocation.setText(getString(R.string.str_placeholder, mapSocialUserInfoE.getAddress()));

            //tvPersonalEmotion.setText(getString(R.string.str_placeholder,));
            //attentionUser(sellerDetailsInfo.getIsfans());


            Gson gson = new Gson();
            String json = gson.toJson(mapSocialUserInfoE);
            Bundle bundle = new Bundle();
            bundle.putString("info", json);
            personalInfoFragment.setArguments(bundle);

            if (sellerPagerAdapter != null) {
                sellerPagerAdapter.setData(fragments, titles);
            }

        }

    }

    @Override
    public void singleUserInfoFailure(int code, String msg) {

    }
}
