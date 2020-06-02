package com.csxs.letterbook.example.mvp.model;

import android.util.ArrayMap;


import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.fuli;
import com.csxs.letterbook.example.mvp.contract.MvpContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * author: yeliu
 * created on: 2020/1/13 14:37
 * description:
 */
public class MvpModel extends BaseModel implements MvpContract.IHomeInfoModel {

    @Inject
    LetterApiService apiService;



    @Inject
    public MvpModel(){

    }

    @Override
    public Observable<Result<List<fuli>>> getFuli() {
        //针对单个接口 添加头字段
        ArrayMap<String, String> headers=new ArrayMap<>();
        headers.put("token","333333333333333333");
        setHeader(headers);

        ArrayMap<String, String> params=new ArrayMap<>();
        params.put("userId","555555555555");
        setParam(params);
      //  return apiService.getInfoData();
       return null;
    }

    @Override
    public Observable<String> getFuli1() {
        //针对单个接口 添加头字段
        ArrayMap<String, String> headers=new ArrayMap<>();
        headers.put("token","333333333333333333");
        setHeader(headers);
        //针对单个接口 添加参数
        ArrayMap<String, String> params=new ArrayMap<>();
        params.put("userId","555555555555");
        setParam(params);
      //  return apiService.getInfoData1();
        return null;
    }


//    @Override
//    public Observable<String> getToDayCalendarInfo(String today) {
//        ArrayMap<String, String> arrayMap=new ArrayMap<>();
//        arrayMap.put("token","12345664541231");
//        setHeader(arrayMap);
//        return apiService.getToDayCalendarInfo(ConstantsApi.TODAYCALENDARINFO+today);
//    }
//
//    @Override
//    public Observable<String> getHistoryTodayInfo(int type) {
//        return apiService.getHistoryTodayInfo(ConstantsApi.HISTORYTODAY);
//    }


}
