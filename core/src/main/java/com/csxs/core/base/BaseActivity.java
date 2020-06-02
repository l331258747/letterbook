package com.csxs.core.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.csxs.core.BaseConstants;
import com.csxs.core.R;
import com.csxs.core.utils.AppManager;
import com.csxs.core.utils.FixMemLeakUtils;
import com.csxs.viewlib.statelayout.OnRetryListener;
import com.csxs.viewlib.statelayout.StateLayoutManager;
import com.csxs.viewlib.titlebar.CommonTitleBar;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * @Author: SenYe
 * @CreateDate: 2019/12/23 23:17
 * @Description: 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    //上下文
    public Context mContext;
    //activity
    public Activity activity;
    //根布局
    public LinearLayout root;
    //布局加载器
    public LayoutInflater layoutInflater;

    //沉侵式
    protected ImmersionBar mImmersionBar;
    //是否需要 状态栏 占位View
    public boolean topBarView = true;
    //状态栏占位View
    public View topView;
    //是否需要titlebar
    public boolean hideTitleBar = true;
    //是否需要 空布局
    public boolean isStateView = true;

    //是否需要EventBus
    public boolean needEventBus=false;

    //titlebar
    private CommonTitleBar titleBar;
    //状态布局
    private StateLayoutManager statusLayoutManager;
    //viewBind
    public Unbinder mUnbinder;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        initParam();
    }

    /**
     * 初始化一些参数
     */
    public void initParam() {
        mContext = this;
        activity = this;
    }


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        initPresenter();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_base);
        root = findViewById(R.id.activity_base_layout);
        initStatusBar();
        setWindowBackGround();
        AppManager.getAppManager().addActivity(this);
        layoutInflater = getLayoutInflater();
        setLayoutContent(getLayoutId());
        initEventBus();
        onInitView(savedInstanceState);
        onInitData();
    }

    private void initEventBus() {
        if(needEventBus){
            EventBus.getDefault().register(this);
        }

    }

    protected void initPresenter() {
        
    }
    

    /**
     * 添加title栏与状态布局
     *
     * @param layoutId
     */
    private void setLayoutContent(int layoutId) {
        //是否添加title栏
        if (hideTitleBar) {
            View titlelayout = layoutInflater.inflate(R.layout.comment_title_bar, null, false);
            titleBar = titlelayout.findViewById(R.id.titlebar);
            titleBar.getBottomLine().setVisibility(View.GONE);
            if (topBarView) {
                root.addView(titlelayout, 1);
            } else {
                root.addView(titlelayout, 0);
            }

            titleBar.setListener((v, action, extra) -> {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    back();
                }

                if (action == CommonTitleBar.ACTION_RIGHT_TEXT) {
                    rightClick(v);
                }
            });

        }

        //是否添加状态布局
        if (isStateView) {
            statusLayoutManager = StateLayoutManager.newBuilder(this)
                    .contentView(layoutId)
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
            root.addView(statusLayoutManager.getRootLayout());

        } else {
            View view = layoutInflater.inflate(layoutId, null, false);
            root.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        }

        mUnbinder = ButterKnife.bind(this);
    }


    /**
     * 初始化沉侵式状态栏
     */
    public void initStatusBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarColor(R.color.white);
        if (topBarView) {
            topView = new View(this);
            int statusBarHeight = ImmersionBar.getStatusBarHeight(this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = statusBarHeight;
            topView.setLayoutParams(params);
            //解决状态栏和布局重叠问题
            topView.setBackgroundColor(getResources().getColor(R.color.white));
            root.addView(topView, 0);
        }

        mImmersionBar.statusBarDarkFont(true);
        mImmersionBar.init();
    }

    public ImmersionBar getImmersionBar() {
        return mImmersionBar;
    }

    /**
     * 这只状态栏颜色
     *
     * @param color
     */
    public void setStatuBarColor(int color) {
        if (topView != null) {
            if (color != 0) {
                topView.setBackgroundColor(getResources().getColor(color));
            }
        }
    }


    public boolean isActivityFinishing() {
        if (this != null && this.isFinishing()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置Window背景
     */
    public void setWindowBackGround() {
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.color.white_f1f1f1);
        this.getWindow().setBackgroundDrawable(drawable);
    }

    public abstract int getLayoutId();

    public abstract void onInitView(Bundle savedInstanceState);

    protected abstract void onInitData();


    /**
     * 是否隐藏titleBar
     *
     * @param hideTitleBar
     */
    public void setHideTitleBar(boolean hideTitleBar) {
        if (hideTitleBar) {
            titleBar.setVisibility(View.GONE);
        } else {
            titleBar.setVisibility(View.VISIBLE);
        }
    }


    /**
     * titlebar背景颜色
     *
     * @param color
     */
    public void setTitleBarBackgroundColor(int color) {
        titleBar.setBackgroundColor(getResources().getColor(color));
    }

    /**
     * titlebar中间文字颜色
     *
     * @param color
     */
    public void setTitleBarCenterTextColor(int color) {
        titleBar.getCenterTextView().setTextColor(getResources().getColor(color));
    }

    /**
     * titlebar隐藏左边按钮图片
     */
    public void setHideleftImage() {
        titleBar.getLeftImageButton().setVisibility(View.GONE);
    }

    /**
     * titlebar设置左边按钮图片
     *
     * @param image
     */
    public void setLeftImage(int image) {
        ImageButton btnLeft = titleBar.getLeftImageButton();
        if (btnLeft != null) {
            btnLeft.setImageResource(image);
        }

    }

    /**
     * 设置中间文字
     * titlebar
     *
     * @param title
     */
    public void setCenterMainTitle(String title) {
        TextView centerTextView = titleBar.getCenterTextView();
        centerTextView.setText(title);
    }

    public void setCenterMainTitle(String title, int color) {
        TextView centerTextView = titleBar.getCenterTextView();
        centerTextView.setText(title);
        centerTextView.setTextColor(getResources().getColor(color));
    }

    /**
     * 是否隐藏titlebar下划线
     *
     * @param b
     */
    public void setBottomLine(boolean b) {
        if (b) {
            titleBar.getBottomLine().setVisibility(View.VISIBLE);
        } else {
            titleBar.getBottomLine().setVisibility(View.GONE);
        }
    }

    public void setCustomRightText(String str, boolean show, int backgroud,int textColor,int textSize) {
        titleBar.getRightTextView().setVisibility(View.GONE);
        View mCustomView = LayoutInflater.from(this).inflate(R.layout.layout_custom_right, null, false);
        TextView mCustomRightTextView = mCustomView.findViewById(R.id.tv_save);
        mCustomRightTextView.setText(str);
        mCustomRightTextView.setTextSize(textSize);
        mCustomRightTextView.setTextColor(getResources().getColor(textColor));
        mCustomRightTextView.setBackground(getResources().getDrawable(backgroud));
        mCustomRightTextView.setOnClickListener(this::rightClick);
        mCustomRightTextView.setVisibility(View.VISIBLE);
        titleBar.setRightView(mCustomView);
        if (show) {
            mCustomView.setVisibility(View.VISIBLE);
        } else {
            mCustomView.setVisibility(View.GONE);
        }


    }


    /**
     * 设置右边文字与颜色
     *
     * @param text
     */
    public void setRightText(String text, int color) {
        if (color != 0) {
            titleBar.setRightText(text, 14, getResources().getColor(color));
        } else {
            titleBar.setRightText(text, 14, getResources().getColor(R.color.text_color));
        }
    }

    /**
     * 隐藏右边文字
     *
     * @param hide
     */
    public void setHideRightText(boolean hide) {

        if (titleBar.getRightTextView() != null) {
            if (hide) {
                titleBar.getRightTextView().setVisibility(View.GONE);
            } else {
                titleBar.getRightTextView().setVisibility(View.VISIBLE);
            }

        }
    }

    /**
     * 设置右边按钮图片
     *
     * @param icon
     */
    public void setRightImage(int icon) {
        titleBar.getRightTextView().setVisibility(View.GONE);
        View customRightImage = LayoutInflater.from(this).inflate(R.layout.layout_custom_right, null, false);
        ImageView imageView = customRightImage.findViewById(R.id.iv_write_note);
        imageView.setImageResource(icon);
        imageView.setOnClickListener((v -> rightClick(v)));
        imageView.setVisibility(View.VISIBLE);
        titleBar.setRightView(customRightImage);
    }

    protected void back() {
        finish();
    }

    //title栏 右边btn 点击事件
    protected void rightClick(View v) {

    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }


    /**
     * 展示布局错误状态
     */
    public void showErrorDataStatus(int state) {
        if (state == BaseConstants.STATE_LAYOUT_EMPTY) {
            showErrorDataStatus(R.drawable.ic_back_black, "暂无数据");
        } else if (state == BaseConstants.STATE_LAYOUT_NETWORK) {
            showErrorDataStatus(R.drawable.ic_launcher, "网络错误,点击重新加载");
        } else if (state == BaseConstants.STATE_LAYOUT_DATA) {
            showErrorDataStatus(R.drawable.ic_back_black, "数据错误,请稍后再试");
        }
    }

    /**
     * 展示布局错误状态
     */
    public void showErrorDataStatus(@DrawableRes int icon, String tip) {
        if (statusLayoutManager != null) {
            statusLayoutManager.showError(icon, tip);
        }

    }


    //数据错误或空数据时重新请求网络
    public void onStatusLyaoutRetryListener() {
        statusLayoutManager.showContent();
    }


    public static void start(Context context, Class clzss, Intent extras) {
        Intent intent = new Intent(context, clzss);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyReleaseResource();
    }

    public void onDestroyReleaseResource() {

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }


        AppManager.getAppManager().finishActivity(this);

        //华为手机内存泄漏修复
        FixMemLeakUtils.fixLeak(this);

        //观察内存泄漏
//        RefWatcher refWatcher = BaseCoreApp.getRefWatcher(this);//1
//        refWatcher.watch(this);
    }
}
