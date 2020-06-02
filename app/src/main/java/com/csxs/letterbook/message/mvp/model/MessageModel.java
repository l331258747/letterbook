package com.csxs.letterbook.message.mvp.model;

import com.csxs.core.base.BaseModel;
import com.csxs.letterbook.api.LetterApiService;
import com.csxs.letterbook.message.mvp.contract.MessageContract;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public class MessageModel extends BaseModel implements MessageContract.IMessageModel {

    @Inject
    LetterApiService apiService;

    @Inject
    public MessageModel() {
    }
}
