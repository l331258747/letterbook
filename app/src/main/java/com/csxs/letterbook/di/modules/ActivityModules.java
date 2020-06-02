package com.csxs.letterbook.di.modules;



import com.csxs.letterbook.MainActivity;
import com.csxs.letterbook.example.MvcActivity;
import com.csxs.letterbook.di.scopes.ActivityScope;
import com.csxs.letterbook.example.MvpActivity;
import com.csxs.letterbook.home.HomeActivity;
import com.csxs.letterbook.login.SplashActivity;
import com.csxs.letterbook.login.activity.BindPhoneActivity;
import com.csxs.letterbook.login.activity.LoginTypeActivity;
import com.csxs.letterbook.login.activity.PerfectUserInfoActivity;
import com.csxs.letterbook.login.activity.SmsCodeLoginActivity;
import com.csxs.letterbook.mine.activity.MineCartShopActivity;
import com.csxs.letterbook.mine.activity.MineOrderActivity;
import com.csxs.letterbook.mine.activity.ModifyInfoActivity;
import com.csxs.letterbook.mine.activity.ModifyMineInfoActivity;
import com.csxs.letterbook.mine.activity.SendDynamicActivity;
import com.csxs.letterbook.order.SingleOrderActivity;
import com.csxs.letterbook.seller.activity.SellerDynamicCommentActivity;
import com.csxs.letterbook.seller.activity.SellerHomeActivity;
import com.csxs.letterbook.seller.activity.SellerSearchActivity;
import com.csxs.letterbook.social.activity.PersonalHomeActivity;
import com.csxs.letterbook.wxapi.WXEntryActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityModules {


    /**
     * 闪屏Activity
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract SplashActivity contributeSplashActivity();

    /**
     * 选择登录方式Activity
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract LoginTypeActivity contributeLoginTypeActivity();

    /**
     * 短信登录Activity
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract SmsCodeLoginActivity contributeSmsCodeLoginActivity();


    /**
     * 微信登录
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract WXEntryActivity contributeWXEntryActivity();


    /**
     * 绑定手机号码
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract BindPhoneActivity contributeBindPhoneActivity();




    /**
     * 新用户短信登录后 完善基本信息Activity
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract PerfectUserInfoActivity contributePerfectUserInfoActivity();


    /**
     * 主页
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeActivity contributeHomeActivity();

    /**
     * 商家搜索
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract SellerSearchActivity contributeSellerSearchActivity();

    /**
     * 商家主页
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = SellerStoreHomeModule.class)
    abstract SellerHomeActivity contributeSellerHomeActivity();


    /**
     * 我的购物车
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract MineCartShopActivity contributeMineCartShopActivity();

    /**
     * 我的订单
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = MineOrderFragmentModel.class)
    abstract MineOrderActivity contributeMineOrderActivity();


    /**
     * 商家动态 评论
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract SellerDynamicCommentActivity contributeSellerDynamicCommentActivity();

    /**
     * 商家发送动态
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract SendDynamicActivity contributeSendDynamicActivity();


    /**
     * 修改个人信息
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract ModifyMineInfoActivity contributeModifyMineInfoActivity();

    /**
     * 修改个人信息的 某一项
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract ModifyInfoActivity contributeModifyInfoActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = PsersonalHomeModule.class)
    abstract PersonalHomeActivity contributePersonalHomeActivity();


    @ActivityScope
    @ContributesAndroidInjector
    abstract SingleOrderActivity contributeSingleOrderActivity();



    /**
     *示例
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract MvcActivity contributeChangeMainActivity();

    /**
     * 示例Activity
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract MvpActivity contributeMvpActivity();

    /**
     * 示例Activity
     * @return
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();




}
