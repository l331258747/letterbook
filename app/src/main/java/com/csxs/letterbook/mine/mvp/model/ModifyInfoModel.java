package com.csxs.letterbook.mine.mvp.model;

import android.util.ArrayMap;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.LabelE;
import com.csxs.letterbook.entity.UserEmotionE;
import com.csxs.letterbook.mine.mvp.contract.ModifyInfoContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public class ModifyInfoModel extends BaseModel implements ModifyInfoContract.IModifyInfoModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public ModifyInfoModel() {

    }


    @Override
    public Observable<Result<String>> updateInfo() {
        return apiService.upUserInfo("flag");
    }

    @Override
    public Observable<Result<List<UserEmotionE>>> queryEmotion() {
        return apiService.queryEmotion();
    }

    @Override
    public Observable<Result<List<LabelE>>> queryLabel() {
        return apiService.queryLabel();
    }
}
