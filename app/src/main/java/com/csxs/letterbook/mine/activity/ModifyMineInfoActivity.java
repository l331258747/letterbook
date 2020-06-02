package com.csxs.letterbook.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.core.BaseConstants;
import com.csxs.core.base.BaseDiffActivity;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.core.utils.StringUtil;
import com.csxs.core.utils.TimeStampUtils;
import com.csxs.core.utils.gallery.GlideCacheEngine;
import com.csxs.core.utils.gallery.GlideEngine;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.MineAreaE;
import com.csxs.letterbook.entity.MineCityE;
import com.csxs.letterbook.entity.MineDistrictE;
import com.csxs.letterbook.entity.MineInfoE;
import com.csxs.letterbook.entity.MineInfoLabelE;
import com.csxs.letterbook.entity.ProvinceE;
import com.csxs.letterbook.entity.ThreeLevelAddressLinkageE;
import com.csxs.letterbook.entity.UserPhotoWallE;
import com.csxs.letterbook.event.ModifyUserInfoEvent;
import com.csxs.letterbook.mine.adapter.PhotoWallAdapter;
import com.csxs.letterbook.mine.adapter.SendDynamicImageAdapter;
import com.csxs.letterbook.mine.mvp.contract.ModifyMineInfoContract;
import com.csxs.letterbook.mine.mvp.presenter.ModifyMineInfoPresenter;
import com.csxs.letterbook.widgets.FullyGridLayoutManager;
import com.csxs.letterbook.widgets.PictureSelectorUtils;
import com.csxs.viewlib.CircleImageView;
import com.csxs.viewlib.LabelsView;
import com.google.gson.Gson;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.style.PictureCropParameterStyle;
import com.luck.picture.lib.style.PictureParameterStyle;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.uber.autodispose.AutoDispose.autoDisposable;

/**
 * @author: yeliu
 * created on 2020/5/19
 * description:
 */
public class ModifyMineInfoActivity extends BaseDiffActivity<ModifyMineInfoPresenter> implements ModifyMineInfoContract.IModifyMineInfoView {

