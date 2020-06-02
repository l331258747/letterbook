package com.csxs.letterbook.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowInsets;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.csxs.core.base.BaseDiffActivity;
import com.csxs.core.imageloader.GlideApp;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.gallery.SelectorImageUtils;
import com.csxs.letterbook.R;
import com.csxs.letterbook.home.HomeActivity;
import com.csxs.letterbook.login.mvp.contract.PrefectUserInfoContract;
import com.csxs.letterbook.login.mvp.presenter.PerfectUserInfoPresenter;
import com.csxs.viewlib.dialog.BindViewHolder;
import com.csxs.viewlib.dialog.TDialog;
import com.csxs.viewlib.dialog.listener.OnViewClickListener;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: yeliu
 * created on 2020/4/10
 * description:完善用户资料 activity
 */
public class PerfectUserInfoActivity extends BaseDiffActivity<PerfectUserInfoPresenter> implements PrefectUserInfoContract.IPrefectUserInfoView {

    @BindView(R.id.iv_user_header)
    ImageView ivUserHeader;

    @BindView(R.id.et_input_user_nickname)
    EditText etInputUserNickname;

    @BindView(R.id.iv_perfect_man)
    ImageView ivPerfectMan;

    @BindView(R.id.iv_select_perfrct_man)
    ImageView ivSelectPerfrctMan;

    @BindView(R.id.tv_text_man)
    TextView tvTextMan;

    @BindView(R.id.iv_perfect_woman)
    ImageView ivPerfectWoman;

    @BindView(R.id.iv_select_perfrct_woman)
    ImageView ivSelectPerfrctWoman;

    @BindView(R.id.tv_perfect_userinfo_ok)
    TextView tvPerfectUserinfoOk;

    private int isSelect = -1;

    @Inject
    ImageLoaderV4 imageLoaderV4;
    private String cutPath;

    @Override
    public void initParam() {
        super.initParam();
        hideTitleBar = false;
        isStateView = false;
        topBarView = false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_perfect_userinfo;
    }


    @Override
    public void setWindowBackGround() {
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.color.white);
        this.getWindow().setBackgroundDrawable(drawable);
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitData() {

    }


    @OnClick({R.id.iv_user_header, R.id.iv_perfect_man, R.id.iv_select_perfrct_man, R.id.iv_perfect_woman, R.id.iv_select_perfrct_woman, R.id.tv_perfect_userinfo_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_header:
//                isNavigationBarExist(activity,);
                new TDialog.Builder(getSupportFragmentManager())
                        .setLayoutRes(R.layout.dialog_select_img)
                        .setScreenWidthAspect(this, 1.0f)
                        .setDimAmount(0.5f)
                        .setGravity(Gravity.BOTTOM)
                        .setDialogAnimationRes(R.style.animate_bottom_dialog)
                        .addOnClickListener(R.id.tv_open_camera, R.id.tv_open_album, R.id.tv_cancel)
                        .setOnViewClickListener(new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                                switch (view.getId()) {
                                    case R.id.tv_open_camera:
                                        openCamera();
                                        tDialog.dismiss();
                                        break;
                                    case R.id.tv_open_album:
                                        openAlbum();
                                        tDialog.dismiss();
                                        break;
                                    case R.id.tv_cancel:
                                        tDialog.dismiss();
                                        break;
                                }
                            }
                        })
                        .create()
                        .show();
                break;
            case R.id.iv_perfect_man:
                isSelect = 0;
                ivSelectPerfrctMan.setVisibility(View.VISIBLE);
                ivSelectPerfrctWoman.setVisibility(View.GONE);
                break;

            case R.id.iv_perfect_woman:
                isSelect = 1;
                ivSelectPerfrctMan.setVisibility(View.GONE);
                ivSelectPerfrctWoman.setVisibility(View.VISIBLE);
                break;

            case R.id.tv_perfect_userinfo_ok:

                String niackname=etInputUserNickname.getText().toString();
                 if(cutPath==null || cutPath.equals("")){
                     Toast.makeText(mContext,"请上传头像",Toast.LENGTH_SHORT).show();
                     return;
                 }

                if("".equals(niackname)){
                    Toast.makeText(mContext,"请填写昵称",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(isSelect==-1){
                    Toast.makeText(mContext,"请选择性别",Toast.LENGTH_SHORT).show();
                    return;
                }

                submitUserInfo(niackname,"",isSelect,new File(cutPath));
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            switch (requestCode) {
                case PictureConfig.REQUEST_CAMERA:
                    break;
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    if (selectList.size() > 0) {
//                        if(selectList.get(0).isCut()){
                        cutPath = selectList.get(0).getCutPath();
                        imageLoaderV4.displayCircleImage(activity, cutPath, ivUserHeader, R.drawable.ic_logo_login_type);
//                        }
                    }
                    break;
            }
        }
    }


    /**
     * 拍照
     */
    public void openCamera() {
        SelectorImageUtils.launcherCamera(this);
    }


    /**
     * 打开相册
     */
    public void openAlbum() {
        //选择头像
        SelectorImageUtils.launcher(this, true, null);
    }

    /**
     * 提交新用户的完善的基本资料
     */
    private void submitUserInfo(String nackname, String phoneNumber, int sex, File file) {
        mPresenter.submitUserInfo(nackname,phoneNumber,sex,file);
    }

    @Override
    public void submitSuccess() {
        HomeActivity.start(this, HomeActivity.class, null);
    }


//    public static int getNavigationHeight(Context activity) {
//        if (activity == null) {
//            return 0;
//        }
//        Resources resources = activity.getResources();
//        int resourceId = resources.getIdentifier("navigation_bar_height",
//                "dimen", "android");
//        int height = 0;
//        if (resourceId > 0) {
//            //获取NavigationBar的高度
//            height = resources.getDimensionPixelSize(resourceId);
//        }
//        return height;
//    }


}
