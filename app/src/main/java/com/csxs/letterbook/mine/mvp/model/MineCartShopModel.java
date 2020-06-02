package com.csxs.letterbook.mine.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.mine.mvp.contract.MineCartShopContract;
import com.csxs.letterbook.mine.mvp.contract.MineContract;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public class MineCartShopModel extends BaseModel implements MineCartShopContract.IMineCartModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public MineCartShopModel() {
    }
}
