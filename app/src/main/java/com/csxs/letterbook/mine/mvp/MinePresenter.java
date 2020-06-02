package com.csxs.letterbook.mine.mvp;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.dynamic.mvp.contract.DynamicContract;
import com.csxs.letterbook.dynamic.mvp.model.DynamicModel;
import com.csxs.letterbook.entity.MineInfoE;
import com.csxs.letterbook.mine.mvp.contract.MineContract;
import com.csxs.letterbook.mine.mvp.model.MineModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public class MinePresenter extends BasePresenter<MineModel, MineContract.IMineView> implements MineContract.IMinePresenter {

    @Inject
    public MinePresenter() {
    }

    @Override
    public void getMineInfo() {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.getMineInfo(), new ResultErrorObserver<MineInfoE>() {
            @Override
            public void handlerResult(MineInfoE mineInfoE) {
                getView().queryMineInfoSuccess(mineInfoE);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().queryMineInfoFailure(code,msg);
            }
        });
    }
}
