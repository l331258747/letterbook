package com.csxs.letterbook.di.modules;

import com.csxs.letterbook.di.scopes.FragmentScope;
import com.csxs.letterbook.dynamic.DynamicFragment;
import com.csxs.letterbook.mine.fragment.MineOrderFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author: yeliu
 * created on 2020/4/26
 * description:
 */
@Module
public abstract class MineOrderFragmentModel {
    /**
     * 动态fragment
     * @return
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract MineOrderFragment provideMineOrderFragment();

}
