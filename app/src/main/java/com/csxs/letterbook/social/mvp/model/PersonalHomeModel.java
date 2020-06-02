package com.csxs.letterbook.social.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.MapSocialUserInfoE;
import com.csxs.letterbook.social.mvp.contract.PersonalHomeContract;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/5/23
 * description:
 */
public class PersonalHomeModel extends BaseModel implements PersonalHomeContract.IPersonalHomeModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public PersonalHomeModel() {
    }

    @Override
    public Observable<Result<MapSocialUserInfoE>> querySingleUserInfo(int userId) {
        return apiService.queryUserInfo(userId);
    }
}
