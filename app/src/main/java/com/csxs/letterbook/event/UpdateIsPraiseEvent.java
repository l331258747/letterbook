package com.csxs.letterbook.event;

/**
 * @author: yeliu
 * created on 2020/5/26
 * description:
 */
public class UpdateIsPraiseEvent {

    private int type;
    public UpdateIsPraiseEvent(int type) {
        this.type=type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
