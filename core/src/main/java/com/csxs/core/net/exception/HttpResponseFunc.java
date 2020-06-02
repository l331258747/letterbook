package com.csxs.core.net.exception;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class HttpResponseFunc <T> implements Function<Throwable, Observable<T>> {

    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {
        //ExceptionEngine为处理异常的驱动器
        return Observable.error(ExceptionHandle.handleException(throwable));
    }
}
