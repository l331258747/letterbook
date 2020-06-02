package com.csxs.letterbook.social.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.MapSocialInfoE;
import com.csxs.letterbook.entity.MapSocialUserInfoE;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/5/23
 * description:
 */
public interface PersonalHomeContract {

    interface IPersonalHomeView extends IBaseView {

        void singleUserInfoSuccess(MapSocialUserInfoE mapSocialUserInfoE);
        void singleUserInfoFailure(int code, String msg);
    }

    interface IPersonalHomePresenter {


        void querySingleUserInfo(int userId);
    }

    interface IPersonalHomeModel extends IModel {

        Observable<Result<MapSocialUserInfoE>> querySingleUserInfo(int userId);
    }





}
