package com.csxs.letterbook.widgets;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.csxs.letterbook.R;
import com.lxj.xpopup.core.HorizontalAttachPopupView;
import com.lxj.xpopup.core.PositionPopupView;


public class LeftSocailPopup extends PositionPopupView {

    leftPopState leftPopState;

    private boolean popstate=true;
    public LeftSocailPopup(@NonNull Context context,leftPopState leftPopState) {
        super(context);
        this.leftPopState=leftPopState;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.layout_left_social_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        ImageView ivLeftSocial=findViewById(R.id.iv_left_social);
        ivLeftSocial.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(popstate){
                     popstate=false;
                     leftPopState.PopDismiss();
                     dismiss();
                 }else{
                     popstate=true;
                     leftPopState.PopOpen();
                 }
            }
        });

    }

    public interface leftPopState{
          void PopOpen();
          void PopDismiss();
    }
}
