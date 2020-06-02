package com.csxs.letterbook.login.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.exception.ServerException;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.login.mvp.contract.PrefectUserInfoContract;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author: yeliu
 * created on 2020/4/10
 * description:完善用户信息 Model
 */
public class PrefectUserInfoModel extends BaseModel implements PrefectUserInfoContract.IPrefectUserInfoModel {
    @Inject
    LetterApiService apiService;

    @Inject
    public PrefectUserInfoModel() {
    }

    @Override
    public Observable<String> submitUserInfo(String nickname, String phoneNumber, int sex, File file) {
        RequestBody fileRQ = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("nickname", nickname)
                .addFormDataPart("sex", String.valueOf(sex))
                .addFormDataPart("file", file.getName(), fileRQ)
                .build();
//        submitUserInfo(nackname,"",sex,file).map(new Function<Result<String>, String>() {
//            @Override
//            public String apply(Result<String> stringResult) throws Exception {
//                if(stringResult.getCode()!=200){
//                    throw new ServerException(stringResult.getCode(), stringResult.getMessage());
//                }
//
//                if (stringResult.getResults()==null){
//                    stringResult.setResults("");
//                }
//                return stringResult.getResults();
//            }
//        });
//        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        return apiService.submitUserBaiscInfo(requestBody).map(new Function<Result<String>, String>() {
            @Override
            public String apply(Result<String> stringResult) throws Exception {
                if(stringResult.getCode()!=200){
                    throw new ServerException(stringResult.getCode(), stringResult.getMsg());
                }

                if (stringResult.getResults()==null){
                    stringResult.setResults("");
                }
                return stringResult.getResults();
            }
        });

    }

    public Observable<Result<String>> submitUserInfo11(String nickname, String phoneNumber, int sex, File file) {
        RequestBody fileRQ = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("nickname", nickname)
                .addFormDataPart("sex", String.valueOf(sex))
                .addFormDataPart("file", file.getName(), fileRQ)
                .build();
        return apiService.submitUserBaiscInfo(requestBody);
    }
}
