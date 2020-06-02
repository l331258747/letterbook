package com.csxs.core.base;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

public abstract class BaseLazyFragment extends BaseFragment {

    //是否开启懒加载
    private boolean isLoaded = false;
    //首次显示
    private boolean isFirstVisible = true;
    //当前状态
    private boolean currentVisibleState = false;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isLoaded && !currentVisibleState && isResumed()) {
            isLoaded = true;
            dispatchUserVisibleHint(true);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        //fragment 是不是懒加载,已经加载过 ,并且是 隐藏 ,不是允许状态
        if (isLoaded && currentVisibleState && !isResumed()) {
            isLoaded = false;
            dispatchUserVisibleHint(false);
        }
    }

    private void dispatchUserVisibleHint(boolean visible) {

        if (visible) {
            lazyInitData(isFirstVisible);
        } else {
            fragmentHidden();
        }

        currentVisibleState = visible;
        isFirstVisible = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded = false;
        currentVisibleState = false;
    }

    protected abstract void lazyInitData(boolean isFirstVisible);

    protected abstract void fragmentHidden();
}
