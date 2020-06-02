package com.csxs.letterbook.mine.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.dynamic.mvp.contract.DynamicContract;
import com.csxs.letterbook.entity.MineInfoE;
import com.csxs.letterbook.mine.mvp.contract.MineContract;
import com.csxs.letterbook.mine.mvp.contract.MineOrderContract;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public class MineModel extends BaseModel implements MineContract.IMineModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public MineModel() {
    }

    @Override
    public Observable<Result<MineInfoE>> getMineInfo() {
        return apiService.queryMineInfo();
    }
}
