package com.csxs.letterbook.mine.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.mine.mvp.contract.SendDynamicContract;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 *    上传图片带参数
 *    @POST(ConstantsApi.SELLER_HOME_MARCHANT_ADDMARCHANTCOMMENT)
 *    Observable<Result<String>> addMarchantComment(@Body RequestBody body);
 *
 *       MultipartBody.Builder builder = new MultipartBody.Builder();
 *         builder.setType(MultipartBody.FORM);
 *         if (image != null && image.size() > 0) {
 *
 *             for (int i = 0; i < image.size(); i++) {
 *                 File file = new File(image.get(i));
 *                 RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
 *                 builder.addFormDataPart("file", file.getName(), imageBody);
 *             }
 *         }
 *
 *         builder.addFormDataPart("marchantId", String.valueOf(marchantId));
 *         builder.addFormDataPart("content", content);
 *         builder.addFormDataPart("isMarchant", String.valueOf(isMarchant));
 *         builder.addFormDataPart("latitude", String.valueOf(latitude));
 *         builder.addFormDataPart("longitude", String.valueOf(longitude));
 *         builder.addFormDataPart("address", address);
 *         RequestBody requestBody = builder.build();
 */

/**
 * @author: yeliu
 * created on 2020/5/13
 * description:
 */
public class SendDynamicModel extends BaseModel implements SendDynamicContract.ISendDynamicModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public SendDynamicModel() {

    }

    @Override
    public Observable<Result<String>> addMarchantComment(int marchantId, String content, int isMarchant, double latitude, double longitude, String address, List<String> image) {

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("marchantId",String.valueOf(marchantId));
        hashMap.put("content",content);
        hashMap.put("isMarchant",String.valueOf(isMarchant));
        hashMap.put("latitude",String.valueOf(latitude));
        hashMap.put("longitude",String.valueOf(longitude));
        hashMap.put("address",address);

        return apiService.addMarchantComment(toRequestBody(hashMap,image));
    }
}
