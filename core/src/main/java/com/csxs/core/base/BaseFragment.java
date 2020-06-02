package com.csxs.core.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.csxs.core.BaseConstants;
import com.csxs.core.R;
import com.csxs.core.utils.ExceptionTypeChange;
import com.csxs.viewlib.statelayout.OnRetryListener;
import com.csxs.viewlib.statelayout.StateLayoutManager;
import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * @Author: SenYe
 * @CreateDate: 2020/1/19 22:19
 * @Description: 类作用描述
 */
public abstract class BaseFragment extends Fragment {

    protected String TAG = getClass().getSimpleName();

    public Activity activity;

    public Context mContext;

    private View rootView;

    public boolean isStateView = true;

    private Unbinder mUnbinder;
    private StateLayoutManager statusLayoutManager;
    private OnRetryListener listener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
        this.activity = getActivity();
        this.mContext = context;
        initParam();
    }


    /**
     * 初始化一些参数
     */
    public abstract void initParam();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            if (isStateView) {
                rootView = initStatusLayout();
            } else {
                rootView = inflater.inflate(getLayoutId(), container, false);
            }
            mUnbinder = ButterKnife.bind(this, rootView);
            initView(savedInstanceState);
        }

        ViewGroup parent = (ViewGroup) rootView.getParent();

        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }


    /**
     * 状态布局
     *
     * @return
     */
    public View initStatusLayout() {
        statusLayoutManager = StateLayoutManager.newBuilder(mContext)
                .contentView(getLayoutId())
                // .emptyDataView(R.layout.activity_emptydata)
                .errorView(R.layout.activity_error)
                //  .netWorkErrorView(R.layout.activity_networkerror)
                .errorIconImageId(R.id.iv_error)
                //设置异常页面文本id
                .errorTextTipId(R.id.tv_content)
                //点击事件
                .errorOnClickId(R.id.ll_error_data)
                .onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry() {
                        onStatusLyaoutRetryListener();
                    }
                })
                .build();
        return statusLayoutManager.getRootLayout();

    }


    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);




    /**
     * 展示布局错误状态
     */
    public void showErrorDataStatus(int state) {

        if (ExceptionTypeChange.handleException(state) == BaseConstants.STATE_LAYOUT_EMPTY) {
            showErrorStatus(R.drawable.ic_launcher, "暂无数据");
        } else if (ExceptionTypeChange.handleException(state) == BaseConstants.STATE_LAYOUT_NETWORK) {
            showErrorStatus(R.drawable.ic_address_empty, "网络错误,点击重新加载");
        } else if (ExceptionTypeChange.handleException(state) == BaseConstants.STATE_LAYOUT_DATA) {
            showErrorStatus(R.drawable.ic_launcher, "服务器内部错误");
        }else if(ExceptionTypeChange.handleException(state) == BaseConstants.STATE_LAYOUT_UNKNOWN){
            showErrorStatus(R.drawable.ic_launcher, "未知错误");
        }
    }

    /**
     * 展示布局错误状态
     */
    public void showErrorDataStatus(int state,String msg) {
        if (state == BaseConstants.STATE_LAYOUT_EMPTY) {
            showErrorStatus(R.drawable.ic_address_empty, msg);
        } else if (state == BaseConstants.STATE_LAYOUT_NETWORK) {
            showErrorStatus(R.drawable.ic_launcher, msg);
        } else if (state == BaseConstants.STATE_LAYOUT_DATA) {
            showErrorStatus(R.drawable.ic_launcher, msg);
        }
    }




    /**
     * 展示布局错误状态
     */
    public void showErrorStatus(@DrawableRes int icon, String tip) {
        if (statusLayoutManager != null) {
            statusLayoutManager.showError(icon,tip);
        }
    }


    //数据错误或空数据时重新请求网络
    public void onStatusLyaoutRetryListener() {
        statusLayoutManager.showContent();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyReleaseResource();
    }

    public void destroyReleaseResource() {

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {

            mUnbinder.unbind();
            mUnbinder = null;
        }

    }
}
