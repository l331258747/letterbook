package com.csxs.viewlib.dialog.listener;

import android.view.View;

import com.csxs.viewlib.dialog.BindViewHolder;
import com.csxs.viewlib.dialog.TDialog;


public interface OnViewClickListener {
    void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog);
}
