package com.csxs.core.net.result;

import com.csxs.core.BaseConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SenYe on 2018/1/26.
 */

public abstract class ResultCallBack<T> implements Callback<Result<T>> {
    @Override
    public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {
        if (response != null && response.body()!=null) {
            if (response.body().getCode() == BaseConstants.NET_CODE_SUCCESS) {
                handlerResult(true, null, response.body().getResults());
            } else {
                handlerResult(false, new Throwable(response.body().getMsg()), null);
            }
        } else {
            handlerResult(false, new Throwable(BaseConstants.EMPTY_RESPONSE_EXCEPTION), null);
        }
    }

    @Override
    public void onFailure(Call<Result<T>> call, Throwable t) {
        handlerResult(false, t, null);
    }

    public abstract void handlerResult(boolean success, Throwable throwable, T t);
}
