package com.csxs.letterbook.api;

import com.csxs.core.BaseConstants;

public class ConstantsApi{



    /**
     * 登录获取验证码
     */
    public static final String LOGIN_GET_SMS_CODE = "xssj/userRegister/sendDX";

    /**
     *验证码登录
     */
    public static final String LOGIN_BY_SMS_CODE = "xssj/userRegister/phoneLogin";

    /**
     * 设置用户基本资料
     */
    public static final String REGISTER_SET_USER_INFO = "xssj/userRegister/setUserInfo";

    /**
     * 微信登录
     */
    public static final String REGISTER_WECHAT_LOGIN = "xssj/userRegister/weChatLogin";

    /**
     * 绑定手机号码
     */
    public static final String REGISTER_BIND_PHONE = "xssj/userRegister/bindingPhone";


    /**
     *查询自己的详细信息接口
     */
    public static final String REGISTER_QUERYUSINFO = "xssj/userRegister/queryUsInfo";

    /**
     *更新自己的信息接口
     */
    public static final String REGISTER_UPUSERINFO = "xssj/userRegister/upUserInfo";


    /**
     *查询个人信息 情感接口
     */
    public static final String REGISTER_QUERYEMOTION = "xssj/userRegister/queryEmotion";

    /**
     *查询标签 接口
     */
    public static final String REGISTER_QUERYLABEL = "xssj/userRegister/queryLabel";




    /**
     *查询周边商户信息接口
     */
    public static final String MAP_SELLER_MARCHANT_QUERYMARCHANTLIST = "xssj/marchant/queryMarchantList";


    /**
     *查询周边商户详情信息接口
     */
    public static final String MAP_SELLER_MARCHANT_QUERYMARCHANTINFOLIST = "xssj/marchant/queryMarchantInfoList";

    /**
     * 商家关注
     */
    public static final String SELLER_MARCHANT_ADDMARCHANTCONCERNS = "xssj/marchant/addMarchantConcerns";

    /**
     * 商家取消关注
     */
    public static final String SELLER_MARCHANT_DELMARCHANTCONCERNS = "xssj/marchant/delMarchantConcerns";

    /**
     * 商家主页动态
     */
    public static final String SELLER_HOME_MARCHANT_QUERYMARCHANTCOMMENT = "xssj/marchant/queryMarchantComment";

    /**
     * 商家主页动态评论详情
     */
    public static final String SELLER_HOME_MARCHANT_QUERYMARCHANTCOMMENTREPLY = "xssj/marchant/queryMarchantCommentReply";


    /**
     * 商家动态回复添加接口
     */
    public static final String SELLER_HOME_MARCHANT_ADDMARCHANTCOMMENTREPLY = "xssj/marchant/addMarchantCommentReply";

    /**
     *商家动态添加接口
     */
    public static final String SELLER_HOME_MARCHANT_ADDMARCHANTCOMMENT = "marchant/addMarchantComment";

    /**
     *商家商品查询接口
     */
    public static final String SELLER_HOME_MARCHANT_QUERYCOMMODITY = "xssj/commodity/queryCommodity";





    /**
     *查询周边  社交用户信息接口
     */
    public static final String MAP_SOCIAL_QUERYSOCIALLIST = "xssj/social/querySocialList";


    /**
     *查询单个 社交用户信息接口
     */
    public static final String MAP_SOCIAL_QUERYUSERINFO = "xssj/social/queryUserInfo";



    /**
     *查询附近人的动态接口
     */
    public static final String SOCIAL_QUERYNEARBYCOMM = "xssj/social/queryNearbyComm";




    /**
     * 通用 动态点赞接口
     */
    public static final String DYNAMIC_THUMBS = "xssj/dynamic/thumbs";

    /**
     * 动态关注与取消关注
     */
    public static final String DYNAMIC_ATTENTION = "xssj/social/addDelConcerns";


    /**
     * 查询商铺提交的商品 信息
     */
    public static final String COMMODITY_ONEKEYABOUTORDER = "xssj/commodity/onekeyAboutOrder";






}

