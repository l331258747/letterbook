package com.csxs.letterbook.login.mvp.model;

import android.content.Context;
import android.util.ArrayMap;

import androidx.lifecycle.Lifecycle;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.fuli;
import com.csxs.letterbook.example.mvp.contract.MvpContract;
import com.csxs.letterbook.login.mvp.contract.LoginContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * 闪屏页Model
 */
public class SplashModel extends BaseModel implements LoginContract.ISplashModel {

    @Inject
    LetterApiService apiService;



    @Inject
    public SplashModel(){}


}
