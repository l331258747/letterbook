package com.csxs.letterbook.mine.mvp.presenter;

import com.csxs.core.base.BasePresenter;
import com.csxs.core.net.result.ResultErrorObserver;
import com.csxs.letterbook.entity.MineInfoE;
import com.csxs.letterbook.mine.mvp.contract.ModifyMineInfoContract;
import com.csxs.letterbook.mine.mvp.contract.SendDynamicContract;
import com.csxs.letterbook.mine.mvp.model.ModifyMineInfoModel;
import com.csxs.letterbook.mine.mvp.model.SendDynamicModel;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/5/19
 * description:
 */
public class ModifyMineInfoPresenter extends BasePresenter<ModifyMineInfoModel, ModifyMineInfoContract.IModifyMineInfoView> implements ModifyMineInfoContract.IModifyMineInfoPresenter {
    @Inject
    public ModifyMineInfoPresenter() {

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

    @Override
    public void updateUserInfo(String nickname,String sex,String birthDay,int emotionId,String address,String signature,String labels,String headimg,String album) {
        HashMap<String,String> hashMap=new HashMap<>();
        if(nickname!=null){
            hashMap.put("nickname",nickname);
        }

        hashMap.put("sex",sex);
        hashMap.put("birthDay",birthDay);
        hashMap.put("emotionId",String.valueOf(emotionId));
        hashMap.put("address",address);
        hashMap.put("signature",signature);
        hashMap.put("labels",labels);
    }

    @Override
    public void updateUserInfo(String headimg) {
        requestHttpResult(getView().getOwnerLifeCycle(), mModel.upUserInfo(headimg), new ResultErrorObserver<String>() {
            @Override
            public void handlerResult(String s) {
                getView().updateHeaderImageSuccess(s);
            }

            @Override
            public void handlerError(int code, String msg) {
                getView().updateHeaderImageFailure(code,msg);
            }
        });
    }
}
