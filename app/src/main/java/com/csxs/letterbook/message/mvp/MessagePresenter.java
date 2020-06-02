package com.csxs.letterbook.message.mvp;

import com.csxs.core.base.BasePresenter;
import com.csxs.letterbook.message.mvp.contract.MessageContract;
import com.csxs.letterbook.message.mvp.model.MessageModel;

import javax.inject.Inject;

/**
 * @author: yeliu
 * created on 2020/4/14
 * description:
 */
public class MessagePresenter extends BasePresenter<MessageModel, MessageContract.IMessageView> {

    @Inject
    public MessagePresenter() {
    }
}
