package com.csxs.letterbook.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.csxs.core.base.BaseDiffActivity;
import com.csxs.letterbook.GlobalConstants;
import com.csxs.letterbook.R;
import com.csxs.letterbook.dynamic.DynamicFragment;
import com.csxs.letterbook.home.mvp.HomePresenter;
import com.csxs.letterbook.message.MessageFragment;
import com.csxs.letterbook.mine.MineFragment;
import com.csxs.letterbook.seller.MapSellerFragment;
import com.csxs.letterbook.seller.listener.MapDataListener;
import com.csxs.letterbook.social.MapSocialUserFragment;
import com.csxs.letterbook.widgets.imagewatcher.ImageWatcherHelper;
import com.csxs.letterbook.widgets.imagewatcher.ImageWatcherUtils;
import com.csxs.viewlib.bottombar.BottomNavigationViewEx;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;

/**
 * @author: yeliu
 * created on 2020/4/13
 * description:首页Activity 承载首页的fragment
 */
public class HomeActivity extends BaseDiffActivity<HomePresenter> implements MapDataListener, ImageWatcherHelper.Provider{

    @BindView(R.id.bottom_tab)
    BottomNavigationViewEx bottomNavigationViewEx;

    private Fragment currentFragment;
    private int currentFragmentPos;

    private DynamicFragment dynamicFragment;
    private MapSellerFragment mapSellerFragment;
    private MapSocialUserFragment mapSocialUserFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;

    private  boolean mapRenderFinished;
    private ImageWatcherUtils imageWatcherUtils;

