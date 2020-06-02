package com.csxs.letterbook.seller.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.csxs.core.base.BaseDiffActivity;
import com.csxs.core.utils.BitmapUtils;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.core.utils.SpannableStringUtils;
import com.csxs.letterbook.GlobalConstants;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.CommodityListE;
import com.csxs.letterbook.entity.DataBean;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.SubmitGoodsE;
import com.csxs.letterbook.mine.activity.SendDynamicActivity;
import com.csxs.letterbook.order.SingleOrderActivity;
import com.csxs.letterbook.seller.adapter.SellerBannerAdapter;
import com.csxs.letterbook.seller.adapter.SellerCarAdapter;
import com.csxs.letterbook.seller.adapter.SellerPagerAdapter;
import com.csxs.letterbook.seller.fragment.SellerStoreDynamicFragment;
import com.csxs.letterbook.seller.fragment.SellerStoreInfoFragment;
import com.csxs.letterbook.seller.fragment.SellerStoreOrderFragment;
import com.csxs.letterbook.seller.fragment.SellerStorePromotionFragment;
import com.csxs.letterbook.seller.listener.AddGoodsSpac;
import com.csxs.letterbook.seller.mvp.contract.SellerHomeContract;
import com.csxs.letterbook.seller.mvp.presenter.SellerHomePresenter;
import com.csxs.letterbook.widgets.GoodsParamBottpmView;
import com.csxs.letterbook.widgets.ShopCarView;
import com.csxs.letterbook.widgets.imagewatcher.ImageWatcherHelper;
import com.csxs.letterbook.widgets.imagewatcher.ImageWatcherUtils;
import com.csxs.letterbook.widgets.numbtn.AddWidget;
import com.csxs.viewlib.CircleImageView;
import com.csxs.viewlib.DrawableCenterTextView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.util.BannerUtils;

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

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商家个人主页
 */
