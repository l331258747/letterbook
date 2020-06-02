package com.csxs.letterbook.home.mvp;

import com.csxs.core.base.BasePresenter;
import com.csxs.letterbook.home.mvp.contract.HomeContract;
import com.csxs.letterbook.home.mvp.model.HomeModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/9
 * description:
 */
public class HomePresenter extends BasePresenter<HomeModel, HomeContract.IHomeView> {
    @Inject
    public HomePresenter() {}
}
