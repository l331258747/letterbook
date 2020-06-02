package com.csxs.letterbook.seller.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.GoodsSpec;
import com.csxs.letterbook.entity.SpecListE;
import com.csxs.letterbook.widgets.section.SectionedRecyclerViewAdapter;
import com.csxs.viewlib.LabelsView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/18
 * description:
 */
public class GoodsParamAdapter extends SectionedRecyclerViewAdapter<GoodsParamTitleHolder, GoodsParamHolder, RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<GoodsSpec> list;

    public GoodsParamAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);

    }


    public void setData(List<GoodsSpec> goodsSpecs) {
        if (goodsSpecs == null) {
            return;
        }
        this.list = goodsSpecs;
        notifyDataSetChanged();
    }

    public List<GoodsSpec> getData() {
        return list;
    }

    @Override
    protected int getSectionCount() {

        return list == null ? 0 : list.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        return list == null ? 0 : 1;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected GoodsParamTitleHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new GoodsParamTitleHolder(mInflater.inflate(R.layout.item_goods_param_title, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected GoodsParamHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new GoodsParamHolder(mInflater.inflate(R.layout.item_seller_goods_param, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(GoodsParamTitleHolder holder, int section) {

        holder.paramTitle.setText(list.get(section).getSpecificationName());

    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(GoodsParamHolder holder, int section, int position) {

        holder.labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                Log.e("onLabelSelectChange", position + "");
                // String paramText = list.get(section).getSpecPList().get(position).getItemText();
                if (list.get(section).getSpecPList().get(position).isSelect()) {
                    list.get(section).getSpecPList().get(position).setSelect(false);
                } else {
                    list.get(section).getSpecPList().get(position).setSelect(true);
                }

            }
        });
        int size = list.get(section).getSpecPList().size();
        Log.e("onBindItemViewHolder", size + "");

        if (list.get(section).getSpecPList() != null && list.get(section).getSpecPList().size() > 0) {
            holder.labelsView.setLabels(list.get(section).getSpecPList(), new LabelsView.LabelTextProvider<SpecListE>() {
                @Override
                public CharSequence getLabelText(TextView label, int position, SpecListE data) {
                    return data.getItemText();
                }
            });
        }


    }
}
