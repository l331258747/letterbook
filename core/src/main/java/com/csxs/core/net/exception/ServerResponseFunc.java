package com.csxs.core.net.exception;


import com.csxs.core.net.result.Result;

import io.reactivex.functions.Function;

public class ServerResponseFunc<T> implements Function<Result<T>, T> {


    @Override
    public T apply(Result<T> reponse) throws Exception {
        //对返回码进行判断，如果不是0，则证明服务器端返回错误信息了，便根据跟服务器约定好的错误码去解析异常
        if (reponse.getCode()!=200) {
            //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
            throw new ServerException(reponse.getCode(), reponse.getMsg());
        }

        //code == 200 data 必须不能为 null,服务器请求数据成功，返回里面的数据实体
        return reponse.getResults();
    }



}
