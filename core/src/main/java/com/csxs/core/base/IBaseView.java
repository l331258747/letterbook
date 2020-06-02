package com.csxs.core.base;

import android.content.Context;

import androidx.lifecycle.Lifecycle;

/**
 * Created by SenYe on 2018/1/24.
 */

public interface IBaseView {

    void showLoading();


    void hideLoading();

    void showError(String message);

    void showError(int code,String message);

    Context getContext();

    Lifecycle getOwnerLifeCycle();
}
