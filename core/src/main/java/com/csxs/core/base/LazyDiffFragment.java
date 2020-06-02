package com.csxs.core.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

import com.csxs.core.utils.Preconditions;

import javax.inject.Inject;

/**
 * author: yeliu
 * created on: 2020/2/19 15:20
 * description:
 */
public abstract class LazyDiffFragment<P extends BasePresenter> extends BaseFragment implements IBaseView {
    //是否开启懒加载 默认开启
    private boolean isLoaded = true;
    //首次显示
    private boolean isFirstVisible = true;
    //当前状态
    private boolean currentVisibleState = false;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Inject
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Preconditions.checkNotNull(mPresenter, "Presenter is null");
        mPresenter.attachView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isLoaded && !currentVisibleState && isResumed()) {
            dispatchUserVisibleHint(true);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (isLoaded && currentVisibleState && !isResumed()) {
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
    public void showError(int code,String message) {
        showErrorDataStatus(code);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }





    protected abstract void lazyInitData(boolean isFirstVisible);

    protected abstract void fragmentHidden();


    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public Lifecycle getOwnerLifeCycle() {
        return this.getLifecycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter = null;
        }

        isLoaded = false;
        currentVisibleState = false;
    }
}
