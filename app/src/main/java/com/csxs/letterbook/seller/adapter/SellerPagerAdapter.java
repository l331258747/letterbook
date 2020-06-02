package com.csxs.letterbook.seller.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author: yeliu
 * created on: 2020/2/17 16:12
 * description:
 */
public class SellerPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    List<String> titles;

    public SellerPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public SellerPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragments, List<String> titles) {
        super(fm, behavior);
        this.fragments = fragments;
        this.titles = titles;
    }

    public void setData(List<Fragment> fragments, List<String> titles) {
        this.fragments = fragments;
        this.titles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
