package com.csxs.core.utils;

import com.csxs.core.BaseCoreApp;
import com.tencent.mmkv.MMKV;

import javax.inject.Inject;

/**
 * author: yeliu
 * created on: 2020/1/8 15:58
 * description:mmkv 存储
 */
public class MmkvUtlis {

    private static class MmkvUtlisHolder {
        private static final MmkvUtlis INSTANCE = new MmkvUtlis();
    }

    public MmkvUtlis(){
        initMmkv(BaseCoreApp.appConext);
    }

    public static MmkvUtlis getInstance() {
        return MmkvUtlisHolder.INSTANCE;
    }

    public void initMmkv(BaseCoreApp app){
        MMKV.initialize(app);
    }

    public MMKV getMmkv(){
        return MMKV.defaultMMKV();
    }




    /**
     * 登录保存用户信息
     * @param token
     * @param phoneNumber
     * @param headimgurl
     * @param nickname
     * @param sex
     * @param userId
     * @param loginway 1 2
     */
    public void putUserInfo(int loginway,String token, String phoneNumber, String headimgurl, String nickname, int sex, int userId){
        getMmkv().putInt("loginway",loginway);
        getMmkv().putString("token",token);
        getMmkv().putString("phoneNumber",phoneNumber);
        getMmkv().putString("headimgurl",headimgurl);
        getMmkv().putString("nickname",nickname);
        getMmkv().putInt("nickname",sex);
        getMmkv().putInt("userId",userId);
    }

   public void putLoationInfo(String latitude,String longitude){
       getMmkv().putString("latitude",latitude);
       getMmkv().putString("longitude",longitude);
   }
}
