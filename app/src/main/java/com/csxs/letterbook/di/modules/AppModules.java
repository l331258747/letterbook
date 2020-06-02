package com.csxs.letterbook.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.MmkvUtlis;
import com.csxs.core.utils.permission.PermissionUtils;
import com.csxs.letterbook.LetterApp;
import com.google.gson.Gson;

import com.tencent.mmkv.MMKV;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = HttpModule.class)
public class AppModules {
    @Provides
    @Singleton
    Context provideContext(LetterApp application) {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(LetterApp application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

//    @Provides
//    @Singleton
//    SPUtils provideSPUtils(SharedPreferences sharedPreferences){
//        return SPUtils.getInstance();
//    }

    /**
     * 本地数据存储mmkv
     * @param application
     * @return
     */
    @Provides
    @Singleton
    MMKV provideMMKV(LetterApp application){
        return MmkvUtlis.getInstance().getMmkv();
    }


    @Provides
    @Singleton
    MmkvUtlis provideMmkvUtlis(){
        return MmkvUtlis.getInstance();
    }
    /**
     * gson解析
     * @return
     */
    @Provides
    @Singleton
    Gson provideGson(){
        return new Gson();
    }

    /**
     * 本地数据库Room
     * @param application
     * @return
     */
//    @Provides
//    @Singleton
//    UsersDataBase provideDataBase(LetterApp application){
//        return UsersDataBase.getInstance(application);
//    }


    /**
     * 数据库Dao
     * @param dataBase
     * @return
     */
//    @Provides
//    @Singleton
//    LocalUserDataSource provideLocalData(UsersDataBase dataBase){
//        return new LocalUserDataSource(dataBase.userDao());
//    }

    /**
     * Glide 图片加载库
     * @return
     */
    @Provides
    @Singleton
    ImageLoaderV4 provideImageLoaderV4(){
        return ImageLoaderV4.getInstance();
    }

    /**
     * AndPermission权限裤
     * @return
     */
    @Provides
    @Singleton
    PermissionUtils providePermissionUtils(){
        return  new PermissionUtils();
    }

}
