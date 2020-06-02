package com.csxs.core.base;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

import com.csxs.core.utils.Preconditions;

import javax.inject.Inject;

import static android.view.View.NO_ID;

/**
 * author: yeliu
 * created on: 2020/1/8 16:07
 * description:
 */
public abstract class BaseDiffActivity<P extends BasePresenter> extends BaseActivity implements IBaseView {

    @Inject
    public P mPresenter;
    private boolean isShowing;//虚拟导航栏是否存在


    @Override
    protected void initPresenter() {
        super.initPresenter();
        Preconditions.checkNotNull(mPresenter, "Presenter is null");
        mPresenter.attachView(this);
        getOwnerLifeCycle().addObserver(mPresenter);
        isNavigationBarExist(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(int code, String message) {
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Lifecycle getOwnerLifeCycle() {
        return this.getLifecycle();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter = null;
        }
    }


//    public  void isNavigationBarExist(Activity activity) {
//        if (activity == null) {
//            return;
//        }
//        final int height = getNavigationHeight(activity);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
//            activity.getWindow().getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
//                @Override
//                public WindowInsets onApplyWindowInsets(View v, WindowInsets windowInsets) {
//                    boolean isShowing = false;
//                    int b = 0;
//                    if (windowInsets != null) {
//                        b = windowInsets.getSystemWindowInsetBottom();
//                        isShowing = (b == height);
//                    }
//                    if (b <= height) {
////                        onNavigationStateListener.onNavigationState(isShowing, b);
//                        Log.e("isNavigationBarExist",isShowing+"------>>"+b);
//                    }
//                    return windowInsets;
//                }
//            });
//        }
//    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        isShowing = isNavigationBarExist(this);

    }

    private static final String NAVIGATION = "navigationBarBackground";

    //判断虚拟导航栏是否存在
    public static boolean isNavigationBarExist(@NonNull Activity activity) {
        ViewGroup vp = (ViewGroup) activity.getWindow().getDecorView();
        if (vp != null) {
            for (int i = 0; i < vp.getChildCount(); i++) {
                vp.getChildAt(i).getContext().getPackageName();
                if (vp.getChildAt(i).getId() != NO_ID && NAVIGATION.equals(activity.getResources().getResourceEntryName(vp.getChildAt(i).getId()))) {
                    return true;
                }
            }
        }
        return false;
    }

}