public class SellerHomeActivity extends BaseDiffActivity<SellerHomePresenter> implements OnPageChangeListener, AppBarLayout.OnOffsetChangedListener,
        SellerHomeContract.ISellerHomeView, ImageWatcherHelper.Provider, AddWidget.OnAddClick, AddGoodsSpac {


    @BindView(R.id.banner_seller_top)
    Banner bannerSellerTop;
    @BindView(R.id.ci_img_seller)
    CircleImageView ciImgSeller;
    @BindView(R.id.tv_attention_seller)
    DrawableCenterTextView tvAttentionSeller;
    @BindView(R.id.rl_top_banner)
    RelativeLayout rlTopBanner;
    @BindView(R.id.tv_seller_store_name)
    TextView tvSellerStoreName;

    @BindView(R.id.tv_seller_desc)
    TextView tvSellerDesc;
    @BindView(R.id.tab_magicIndicator)
    MagicIndicator tabMagicIndicator;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.view_top)
    View topViewStatus;
    @BindView(R.id.layout_topbar)
    LinearLayout topbarlayout;

    @BindView(R.id.ib_bnt)
    ImageButton ibBnt;

    @BindView(R.id.tv_common_title)
    TextView tvCommonTitle;

    @BindView(R.id.iv_share_store)
    ImageView ivShareStore;

    @BindView(R.id.iv_store_chat_msg)
    ImageView ivStoreChatMsg;


    @BindView(R.id.tv_store_visitor)
    TextView tvStoreVisitor;
    @BindView(R.id.tv_store_fans)

    TextView tvStoreFans;

    @BindView(R.id.tv_store_tag)
    TextView tvStoreTag;

    @BindView(R.id.iv_seller_type_tag)
    ImageView ivSellerTypeTag;

    @BindView(R.id.tv_seller_distance)
    TextView tvSellerDistance;

    @BindView(R.id.banner_num_indicator)
    TextView bannerNumIndicator;

    @BindView(R.id.ll_strat_chat)
    LinearLayout llStratChat;

    @BindView(R.id.tv_comment_shop)
    DrawableCenterTextView tvCommentShop;

    @BindView(R.id.tv_strat_chat)
    DrawableCenterTextView tvStratChat;

    @Inject
    Gson gson;

    @BindView(R.id.car_container)
    LinearLayout carContainer;

    @BindView(R.id.car_mainfl)
    ShopCarView shopCarView;

    @BindView(R.id.blackview)
    View blackView;

    @BindView(R.id.car_recyclerview)
    RecyclerView carRecView;

    @BindView(R.id.tv_clear_car_goods)
    TextView clearCarGoods;


    private ArrayList<Fragment> fragments;

    int position = 0;


    private static final String[] CHANNELS = new String[]{"店铺信息", "商家动态", "商家活动", "商家订单"};
    private List<String> titles = Arrays.asList(CHANNELS);
    private MapSellerDetailsInfoE sellerDetailsInfo;
    private ImageWatcherUtils imageWatcherUtils;
    private GoodsParamBottpmView goddsParamBottpmView;
    private XPopup.Builder xPopup;
    private BasePopupView basePopupView;
    private SellerStoreOrderFragment sellerStoreOrderFragment;
    private SellerStoreInfoFragment sellerStoreInfoFragment;
    private int type;
    private SellerBannerAdapter adapter;
    private SellerStorePromotionFragment sellerStorePromotionFragment;


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
        return R.layout.activity_home_seller;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {


        int statusBarHeight = ScreenUtil.getStatusBarHeight(this);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) topViewStatus.getLayoutParams();
        lp.height = lp.height + statusBarHeight;
        topViewStatus.setLayoutParams(lp);
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能

        adapter = new SellerBannerAdapter(this, null);
        bannerSellerTop.setAdapter(adapter);

        bannerSellerTop.isAutoLoop(true);
        bannerSellerTop.setIndicator(new CircleIndicator(this));
        bannerSellerTop.setOnBannerListener((data, position) -> {
//            Snackbar.make(bannerSellerTop, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();//DataBean 是测试数据
        });

        type = getIntent().getIntExtra("type", -1);
        String details = getIntent().getStringExtra("detailsinfo");
        if (type == 1) {
            //商家地图跳转接受数据
            sellerDetailsInfo = gson.fromJson(details, MapSellerDetailsInfoE.class);
            fillInData();

            initFragment();
        } else if (type == 2) {
            //其他页面点击头像或者别的地方 跳转接受数据
            int shopId = getIntent().getIntExtra("shopId", -1);
            double latitude = getIntent().getDoubleExtra("latitude", 0.0);
            double longitude = getIntent().getDoubleExtra("longitude", 0.0);
            mPresenter.querySingleSellerInfo(shopId, latitude, longitude);
        }


        //添加切换监听
        bannerSellerTop.addOnPageChangeListener(this);

        bannerSellerTop.setStartPosition(0);
        appbarLayout.addOnOffsetChangedListener(this);

        initTab();


        initShopCar();
    }

    private SellerCarAdapter carAdapter;

    public BottomSheetBehavior behavior;

    private void initShopCar() {
        carAdapter = new SellerCarAdapter(new ArrayList<CommodityListE>(), this);
        behavior = BottomSheetBehavior.from(carContainer);
        //  shopCarView = (ShopCarView) findViewById(R.id.car_mainfl);
        // blackView = findViewById(R.id.blackview);
        shopCarView.setBehavior(behavior, blackView, carAdapter);
        // RecyclerView carRecView = (RecyclerView) findViewById(R.id.car_recyclerview);
//		carRecView.setNestedScrollingEnabled(false);
        carRecView.setLayoutManager(new LinearLayoutManager(mContext));
        if (carRecView.getItemAnimator() != null) {

            ((DefaultItemAnimator) carRecView.getItemAnimator()).setSupportsChangeAnimations(false);
        }

        carAdapter.bindToRecyclerView(carRecView);


        clearCarGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCar(v);
            }
        });

        shopCarView.setSubmitGoodsListener(new ShopCarView.SettlementClickListener() {
            @Override
            public void onSettlementSellerGoods(View view) {
                if (carAdapter != null && carAdapter.getData().size() > 0) {
                    submitSellerGoods();
                }

            }
        });


    }

    /**
     * 结算
     */
    private void submitSellerGoods() {
        List<CommodityListE> commodityListE = carAdapter.getData();
        SubmitGoodsE submitGoodsE = new SubmitGoodsE();
        List<SubmitGoodsE.CommListBean> commList = new ArrayList<>();
        for (int i = 0; i < commodityListE.size(); i++) {
            submitGoodsE.setMarchantId(commodityListE.get(i).getMarchantId());
            SubmitGoodsE.CommListBean commListBean = new SubmitGoodsE.CommListBean();
            commListBean.setCommodityId(commodityListE.get(i).getId());
            commListBean.setAmount((int)commodityListE.get(i).getSelectCount());
            if(commodityListE.get(i).getSelectSpec()!=null&&commodityListE.get(i).getSpecResultList().size()>0){
                commListBean.setTempSpecId(commodityListE.get(i).getSelectSpec().getId());
            }

            commList.add(commListBean);
        }

        submitGoodsE.setCommoditys(commList);

        String json = gson.toJson(submitGoodsE);
        Log.e("submitGoods", json);
        Intent intent=new Intent();
        intent.putExtra("goodsinfo",json);
        SingleOrderActivity.start(mContext,SingleOrderActivity.class,intent);
    }

    /**
     * 清空商家购物车
     *
     * @param view
     */
    public void clearCar(View view) {
        showClearCar(mContext, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<CommodityListE> flist = carAdapter.getData();
                for (int i = 0; i < flist.size(); i++) {
                    CommodityListE fb = flist.get(i);
                    fb.setSelectCount(0);
                }
                carAdapter.setNewData(new ArrayList<CommodityListE>());
                shopCarView.showBadge(0);
                shopCarView.updateAmount(new BigDecimal(0.0));
            }
        });
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
//            topbarlayout.setBackgroundColor(color);
              topViewStatus.setBackgroundColor(color);
            ibBnt.setColorFilter(Color.WHITE);
            ivShareStore.setColorFilter(Color.WHITE);
            ivStoreChatMsg.setColorFilter(Color.WHITE);
        } else if (offset > 0 && offset < scrollRange) {
            //算出滑动距离比例
            float scale = (float) offset / scrollRange;
            //得到透明度
            float alpha = (255 * scale);
            color = Color.argb((int) alpha, 255, 255, 255);
            topbarlayout.setBackgroundColor(color);
            //topViewStatus.setBackgroundColor(color);
            ibBnt.setColorFilter(Color.BLACK);
            ivShareStore.setColorFilter(Color.BLACK);
            ivStoreChatMsg.setColorFilter(Color.BLACK);

            tvCommonTitle.setTextColor(Color.argb((int) alpha, 0, 0, 0));

        } else {
            color = Color.argb(255, 255, 255, 255);
            topbarlayout.setBackgroundColor(color);
            //  topViewStatus.setBackgroundColor(color);

            ibBnt.setColorFilter(Color.BLACK);
            ivShareStore.setColorFilter(Color.BLACK);
            ivStoreChatMsg.setColorFilter(Color.BLACK);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (bannerSellerTop != null) {
            bannerSellerTop.start();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();

        if (bannerSellerTop != null) {
            bannerSellerTop.stop();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bannerSellerTop != null) {
            bannerSellerTop.stop();
            bannerSellerTop.destroy();
        }

        if (fragments != null && fragments.size() > 0) {
            fragments.clear();
        }


        if (imageWatcherUtils != null) {
            imageWatcherUtils.onDestroy();
        }
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

    private void initFragment() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }

        sellerStoreInfoFragment = (SellerStoreInfoFragment) SellerStoreInfoFragment.newInstance(GlobalConstants.SELLER_STOREINFO_FRAGMENT);
        if (type == 1) {
            sellerStoreInfoFragment.setArguments(getIntent().getExtras());
        } else {
            String json = gson.toJson(this.sellerDetailsInfo);
            Bundle bundle = new Bundle();
            bundle.putString("detailsinfo", json);
            sellerStoreInfoFragment.setArguments(bundle);
        }

        fragments.add(sellerStoreInfoFragment);

        SellerStoreDynamicFragment sellerStoreDynamicFragment = (SellerStoreDynamicFragment) SellerStoreDynamicFragment.newInstance(GlobalConstants.SELLER_STOREDYNAMIC_FRAGMENT);


        sellerStorePromotionFragment = (SellerStorePromotionFragment) SellerStorePromotionFragment.newInstance(GlobalConstants.SELLER_STOREPROMOTION_FRAGMENT);

        sellerStoreOrderFragment = (SellerStoreOrderFragment) SellerStoreOrderFragment.newInstance(GlobalConstants.SELLER_STOREORDER_FRAGMENT);


        if (type == 1) {
            sellerStoreDynamicFragment.setArguments(getIntent().getExtras());
        } else {
            String json = gson.toJson(this.sellerDetailsInfo);
            Bundle bundle = new Bundle();
            bundle.putString("detailsinfo", json);
            sellerStoreDynamicFragment.setArguments(bundle);
        }

        fragments.add(sellerStoreDynamicFragment);

        fragments.add(sellerStorePromotionFragment);

        fragments.add(sellerStoreOrderFragment);

        SellerPagerAdapter adapter = new SellerPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, titles);

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(position);
        bottomVisibility(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomVisibility(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
    }

    public void bottomVisibility(int index) {
        switch (index) {
            case 0:

                llStratChat.setVisibility(View.VISIBLE);
                tvCommentShop.setVisibility(View.GONE);
                tvStratChat.setVisibility(View.VISIBLE);


                cartHide();


                break;
            case 1:
                llStratChat.setVisibility(View.VISIBLE);
                tvCommentShop.setVisibility(View.VISIBLE);
                tvStratChat.setVisibility(View.VISIBLE);
                cartHide();
                break;
            case 2:
                llStratChat.setVisibility(View.VISIBLE);
                tvCommentShop.setVisibility(View.GONE);
                tvStratChat.setVisibility(View.VISIBLE);
                cartHide();
                break;
            case 3:
                llStratChat.setVisibility(View.GONE);
                tvCommentShop.setVisibility(View.GONE);
                tvStratChat.setVisibility(View.GONE);
                cartShow();
                break;

        }
    }

    public void cartHide() {
        carContainer.setVisibility(View.GONE);
        shopCarView.setVisibility(View.GONE);
        blackView.setVisibility(View.GONE);

    }

    public void cartShow() {
        carContainer.setVisibility(View.VISIBLE);
        shopCarView.setVisibility(View.VISIBLE);
        blackView.setVisibility(View.GONE);

    }


    @Override
    protected void onInitData() {
        imageWatcherUtils = new ImageWatcherUtils(this);
        imageWatcherUtils.init();
    }


    @OnClick({R.id.tv_seller_store_name, R.id.tv_seller_desc, R.id.ib_bnt, R.id.tv_attention_seller, R.id.tv_comment_shop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_seller_store_name:
                break;

            case R.id.tv_seller_desc:
                break;
            case R.id.ib_bnt:
                finish();
                break;
            case R.id.tv_attention_seller:
                if (sellerDetailsInfo.getIsfans() == 0) {
                    mPresenter.attentionUser(sellerDetailsInfo.getId());
                } else {
                    mPresenter.cancleAttentionUser(sellerDetailsInfo.getId());
                }
                break;
            case R.id.tv_comment_shop:
                Intent intent = new Intent();
                intent.putExtra("marchantId", sellerDetailsInfo.getId());
                SendDynamicActivity.start(mContext, SendDynamicActivity.class, intent);
                break;
        }


    }


    @Override
    public void attentionUserSuccess() {
        if (sellerDetailsInfo.getIsfans() == 0) {
            sellerDetailsInfo.setIsfans(1);
        } else {
            sellerDetailsInfo.setIsfans(0);
        }

        attentionUser(sellerDetailsInfo.getIsfans());
    }

    @Override
    public void cancleAttentionUserSuccess() {
        if (sellerDetailsInfo.getIsfans() == 0) {
            sellerDetailsInfo.setIsfans(1);
        } else {
            sellerDetailsInfo.setIsfans(0);
        }

        attentionUser(sellerDetailsInfo.getIsfans());
    }

    @Override
    public void attentionUserFailure(int code, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancleAttentionUserFailure(int code, String msg) {

    }

    @Override
    public void sellerDetailsInfoSuccess(MapSellerDetailsInfoE mapSellerDetailsInfo) {
        this.sellerDetailsInfo = mapSellerDetailsInfo;
        fillInData();
    }

    @Override
    public void sellerDetailsInfoFailure(int code, String msg) {

    }

    public void fillInData() {

        if (sellerDetailsInfo != null) {


            bannerNumIndicator.setText(getResources().getString(R.string.str_banner_num_placeholder, sellerDetailsInfo.getThepointSins().size() == 0? 0:1, sellerDetailsInfo.getThepointSins().size()));
            if (sellerDetailsInfo.getThepointSins() != null && sellerDetailsInfo.getThepointSins().size() > 0) {
                adapter.setDatas(sellerDetailsInfo.getThepointSins());
            }


            String accessNumber = "访客 " + sellerDetailsInfo.getAccessNumber();

            SpannableStringBuilder str = SpannableStringUtils.textFormat(accessNumber, getResources().getColor(R.color.color_E7A124), 2, accessNumber.length(), 1.4f, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            tvStoreVisitor.setText(str);

            String fans = "粉丝 " + sellerDetailsInfo.getFans();
            SpannableStringBuilder str1 = SpannableStringUtils.textFormat(fans, getResources().getColor(R.color.color_E7A124), 2, fans.length(), 1.4f, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            tvStoreFans.setText(str1);

            //昵称
            tvSellerStoreName.setText(getString(R.string.str_placeholder, !sellerDetailsInfo.getNickName().equals("") ? sellerDetailsInfo.getNickName() : sellerDetailsInfo.getContactsMobile()));

            //头像
            ciImgSeller.setImageBitmap(BitmapUtils.stringToBitmap(sellerDetailsInfo.getLogoPic()));

            //商家Id
            // tvSellerId.setText(getString(R.string.str_seller_id_placeholder, sellerDetailsInfo.getId()));

            //简介
            tvSellerDesc.setText(getString(R.string.str_brief_placeholder, !sellerDetailsInfo.getBrief().equals("") ? sellerDetailsInfo.getBrief() : "-"));

            //类型
            tvStoreTag.setText(getString(R.string.str_placeholder, sellerDetailsInfo.getBusinessName().equals("") ? "杂货铺" : "未知"));

            //距离
            tvSellerDistance.setText(getString(R.string.str_distance_placeholder, sellerDetailsInfo.getDistance()));

            attentionUser(sellerDetailsInfo.getIsfans());


            initFragment();


        }


    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (sellerDetailsInfo.getThepointSins() != null) {
            bannerNumIndicator.setText(getResources().getString(R.string.str_banner_num_placeholder, position + 1, sellerDetailsInfo.getThepointSins().size()));
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void attentionUser(int isfans) {
        if (isfans == 0) {
            tvAttentionSeller.setBackground(getResources().getDrawable(R.drawable.bg_tv_attention_seller));
            tvAttentionSeller.setText("关注");
            tvAttentionSeller.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            Drawable nav_up = getResources().getDrawable(R.drawable.ic_seller_attention);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            tvAttentionSeller.setCompoundDrawables(nav_up, null, null, null);

        } else {
            tvAttentionSeller.setBackground(getResources().getDrawable(R.drawable.bg_tv_attentioned_seller));
            tvAttentionSeller.setText("取消关注");
            tvAttentionSeller.setGravity(Gravity.CENTER);
            tvAttentionSeller.setTextColor(getResources().getColor(R.color.white));
            tvAttentionSeller.setCompoundDrawables(null, null, null, null);
        }
    }


    @Override
    public void onBackPressed() {
        if (!imageWatcherUtils.getIwHelper().handleBackPressed()) {
            super.onBackPressed();
        }
    }


    @Override
    public ImageWatcherHelper iwHelper() {
        return imageWatcherUtils.getIwHelper();
    }

    @Override
    public void onAddClick(View view, CommodityListE commodityList) {
        if (view.getTag() != null) {
            //更新右边 recylerView 的 添加按钮商品个数
            //  sellerStoreOrderFragment.get


        }
        dealCar(commodityList);
    }

    @Override
    public void onSubClick(CommodityListE commodityListE) {
        dealCar(commodityListE);
    }

    /**
     * 有规格参数时跳转 选择 规格的界面
     */
    @Override
    public void onSpacDerails(CommodityListE commodityListE) {

        new XPopup.Builder(getContext())
                .hasStatusBarShadow(true)
                .autoOpenSoftInput(true)
                .asCustom(new GoodsParamBottpmView(getContext(), commodityListE, this))
                .show();

    }


    private void dealCar(CommodityListE commodityList) {

        HashMap<String, Long> typeSelect = new HashMap<>();//更新左侧类别badge用
        BigDecimal amount = new BigDecimal(0.0);
        int total = 0;
        boolean hasFood = false;
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            //  firstFragment.getFoodAdapter().notifyDataSetChanged();
        }
        List<CommodityListE> flist = carAdapter.getData();
        int p = -1;
        for (int i = 0; i < flist.size(); i++) {
            CommodityListE fb = flist.get(i);
            if (fb.getId() == commodityList.getId()) {
                fb = commodityList;
                hasFood = true;
                if (commodityList.getSelectCount() == 0) {
                    p = i;
                } else {
                    carAdapter.setData(i, commodityList);
                }
            }
            total += fb.getSelectCount();
//            if (typeSelect.containsKey(fb.getClassifyName())) {
//                typeSelect.put(fb.getClassifyName(), typeSelect.get(fb.getClassifyName()) + fb.getSelectCount());
//            } else {
//                typeSelect.put(fb.getClassifyName(), fb.getSelectCount());
//            }

            amount = amount.add(new BigDecimal(Double.toString(fb.getOriginalPrice())).multiply(BigDecimal.valueOf(fb.getSelectCount())));
        }
        if (p >= 0) {
            carAdapter.remove(p);
        } else if (!hasFood && commodityList.getSelectCount() > 0) {
            carAdapter.addData(commodityList);
//            if (typeSelect.containsKey(commodityList.getClassifyName())) {
//                typeSelect.put(commodityList.getClassifyName(), typeSelect.get(commodityList.getClassifyName()) + commodityList.getSelectCount());
//            } else {
//                typeSelect.put(commodityList.getClassifyName(), commodityList.getSelectCount());
//            }
            amount = amount.add(new BigDecimal(Double.toString(commodityList.getOriginalPrice())).multiply(BigDecimal.valueOf(commodityList.getSelectCount())));
            total += commodityList.getSelectCount();
        }
        shopCarView.showBadge(total);
//        firstFragment.getTypeAdapter().updateBadge(typeSelect);
        shopCarView.updateAmount(amount);

    }


    public static void showClearCar(Context mContext, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        TextView tv = new TextView(mContext);
        tv.setText("清空购物车?");
        tv.setTextSize(14);
        tv.setPadding(ScreenUtil.dip2px(mContext, 16), ScreenUtil.dip2px(mContext, 16), 0, 0);
        tv.setTextColor(Color.parseColor("#757575"));
        AlertDialog alertDialog = builder
                .setNegativeButton("取消", null)
                .setCustomTitle(tv)
                .setPositiveButton("清空", onClickListener)
                .show();
        Button nButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nButton.setTextColor(ContextCompat.getColor(mContext, R.color.dodgerblue));
        nButton.setTypeface(Typeface.DEFAULT_BOLD);
        Button pButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pButton.setTextColor(ContextCompat.getColor(mContext, R.color.dodgerblue));
        pButton.setTypeface(Typeface.DEFAULT_BOLD);
    }


}