    @Override
    public void initParam() {
        super.initParam();
        hideTitleBar = false;
        isStateView = false;
        topBarView = false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {

        initFragment(savedInstanceState);
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextSize(10);
        bottomNavigationViewEx.setCurrentItem(1);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(item -> {
                int position = bottomNavigationViewEx.getMenuItemPosition(item);
                if (position == 0) {
                    if (dynamicFragment == null) {
                        dynamicFragment = DynamicFragment.newInstance(GlobalConstants.DYNAMIC_FRAGMENT);
                    }
                    //设置状态栏字体为白色
                    mImmersionBar.statusBarDarkFont(false).init();
                    switchFragment(dynamicFragment, GlobalConstants.DYNAMIC_FRAGMENT, 0);
                } else if (position == 1) {
                    if (mapSellerFragment == null) {
                        mapSellerFragment = MapSellerFragment.newInstance(GlobalConstants.MAP_SELLER_FRAGMENT);
                    }
                    mImmersionBar.statusBarDarkFont(true).init();
                    switchFragment(mapSellerFragment, GlobalConstants.MAP_SELLER_FRAGMENT, 1);
                } else if (position == 2) {
                    if (mapSocialUserFragment == null) {
                        mapSocialUserFragment = MapSocialUserFragment.newInstance(GlobalConstants.MAP_SOCIAL_FRAGMENT);
                    }
                    mImmersionBar.statusBarDarkFont(true).init();
                    switchFragment(mapSocialUserFragment, GlobalConstants.MAP_SOCIAL_FRAGMENT, 2);
                } else if (position == 3) {
                    if (messageFragment == null) {
                        messageFragment = MessageFragment.newInstance(GlobalConstants.MESSAGE_FRAGMENT);
                    }
                    mImmersionBar.statusBarDarkFont(true).init();
                    switchFragment(messageFragment, GlobalConstants.MESSAGE_FRAGMENT, 3);
                } else if (position == 4) {
                    if (mineFragment == null) {
                        mineFragment = MineFragment.newInstance(GlobalConstants.MESSAGE_FRAGMENT);
                    }
                    mImmersionBar.statusBarDarkFont(true).init();
                    switchFragment(mineFragment, GlobalConstants.MINE_FRAGMENT, 4);
                }

//                bottomNavigationViewEx.setCurrentItem(position);
            return true;
        });


        imageWatcherUtils = new ImageWatcherUtils(this);
        imageWatcherUtils.init();
    }


    @Override
    public void MapMarkerDataListener(boolean mapRenderFinished) {
        this.mapRenderFinished=mapRenderFinished;
    }


    @Override
    protected void onInitData() {

    }



    private void initFragment(Bundle savedInstanceState) {

     //


        if (savedInstanceState != null) {
            int index = savedInstanceState.getInt(GlobalConstants.STATE_SAVE_IS_HIDDEN);
            FragmentManager fm = getSupportFragmentManager();
            dynamicFragment = (DynamicFragment) fm.findFragmentByTag(GlobalConstants.DYNAMIC_FRAGMENT);
            mapSellerFragment = (MapSellerFragment) fm.findFragmentByTag(GlobalConstants.MAP_SELLER_FRAGMENT);
            mapSocialUserFragment = (MapSocialUserFragment) fm.findFragmentByTag(GlobalConstants.MAP_SOCIAL_FRAGMENT);
            messageFragment = (MessageFragment) fm.findFragmentByTag(GlobalConstants.MESSAGE_FRAGMENT);
            mineFragment = (MineFragment) fm.findFragmentByTag(GlobalConstants.MINE_FRAGMENT);
            hideFragment();
            if (index == 0) {
                switchFragment(dynamicFragment, GlobalConstants.DYNAMIC_FRAGMENT, 0);
            } else if (index == 1) {
                switchFragment(mapSellerFragment, GlobalConstants.MAP_SELLER_FRAGMENT, 1);
            } else if (index == 2) {
                switchFragment(mapSocialUserFragment, GlobalConstants.MAP_SOCIAL_FRAGMENT, 2);
            } else if (index == 3) {
                switchFragment(messageFragment, GlobalConstants.MESSAGE_FRAGMENT, 3);
            } else if (index == 4) {
                switchFragment(mineFragment, GlobalConstants.MINE_FRAGMENT, 4);
            }

        } else {
            //正常初始化fragment
            mapSellerFragment = MapSellerFragment.newInstance(GlobalConstants.MAP_SELLER_FRAGMENT);
            switchFragment(mapSellerFragment, GlobalConstants.MAP_SELLER_FRAGMENT, 1);

        }
    }


    /**
     * 切换fragment
     *
     * @param targetFragment
     * @param tag
     * @param index
     */
    private void switchFragment(Fragment targetFragment, String tag, int index) {
        //从Androidx 源码可以看到 首先从Adds集合中去找 找到就直接返回,如果没找到 就会去当前已知的mActive中去找(在FragmentManagerImpl 中onCreateView会把Fragment添加到mActive中)
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //用tag从缓存中找fragment,不为空,表示fragment不是第一次展示
        if (fragment != null) {
            if (fragment.isAdded()) {
                //如果需要展示出的这个Fragment是被添加过的，那么直接隐藏当前Fragment，展示这个Fragment
                if (currentFragment!=null&&currentFragment != fragment) {
                    fragmentTransaction.hide(currentFragment).show(fragment);
                    //隐藏的fragment的最大Lifecycle为Lifecycle.State.STARTED
                    fragmentTransaction.setMaxLifecycle(currentFragment, Lifecycle.State.STARTED);
                    //显示位置的Fragment，最大Lifecycle为Lifecycle.State.RESUMED
                    fragmentTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
                }

            } else {

                //如果没有被添加过 隐藏当前currentFragment,展示需要展示的fragment
                fragmentTransaction.hide(currentFragment).add(R.id.home_container, fragment, tag).show(fragment);
                fragmentTransaction.setMaxLifecycle(currentFragment, Lifecycle.State.STARTED);
                fragmentTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);

            }
            currentFragment = fragment;
        } else {
            //从缓存中没有找到fragment,判断当前的fragment是否为null
            if (currentFragment != null && targetFragment != null) {
                if (!targetFragment.isAdded()) {
                    fragmentTransaction.hide(currentFragment).add(R.id.home_container, targetFragment, tag);
                }else{
                    fragmentTransaction.hide(currentFragment).show(targetFragment);
                }
                fragmentTransaction.setMaxLifecycle(currentFragment, Lifecycle.State.STARTED);
                fragmentTransaction.setMaxLifecycle(targetFragment, Lifecycle.State.RESUMED);

            } else if (targetFragment != null) {
                fragmentTransaction.add(R.id.home_container, targetFragment, tag).show(targetFragment);
                fragmentTransaction.setMaxLifecycle(targetFragment, Lifecycle.State.RESUMED);
            }

            currentFragment = targetFragment;
        }


        currentFragmentPos = index;
        fragmentTransaction.commit();
        getSupportFragmentManager().executePendingTransactions();
    }


