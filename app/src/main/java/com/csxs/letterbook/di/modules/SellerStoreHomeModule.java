package com.csxs.letterbook.di.modules;

import com.csxs.letterbook.di.scopes.FragmentScope;
import com.csxs.letterbook.dynamic.DynamicFragment;
import com.csxs.letterbook.seller.MapSellerFragment;
import com.csxs.letterbook.seller.fragment.SellerStoreDynamicFragment;
import com.csxs.letterbook.seller.fragment.SellerStoreInfoFragment;
import com.csxs.letterbook.seller.fragment.SellerStoreOrderFragment;
import com.csxs.letterbook.seller.fragment.SellerStorePromotionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description: 商家主页 拥有的fragment
 */

@Module
public abstract class SellerStoreHomeModule {

    /**
     * 商家信息fragment
     * @return
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract SellerStoreInfoFragment provideSellerStoreInfoFragment();

    /**
     * 商家动态fragment
     * @return
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract SellerStoreDynamicFragment provideSellerStoreDynamicFragment();

    /**
     * 商家活动fragment
     * @return
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract SellerStorePromotionFragment provideSellerStorePromotionFragment();

    /**
     * 商家订单fragment
     * @return
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract SellerStoreOrderFragment provideSellerSellerStoreOrderFragment();
}
