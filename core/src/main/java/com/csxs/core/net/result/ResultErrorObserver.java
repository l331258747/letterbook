package com.csxs.core.net.result;


import com.csxs.core.BaseConstants;
import com.csxs.core.net.exception.ErrorCode;
import com.csxs.core.net.exception.ExceptionHandle;

import org.greenrobot.eventbus.EventBus;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * 请求网络接口 回调
 *
 * @param <T>
 */

public abstract class ResultErrorObserver<T> implements Observer<Result<T>> {

    private Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
    }

    @Override
    public void onNext(Result<T> result) {
        if (result != null) {
            if (result.getCode() == BaseConstants.NET_CODE_SUCCESS) {
                handlerResult(result.getResults());
            } else {
                handlerError(result.getCode(), result.getMsg());
            }
        } else {
            handlerError(BaseConstants.NET_CODE_ERROR, BaseConstants.EMPTY_RESPONSE_EXCEPTION);
        }
    }

    @Override
    public void onError(Throwable e) {
        try {
            ExceptionHandle.ResponeThrowable responeThrowable = ExceptionHandle.handleException(e);
            handlerError(responeThrowable.code, responeThrowable.message);
        } catch (Throwable t) {
            RxJavaPlugins.onError(new CompositeException(e, t));
        }

    }

    @Override
    public void onComplete() {

    }

    /**
     * 返回正常数据
     */
    public abstract void handlerResult(T t);

    /**
     * 返回业务错误
     */
    public abstract void handlerError(int code, String msg);

}
