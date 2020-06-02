package com.csxs.letterbook.event;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/21
 * description:
 */
public class ModifyUserInfoEvent {
    private String message;
    private int type;
    private List<String> list;

    public ModifyUserInfoEvent(int type, String message) {
        this.message = message;
        this.type=type;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
