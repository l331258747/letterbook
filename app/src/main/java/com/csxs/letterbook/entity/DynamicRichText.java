package com.csxs.letterbook.entity;

import com.csxs.viewlib.dynamictext.enumtext.StatusType;
import com.csxs.viewlib.dynamictext.model.ExpandableStatusFix;

/**
 * @author: yeliu
 * created on 2020/5/11
 * description:
 */
public class DynamicRichText implements ExpandableStatusFix {

    private StatusType status;
    private String content;

    public DynamicRichText(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setStatus(StatusType status) {
        this.status = status;
    }

    @Override
    public StatusType getStatus() {
        return status;
    }
}
