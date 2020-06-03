package com.csxs.letterbook.social.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.csxs.core.base.LazyDiffFragment;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.MapSocialUserInfoE;
import com.csxs.letterbook.entity.MineInfoLabelE;
import com.csxs.letterbook.entity.UserLabelE;
import com.csxs.letterbook.seller.mvp.presenter.SellerStoreInfoPresenter;
import com.csxs.letterbook.social.mvp.presenter.PersonalHomePresenter;
import com.csxs.viewlib.LabelsView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * @author: yeliu
 * created on 2020/4/18
 * description:店铺信息
 */
public class PersonalInfoFragment extends LazyDiffFragment<PersonalHomePresenter> {

    @Inject
    ImageLoaderV4 imageLoaderV4;

    @Inject
    Gson gson;

    @BindView(R.id.tv_personal_emotion)
    TextView tvPersonalEmotion;
    @BindView(R.id.tv_personal_address)
    TextView tvPersonalAddress;
    @BindView(R.id.tv_personal_signed)
    TextView tvPersonalSigned;
    @BindView(R.id.ll_tag_text)
    LabelsView llTagText;

    private MapSocialUserInfoE personalInfo;

    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_personal;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


    }

    @Override
    protected void lazyInitData(boolean isFirstVisible) {
        if (isFirstVisible) {
            if (getArguments() != null) {
                String json = getArguments().getString("info");
                personalInfo = gson.fromJson(json, MapSocialUserInfoE.class);
                tvPersonalEmotion.setText(getResources().getString(R.string.str_emotion, personalInfo.getEmotionId() + ""));
                tvPersonalAddress.setText(getResources().getString(R.string.str_city, !"".equals(personalInfo.getAddress()) ? personalInfo.getAddress() : "暂无地址"));
                tvPersonalSigned.setText(getResources().getString(R.string.str_placeholder, !"".equals(personalInfo.getSignature()) ? personalInfo.getSignature() : "主人很懒什么都没留下"));
                if (personalInfo.getLabel() != null && personalInfo.getLabel().size() > 0) {
                    //tvAreaLocation.setText(getResources().getString(R.string.str_placeholder, "查看标签(" + mineInfoE.getLabelList().size() + ")"));
                    llTagText.setLabels(personalInfo.getLabel(), new LabelsView.LabelTextProvider<UserLabelE>() {
                        @Override
                        public CharSequence getLabelText(TextView label, int position, UserLabelE data) {
                            return data.getLabelName();
                        }
                    });

                    llTagText.setSelectType(LabelsView.SelectType.NONE);

                } else {
                    List<UserLabelE> tag = new ArrayList<>();
                    UserLabelE mineInfoLabel = new UserLabelE();
                    mineInfoLabel.setLabelName("暂无标签");
                    tag.add(mineInfoLabel);
                    llTagText.setLabelBackgroundResource(R.drawable.bg_empty);
                    llTagText.setLabelTextPadding(0, ScreenUtil.dip2px(mContext, 5), 0, ScreenUtil.dip2px(mContext, 5));
                    // llTagText.setLabelTextSize(ScreenUtil.sp2px(mContext));
                    llTagText.setLabels(tag, new LabelsView.LabelTextProvider<UserLabelE>() {
                        @Override
                        public CharSequence getLabelText(TextView label, int position, UserLabelE data) {
                            return data.getLabelName();
                        }
                    });

                    llTagText.setSelectType(LabelsView.SelectType.NONE);
                }

            }


        }
    }

    @Override
    protected void fragmentHidden() {

    }


    public static Fragment newInstance(int status) {
        Bundle args = new Bundle();
        args.putInt("status", status);
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
