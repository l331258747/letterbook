package com.csxs.letterbook.mine.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public interface OnDeleteItemClickListener {
    void onItemLongClick(SendDynamicImageAdapter adapter, int position, View v);
}
