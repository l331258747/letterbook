package com.csxs.letterbook.mine.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.mine.mvp.contract.MineAllOrderContract;
import com.csxs.letterbook.mine.mvp.contract.MineOrderContract;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/26
 * description:
 */
public class MineAllOrderModel extends BaseModel implements MineAllOrderContract.IMineAllOrderModel {


    @Inject
    LetterApiService apiService;

    @Inject
    public MineAllOrderModel() {
    }
}