    @BindView(R.id.mine_photo_wall)
    RecyclerView minePhotoWall;
    @BindView(R.id.ci_mine_header)
    CircleImageView ciMineHeader;
    @BindView(R.id.iv_more_image)
    ImageView ivMoreImage;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.rl_nickname)
    RelativeLayout rlNickname;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.rl_gender)
    RelativeLayout rlGender;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.rl_age)
    RelativeLayout rlAge;
    @BindView(R.id.tv_emotion)
    TextView tvEmotion;
    @BindView(R.id.rl_emotion)
    RelativeLayout rlEmotion;
    @BindView(R.id.tv_area_location)
    TextView tvAreaLocation;
    @BindView(R.id.rl_area_location)
    RelativeLayout rlAreaLocation;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.rl_tag)
    RelativeLayout rlTag;

    @BindView(R.id.ll_tag_text)
    LabelsView llTagText;


    @BindView(R.id.tv_signed)
    TextView tvSigned;
    @BindView(R.id.rl_signed)
    LinearLayout rlSigned;

    @Inject
    Gson gson;


    private PictureParameterStyle mPictureParameterStyle;

    private PictureCropParameterStyle mCropParameterStyle;

    private PictureWindowAnimationStyle mWindowAnimationStyle;

    private PhotoWallAdapter photoWallAdapter;

    private MineInfoE mineInfoE;
    private PictureSelectorUtils pictureSelectorUtils;

    @Override
    public void initParam() {
        super.initParam();
        topBarView = false;
        needEventBus = true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_mineinfo;
    }

    @Override
    public void initStatusBar() {

        ImmersionBar.with(this).statusBarDarkFont(false).statusBarColor(R.color.black).fitsSystemWindows(true).init();
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {

        setCenterMainTitle("个人信息", R.color.white);

        setTitleBarBackgroundColor(R.color.black);

        setLeftImage(R.drawable.ic_back_white);

        photoWallAdapter = new PhotoWallAdapter(this);
        minePhotoWall.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        minePhotoWall.setAdapter(photoWallAdapter);

        mineInfoE = new MineInfoE();

        photoWallAdapter.setOnItemClickListener(new PhotoWallAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ImageView view, String url, SparseArray<ImageView> mVisiblePictureList, int Position) {

                if (null != url && null != mVisiblePictureList) {

                } else {
                    //添加图片
                }


                if (pictureSelectorUtils == null) {
                    pictureSelectorUtils = new PictureSelectorUtils(activity, BaseConstants.SELECTOR_MULTI_IMAGE, 9, 9);
                }

                int size = photoWallAdapter.getData().size();
                int max = pictureSelectorUtils.getDefaultSize() - size;
                pictureSelectorUtils.setMaxSelectImage(max);

                pictureSelectorUtils.laucherSelector();


            }
        });

        // photoWallAdapter.setData();

    }

    @Override
    protected void onInitData() {
        if (mPresenter != null) {
            mPresenter.getMineInfo();
        }

    }

    @Override
    public void queryMineInfoSuccess(MineInfoE mineInfoE) {
        this.mineInfoE = mineInfoE;
        if (mineInfoE != null) {
//            if (mineInfoE.getAlbumList() != null && mineInfoE.getAlbumList().size() > 0) {
//                maxSelectImage = defaultSelectImage - mineInfoE.getAlbumList().size();
//                for (int i = 0; i < mineInfoE.getAlbumList().size(); i++) {
//                    list.add(mineInfoE.getAlbumList().get(i).getHttpAddress());
//                }
//                photoWallAdapter.setData(list);
//                photoWallAdapter.notifyDataSetChanged();
//
//            }

            if (mineInfoE.getHeadimgurl() != null && mineInfoE.getHeadimgurl().length() > 0) {
                ImageLoaderV4.getInstance().displayImage(mContext, mineInfoE.getHeadimgurl(), ciMineHeader, R.drawable.ic_default_circle_store_header);
            } else {
                ciMineHeader.setImageResource(R.drawable.ic_default_circle_store_header);
            }


            if (mineInfoE.getNickname() != null) {
                tvNickname.setText(getResources().getString(R.string.str_placeholder, mineInfoE.getNickname()));
            } else {
                tvNickname.setHint("请填写昵称");
            }


            if (mineInfoE.getSex() != null && "1".equals(mineInfoE.getSex())) {
                tvGender.setText("男");
            } else if (mineInfoE.getSex() != null && "2".equals(mineInfoE.getSex())) {
                tvGender.setText("女");
            } else {
                tvGender.setHint("请选择性别");
            }


            if (mineInfoE.getBirthDay() != null && mineInfoE.getBirthDay().length() > 0) {

                try {
                    int age = TimeStampUtils.getAgeByBirth(TimeStampUtils.stringChangeToDate(mineInfoE.getBirthDay(), "yyyy-MM-dd HH:mm:ss"));
                    tvAge.setText(getResources().getString(R.string.int_placeholder, age));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                tvAge.setHint("未设置");
            }

            if (mineInfoE.getEmotion() != null && mineInfoE.getEmotion().length() > 0) {
                tvEmotion.setText(getResources().getString(R.string.str_placeholder, mineInfoE.getEmotion()));
            } else {
                tvEmotion.setHint("未设置");
            }


            if (mineInfoE.getAddress() != null && mineInfoE.getAddress().length() > 0) {
                tvAreaLocation.setText(getResources().getString(R.string.str_placeholder, mineInfoE.getAddress()));
            } else {
                tvAreaLocation.setHint("请选择你的地址");
            }

            if (mineInfoE.getLabelList() != null && mineInfoE.getLabelList().size() > 0) {
                //tvAreaLocation.setText(getResources().getString(R.string.str_placeholder, "查看标签(" + mineInfoE.getLabelList().size() + ")"));
                llTagText.setLabels(mineInfoE.getLabelList(), new LabelsView.LabelTextProvider<MineInfoLabelE>() {
                    @Override
                    public CharSequence getLabelText(TextView label, int position, MineInfoLabelE data) {
                        return data.getLabelName();
                    }
                });

                llTagText.setSelectType(LabelsView.SelectType.NONE);

                tvTag.setHint("");
            } else {
//                llTagText.setVisibility(View.GONE);
                tvTag.setHint("请选择你的标签");
                List<MineInfoLabelE> tag = new ArrayList<>();
                MineInfoLabelE mineInfoLabel = new MineInfoLabelE();
                mineInfoLabel.setLabelName("暂无标签");
                tag.add(mineInfoLabel);
                llTagText.setLabelBackgroundResource(R.drawable.bg_empty);
                llTagText.setLabelTextPadding(0, ScreenUtil.dip2px(mContext, 5), 0, ScreenUtil.dip2px(mContext, 5));
                // llTagText.setLabelTextSize(ScreenUtil.sp2px(mContext));
                llTagText.setLabels(tag, new LabelsView.LabelTextProvider<MineInfoLabelE>() {
                    @Override
                    public CharSequence getLabelText(TextView label, int position, MineInfoLabelE data) {
                        return data.getLabelName();
                    }
                });

                llTagText.setSelectType(LabelsView.SelectType.NONE);
            }

            if (mineInfoE.getSignature() != null && mineInfoE.getSignature().length() > 0) {
                tvSigned.setText(getResources().getString(R.string.str_placeholder, mineInfoE.getSignature()));
            } else {
                tvSigned.setHint("主人很懒什么都没留下");
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ModifyUserInfoEvent(ModifyUserInfoEvent messageEvent) {
        switch (messageEvent.getType()) {
            case 1:
                tvNickname.setText(getResources().getString(R.string.str_placeholder, messageEvent.getMessage()));
                break;
            case 2:
                if ("1".equals(messageEvent.getMessage())) {
                    tvGender.setText("男");
                } else {
                    tvGender.setText("女");
                }
                break;
            case 3:
                tvAge.setText(getResources().getString(R.string.str_placeholder, messageEvent.getMessage()));
                break;
            case 4:
                tvEmotion.setText(getResources().getString(R.string.str_placeholder, messageEvent.getMessage()));
                break;
            case 5:
                tvAreaLocation.setText(getResources().getString(R.string.str_placeholder, messageEvent.getMessage()));
                break;
            case 6:
                if (messageEvent.getList() != null && messageEvent.getList().size() > 0) {
                    llTagText.setLabels(messageEvent.getList(), new LabelsView.LabelTextProvider<String>() {
                        @Override
                        public CharSequence getLabelText(TextView label, int position, String data) {
                            return data;
                        }
                    });
                } else {
                    List<String> list = new ArrayList<>();
                    list.add("暂无标签");
                    llTagText.setLabelBackgroundResource(R.drawable.bg_empty);
                    llTagText.setLabelTextPadding(0, ScreenUtil.dip2px(mContext, 5), 0, ScreenUtil.dip2px(mContext, 5));
                    llTagText.setLabels(list, new LabelsView.LabelTextProvider<String>() {
                        @Override
                        public CharSequence getLabelText(TextView label, int position, String data) {
                            return data;
                        }
                    });
                }

                llTagText.setSelectType(LabelsView.SelectType.NONE);
                break;
            case 7:
                tvSigned.setText(getResources().getString(R.string.str_placeholder, messageEvent.getMessage()));
                break;
        }
    }

    @Override
    public void queryMineInfoFailure(int code, String msg) {

    }

    @Override
    public void updateUserInfoFailure(int code, String msg) {

    }

    @Override
    public void updateUserInfoSuccess(String s) {

    }

    /**
     * 上传头成功
     *
     * @param s
     */
    @Override
    public void updateHeaderImageSuccess(String s) {

    }

    /**
     * 上传头像失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void updateHeaderImageFailure(int code, String msg) {
        ciMineHeader.setImageResource(R.drawable.ic_default_circle_store_header);
    }


    @OnClick({R.id.rl_header, R.id.rl_nickname, R.id.rl_gender, R.id.rl_age, R.id.rl_emotion, R.id.rl_area_location, R.id.rl_tag, R.id.rl_signed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_header:
//                selectImage();
                if (pictureSelectorUtils == null) {
                    pictureSelectorUtils = new PictureSelectorUtils(this, BaseConstants.SELECTOR_SINGLE_IMAGE, 1, 9);
                }

                pictureSelectorUtils.laucherSelector();
                break;
            case R.id.rl_nickname:
                Intent intent = new Intent();
                intent.putExtra(BaseConstants.EXTRA_KAY1, 1);
                if (!"".equals(tvNickname.getText().toString())) {
                    intent.putExtra(BaseConstants.EXTRA_KAY2, tvNickname.getText().toString());
                }
                ModifyInfoActivity.start(mContext, ModifyInfoActivity.class, intent);
                break;
            case R.id.rl_gender:
                Intent igender = new Intent();
                igender.putExtra(BaseConstants.EXTRA_KAY1, 2);
                if (!"".equals(tvGender.getText().toString())) {
                    igender.putExtra(BaseConstants.EXTRA_KAY2, 1);
                }
                ModifyInfoActivity.start(mContext, ModifyInfoActivity.class, igender);
                break;
            case R.id.rl_age:
                Intent iage = new Intent();
                iage.putExtra(BaseConstants.EXTRA_KAY1, 3);
                if (!"".equals(tvAge.getText().toString())) {
                    iage.putExtra(BaseConstants.EXTRA_KAY2, tvAge.getText().toString());
                }
                ModifyInfoActivity.start(mContext, ModifyInfoActivity.class, iage);
                break;
            case R.id.rl_emotion:
                Intent iemotion = new Intent();
                iemotion.putExtra(BaseConstants.EXTRA_KAY1, 4);
                if (!"".equals(tvEmotion.getText().toString())) {
                    iemotion.putExtra(BaseConstants.EXTRA_KAY2, tvEmotion.getText().toString());
                    iemotion.putExtra(BaseConstants.EXTRA_KAY3, 1);
                }
                ModifyInfoActivity.start(mContext, ModifyInfoActivity.class, iemotion);
                break;
            case R.id.rl_area_location:
                Intent iaddress = new Intent();
                iaddress.putExtra(BaseConstants.EXTRA_KAY1, 5);
                if (!"".equals(tvAreaLocation.getText().toString())) {
                    iaddress.putExtra(BaseConstants.EXTRA_KAY2, tvAreaLocation.getText().toString());
                }
                ModifyInfoActivity.start(mContext, ModifyInfoActivity.class, iaddress);
                break;
            case R.id.rl_tag:
                Intent itag = new Intent();
                itag.putExtra(BaseConstants.EXTRA_KAY1, 6);
                if (mineInfoE.getLabelList() != null && mineInfoE.getLabelList().size() > 0) {

                    String json = gson.toJson(mineInfoE.getLabelList());
                    itag.putExtra(BaseConstants.EXTRA_KAY2, json);
                }
                ModifyInfoActivity.start(mContext, ModifyInfoActivity.class, itag);
                break;
            case R.id.rl_signed:
                Intent isigned = new Intent();
                isigned.putExtra(BaseConstants.EXTRA_KAY1, 7);
                if (!"".equals(tvSigned.getText().toString())) {
                    isigned.putExtra(BaseConstants.EXTRA_KAY2, tvSigned.getText().toString());
                }
                ModifyInfoActivity.start(mContext, ModifyInfoActivity.class, isigned);
                break;
        }
    }


    private SendDynamicImageAdapter.onAddPicClickListener onAddPicClickListener = new SendDynamicImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

        }
    };
    List<String> list = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            switch (requestCode) {
                case BaseConstants.SELECTOR_SINGLE_IMAGE:
                    // 图片选择结果回调


                    if (selectList.size() > 0) {
                        mPresenter.updateUserInfo(selectList.get(0).getCompressPath());
                        ImageLoaderV4.getInstance().displayImage(mContext, selectList.get(0).getCompressPath(), ciMineHeader, R.drawable.ic_default_circle_store_header);

                    } else {
                        ciMineHeader.setImageResource(R.drawable.ic_default_circle_store_header);
                    }


                    break;
                case BaseConstants.SELECTOR_MULTI_IMAGE:

                    for (LocalMedia media : selectList) {
//                            Log.i(TAG, "是否压缩:" + media.isCompressed());
//                            Log.i(TAG, "压缩:" + media.getCompressPath());
//                            Log.i(TAG, "原图:" + media.getPath());
//                            Log.i(TAG, "是否裁剪:" + media.isCut());
//                            Log.i(TAG, "裁剪:" + media.getCutPath());
//                            Log.i(TAG, "是否开启原图:" + media.isOriginal());
//                            Log.i(TAG, "原图路径:" + media.getOriginalPath());
//                            Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
//                            Log.i(TAG, "Size: " + media.getSize());
                        list.add(media.getCompressPath());

                    }

                    if (list.size() > 0) {
                        photoWallAdapter.setData(list);
                        photoWallAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }
}
