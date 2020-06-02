package com.csxs.letterbook.api;

import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.fuli;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author: yeliu
 * created on 2020/4/13
 * description:
 */
public interface TestApi {

    @GET("api/data/福利/10/1")
    Observable<Result<List<fuli>>> getInfoData();

    @GET("api/data/福利/10/1")
    Observable<String> getInfoData1();
//
//
//    @FormUrlEncoded
//    @POST("movie/video")
//    Observable<Result<Kuaidi>> query(@Field("key") String key, @Field("dtype") String dType, @Field("q") String name);
//   //http://api.apiopen.top/todayVideo
//
//    @POST("todayVideo")
//    Observable<Result<List<Kuaidi>>> getMove2();

//    @POST("todayVideo")
//    Observable<String> getStringMove2();
    //获取当前日历今天的信息
    //@Headers({"app_id:"+GlobalConstants.APP_ID,"app_secret:"+GlobalConstants.APP_SECRET})
//    @POST
//    Observable<String> getToDayCalendarInfo(@Url String url);

    //历史上的今天
    //@Headers({"app_id:"+GlobalConstants.APP_ID,"app_secret:"+GlobalConstants.APP_SECRET})
//    @POST
//    Observable<String> getHistoryTodayInfo(@Url String url);
//
//    @Headers({"Content-Type:application/json","token:cc0341b40ec360309f9f1caad3745a30"})
//    @POST
//    Observable<String> getOrder(@Url String url, @Body RequestBody requestBody);

//    @POST("user/register")
//    Observable<Result<String>> getUserInfo(@Body RequestBody requestBody);
//    @POST("user/login")
//    Observable<Result<String>> LogUserInfo(@Body RequestBody requestBody);
//
//    //创建房间 上传房间号
//    @FormUrlEncoded
//    @POST("nim/createChatRoomCommunication")
//    Observable<Result<String>> createChatRoom(@Field("room_id") String romm_id, @Field("user_id") String user_id, @Field("room_name") String room_name);
//    //删除房间 上传房间号
//    @FormUrlEncoded
//    @POST("nim/shutDownChatRoomCommunication")
//    Observable<Result<String>> deleteChatRoom(@Field("room_id") String romm_id, @Field("user_id") String user_id, @Field("room_name") String room_name);
//
//
//    @GET("nimAV/queryWheatList")
//    Observable<Result<WheatEntity>> queryWheatList(@Query("roomId") String roomId, @Query("state") String state);
//
//    //查询其他用户信息
//    @GET("userInfo/getOtherInfo")
//    Observable<Result<OtherInfo>> getOtherInfo(@Query("friendId") String friendId);
//
//
//    //关注或取消
//    @FormUrlEncoded
//    @POST("focus/set")
//    Observable<Result<String>> follow(@Field("focusId") String focusId);
//    //用户申请上麦
//    @FormUrlEncoded
//    @POST("nimAV/applyWheat")//state 1申请上麦  2允许上麦 3踢出麦序 4踢出房间 5禁言 6解除禁言  离开麦序或者离开房间
//    Observable<Result<String>> applyWheat(@Field("roomId") String roomId, @Field("accid") String accid, @Field("state") String state);
//    //用户加入房间
//    @FormUrlEncoded
//    @POST("nimAV/userJoin")
//    Observable<Result<Map<String,WheatEntity.Wheat>>> userJoin(@Field("roomId") String roomId, @Field("userId") String userId);
//    @GET("nimAV/userExit")
//    Observable<Result<String>> userExit(@Query("roomId") String roomId, @Query("userId") String userId);
//
//    //房主操作用户麦状态，0为禁言，2为同意上麦
//    @FormUrlEncoded
//    @POST("nimAV/ownerAgreeUserApply")//
//    Observable<Result<String>> ownerAgreeUserApply(@Field("roomId") String roomId, @Field("userId") String userId, @Field("state") String state, @Field("ownerId") String ownerId);
}
