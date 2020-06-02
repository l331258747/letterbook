package com.csxs.core.net;

/**
 * Created by SenYe on 2018/1/2.
 */

public interface BaseApiService {

    //
//    @GET("api/data/福利/10/1")
//    Observable<Result<List<fuli>>> getInfoData();
//
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
