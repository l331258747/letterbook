package com.csxs.core.net.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.HttpException;


/**
 * Created by Tamic on 2016-08-12.
 */
public class ExceptionHandle {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponeThrowable handleException(Throwable e) {
        ResponeThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponeThrowable(e, ErrorCode.NETWORD_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                    ex.code=NOT_FOUND;
                    ex.message = "NOT_FOUND";
                    break;
                case REQUEST_TIMEOUT:
                    ex.code=REQUEST_TIMEOUT;
                    ex.message = "请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                    ex.code=GATEWAY_TIMEOUT;
                    ex.message = "请接连WIFI或无线网";
                    break;
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                    ex.code=INTERNAL_SERVER_ERROR;
                    ex.message = "服务器错误";
                default:
                    ex.code=ErrorCode.NETWORD_ERROR;
                    ex.message = "网络错误";
                    break;
            }
            return ex;
        } else if(e instanceof UnknownHostException){
            ex = new ResponeThrowable(e, ErrorCode.UnknownHost_TIMEOUT);
            ex.message = "请打开网络服务";
            return ex;
        }else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponeThrowable(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponeThrowable(e, ErrorCode.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponeThrowable(e, ErrorCode.NETWORD_ERROR);
            ex.message = "连接失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponeThrowable(e, ErrorCode.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof ConnectTimeoutException){
            ex = new ResponeThrowable(e, ErrorCode.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ResponeThrowable(e, ErrorCode.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        }
        else {
            ex = new ResponeThrowable(e, ErrorCode.UNKNOWN);
            ex.message = "未知错误";
            return ex;
        }
    }



    public static class ResponeThrowable extends Exception {
        public int code;
        public String message;

        public ResponeThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;

        }
    }

}

