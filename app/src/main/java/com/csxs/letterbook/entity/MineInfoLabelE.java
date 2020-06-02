package com.csxs.letterbook.entity;

import java.io.Serializable;

/**
 * @author: yeliu
 * created on 2020/5/19
 * description:
 */
public class MineInfoLabelE {


    /**
     * labelId : 1
     * id : 11
     * labelName : 养宠物
     * userId : 29920
     */

    private int labelId;
    private int id;
    private String labelName;
    private int userId;

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
