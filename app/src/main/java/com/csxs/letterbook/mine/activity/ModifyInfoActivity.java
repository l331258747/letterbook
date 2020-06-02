package com.csxs.letterbook.mine.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.csxs.core.BaseConstants;
import com.csxs.core.base.BaseDiffActivity;
import com.csxs.core.utils.StringUtil;
import com.csxs.core.utils.TimeStampUtils;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.LabelE;
import com.csxs.letterbook.entity.MineAreaE;
import com.csxs.letterbook.entity.MineCityE;
import com.csxs.letterbook.entity.MineDistrictE;
import com.csxs.letterbook.entity.MineInfoLabelE;
import com.csxs.letterbook.entity.ProvinceE;
import com.csxs.letterbook.entity.ThreeLevelAddressLinkageE;
import com.csxs.letterbook.entity.UserEmotionE;
import com.csxs.letterbook.event.BaseEvent;
import com.csxs.letterbook.event.ModifyUserInfoEvent;
import com.csxs.letterbook.mine.mvp.contract.ModifyInfoContract;
import com.csxs.letterbook.mine.mvp.presenter.ModifyInfoPresenter;
import com.csxs.letterbook.seller.MapSellerFragment;
import com.csxs.viewlib.LabelsView;
import com.csxs.viewlib.TextClearEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.immersionbar.ImmersionBar;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.uber.autodispose.AutoDispose.autoDisposable;

/**
 * @author: yeliu
 * created on 2020/5/20
 * description:
 */
public class ModifyInfoActivity extends BaseDiffActivity<ModifyInfoPresenter> implements ModifyInfoContract.IModifyInfoView {


    @BindView(R.id.layout_nickname)
    RelativeLayout layoutNickname;

    @BindView(R.id.modify_user_nickname)
    TextClearEditText modifyUserNickname;

    @BindView(R.id.nickname_input_number)
    TextView nickNameinputNumber;


    @BindView(R.id.rg_user_gender)
    RadioGroup radioGroupGender;

    @BindView(R.id.rg_user_gender_man)
    RadioButton rgUserGenderMan;

    @BindView(R.id.rg_user_gender_woman)
    RadioButton rgUserGenderWoman;


    @BindView(R.id.rl_user_age)
    RelativeLayout rlUserAge;

    @BindView(R.id.tv_user_age)
    TextView tvUserAge;

    @BindView(R.id.rl_user_emotion)
    RelativeLayout rlUserEmotion;

    @BindView(R.id.tv_user_emotion)
    TextView tvUserEmotion;

    @BindView(R.id.tv_user_emotion_text)
    TextView tvUserEmotionText;

    @BindView(R.id.labelsview_text)
    LabelsView labelsviewText;

    @BindView(R.id.layout_sign)
    RelativeLayout rlSign;

    @BindView(R.id.modify_user_sign)
    EditText etSign;

    @BindView(R.id.sign_input_number)
    TextView signInputNumber;

    @Inject
    Gson gson;

    private boolean change = false;

    private boolean isChange = false;
    private int modifyType;

    private OptionsPickerView pvCustomOptions, pvEmotionOptions;
    private TimePickerView pvCustomTime;

    private List<UserEmotionE> emotions;
    private int emotionId;
    private OptionsPickerView pvOptions;
    private String birthDay;
//    private ThreeLevelAddressLinkageE threeLevelAddressLinkageE;

    private boolean isLoaded = false;
    private Set<String> hashSet;
    private String pCoede;
    private String cCode;
    private String dCode;
    private int sex;
    private List<MineInfoLabelE> tagList;


    @Override
    public void initParam() {
        super.initParam();
        topBarView = false;
        // needEventBus = true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_info;
    }


