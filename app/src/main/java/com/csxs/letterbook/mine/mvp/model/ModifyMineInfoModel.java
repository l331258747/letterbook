package com.csxs.letterbook.mine.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.MineInfoE;
import com.csxs.letterbook.mine.mvp.contract.MineContract;
import com.csxs.letterbook.mine.mvp.contract.ModifyMineInfoContract;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public class ModifyMineInfoModel extends BaseModel implements ModifyMineInfoContract.IModifyMineInfoModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public ModifyMineInfoModel() {
    }

    @Override
    public Observable<Result<MineInfoE>> getMineInfo() {
        return apiService.queryMineInfo();
    }

    @Override
    public Observable<Result<String>> upUserInfo(String url) {
        File file = new File(url);
        RequestBody fileRQ = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("headimg", file.getName(), fileRQ)
                .build();
          return apiService.upUserInfo(requestBody);

    }
}
