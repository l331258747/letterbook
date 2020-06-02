package com.csxs.letterbook.mine.mvp.presenter;

import android.util.ArrayMap;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.LabelE;
import com.csxs.letterbook.entity.MineInfoE;
import com.csxs.letterbook.entity.UserEmotionE;
import com.csxs.letterbook.mine.mvp.contract.ModifyInfoContract;
import com.csxs.letterbook.mine.mvp.contract.ModifyMineInfoContract;
import com.csxs.letterbook.mine.mvp.model.ModifyInfoModel;
import com.csxs.letterbook.mine.mvp.model.ModifyMineInfoModel;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/5/20
 * description:修改个人资料某一项
 */
public class ModifyInfoPresenter extends BasePresenter<ModifyInfoModel, ModifyInfoContract.IModifyInfoView> implements ModifyInfoContract.IModifyInfoPresenter {

    private final ArrayMap<String, String> arrayMap;

    @Inject
    public ModifyInfoPresenter() {
        arrayMap = new ArrayMap<>();
    }

    @Override
    public void updateUserNickNameInfo(String nickname) {
        arrayMap.put("nickname", nickname);
        mModel.setParam(arrayMap);
        requestNet(1);
    }

    @Override
    public void updateUserSexInfo(int sex) {
        arrayMap.put("sex", String.valueOf(sex));
        mModel.setParam(arrayMap);
        requestNet(2);
    }

    @Override
    public void updateUserAgeInfo(String age) {

        arrayMap.put("birthDay", age);
        mModel.setParam(arrayMap);
        requestNet(3);
    }

    @Override
    public void updateUserEmotionIdInfo(int emotionId) {

        arrayMap.put("emotionId", String.valueOf(emotionId));
        mModel.setParam(arrayMap);
        requestNet(4);
    }

    @Override
    public void updateUserAddressInfo(String address, String provinceid, String cityid, String areaid) {

        arrayMap.put("cityid", cityid);
        arrayMap.put("areaid", areaid);
        arrayMap.put("provinceid", provinceid);
        arrayMap.put("address", address);
        mModel.setParam(arrayMap);
        requestNet(5);
    }

    @Override
    public void updateUserlabelsInfo(String labels) {
        arrayMap.put("labels", labels);
        mModel.setParam(arrayMap);
        requestNet(6);
    }

    @Override
    public void updateUserSignatureInfo(String signature) {

        arrayMap.put("signature", signature);
        mModel.setParam(arrayMap);
        requestNet(7);
    }



    @Override
    public void queryEmotion() {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.queryEmotion(), new ResultErrorObserver<List<UserEmotionE>>() {
            @Override
            public void handlerResult(List<UserEmotionE> list) {
                getView().queryEmotionSuccess(list);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().showError(code, msg);
            }
        });
    }

    @Override
    public void queryLabel() {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.queryLabel(), new ResultErrorObserver<List<LabelE>>() {
            @Override
            public void handlerResult(List<LabelE> list) {
                getView().queryLabelSuccess(list);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().queryLabelFailure(code, msg);
            }
        });
    }


    public void requestNet(int type) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.updateInfo(), new ResultErrorObserver<String>() {
            @Override
            public void handlerResult(String s) {
                switch (type) {
                    case 1:
                        getView().updateNickNameSuccess();
                        break;
                    case 2:
                        getView().updateSexSuccess();
                        break;
                    case 3:
                        getView().updateAgeSuccess();
                        break;
                    case 4:
                        getView().updateEmotionSuccess();
                        break;
                    case 5:
                        getView().updateAddressSuccess();
                        break;
                    case 6:
                        getView().updateLabelsSuccess();
                        break;
                    case 7:
                        getView().updateSignatureSuccess();
                        break;
                }

            }

            @Override
            public void handlerError(int code, String msg) {
                getView().showError(code, msg);
            }
        });
    }


}
