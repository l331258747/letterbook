package com.csxs.letterbook.mine.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.LabelE;
import com.csxs.letterbook.entity.MineInfoE;
import com.csxs.letterbook.entity.UserEmotionE;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/5/19
 * description:
 */
public interface ModifyInfoContract {

    interface IModifyInfoView extends IBaseView {
//        void queryMineInfoSuccess(MineInfoE mineInfoE);
//        void queryMineInfoFailure(int code, String msg);
//        void updateUserInfoFailure(int code, String msg);
//        void updateUserInfoSuccess(String s);

        void updateNickNameSuccess();

        void updateSexSuccess();

        void updateAgeSuccess();

        void updateEmotionSuccess();

        void updateAddressSuccess();

        void updateSignatureSuccess();

        void updateLabelsSuccess();


        void queryEmotionSuccess(List<UserEmotionE> emotions);

        void queryLabelSuccess(List<LabelE> labels);

        void queryLabelFailure(int code,String msg);

    }

    interface IModifyInfoPresenter {

        void updateUserNickNameInfo(String nickname);

        void updateUserSexInfo(int sex);

        void updateUserAgeInfo(String age);

        void updateUserEmotionIdInfo(int emotionId);

        void updateUserAddressInfo(String address, String provinceid, String cityid, String areaid);

        void updateUserSignatureInfo(String signature);

        void updateUserlabelsInfo(String labels);

        void queryEmotion();

        void queryLabel();

    }


    interface IModifyInfoModel extends IModel {

        Observable<Result<String>> updateInfo();

        Observable<Result<List<UserEmotionE>>> queryEmotion();

        Observable<Result<List<LabelE>>> queryLabel();


//        Observable<Result<String>> updateUserNickNameInfo();
//
//        Observable<Result<String>> updateUserSexInfo();
//
//        Observable<Result<String>> updateUserAgeInfo();
//
//        Observable<Result<String>> updateUserEmotionIdInfo();
//
//        Observable<Result<String>> updateUserAddressInfo();
//
//        Observable<Result<String>> updateUserSignatureInfo();
//
//        Observable<Result<String>> updateUserlabelsInfo();
    }


}
