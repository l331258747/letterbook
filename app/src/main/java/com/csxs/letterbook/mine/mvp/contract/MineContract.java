package com.csxs.letterbook.mine.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.MineInfoE;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public interface MineContract {

    interface IMineView extends IBaseView {
        void queryMineInfoSuccess(MineInfoE mineInfoE);
        void queryMineInfoFailure(int code,String msg);
    }

    interface IMinePresenter {

        void getMineInfo();
    }


    interface IMineModel extends IModel {
        Observable<Result<MineInfoE>> getMineInfo();
    }
}