    @Override
    public void initStatusBar() {
        ImmersionBar.with(this).statusBarDarkFont(false).statusBarColor(R.color.black).fitsSystemWindows(true).init();
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {

        modifyType = getIntent().getIntExtra(BaseConstants.EXTRA_KAY1, -1);

        setTitleBarBackgroundColor(R.color.black);

        setLeftImage(R.drawable.ic_back_white);

        setCustomRightText("保存", true, R.drawable.bg_user_top_right_normal, R.color.black_999999, 12);

        setBottomLine(true);

        if (modifyType == 1) {

            initNickNameData();

        } else if (modifyType == 2) {

            initGenderData();

        } else if (modifyType == 3) {

            initAgeData();

        } else if (modifyType == 4) {

            initEmotionData();

        } else if (modifyType == 5) {

            initAddressData();

        } else if (modifyType == 6) {

            initLabelView();

        } else if (modifyType == 7) {
            initSign();
        }


    }

    private void initSign() {

        setCenterMainTitle("设置签名", R.color.white);
        String sign = getIntent().getStringExtra(BaseConstants.EXTRA_KAY2);

        rlSign.setVisibility(View.VISIBLE);
//        signInputNumber.setText(sign.length() + "/20");
        etSign.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String nickname = editable.toString();
                String result = nickname;
                if (nickname.length() > 20) {
                    result = nickname.substring(0, 20);
                    etSign.setText(result);
                    etSign.setSelection(etSign.getText().length());
                }

                if (nickname.length() <= 0) {
                    change = false;
                    isChange = false;
                } else {
                    change = true;
                }

                setRightText();
                signInputNumber.setText(result.length() + "/20");

            }
        });

        if (!"".equals(sign)) {
            etSign.setText(sign);
        } else {
            etSign.setHint("请输入个性签名");
        }

    }

    private void initLabelView() {
        setCenterMainTitle("设置标签", R.color.white);

        String tag = getIntent().getStringExtra(BaseConstants.EXTRA_KAY2);
        try {
            if (tag != null && !"".equals(tag)) {

                Type type = new TypeToken<ArrayList<MineInfoLabelE>>() {
                }.getType();
                tagList = gson.fromJson(tag, type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mPresenter.queryLabel();
        change = false;
        isChange = false;
        setRightText();
    }

    private void initAddressData() {

        setCenterMainTitle("设置地区", R.color.white);
        String address = getIntent().getStringExtra(BaseConstants.EXTRA_KAY2);
        rlUserEmotion.setVisibility(View.VISIBLE);
        tvUserEmotionText.setText("地区");
        if (address != null && !"".equals(address)) {
            tvUserEmotion.setText(address);
        } else {
            tvUserEmotion.setHint("请选择地区");
        }

        Observable.just("address").map(new Function<String, ThreeLevelAddressLinkageE>() {
            @Override
            public ThreeLevelAddressLinkageE apply(String s) throws Exception {
                ThreeLevelAddressLinkageE threeLevelAddressLinkageE = new ThreeLevelAddressLinkageE();

                List<ProvinceE> options1Items = new ArrayList<>();
                List<List<MineCityE>> options2Items = new ArrayList<>();
                List<List<List<MineDistrictE>>> options3Items = new ArrayList<>();

                String JsonData = new StringUtil().getJson(mContext, "provincedata.json");//获取assets目录下的json文件数据
                Gson gson = new Gson();
                MineAreaE mineAreaE = gson.fromJson(JsonData, MineAreaE.class);
                options1Items = mineAreaE.getProvince();

                for (int i = 0; i < mineAreaE.getProvince().size(); i++) {

                    List<MineCityE> city = new ArrayList<>();

                    List<List<MineDistrictE>> province_AreaList = new ArrayList<>();

                    for (int j = 0; j < mineAreaE.getProvince().get(i).getCity().size(); j++) {
                        city.add(mineAreaE.getProvince().get(i).getCity().get(j));
                        List<MineDistrictE> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                        city_AreaList.addAll(mineAreaE.getProvince().get(i).getCity().get(j).getDistrict());
                        province_AreaList.add(city_AreaList);
                    }

                    options2Items.add(city);

                    options3Items.add(province_AreaList);
                }

                threeLevelAddressLinkageE.setOptions1Items(options1Items);
                threeLevelAddressLinkageE.setOptions2Items(options2Items);
                threeLevelAddressLinkageE.setOptions3Items(options3Items);

                return threeLevelAddressLinkageE;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).as(autoDisposable(AndroidLifecycleScopeProvider.from(ModifyInfoActivity.this, Lifecycle.Event.ON_PAUSE))).subscribe(new Consumer<ThreeLevelAddressLinkageE>() {
            @Override
            public void accept(ThreeLevelAddressLinkageE threeLevelAddressLinkageE) throws Exception {
                //  ModifyInfoActivity.this.threeLevelAddressLinkageE = threeLevelAddressLinkageE;
                initAddressPickerView(threeLevelAddressLinkageE);
                isLoaded = true;
            }
        });


        rlUserEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoaded) {
                    pvOptions.show();
                }

            }
        });


    }

    private void initAddressPickerView(ThreeLevelAddressLinkageE addressLinkage) {

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = addressLinkage.getOptions1Items().size() > 0 ? addressLinkage.getOptions1Items().get(options1).getPickerViewText() : "";
                pCoede = addressLinkage.getOptions1Items().size() > 0 ? addressLinkage.getOptions1Items().get(options1).getZipcode() : "";


                String opt2tx = addressLinkage.getOptions2Items().size() > 0 && addressLinkage.getOptions2Items().get(options1).size() > 0 ? addressLinkage.getOptions2Items().get(options1).get(options2).getName() : "";
                cCode = addressLinkage.getOptions2Items().size() > 0 && addressLinkage.getOptions2Items().get(options1).size() > 0 ? addressLinkage.getOptions2Items().get(options1).get(options2).getZipcode() : "";

                String opt3tx = addressLinkage.getOptions2Items().size() > 0 && addressLinkage.getOptions3Items().get(options1).size() > 0 && addressLinkage.getOptions3Items().get(options1).get(options2).size() > 0 ? addressLinkage.getOptions3Items().get(options1).get(options2).get(options3).getName() : "";
                dCode = addressLinkage.getOptions2Items().size() > 0 && addressLinkage.getOptions3Items().get(options1).size() > 0 && addressLinkage.getOptions3Items().get(options1).get(options2).size() > 0 ? addressLinkage.getOptions3Items().get(options1).get(options2).get(options3).getZipcode() : "";
                String tx = opt1tx + opt2tx + opt3tx;
                // String code = pCoede + cCode + dCode;
                tvUserEmotion.setText(tx);

                change = true;
                isChange = false;
                setRightText();
            }
        })
                .setLayoutRes(R.layout.pickerview_sex_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView tvSubmit = v.findViewById(R.id.tv_finish);
                        TextView ivCancel = v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.dismiss();
                            }
                        });

                    }
                })
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(addressLinkage.getOptions1Items(), addressLinkage.getOptions2Items(), addressLinkage.getOptions3Items());//三级选择器

    }

    private void initEmotionData() {
        setCenterMainTitle("设置情感", R.color.white);
        rlUserEmotion.setVisibility(View.VISIBLE);
        tvUserEmotionText.setText("情感");
        String emotion = getIntent().getStringExtra(BaseConstants.EXTRA_KAY2);
        emotionId = getIntent().getIntExtra(BaseConstants.EXTRA_KAY3, -1);
        if (emotion != null && !"".equals(emotion)) {
            tvUserEmotion.setText(emotion);
        } else {
            tvUserEmotion.setHint("请选择情感状态");
        }

        if (emotions != null && emotions.size() > 0) {
            initEmotionPickerView();
        } else {
            mPresenter.queryEmotion();
        }

        rlUserEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvEmotionOptions.show();
            }
        });
    }


    @Override
    public void updateNickNameSuccess() {
        EventBus.getDefault().post(new ModifyUserInfoEvent(1, modifyUserNickname.getText().toString()));
        finishCurrentPage();

    }

    @Override
    public void updateSexSuccess() {
        EventBus.getDefault().post(new ModifyUserInfoEvent(2, String.valueOf(sex)));
        finishCurrentPage();
    }

    @Override
    public void updateAgeSuccess() {
        EventBus.getDefault().post(new ModifyUserInfoEvent(3, tvUserAge.getText().toString()));
        finishCurrentPage();
    }

    @Override
    public void updateEmotionSuccess() {
        EventBus.getDefault().post(new ModifyUserInfoEvent(4, tvUserEmotion.getText().toString()));
        finishCurrentPage();
    }

    @Override
    public void updateAddressSuccess() {
        EventBus.getDefault().post(new ModifyUserInfoEvent(5, tvUserEmotion.getText().toString()));
        finishCurrentPage();
    }


    @Override
    public void updateLabelsSuccess() {
        List<String> list = new ArrayList<>();
        for (String str : hashSet) {
            list.add(str);
        }
        ModifyUserInfoEvent modifyUserInfoEvent = new ModifyUserInfoEvent(6, "tag");
        modifyUserInfoEvent.setList(list);
        EventBus.getDefault().post(modifyUserInfoEvent);
        finishCurrentPage();
    }

    @Override
    public void updateSignatureSuccess() {
        EventBus.getDefault().post(new ModifyUserInfoEvent(7, etSign.getText().toString()));
        finishCurrentPage();
    }

    public void finishCurrentPage() {
        finish();
    }

    @Override
    public void queryEmotionSuccess(List<UserEmotionE> emotions) {
        this.emotions = emotions;
        initEmotionPickerView();
    }

    @Override
    public void queryLabelSuccess(List<LabelE> labels) {
        labelsviewText.setVisibility(View.VISIBLE);




        labelsviewText.setLabels(labels, new LabelsView.LabelTextProvider<LabelE>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, LabelE data) {
                return data.getLabelName();
            }
        });

        hashSet = new HashSet<>();

        labelsviewText.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                if (isSelect) {
                    hashSet.add(String.valueOf(labels.get(position).getId()));
                } else {
                    hashSet.remove(String.valueOf(labels.get(position).getId()));
                }

                if (hashSet.size() > 0) {
                    change = true;
                    isChange = false;

                } else {
                    change = false;
                    isChange = false;
                }
                setRightText();
            }
        });


        List<Integer> list=null;
        if (labels != null && labels.size() > 0) {
            if (tagList != null && tagList.size() > 0) {
                list=new ArrayList<>();
                for (int i = 0; i < labels.size(); i++) {
                    int id = labels.get(i).getId();
                    for (int j = 0; j < tagList.size(); j++) {
                        if(id ==tagList.get(j).getLabelId()){
                            list.add(i);
                        }
                    }
                }
            }
        }
        if(list!=null&&list.size()>0){
            labelsviewText.setSelects(list);
        }

    }

    @Override
    public void queryLabelFailure(int code, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    private void initEmotionPickerView() {
        if (pvEmotionOptions == null) {
            pvEmotionOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String tx = emotions.get(options1).getPickerViewText();
                    emotionId = emotions.get(options1).getId();
                    tvUserEmotion.setText(tx);

                    change = true;
                    isChange = false;
                    setRightText();

                }
            })
                    .setLayoutRes(R.layout.pickerview_sex_options, new CustomListener() {
                        @Override
                        public void customLayout(View v) {
                            TextView tvSubmit = v.findViewById(R.id.tv_finish);
                            TextView ivCancel = v.findViewById(R.id.iv_cancel);
                            tvSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pvEmotionOptions.returnData();
                                    pvEmotionOptions.dismiss();
                                }
                            });

                            ivCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pvEmotionOptions.dismiss();
                                }
                            });

                        }
                    })

                    .setContentTextSize(20)//设置滚轮文字大小
                    .setOutSideColor(0x00000000)
                    .setOutSideCancelable(true)
                    .build();

            Dialog mDialog = pvEmotionOptions.getDialog();

            if (mDialog != null) {

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        Gravity.BOTTOM);

                params.leftMargin = 0;
                params.rightMargin = 0;

                pvEmotionOptions.getDialogContainerLayout().setLayoutParams(params);

                Window dialogWindow = mDialog.getWindow();

                if (dialogWindow != null) {

                    dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                    dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                    dialogWindow.setDimAmount(0);
                }
            }

            pvEmotionOptions.setPicker(emotions);//添加数据
        }


    }

    private void initAgeData() {
        setCenterMainTitle("设置年龄", R.color.white);
        rlUserAge.setVisibility(View.VISIBLE);
        String age = getIntent().getStringExtra(BaseConstants.EXTRA_KAY2);
        if (age != null && !"".equals(age)) {
            tvUserAge.setText(age);
        } else {
            tvUserAge.setHint("请选择出生日期");
        }

        change = false;
        isChange = false;
        setRightText();

        rlUserAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTimePickerView();
            }
        });

    }

    private void initTimePickerView() {
        if (pvCustomTime == null) {
            Calendar selectedDate = Calendar.getInstance();//系统当前时间
            Calendar startDate = Calendar.getInstance();
            startDate.set(1970, 0, 1);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2027, 11, 31);
            //时间选择器 ，自定义布局
            pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {


                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    birthDay = TimeStampUtils.dateChangeToStringYMD(date);
                    int age = TimeStampUtils.getAgeByBirth(date);
                    tvUserAge.setText(getResources().getString(R.string.int_placeholder, age));
                    change = true;
                    isChange = false;
                    setRightText();
                }
            }).setDate(selectedDate)
                    .setRangDate(startDate, endDate)
                    .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                        @Override
                        public void customLayout(View v) {
                            TextView tvSubmit = v.findViewById(R.id.tv_finish);
                            TextView ivCancel = v.findViewById(R.id.iv_cancel);
                            tvSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pvCustomTime.returnData();
                                    pvCustomTime.dismiss();
                                }
                            });
                            ivCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pvCustomTime.dismiss();
                                }
                            });
                        }
                    }).setContentTextSize(18)
                    .setType(new boolean[]{true, true, true, false, false, false})
                    .setLabel("", "", "", "", "", "")
                    .setLineSpacingMultiplier(1.2f)
                    .setTextXOffset(0, 0, 0, 40, 0, -40)
                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                    .setOutSideColor(0x00000000)
                    .setOutSideCancelable(true)
                    .build();
        }

        pvCustomTime.show();
    }

    private void initGenderData() {

        setCenterMainTitle("设置性别", R.color.white);

        radioGroupGender.setVisibility(View.VISIBLE);

        int gender = getIntent().getIntExtra(BaseConstants.EXTRA_KAY2, -1);

        Drawable drawable1 = getResources().getDrawable(R.drawable.ic_checked);

        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        if (gender == 1) {
            rgUserGenderMan.setChecked(true);
            rgUserGenderMan.setCompoundDrawables(null, null, drawable1, null);
            rgUserGenderWoman.setCompoundDrawables(null, null, null, null);
        } else if (gender == 2) {
            rgUserGenderWoman.setChecked(true);
            rgUserGenderWoman.setCompoundDrawables(null, null, drawable1, null);
            rgUserGenderMan.setCompoundDrawables(null, null, null, null);

        } else {
            rgUserGenderMan.setChecked(true);
            rgUserGenderMan.setCompoundDrawables(null, null, drawable1, null);
            rgUserGenderWoman.setCompoundDrawables(null, null, null, null);
        }

        change = true;
        isChange = false;
        setRightText();


        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rg_user_gender_man) {
                    rgUserGenderMan.setChecked(true);
                    rgUserGenderMan.setCompoundDrawables(null, null, drawable1, null);
                    rgUserGenderWoman.setCompoundDrawables(null, null, null, null);
                } else if (checkedId == R.id.rg_user_gender_woman) {
                    rgUserGenderWoman.setChecked(true);
                    rgUserGenderWoman.setCompoundDrawables(null, null, drawable1, null);
                    rgUserGenderMan.setCompoundDrawables(null, null, null, null);
                }
            }
        });
    }