    public void  switchFragment2(Fragment targetFragment, String tag, int index){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
          if(targetFragment!=null){
              if(!targetFragment.isAdded()){
                  if(currentFragment==null){
                      fragmentTransaction.add(R.id.home_container, targetFragment, tag).show(targetFragment);
                      fragmentTransaction.setMaxLifecycle(targetFragment, Lifecycle.State.RESUMED);
                      Log.e("switchFragment1","targetFragment 没有被添加过,currentFragment为空");
                  }else if(currentFragment!=targetFragment){
                      fragmentTransaction.hide(currentFragment).add(R.id.home_container, targetFragment, tag).show(targetFragment);
                      //隐藏的fragment的最大Lifecycle为Lifecycle.State.STARTED
                      //fragmentTransaction.setMaxLifecycle(currentFragment, Lifecycle.State.STARTED);
                      //显示位置的Fragment，最大Lifecycle为Lifecycle.State.RESUMED
                     // fragmentTransaction.setMaxLifecycle(targetFragment, Lifecycle.State.RESUMED);
                      Log.e("switchFragment1","targetFragment不为空,targetFragment没有被添加过,currentFragment也不为空,currentFragment不等于,targetFragment");
                  }
                  currentFragment=targetFragment;
              }else{
                  //已经add过 并且 currentFragment!=null 情况下
                  if(currentFragment!=null&&currentFragment!=targetFragment){
                      fragmentTransaction.hide(currentFragment).show(targetFragment);
                      //隐藏的fragment的最大Lifecycle为Lifecycle.State.STARTED
                     // fragmentTransaction.setMaxLifecycle(currentFragment, Lifecycle.State.STARTED);
                      //显示位置的Fragment，最大Lifecycle为Lifecycle.State.RESUMED
                    //  fragmentTransaction.setMaxLifecycle(targetFragment, Lifecycle.State.RESUMED);

                      Log.e("switchFragment1","currentFragment非为空 currentFragment 被添加过");
                  }else if(currentFragment!=targetFragment){
                    //  fragmentTransaction.add(R.id.home_container, targetFragment, tag).show(targetFragment);
                      fragmentTransaction.show(targetFragment);
                    //  fragmentTransaction.setMaxLifecycle(targetFragment, Lifecycle.State.RESUMED);

                      Log.e("switchFragment1","currentFragment为空 currentFragment 被添加过");
                  }
                  currentFragment=targetFragment;
              }

          }else{

              Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
              if(fragment!=null){
                  if (fragment.isAdded()) {
                      if (currentFragment!=null&&currentFragment != fragment) {
                          fragmentTransaction.hide(currentFragment).show(fragment);
                          //隐藏的fragment的最大Lifecycle为Lifecycle.State.STARTED
                     //     fragmentTransaction.setMaxLifecycle(currentFragment, Lifecycle.State.STARTED);
                          //显示位置的Fragment，最大Lifecycle为Lifecycle.State.RESUMED
                       //   fragmentTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);

                          Log.e("switchFragment1","targetFragment为空  fragment 非空,currentFragment非空");
                      }
                  }else{
                      fragmentTransaction.add(R.id.home_container, fragment, tag).show(fragment);
                      //fragmentTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);

                      Log.e("switchFragment1","targetFragment为空  fragment 非空,currentFragment为空");
                  }
                  currentFragment=fragment;
              }
          }

        currentFragmentPos = index;
        fragmentTransaction.commit();
        getSupportFragmentManager().executePendingTransactions();

    }


    /**
     * 隐藏所有Fragment
     */
    public void hideFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (dynamicFragment != null) {
            fragmentTransaction.hide(dynamicFragment);
        }

        if (mapSellerFragment != null) {
            fragmentTransaction.hide(mapSellerFragment);
        }

        if (mapSocialUserFragment != null) {
            fragmentTransaction.hide(mapSocialUserFragment);
        }

        if (messageFragment != null) {
            fragmentTransaction.hide(messageFragment);
        }

        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }

        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.e("onSaveInstanceState","fragment 异常保存");
        outState.putInt(GlobalConstants.STATE_SAVE_IS_HIDDEN, currentFragmentPos);
        super.onSaveInstanceState(outState);

    }


    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // 总是调用超类，以便它可以恢复视图层次超级
        Log.e("onSaveInstanceState","fragment 恢复");
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //你的代码
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        imageWatcherUtils.onDestroy();
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
}
