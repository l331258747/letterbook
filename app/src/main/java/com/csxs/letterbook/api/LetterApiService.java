package com.csxs.letterbook.api;

import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.LabelE;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.MapSellerStoreE;
import com.csxs.letterbook.entity.MapSocialInfoE;
import com.csxs.letterbook.entity.MapSocialUserInfoE;
import com.csxs.letterbook.entity.MarchantCommentE;
import com.csxs.letterbook.entity.MarchantCommentReplyE;
import com.csxs.letterbook.entity.MineInfoE;
import com.csxs.letterbook.entity.NearbyUserDynamicE;
import com.csxs.letterbook.entity.SellerGoodsE;
import com.csxs.letterbook.entity.SingleOrderE;
import com.csxs.letterbook.entity.SmsLoginE;
import com.csxs.letterbook.entity.UserEmotionE;
import com.csxs.letterbook.entity.WxLoginE;
import com.csxs.letterbook.entity.fuli;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by SenYe on 2018/1/2.
 */

public interface LetterApiService {

    @FormUrlEncoded
    @POST(ConstantsApi.LOGIN_GET_SMS_CODE)
    Observable<Result<String>> sendSmsCode(@Field("nationCode") int nationCode, @Field("phoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST(ConstantsApi.LOGIN_BY_SMS_CODE)
    Observable<Result<SmsLoginE>> loginBySmsCode(@Field("nationCode") String nationCode, @Field("phoneNumber") String phoneNumber, @Field("DXCode") String code);

    @FormUrlEncoded
    @POST(ConstantsApi.REGISTER_WECHAT_LOGIN)
    Observable<Result<WxLoginE>> wxlogin(@Field("openId") String unionid, @Field("openId1") String openId, @Field("nickname") String nickname, @Field("headimgurl") String headimgurl, @Field("sex") int sex);

    @FormUrlEncoded
    @POST(ConstantsApi.REGISTER_BIND_PHONE)
    Observable<Result<WxLoginE>> bindPhone(@Field("nationCode") String nationCode, @Field("phoneNumber") String phoneNumber, @Field("DXCode") String code);

    @GET
    Observable<String> getWxToken(@Url String url);

    @GET
    Observable<String> getWxUserInfo(@Url String url);

    @POST(ConstantsApi.REGISTER_SET_USER_INFO)
    Observable<Result<String>> submitUserBaiscInfo(@Body RequestBody body);


    /**
     * 查询周边商户信息
     *
     * @param body
     * @return
     */
    @POST(ConstantsApi.MAP_SELLER_MARCHANT_QUERYMARCHANTLIST)
    Observable<Result<List<MapSellerStoreE>>> queryMarchantList(@Body RequestBody body);

    /**
     * 查询商家详情
     *
     * @param id
     * @param latitude
     * @param longitude
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.MAP_SELLER_MARCHANT_QUERYMARCHANTINFOLIST)
    Observable<Result<MapSellerDetailsInfoE>> queryMarchantInfoList(@Field("id") int id, @Field("latitude") double latitude, @Field("longitude") double longitude);


    /**
     * 用户关注添加接口
     *
     * @param marchantId
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.SELLER_MARCHANT_ADDMARCHANTCONCERNS)
    Observable<Result<String>> addMarchantConcerns(@Field("marchantId") int marchantId);

    /**
     * 用户关注取消接口
     *
     * @param marchantId
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.SELLER_MARCHANT_DELMARCHANTCONCERNS)
    Observable<Result<String>> delMarchantConcerns(@Field("marchantId") int marchantId);

    /**
     * 商家主页动态
     *
     * @param id
     * @param latitude
     * @param longitude
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.SELLER_HOME_MARCHANT_QUERYMARCHANTCOMMENT)
    Observable<Result<List<MarchantCommentE>>> queryMarchantComment(@Field("id") int id, @Field("latitude") double latitude, @Field("longitude") double longitude, @Field("pageCurr") int pageCurr, @Field("pageSize") int pageSize);

    /**
     * 商家主页动态评论详情
     *
     * @param commentId
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.SELLER_HOME_MARCHANT_QUERYMARCHANTCOMMENTREPLY)
    Observable<Result<MarchantCommentReplyE>> queryMarchantCommentReply(@Field("commentId") int commentId, @Field("latitude") double latitude, @Field("longitude") double longitude);


    /**
     * 回复 评论的评论
     *
     * @param commentId
     * @param content
     * @param isMarchant
     * @param parentId
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.SELLER_HOME_MARCHANT_ADDMARCHANTCOMMENTREPLY)
    Observable<Result<String>> queryMarchantCommentReply(@Field("commentId") int commentId, @Field("content") String content, @Field("isMarchant") int isMarchant, @Field("parentId") int parentId);


    /**
     * 商家界面发布动态
     *
     * @param body
     * @return
     */
    @POST(ConstantsApi.SELLER_HOME_MARCHANT_ADDMARCHANTCOMMENT)
    Observable<Result<String>> addMarchantComment(@Body RequestBody body);


    /**
     * 查询商家商品
     *
     * @param marchantId
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.SELLER_HOME_MARCHANT_QUERYCOMMODITY)
    Observable<Result<List<SellerGoodsE>>> queryCommodity(@Field("marchantId") int marchantId);


    /**
     * 查询周边社交用户信息接口
     *
     * @param distance
     * @param longitude
     * @param pageCurr
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.MAP_SOCIAL_QUERYSOCIALLIST)
    Observable<Result<List<MapSocialInfoE>>> querySocialList(@Field("distance") double distance, @Field("latitude") double latitude, @Field("longitude") double longitude, @Field("pageCurr") int pageCurr, @Field("pageSize") int pageSize);


    /**
     * 查询周边单个 社交用户信息接口
     *
     * @param qUserId
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.MAP_SOCIAL_QUERYUSERINFO)
    Observable<Result<MapSocialUserInfoE>> queryUserInfo(@Field("qUserId") int qUserId);


    /**
     * 查询我自己的详细信息
     *
     * @return
     */
    @POST(ConstantsApi.REGISTER_QUERYUSINFO)
    Observable<Result<MineInfoE>> queryMineInfo();

    /**
     * 更新我自己的信息
     *
     * @return
     */
    @POST(ConstantsApi.REGISTER_UPUSERINFO)
    Observable<Result<String>> upUserInfo(@Body RequestBody body);


    /**
     * 更新我自己的信息
     *
     * @param flag
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.REGISTER_UPUSERINFO)
    Observable<Result<String>> upUserInfo(@Field("flag") String flag);


    /**
     * 查询 感情列表
     *
     * @return
     */
    @POST(ConstantsApi.REGISTER_QUERYEMOTION)
    Observable<Result<List<UserEmotionE>>> queryEmotion();


    /**
     * 查询 标签
     *
     * @return
     */
    @POST(ConstantsApi.REGISTER_QUERYLABEL)
    Observable<Result<List<LabelE>>> queryLabel();


    /**
     * 查询 附近人的动态
     *
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.SOCIAL_QUERYNEARBYCOMM)
    Observable<Result<List<NearbyUserDynamicE>>> queryNearbyComm(@FieldMap Map<String, Object> map);

    /**
     * 通用 动态点赞 接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.DYNAMIC_THUMBS)
    Observable<Result<String>> thumbs(@Field("commentId") int commentId);


    /**
     * 动态 关注与取消关注
     *
     * @return
     */
    @FormUrlEncoded
    @POST(ConstantsApi.DYNAMIC_ATTENTION)
    Observable<Result<String>> addDelConcerns(@Field("userIsMar") int userIsMar, @Field("conUserIsMar") int conUserIsMar, @Field("userId") int userId, @Field("conUserId") int conUserId);



    /**
     * 查询单个商铺提交的商品信息
     *
     * @param body
     * @return
     */
    @POST(ConstantsApi.COMMODITY_ONEKEYABOUTORDER)
    Observable<Result<SingleOrderE>> onekeyAboutOrder(@Body RequestBody body);
}