//    private void initSexPickerView() {
//        if (pvCustomOptions == null) {
//            pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
//                @Override
//                public void onOptionsSelect(int options1, int option2, int options3, View v) {
//                    //返回的分别是三个级别的选中位置
//                    String tx = itemSex.get(options1);
//                    tvUserGander.setText(tx);
//                }
//            })
//                    .setLayoutRes(R.layout.pickerview_sex_options, new CustomListener() {
//                        @Override
//                        public void customLayout(View v) {
//                            TextView tvSubmit = v.findViewById(R.id.tv_finish);
//                            TextView ivCancel = v.findViewById(R.id.iv_cancel);
//                            tvSubmit.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    pvCustomOptions.returnData();
//                                    pvCustomOptions.dismiss();
//                                }
//                            });
//
//                            ivCancel.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    pvCustomOptions.dismiss();
//                                }
//                            });
//
//                        }
//                    })
//
//                    .setContentTextSize(20)//设置滚轮文字大小
//                    .setOutSideColor(0x00000000)
//                    .setOutSideCancelable(true)
//                    .build();
//
//            Dialog mDialog = pvCustomOptions.getDialog();
//
//            if (mDialog != null) {
//
//                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT,
//                        Gravity.BOTTOM);
//
//                params.leftMargin = 0;
//                params.rightMargin = 0;
//
//                pvCustomOptions.getDialogContainerLayout().setLayoutParams(params);
//
//                Window dialogWindow = mDialog.getWindow();
//
//                if (dialogWindow != null) {
//
//                    dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
//                    dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
//                    dialogWindow.setDimAmount(0);
//                }
//            }
//
//            pvCustomOptions.setPicker(itemSex);//添加数据
//        }
//
//        pvCustomOptions.show();
//    }


    /**
     * 设置昵称
     */
    private void initNickNameData() {
        setCenterMainTitle("设置昵称", R.color.white);
        layoutNickname.setVisibility(View.VISIBLE);
        String nickname = getIntent().getStringExtra(BaseConstants.EXTRA_KAY2);
        if (nickname != null) {
            modifyUserNickname.setText(nickname);
        }

        modifyUserNickname.setOnTextChangedListener(new TextClearEditText.OnTextChangedListener() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String nickname = s.toString();
                String result = nickname;
                if (nickname.length() > 10) {
                    result = nickname.substring(0, 10);
                    modifyUserNickname.setText(result);
                    modifyUserNickname.setSelection(modifyUserNickname.getText().length());
                }

                if (nickname.length() <= 0) {
                    change = false;
                    isChange = false;
                } else {
                    change = true;
                }

                setRightText();
                nickNameinputNumber.setText(result.length() + "/10");
            }
        });

        modifyUserNickname.setText(nickname);
        modifyUserNickname.setSelection(modifyUserNickname.getText().length());
    }

    @Override
    protected void rightClick(View v) {
        super.rightClick(v);
        if (modifyType == 1) {

            mPresenter.updateUserNickNameInfo(Objects.requireNonNull(modifyUserNickname.getText()).toString());

        } else if (modifyType == 2) {
            sex = radioGroupGender.getCheckedRadioButtonId();

            if (sex == R.id.rg_user_gender_man) {
                sex = 1;
            } else if (sex == R.id.rg_user_gender_woman) {
                sex = 2;
            }

            mPresenter.updateUserSexInfo(sex);
        } else if (modifyType == 3) {
            if (birthDay != null) {
                mPresenter.updateUserAgeInfo(birthDay);
            }

        } else if (modifyType == 4) {
            if (emotionId != -1 && emotionId != 0) {
                mPresenter.updateUserEmotionIdInfo(emotionId);
            } else {
                Toast.makeText(this, "请选择感情状态", Toast.LENGTH_SHORT).show();
            }
        } else if (modifyType == 5) {
            if (pCoede != null && cCode != null && dCode != null) {
                mPresenter.updateUserAddressInfo(tvUserEmotion.getText().toString(), pCoede, cCode, dCode);
            }

        } else if (modifyType == 6) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String str : hashSet) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(str);
            }
            mPresenter.updateUserlabelsInfo(stringBuilder.toString());
        } else if (modifyType == 7) {
            mPresenter.updateUserSignatureInfo(etSign.getText().toString());

        }


    }


    @Override
    protected void onInitData() {

    }


    public void setRightText() {
        if (change) {
            if (!isChange) {
                setCustomRightText("保存", true, R.drawable.bg_user_top_right_whith, R.color.color_E7A124, 12);
                isChange = true;
            }
        } else {
            setCustomRightText("保存", true, R.drawable.bg_user_top_right_normal, R.color.black_999999, 12);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseEvent messageEvent) {
        Log.e("event", "基础事件");
    }
}
