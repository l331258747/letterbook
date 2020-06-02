package com.csxs.letterbook.example.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.fuli;

import java.util.List;

public class ExampleAdapter extends BaseQuickAdapter<fuli,BaseViewHolder> {

    public ExampleAdapter(int layoutResId, @Nullable List<fuli> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, fuli item) {
       TextView textView= helper.getView(R.id.tv_desc);
        textView.setText(item.getDesc());

    }
}
