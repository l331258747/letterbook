package com.csxs.core.net.gson.gsonconverter;

import com.csxs.core.net.exception.ServerException;
import com.csxs.core.net.result.Result;
import com.csxs.core.net.result.ResultError;
import com.google.gson.Gson;


import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    /**
     * 针对数据返回成功、错误不同类型字段处理
     */
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
      //  Log.e("ResponseBody",response);
        try {
         //   Result result = gson.fromJson(response, Result.class);

//            String code = result.getStatusCode();
//            if ("200".equals(code)){
//                if(result.getResults()==null){
//                    result.setResults(new Object());
//                    return gson.fromJson(response, type );
//                }
//                return gson.fromJson(response, type );
//            } else {
//                ResultError errResponse = gson.fromJson(response, ResultError.class);
//                throw new ServerException(Integer.valueOf(errResponse.getStatusCode()),errResponse.getMessage());
////                if(code == ){
////                    throw new ResultException(errResponse.getRetMsg(), code);
////                }else{
////                    throw new ResultException(errResponse.getErrMsg(), code);
////                }
//            }
           return gson.fromJson(response, type );
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            value.close();
        }

        return null;
    }
}
