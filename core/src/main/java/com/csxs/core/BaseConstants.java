package com.csxs.core;


import okhttp3.MediaType;

/**
 * 全局静态数据
 */
public class BaseConstants {

    public static final String BASE_URL = BuildConfig.HTTP_BASE;

    public static final String WX_APPID = BuildConfig.WX_APPID;

    public static final String WX_APPSECRET = BuildConfig.WX_APPSECRET;

    public static final int USER_IDENTITY_FLAG = 0;//普通用户


    public static final int NET_CODE_SUCCESS = 200;

    public static final int NET_CODE_ERROR = 1;

    public static final int SELECTOR_SINGLE_IMAGE = 10010;
    public static final int SELECTOR_MULTI_IMAGE = 10011;

    public static final MediaType MEDIA_MULTIPART = MediaType.parse("multipart/form-data");

    public static final int NET_CODE_CONNECT = 400;
    public static final int NET_CODE_UNKNOWN_HOST = 401;
    public static final int NET_CODE_SOCKET_TIMEOUT = 402;

    public static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    public static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    public static final String UNKNOWN_HOST_EXCEPTION = "与服务器连接失败";
    public static final String EMPTY_RESPONSE_EXCEPTION = "无效的返回";


    public static final String KEY_USER_ACCOUNT = "account";//ACCID
    public static final String KEY_USER_TOKEN = "token";//云信token
    public static final String KEY_SESSION_TOKEN = "sessiontoken";//自己的token
    public static final String KEY_USER_NAME = "user_name";//自己登陆的账号
    public static final String KEY_USER_CARDID = "card_id";//自己登陆的账号Id
    public static final String KEY_USER_AFFECTIVE = "affective";
    public static final String KEY_USER_VIP = "user_vip";
    public static final String KEY_USER_PERFECT = "perfect";
    public static final String KEY_USER_HEADIMAGE = "headimage";
    public static final String KEY_USER_GANDER = "gender";
    public static final String KEY_USER_BIRTHDAY = "birthday";
    public static final String KEY_USER_NICKNAME = "nickname";

    public static final String KEY_FONT_SIZE = "fontsize";//ACCID

    public static final String KEY_USER_IMG = "user_img";

    public static final String KEY_CITY = "city";

    public static final String KEY_USER_ALIAS = "alias";

    public static final String EXTRA_KAY1 = "key1";
    public static final String EXTRA_KAY2 = "key2";
    public static final String EXTRA_KAY3 = "key3";
    public static final String EXTRA_KAY4 = "key4";
    public static final String EXTRA_KAY5 = "key5";

    public static final String EXTRA_TYPE = "type";


    public static final int TYPE_EVENT_OPERATION = 0;
    public static final int TYPE_EVENT_ADD = 1;
    public static final int TYPE_EVENT_CANCEL = 2;


    //空布局 0
    public static final int STATE_LAYOUT_EMPTY = 0;

    //网络错误 1
    public static final int STATE_LAYOUT_NETWORK = 1;

    //数据错误 2
    public static final int STATE_LAYOUT_DATA = 2;

    //未知错误 3
    public static final int STATE_LAYOUT_UNKNOWN = 4;

}
