package com.csxs.letterbook.social.mvp.contract;

import com.csxs.core.base.IBaseView;
import com.csxs.core.base.IModel;
import com.csxs.core.net.result.Result;
import com.csxs.letterbook.entity.PersonalDynamicE;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author: yeliu
 * created on 2020/6/2
 * description:
 */
public interface PersonalDynamicContract {

    interface IPersonalDynamicView extends IBaseView {

        void getDynamicListSuccess(List<PersonalDynamicE> mapSocialUserInfoE);
    }

    interface IPersonalDynamicPresenter {

        void getDynamicList();
    }

    interface IPersonalDynamicModel extends IModel {

        Observable<Result<List<PersonalDynamicE>>> getDynamicList();
    }
}
