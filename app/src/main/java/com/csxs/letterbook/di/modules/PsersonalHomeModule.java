package com.csxs.letterbook.di.modules;

import com.csxs.letterbook.di.scopes.FragmentScope;
import com.csxs.letterbook.seller.fragment.SellerStoreDynamicFragment;
import com.csxs.letterbook.seller.fragment.SellerStoreInfoFragment;
import com.csxs.letterbook.seller.fragment.SellerStoreOrderFragment;
import com.csxs.letterbook.seller.fragment.SellerStorePromotionFragment;
import com.csxs.letterbook.social.activity.PersonalHomeActivity;
import com.csxs.letterbook.social.fragment.PersonalDynamicFragment;
import com.csxs.letterbook.social.fragment.PersonalInfoFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description: 个人主页 拥有的fragment
 */

@Module
public abstract class PsersonalHomeModule {


    /**
     * 个人基本信息资料
     * @return
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract PersonalInfoFragment providePersonalInfoFragment();

    /**
     * 商家活动fragment
     * @return
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract PersonalDynamicFragment providePersonalDynamicFragment();

//    /**
//     * 商家活动fragment
//     * @return
//     */
//    @FragmentScope
//    @ContributesAndroidInjector
//    abstract SellerStorePromotionFragment provideSellerStorePromotionFragment();


}
