package com.csxs.letterbook.widgets;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.R;
import com.csxs.viewlib.LabelsView;
import com.csxs.viewlib.seekbar.IndicatorSeekBar;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.core.PositionPopupView;
import com.lxj.xpopup.impl.PartShadowPopupView;

import java.util.ArrayList;
import java.util.List;

public class SearchOptionsPopupView extends PositionPopupView {
    private Context context;
    private LabelsView searchIndustry,searchActivites;
    private RelativeLayout rlTop;
    private TextView tvOptionsOk;
    private TextView tvOptionCancel;

    public SearchOptionsPopupView(@NonNull Context context) {
        super(context);
        this.context=context;

    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.search_options_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        searchIndustry = findViewById(R.id.lv_search_industry);
        rlTop = findViewById(R.id.ll_search_seller);
        tvOptionsOk = findViewById(R.id.tv_options_ok);
        tvOptionCancel = findViewById(R.id.tv_option_cancel);
        IndicatorSeekBar indicatorSeekBar=findViewById(R.id.indicator_seekbar_distance);
//        indicatorSeekBar.setTickCount(5);
        searchActivites = findViewById(R.id.lv_search_activites);



        int statusBarHeight = ScreenUtil.getStatusBarHeight(context) + ScreenUtil.dip2px(context, 10);
        RelativeLayout.LayoutParams relativeLayout = (RelativeLayout.LayoutParams) rlTop.getLayoutParams();
        relativeLayout.topMargin = statusBarHeight;
        rlTop.setLayoutParams(relativeLayout);

        List<String> list=new ArrayList<>();
        list.add("美食");
        list.add("服饰");
        list.add("数码");
        list.add("居家");
        list.add("母婴");
        list.add("美妆");
        list.add("美食");
        list.add("服饰");
        list.add("数码");
        list.add("居家");
        list.add("母婴");
        list.add("美妆");
        list.add("美妆");
        list.add("美妆");
        list.add("美妆");
        list.add("美妆");
        list.add("美妆");
        list.add("美妆");

        searchIndustry.setLabels(list, new LabelsView.LabelTextProvider<String>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, String data) {
                return data;
            }
        });

        List<String> list1=new ArrayList<>();
        list1.add("红包");
        list1.add("满减 ");
        list1.add("满赠");
        searchActivites.setLabels(list1, new LabelsView.LabelTextProvider<String>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, String data) {
                return data;
            }
        });



        tvOptionCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              dismiss();
            }
        });

        tvOptionsOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("distance",indicatorSeekBar.getProgressFloat()+"");
                dismiss();
            }
        });
    }

    @Override
    protected void onShow() {
        super.onShow();
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
    }
}
