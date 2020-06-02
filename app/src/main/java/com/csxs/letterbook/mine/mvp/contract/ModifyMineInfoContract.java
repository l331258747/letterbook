package com.csxs.letterbook.mine.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.MineInfoE;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/5/19
 * description:
 */
public interface ModifyMineInfoContract {

    interface IModifyMineInfoView extends IBaseView {
        void queryMineInfoSuccess(MineInfoE mineInfoE);
        void queryMineInfoFailure(int code,String msg);
        void updateUserInfoFailure(int code,String msg);
        void updateUserInfoSuccess(String s);

        void updateHeaderImageSuccess(String s);
        void updateHeaderImageFailure(int code,String msg);
    }

    interface IModifyMineInfoPresenter {
          void getMineInfo();
          void updateUserInfo(String nickname,String sex,String birthDay,int emotionId,String address,String signature,String labels,String headimg,String album);
          void updateUserInfo(String headimg);
        //void sendDynamic(int marchantId, String content, int isMarchant, double latitude, double longitude, String address, List<String> image);
    }


    interface IModifyMineInfoModel extends IModel {
          Observable<Result<MineInfoE>> getMineInfo();
          Observable<Result<String>> upUserInfo(String url);

    }


}
