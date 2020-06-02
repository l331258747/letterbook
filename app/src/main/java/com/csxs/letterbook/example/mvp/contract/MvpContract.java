package com.csxs.letterbook.example.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.fuli;
import java.util.List;

import io.reactivex.Observable;

/**
 * author: yeliu
 * created on: 2020/1/17 11:23
 * description:
 */
public interface MvpContract {




    interface IHomeInfoView extends IBaseView {
        void reusltSucessFuli(List<fuli> list);

        void reusltSucessFuli(String string);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
    interface IHomeInfoModel extends IModel {

        Observable<Result<List<fuli>>> getFuli();

        Observable<String> getFuli1();
    }
}
