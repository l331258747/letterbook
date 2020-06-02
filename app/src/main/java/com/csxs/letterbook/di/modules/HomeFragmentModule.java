package com.csxs.letterbook.di.modules;

import com.csxs.letterbook.di.scopes.FragmentScope;
import com.csxs.letterbook.dynamic.DynamicFragment;
import com.csxs.letterbook.dynamic.fragment.AttentionUserFragment;
import com.csxs.letterbook.dynamic.fragment.NearbyUserFragment;
import com.csxs.letterbook.message.MessageFragment;
import com.csxs.letterbook.mine.MineFragment;
import com.csxs.letterbook.seller.MapSellerFragment;
import com.csxs.letterbook.social.MapSocialUserFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author: yeliu
 * created on 2020/4/13
 * description:Activity所包含的fragment
 */

@Module
public abstract class HomeFragmentModule {

    /**
     * 动态fragment
     * @return
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract DynamicFragment provideDynamicFragment();

    /**
     * 首页商家fragment
     * @return
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract MapSellerFragment provideMapSellerFragment();

    /**
     * 首页社交地图fragment
     * @return
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract MapSocialUserFragment provideMapSocialUserFragment();

    /**
     * 首页消息fragment
     * @return
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract MessageFragment provideMessageFragment();
    @FragmentScope

    /**
     *首页我的fragment
     */
    @ContributesAndroidInjector
    abstract MineFragment provideMineFragment();


    /**
     * 首页 动态 附近的人
     * @return
     */
    @ContributesAndroidInjector
    abstract NearbyUserFragment provideNearbyUserFragment();


    /**
     * 首页 动态 关注的人
     * @return
     */
    @ContributesAndroidInjector
    abstract AttentionUserFragment provideAttentionUserFragment();
}
