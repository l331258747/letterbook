package com.csxs.letterbook.mine.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.letterbook.dynamic.mvp.model.DynamicModel;
import com.csxs.letterbook.mine.mvp.contract.MineCartShopContract;
import com.csxs.letterbook.mine.mvp.contract.MineContract;
import com.csxs.letterbook.mine.mvp.model.MineCartShopModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:我的购物车 P
 */
public class MineCartShopPresenter extends BasePresenter<MineCartShopModel, MineCartShopContract.IMineCartView> {

    @Inject
    public MineCartShopPresenter() { }
}
