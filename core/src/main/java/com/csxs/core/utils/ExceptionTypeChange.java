package com.csxs.core.utils;


import com.csxs.core.BaseConstants;
import com.csxs.core.net.exception.ErrorCode;
import com.csxs.core.net.exception.ExceptionHandle;

public class ExceptionTypeChange {

//    public static int changeTopicType(int code) {
//        if (Constant.DYNAMIC_ARTILC.equals(status)) {
//            return Constant.DYNAMIC_ARTILC_TYPE;
//        } else if (Constant.DYNAMIC_FOLLOW.equals(status)) {
//            return Constant.DYNAMIC_FOLLOW_TYPE;
//        }else if(Constant.DYNAMIC_TOPIC.equals(status)){
//            return Constant.DYNAMIC_TOPIC_TYPE;
//        }else if(Constant.DYNAMIC_FRIEND.equals(status)){
//            return Constant.DYNAMIC_FRIEND_TYPE;
//        }
//        return Constant.DYNAMIC_FOLLOW_TYPE;
//    }

    public static int handleException(int code) {
        int layoutCode = 0;
        switch (code) {
            case ErrorCode.UNAUTHORIZED:
            case ErrorCode.FORBIDDEN:
            case ErrorCode.NOT_FOUND:
            case ErrorCode.REQUEST_TIMEOUT:
            case ErrorCode.GATEWAY_TIMEOUT:
            case ErrorCode.INTERNAL_SERVER_ERROR:
            case ErrorCode.BAD_GATEWAY:
            case ErrorCode.SERVICE_UNAVAILABLE:
            case ErrorCode.UnknownHost_TIMEOUT:
            case ErrorCode.NETWORD_ERROR:
            case ErrorCode.SSL_ERROR:
            case ErrorCode.TIMEOUT_ERROR:
                layoutCode = BaseConstants.STATE_LAYOUT_NETWORK;
                break;
            case ErrorCode.PARSE_ERROR:
                layoutCode = BaseConstants.STATE_LAYOUT_DATA;
                break;
            case ErrorCode.UNKNOWN:
                layoutCode = BaseConstants.STATE_LAYOUT_UNKNOWN;
                break;
            default:

                break;
        }

        return layoutCode;

    }

}
