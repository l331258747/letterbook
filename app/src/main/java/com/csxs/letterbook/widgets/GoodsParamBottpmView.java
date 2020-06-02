package com.csxs.letterbook.widgets;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.CommodityListE;
import com.csxs.letterbook.entity.GoodsSpec;
import com.csxs.letterbook.entity.SpecListE;
import com.csxs.letterbook.entity.SpecResultListE;
import com.csxs.letterbook.seller.adapter.GoodsParamAdapter;
import com.csxs.letterbook.widgets.numbtn.AddWidget;
import com.csxs.viewlib.LabelsView;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: yeliu
 * created on 2020/5/18
 * description:
 */
public class GoodsParamBottpmView extends BottomPopupView {


    private ImageView goodsImage;

    private ImageView ivCloseGoodsParam;

    private TextView goodsNameParam;

    private TextView goodsParamText;

    private TextView ivGoodsPrice;

    private RecyclerView rcGoodsParam;

    private TextView selectParamFinish;

    private Context context;

    private GoodsParamAdapter goodsParamAdapter;

    CommodityListE commodityList;
    private LinearLayoutManager manager;
    private AddWidget.OnAddClick onAddClick;
    private HashSet<SpecResultListE> hashSet=new HashSet<>();
    private SpecResultListE specResultListE;
    public GoodsParamBottpmView(@NonNull Context context, CommodityListE commodityList, AddWidget.OnAddClick onAddClick) {
        super(context);
        this.context = context;
        this.commodityList = commodityList;
        this.onAddClick = onAddClick;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.layout_goods_param;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        goodsImage = findViewById(R.id.iv_goods_param_image);
        goodsNameParam = findViewById(R.id.iv_param_goods_name);
        goodsParamText = findViewById(R.id.tv_goods_param_text);
        ivGoodsPrice = findViewById(R.id.iv_goods_price);
        ivCloseGoodsParam = findViewById(R.id.iv_close_goods_param);

       // rcGoodsParam = findViewById(R.id.recycler_goods_param);
        selectParamFinish = findViewById(R.id.tv_select_finish);

      //  manager = new LinearLayoutManager(context);
     //   goodsParamAdapter = new GoodsParamAdapter(context);
    //    rcGoodsParam.setLayoutManager(manager);
    //    rcGoodsParam.setAdapter(goodsParamAdapter);
    //    rcGoodsParam.getItemAnimator().setChangeDuration(0);
     //   goodsParamAdapter.setData(commodityList.getSpecResultList());

      LabelsView  labelsView=   findViewById(R.id.lv_goods_param);

        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                Log.e("onLabelSelectChange", position + "");



            }
        });


        labelsView.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
//                      for (int i=0;i<commodityList.getSpecResultList().size();i++){
//
//                      }
                specResultListE=      commodityList.getSpecResultList().get(position);
//                if (isSelect) {
//                    hashSet.add(commodityList.getSpecResultList().get(position));
//                } else {
//                    hashSet.remove(commodityList.getSpecResultList().get(position));
//                }
            }
        });

        if(commodityList.getSpecResultList()!=null&&commodityList.getSpecResultList().size()>0){
            labelsView.setLabels(commodityList.getSpecResultList(), new LabelsView.LabelTextProvider<SpecResultListE>() {
                @Override
                public CharSequence getLabelText(TextView label, int position, SpecResultListE data) {
                    return data.getItemText();
                }
            });
        }





        selectParamFinish.setOnClickListener(v -> {
            if (onAddClick != null) {
                v.setTag("finish");
                if(specResultListE!=null){
                    commodityList.setSelectSpec(specResultListE);
                    commodityList.setSelectCount(1);
                    onAddClick.onAddClick(v,commodityList);
                    dismiss();
                }


//                    StringBuilder stringBuilder = new StringBuilder();
//                    List<GoodsSpec> list = goodsParamAdapter.getData();
//                    for (int i = 0; i < list.size(); i++) {
//                        int specSize = list.get(i).getSpecPList().size();
//                        for (int k = 0; k < specSize; k++) {
//                            if (list.get(i).getSpecPList().get(k).isSelect()) {
//                                select+=1;
//
//                                if(select==list.size()){
////                                    if (stringBuilder.length() > 0) {
////                                        stringBuilder.append(",");
////                                    }
////                                    stringBuilder.append(spac);
//                                    commodityList.setSelectCount(1);
//                                    onAddClick.onAddClick(v,commodityList);
//                                    dismiss();
//                                }
//
//                            }
//
//                        }
//                    }


            }
        });


    }


    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
    }

    //完全消失执行
    @Override
    protected void onDismiss() {

    }

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .85f);
    }
}
