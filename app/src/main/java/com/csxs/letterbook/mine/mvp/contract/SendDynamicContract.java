package com.csxs.letterbook.mine.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/5/13
 * description:
 */
public interface SendDynamicContract {

    interface ISendDynamicView extends IBaseView {
        void sendDynamicSuccess(String s);
        void sendDynamicFailure(int code,String msg);
    }


    interface ISendDynamicPresenter {

        void sendDynamic(int marchantId, String content, int isMarchant, double latitude, double longitude, String address, List<String> image);

    }



    interface ISendDynamicModel extends IModel {
        Observable<Result<String>>  addMarchantComment(int marchantId, String content, int isMarchant, double latitude, double longitude, String address, List<String> image);
    }
}
