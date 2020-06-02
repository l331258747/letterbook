package com.csxs.letterbook.mine.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.letterbook.mine.mvp.contract.MineCartShopContract;
import com.csxs.letterbook.mine.mvp.contract.MineOrderContract;
import com.csxs.letterbook.mine.mvp.model.MineCartShopModel;
import com.csxs.letterbook.mine.mvp.model.MineOrderModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/26
 * description:
 */
public class MineOrderPresenter extends BasePresenter<MineOrderModel, MineOrderContract.IMineOrderView> {

    @Inject
    public MineOrderPresenter() { }
}
