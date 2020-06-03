package com.csxs.letterbook.social.mvp.model;

import android.util.ArrayMap;

import com.csxs.core.base.BaseModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.entity.PersonalDynamicE;
import com.csxs.letterbook.social.mvp.contract.PersonalDynamicContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/6/2
 * description:
 */
public class PersonDynamicModel extends BaseModel implements PersonalDynamicContract.IPersonalDynamicModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public PersonDynamicModel() {
    }

    @Override
    public Observable<Result<List<PersonalDynamicE>>> getDynamicList() {
        return null;
    }
}
